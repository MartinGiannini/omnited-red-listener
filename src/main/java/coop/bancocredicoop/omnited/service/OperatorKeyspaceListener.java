package coop.bancocredicoop.omnited.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.entity.OperadorCompleto;
import coop.bancocredicoop.omnited.message.MessageToRabbit;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OperatorKeyspaceListener implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MessageToRabbit messageToRabbit;
    UUID uuid = UUID.randomUUID();

    public OperatorKeyspaceListener(
            RedisTemplate<String, Object> redisTemplate,
            MessageToRabbit messageToRabbit
    ) {
        this.redisTemplate = redisTemplate;
        this.messageToRabbit = messageToRabbit;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        
        String channel = new String(message.getChannel());
        String event = new String(message.getBody());

        System.out.println("Evento recibido: " + event + " en el canal: " + channel);

        int index = channel.indexOf("operator:");
        if (index == -1) {
            return;
        }
        String operatorKey = channel.substring(index);

        // Recupera el hash completo del operador
        Map<Object, Object> operatorData = redisTemplate.opsForHash().entries(operatorKey);
        System.out.println("Datos del operador actualizados: " + operatorData);

        // Mapear el hash a tu entidad OperadorCompleto
        OperadorCompleto operador = mapToOperadorCompleto(operatorData);
        System.out.println("Operador mapeado: " + operador);
        
        try {
            String operadorJson = objectMapper.writeValueAsString(operador);
            messageToRabbit.processMessageUnicastMultiple(uuid.toString(), "operadorRealTimeLIST", operadorJson, operador.getIdSector());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(OperatorKeyspaceListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private OperadorCompleto mapToOperadorCompleto(Map<Object, Object> data) {
        OperadorCompleto operador = new OperadorCompleto();

        operador.setIdOperador(data.get("OperadorId").toString());
        operador.setOperadorNombre((String) data.get("OperadorNombre"));
        operador.setOperadorApellido((String) data.get("OperadorApellido"));
        operador.setOperadorCorreo((String) data.get("OperadorCorreo"));
        operador.setOperadorUsuario((String) data.get("OperadorUsuario"));
        
        // Asignar colecciones: se intenta primero si vienen como Collection, sino se parsea un string separado por comas
        Object habilidades = data.get("OperadorHabilidad");
        if (habilidades instanceof Collection<?>) {
            operador.setOperadorHabilidad(new HashSet<>((Collection<String>) habilidades));
        } else if (habilidades != null) {
            operador.setOperadorHabilidad(new HashSet<>(Arrays.asList(habilidades.toString().split(","))));
        }

        Object estados = data.get("OperadorEstado");
        if (estados instanceof Collection<?>) {
            operador.setOperadorEstado(new HashSet<>((Collection<String>) estados));
        } else if (estados != null) {
            operador.setOperadorEstado(new HashSet<>(Arrays.asList(estados.toString().split(","))));
        }

        Object permisos = data.get("OperadorPermisos");
        if (permisos instanceof Collection<?>) {
            operador.setOperadorPermisoOperacion(new HashSet<>((Collection<String>) permisos));
        } else if (permisos != null) {
            operador.setOperadorPermisoOperacion(new HashSet<>(Arrays.asList(permisos.toString().split(","))));
        }

        // Asignar datos de extensión: se mapea "ExtensionEstado" a peerStatus 
        operador.setExtensionPeerStatus((String) data.get("ExtensionEstado"));
        // Asignar el valor numérico actualizado: se convierte a int
        if (data.containsKey("ExtensionUltimoTiempo")) {
            operador.setExtensionLastTime(Long.parseLong(data.get("ExtensionUltimoTiempo").toString()));
        }

        // Los campos que no se reciben, se dejan nulos o con valor por defecto
        operador.setExtensionPeer((String) data.get("ExtensionPeer"));
        operador.setExtensionCause(null);
        operador.setExtensionState((String) data.get("ExtensionEstado"));
        operador.setExtensionComunicacion((String) data.get("ExtensionComunicacion"));
        operador.setIdSector(Integer.parseInt(data.get("OperadorIdSector").toString()));
        operador.setIdEstado(Integer.parseInt(data.get("OperadorIdEstado").toString()));

        return operador;
    }
}
