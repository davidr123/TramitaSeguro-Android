package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class Requisitos {
    private String id;
    private  String requisitotramites;

    public Requisitos(String id, String requisitotramites) {
        this.id = id;
        this.requisitotramites = requisitotramites;

    }


    public Requisitos() {


    }
    public String getId() {
        return id;
    }

    public String getRequisitotramites() {
        return requisitotramites;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRequisitotramites(String requisitotramites) {
        this.requisitotramites = requisitotramites;
    }
}
