package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class Tramites {
    private String id;
    private  String descriocion;
    private  String fecha;
    private  String consideración;
    private  String notageneral;
    private  boolean estado;

    public Tramites(String id, String descriocion, String fecha, boolean estado, String consideración,String notageneral ) {
        this.id = id;
        this.descriocion = descriocion;
        this.fecha = fecha;
        this.estado = estado;
        this.consideración = consideración;

        this.notageneral = notageneral;
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

    public void setConsideración(String consideración) {
        this.consideración = consideración;
    }

    public void setNotageneral(String notageneral) {
        this.notageneral = notageneral;
    }

    public String getConsideración() {
        return consideración;
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
