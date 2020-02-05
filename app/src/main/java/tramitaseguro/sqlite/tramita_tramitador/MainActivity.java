package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static tramitaseguro.sqlite.tramita_tramitador.UserProvider.password;

public class MainActivity extends AppCompatActivity {

    EditText editTextemail, editTextpassword, name, phone;
    private Button registrar, btnlogin, huella;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextemail= (EditText)findViewById(R.id.editTextmail);
        editTextpassword= (EditText)findViewById(R.id.editText2password);
        btnlogin= (Button) findViewById(R.id.buttonloguin);


        registrar= (Button) findViewById(R.id.buttonregistrar);
        huella= (Button) findViewById(R.id.buttonhuella);

        huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sensor.class);
                startActivity(intent);
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cosumirSerVicioPost();


              //  int pos= email.indexOf("@");
               // String usuario=email.substring(0, pos);
             //   Toast.makeText(MainActivity.this, "BIENVENIDO  : " + editTextemail.getText(), Toast.LENGTH_LONG).show();
                /*Intent usuariomail= new Intent(getApplication(), Bievenido.class);
                usuariomail.putExtra(Bievenido.usuariologueado, usuario);

                startActivity(usuariomail);*/

            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });


    }



    public void cosumirSerVicioPost(){


        email= editTextemail.getText().toString();
         password= editTextpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar un email:" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Se debe ingresa una contraseña ", Toast.LENGTH_LONG).show();
            return;

        }



        String url="https://jsonappget.herokuapp.com/register/login";


        Servicio servicio= new Servicio(this, url, email, password);
       servicio.execute();





    }






}