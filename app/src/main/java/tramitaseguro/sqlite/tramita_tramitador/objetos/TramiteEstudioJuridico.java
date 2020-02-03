package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class TramiteEstudioJuridico {

    private String id;
    private  String nombre;
    private  String direccion;
    private  String correo;
    private  String telefono;
    private  boolean estado;

    public TramiteEstudioJuridico(String id, String nombre, String direccion, String correo, String telefono, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public TramiteEstudioJuridico() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isEstado() {
        return estado;
    }
}
