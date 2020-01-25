package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextemail, editTextpassword, name, phone;
    private Button registrar, btnlogin;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextemail= (EditText)findViewById(R.id.editTextmail);
        editTextpassword= (EditText)findViewById(R.id.editText2password);
        btnlogin= (Button) findViewById(R.id.buttonloguin);


        registrar= (Button) findViewById(R.id.buttonregistrar);




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
cosumirSerVicioPost();
                int pos= email.indexOf("@");
                String usuario=email.substring(0, pos);
                Toast.makeText(MainActivity.this, "BIENVENIDO  : " + editTextemail.getText(), Toast.LENGTH_LONG).show();
                Intent usuariomail= new Intent(getApplication(), Bienvenido.class);
                usuariomail.putExtra(UsuarioLogin.usuariologueado, usuario);
                startActivity(usuariomail);

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
        String password= editTextpassword.getText().toString();






        Servicio servicio= new Servicio(this, "https://jsonappget.herokuapp.com/register/login", email, password);
        servicio.execute();





    }






}
