package tramitaseguro.sqlite.tramita_tramitador.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActividadesHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "registro.db";

    public  ActividadesHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ActividadesContract.ActividadesEntry.TABLE_NAME + " ("
                +  ActividadesContract.ActividadesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  ActividadesContract.ActividadesEntry.name + " TEXT NOT NULL,"
                +  ActividadesContract.ActividadesEntry.mail + " TEXT NOT NULL,"
                +  ActividadesContract.ActividadesEntry.password + " TEXT NOT NULL" +
                ","
                + "UNIQUE (" +ActividadesContract.ActividadesEntry._ID + "))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
