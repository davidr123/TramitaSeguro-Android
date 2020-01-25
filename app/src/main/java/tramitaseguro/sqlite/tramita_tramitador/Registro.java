package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Registro extends AppCompatActivity {

 EditText editname, editpassword, ditphone;

 EditText editmail;
    private Button registrar, btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

     editname= (EditText) findViewById(R.id.editname);

     editmail=(EditText) findViewById(R.id.editemail);
          editpassword= (EditText) findViewById(R.id.editpassword);
        registrar= (Button) findViewById(R.id.buttonregistrar);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
cosumirSerVicioPost();

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
