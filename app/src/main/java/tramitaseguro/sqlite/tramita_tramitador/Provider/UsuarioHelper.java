package tramitaseguro.sqlite.tramita_tramitador.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "registro.db";

    public UsuarioHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + UsuarioContract.UsuariosEntry.TABLE_NAME + " ("
                +  UsuarioContract.UsuariosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                +  UsuarioContract.UsuariosEntry.name + " TEXT NOT NULL,"
                +  UsuarioContract.UsuariosEntry.email + " TEXT NOT NULL,"
                +  UsuarioContract.UsuariosEntry.password + " TEXT NOT NULL,"

                +  UsuarioContract.UsuariosEntry.GUARDADO + " TEXT NOT NULL,"


                + "UNIQUE (" +UsuarioContract.UsuariosEntry._ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
