package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tramitaseguro.sqlite.tramita_tramitador.tareas.TareaInternet;


public class Registro extends AppCompatActivity {

 EditText editname, editpassword, ditphone;
    String email;

 EditText editmail;
    private Button registrar, btnlogin, provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

     editname= (EditText) findViewById(R.id.editname);

     editmail=(EditText) findViewById(R.id.editemail);
          editpassword= (EditText) findViewById(R.id.editpassword);
        registrar= (Button) findViewById(R.id.buttonregistrarre);




        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TareaInternet tareaInternet=new TareaInternet(getApplicationContext());
                tareaInternet.execute();

                if(isConnectedToInternet())
                {
                    cosumirSerVicioPost();
                }
                else
                {
                 // onClickAddDetails(v);

                    Toast.makeText(getApplicationContext(),"no hay internet", Toast.LENGTH_LONG).show();
                }



                //Intent usuariomail= new Intent(getApplication(), Bievenido.class);

                //startActivity(usuariomail);

            }
        });




    }


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
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

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
    }

    public void cosumirSerVicioPost(){


        String name= editname.getText().toString();
        String mail= editmail.getText().toString();
        String password= editpassword.getText().toString();




        ServicioRegistro servicioregistro= new ServicioRegistro(this, "https://jsonappget.herokuapp.com/register/registro", name, mail, password);
        servicioregistro.execute();



    }






}
