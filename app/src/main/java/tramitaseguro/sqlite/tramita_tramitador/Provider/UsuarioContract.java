package tramitaseguro.sqlite.tramita_tramitador.Provider;

import android.provider.BaseColumns;

public class UsuarioContract {

    public static abstract class UsuariosEntry implements BaseColumns {
        public static final String TABLE_NAME ="user";

        public static final String _ID = "_ID";
        public static final String name = "name";
        public static final String email = "email";
        public static final String password = "password";

        public static final String GUARDADO = "GUARDADO";


    }

}
