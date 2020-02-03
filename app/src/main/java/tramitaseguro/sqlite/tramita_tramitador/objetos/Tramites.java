package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class Tramites {
    private String id;
    private  String descriocion;
    private  String fecha;
    private  boolean estado;

    public Tramites(String id, String descriocion, String fecha, boolean estado) {
        this.id = id;
        this.descriocion = descriocion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Tramites() {
    }

    public void setDescriocion(String descriocion) {
        this.descriocion = descriocion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }


    public String getDescriocion() {
        return descriocion;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isEstado() {
        return estado;
    }
}
