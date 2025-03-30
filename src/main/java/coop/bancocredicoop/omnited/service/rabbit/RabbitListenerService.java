package coop.bancocredicoop.omnited.service.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.message.MessageToRabbit;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();

    /**
     * Servicios que se registran en la carga de la aplicación.
     *
     * @param messageToRabbit
     */
    public RabbitListenerService(
            MessageToRabbit messageToRabbit
    ) {
        // Registrar handlers para cada tipo de mensaje
        //handlers.put("grupoEstadosWS", new GrupoEstadosModificaHandler(grupoEstadoService, messageToRabbit));
    }

    /**
     * Colas registradas en Rabbit. Esta aplicación solo escuchará mensajes
     * provinientes de esas colas.
     *
     * @param message
     */
    //@RabbitListener(queues = {
    //    "#{@environment.getProperty('spring.rabbitmq.colaWSFutura')}"
    //})

    /**
     * Método que buscará manejar los mensajes de acuerdo al TYPE.
     *
     */
    public void receiveMessage(MensajeJSON message) {

        try {
            String idMensaje = message.getIdMensaje();
            String mensajeType = message.getMensajeType();
            String mensajeJson = message.getMensajeJson();
            
            // Identificar y procesar el mensaje según su tipo
            RabbitMessageHandler handler = handlers.get(mensajeType);

            if (handler != null) {
                handler.handle(idMensaje, mensajeJson);
            } else {
                System.err.println("No handler found for type: " + mensajeType);
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }
}
