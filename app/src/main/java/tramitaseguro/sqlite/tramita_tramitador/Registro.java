package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import tramitaseguro.sqlite.tramita_tramitador.Provider.UsuarioContract;
import tramitaseguro.sqlite.tramita_tramitador.Provider.UsuarioHelper;
import tramitaseguro.sqlite.tramita_tramitador.objetos.Usuarios;


public class Registro extends AppCompatActivity  {
/*
    private DatabaseHelper db;

    //View objects
    private Button buttonSave;
    private EditText editTextName;
    private ListView listViewNames;

    //List to store all the names
    private List<ModelName> names;

    //1 means data is synced and 0 means data is not synced
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";

    //Broadcast receiver to know the sync status
    private BroadcastReceiver broadcastReceiver;

    //adapterobject for list view
    private NameAdapter nameAdapter;


*/
 EditText editname, editpassword, ditphone;
    EditText editmail;
    String email, name, password;

   // ArrayAdapter<ModelName> nameArrayAdapter= new ArrayAdapter<ModelName>( );
   UsuarioHelper dbUser;
    private static final String PREFS_KEY = "PREFS";
    private Button registrar, btnlogin, provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);






        editname= (EditText) findViewById(R.id.editname);

        editmail=(EditText) findViewById(R.id.editemail);
        editpassword= (EditText) findViewById(R.id.editpassword);
        registrar= (Button) findViewById(R.id.buttonregistrarre);
/*
       // TareaInternet tareaInternet=new TareaInternet(getApplicationContext());
        //tareaInternet.execute();

        names = new ArrayList<>();
      // initializeViews();
        initializeObjects();

        //loadNames();
        //cosumirSerVicioPost();
        //saveName1();
        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        //the broadcast receiver to update sync status
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),"in the receiver",Toast.LENGTH_LONG).show();
                //loading the names again
                //loadNames();
            }
        };
        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
        unregisterReceiver( broadcastReceiver );
*/
name=editmail.getText().toString();
email= editmail.getText().toString();
        password= editpassword.getText().toString();


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//guardarRegistro( name, email, password );
              //  TareaInternet tareaInternet=new TareaInternet(getApplicationContext());
               // tareaInternet.execute();

             //   if(isConnectedToInternet())
               // {
                    cosumirSerVicioPost();
                //}
              //  else
                //{
                 // onClickAddDetails(v);
                   // saveContentProvider();

                    //Toast.makeText(getApplicationContext(),"no hay internet", Toast.LENGTH_LONG).show();
              // }



                //Intent usuariomail= new Intent(getApplication(), Bievenido.class);

                //startActivity(usuariomail);

            }
        });




    }

    /*

    private void saveContentProvider(){
        SQLiteDatabase db = dbUser.getWritableDatabase();
        ContentValues values = new ContentValues();
       Usuarios user= new Usuarios();
        user.setName(editname.getText()+"");
        user.setEmail(editmail.getText()+"");
        user.setPassword(editpassword.getText()+"");

        values.put( UsuarioContract.UsuariosEntry.name,user.getName());
        values.put(UsuarioContract.UsuariosEntry.email,user.getEmail());
        values.put(UsuarioContract.UsuariosEntry.password,user.getPassword());

        values.put(UsuarioContract.UsuariosEntry.GUARDADO,"false");
        long newRowId = db.insert(UsuarioContract.UsuariosEntry.TABLE_NAME, null, values);
        Log.i("rowId", newRowId+"");
      //  Intent itemintent = new Intent(segundapantalla.this, cuartapantalla.class);
        //segundapantalla.this.startActivity(itemintent);
        guardarusuario(getApplicationContext(),"idusuario", ""+newRowId);
        guardarusuario(getApplicationContext(),"nombre", String.valueOf(user.getName()));
        guardarusuario(getApplicationContext(),"avatar", user.getEmail());
        guardarusuario(getApplicationContext(),"avatar", user.getPassword());

    }*/
/*

    public static void guardarusuario(Context context, String keyPref, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(keyPref, valor);
        editor.commit();
    }
*/


/*
    public void initializeObjects() {
        db = new DatabaseHelper(this);
    }
*/
/*

    public void guardarRegistro(String name, String email, String password){

        final ProgressDialog progressDialog= new ProgressDialog( this );
                progressDialog.setMessage("Guardando Registro");
                progressDialog.show();
                progressDialog.setCancelable( false );
                saveNameToLocalStorage( name, email,password, NAME_NOT_SYNCED_WITH_SERVER);
                progressDialog.dismiss();



    }*/


/*
    private void loadNames() {
        names.clear();
        Cursor cursor = db.getNames();
        if (cursor.moveToFirst()) {
            do {
                ModelName name = new ModelName(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                );
                names.add(name);
            } while (cursor.moveToNext());
        }

        nameAdapter = new NameAdapter(this, R.layout.name, names);
        listViewNames.setAdapter(nameAdapter);
    }*/
/*
    private void refreshList() {
        nameAdapter.notifyDataSetChanged();
    }*/

    //saving the name to local storage
/*
    private void saveNameToLocalStorage(String name, String email, String password, int status) {
       // editTextName.setText("");
        db.addName(name, email, password, status);
        ModelName n = new ModelName(name,email, password, status);
        names.add(n);
        //refreshList();
    }*/


/*
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }*/

/*

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }*/

/*
    public void onClickAddDetails(View view) {
        ContentValues values = new ContentValues();
        values.put(UserProvider.name, ((EditText) findViewById(R.id.editname)).getText().toString());
        values.put(UserProvider.email, ((EditText) findViewById(R.id.editemail)).getText().toString());
        values.put(UserProvider.password, ((EditText) findViewById(R.id.editpassword)).getText().toString());
        getContentResolver().insert(UserProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
        Cursor cursor = getContentResolver().query(Uri.parse("content://tramitaseguro.sqlite.tramita_tramitador.UserProvider/users"), null, null, null, null);
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name"))
                        + "-"+ cursor.getString(cursor.getColumnIndex("email"))+ "-"+ cursor.getString(cursor.getColumnIndex("password")));
                cursor.moveToNext();
            }
            // resultView.setText(strBuild);
        }
        else {
            // resultView.setText("No Records Found");
        }



        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getBaseContext(), Resultado.class);
        startActivity(intent);
    }*/




    public void cosumirSerVicioPost(){


        String name= editname.getText().toString();
        String mail= editmail.getText().toString();
        String password= editpassword.getText().toString();






        ServicioRegistro servicioregistro= new ServicioRegistro(this, "https://jsonappget.herokuapp.com/register/registro", name, mail, password);
        servicioregistro.execute();



    }






}
