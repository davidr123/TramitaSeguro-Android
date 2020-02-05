package tramitaseguro.sqlite.tramita_tramitador.Provider;

import android.provider.BaseColumns;

public class ActividadesContract {

    public static abstract class ActividadesEntry implements BaseColumns {
        public static final String TABLE_NAME ="user";

        public static final String _id = "_id";
        public static final String name = "name";
        public static final String mail = "mail";
        public static final String password = "password";



    }
}
