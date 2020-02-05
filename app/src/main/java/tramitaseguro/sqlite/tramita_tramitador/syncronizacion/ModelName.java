package tramitaseguro.sqlite.tramita_tramitador.syncronizacion;

public class ModelName {

    private String name;
private String email;
    private String password;
    private int status;

    public ModelName(String name, String email, String password, int status) {
        this.name = name;
        this.name = email;
        this.name = password;

        this.status = status;
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

    public int getStatus() {
        return status;
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

    public void setStatus(int status) {
        this.status = status;
    }
}
