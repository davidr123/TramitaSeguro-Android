package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class Usuarios {

    private String id;
    private String name;
    private String email;
    private String password;


    private  boolean guardado;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }


    @Override
    public String toString() {
        return "Usuarios{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", guardado=" + guardado +
                '}';
    }
}
