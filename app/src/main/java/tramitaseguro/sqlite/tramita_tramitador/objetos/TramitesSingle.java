package tramitaseguro.sqlite.tramita_tramitador.objetos;

public class TramitesSingle {
    private static final TramitesSingle ourInstance = new TramitesSingle();
   public  static String id = "";
   public static TramitesSingle getInstance() {
        return ourInstance;
    }

    private TramitesSingle() {

    }
}
