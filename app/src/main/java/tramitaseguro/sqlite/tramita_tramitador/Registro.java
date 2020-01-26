package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registro extends AppCompatActivity {

 EditText editname, editpassword, ditphone;
    String email;

 EditText editmail;
    private Button registrar, btnlogin;

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
cosumirSerVicioPost();

                Intent usuariomail= new Intent(getApplication(), MainActivity.class);

                startActivity(usuariomail);

            }
        });


    }

    public void cosumirSerVicioPost(){


        String name= editname.getText().toString();
        String mail= editmail.getText().toString();
        String password= editpassword.getText().toString();



        ServicioRegistro servicioregistro= new ServicioRegistro(this, "https://jsonappget.herokuapp.com/register/registro", name, mail, password);
        servicioregistro.execute();



    }






}
