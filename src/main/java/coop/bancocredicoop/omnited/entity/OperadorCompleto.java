package coop.bancocredicoop.omnited.entity;

import java.util.Set;

public class OperadorCompleto {
    private String idOperador;
    private String operadorNombre;
    private String operadorApellido;
    private String operadorUsuario;
    private String operadorCorreo;
    private Set<String> operadorHabilidad;
    private Set<String> operadorEstado;
    private Set<String> operadorPermisoOperacion;
    private String extensionPeer;
    private String extensionPeerStatus;
    private long extensionLastTime;
    private String extensionCause;
    private String extensionState;
    private String extensionComunicacion;
    private int idSector;
    private int idEstado;

    public OperadorCompleto() {
    }

    public OperadorCompleto(String idOperador, String operadorNombre, String operadorApellido, String operadorUsuario, String operadorCorreo, Set<String> operadorHabilidad, Set<String> operadorEstado, Set<String> operadorPermisoOperacion, String extensionPeer, String extensionPeerStatus, long extensionLastTime, String extensionCause, String extensionState, String extensionComunicacion, int idSector, int idEstado) {
        this.idOperador = idOperador;
        this.operadorNombre = operadorNombre;
        this.operadorApellido = operadorApellido;
        this.operadorUsuario = operadorUsuario;
        this.operadorCorreo = operadorCorreo;
        this.operadorHabilidad = operadorHabilidad;
        this.operadorEstado = operadorEstado;
        this.operadorPermisoOperacion = operadorPermisoOperacion;
        this.extensionPeer = extensionPeer;
        this.extensionPeerStatus = extensionPeerStatus;
        this.extensionLastTime = extensionLastTime;
        this.extensionCause = extensionCause;
        this.extensionState = extensionState;
        this.extensionComunicacion = extensionComunicacion;
        this.idSector = idSector;
        this.idEstado = idEstado;
    }

    public String getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public String getOperadorNombre() {
        return operadorNombre;
    }

    public void setOperadorNombre(String operadorNombre) {
        this.operadorNombre = operadorNombre;
    }

    public String getOperadorApellido() {
        return operadorApellido;
    }

    public void setOperadorApellido(String operadorApellido) {
        this.operadorApellido = operadorApellido;
    }

    public String getOperadorUsuario() {
        return operadorUsuario;
    }

    public void setOperadorUsuario(String operadorUsuario) {
        this.operadorUsuario = operadorUsuario;
    }

    public String getOperadorCorreo() {
        return operadorCorreo;
    }

    public void setOperadorCorreo(String operadorCorreo) {
        this.operadorCorreo = operadorCorreo;
    }

    public Set<String> getOperadorHabilidad() {
        return operadorHabilidad;
    }

    public void setOperadorHabilidad(Set<String> operadorHabilidad) {
        this.operadorHabilidad = operadorHabilidad;
    }

    public Set<String> getOperadorEstado() {
        return operadorEstado;
    }

    public void setOperadorEstado(Set<String> operadorEstado) {
        this.operadorEstado = operadorEstado;
    }

    public Set<String> getOperadorPermisoOperacion() {
        return operadorPermisoOperacion;
    }

    public void setOperadorPermisoOperacion(Set<String> operadorPermisoOperacion) {
        this.operadorPermisoOperacion = operadorPermisoOperacion;
    }

    public String getExtensionPeer() {
        return extensionPeer;
    }

    public void setExtensionPeer(String extensionPeer) {
        this.extensionPeer = extensionPeer;
    }

    public String getExtensionPeerStatus() {
        return extensionPeerStatus;
    }

    public void setExtensionPeerStatus(String extensionPeerStatus) {
        this.extensionPeerStatus = extensionPeerStatus;
    }

    public long getExtensionLastTime() {
        return extensionLastTime;
    }

    public void setExtensionLastTime(long extensionLastTime) {
        this.extensionLastTime = extensionLastTime;
    }

    public String getExtensionCause() {
        return extensionCause;
    }

    public void setExtensionCause(String extensionCause) {
        this.extensionCause = extensionCause;
    }

    public String getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(String extensionState) {
        this.extensionState = extensionState;
    }

    public String getExtensionComunicacion() {
        return extensionComunicacion;
    }

    public void setExtensionComunicacion(String extensionComunicacion) {
        this.extensionComunicacion = extensionComunicacion;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}