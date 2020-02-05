package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformacionEstudioNotaPicay extends AppCompatActivity {

    TextView titulo, telf, correo, direccion;
private Button req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_informacion_estudio_nota_picay );

req=(Button) findViewById( R.id.buttonrequisitopermisossalida ) ;

        titulo= (TextView) findViewById( R.id.textitulopincay );
        direccion= (TextView) findViewById( R.id.textdireccpincay );
        correo= (TextView) findViewById( R.id.textcorreopincay );
        telf= (TextView) findViewById( R.id.texttelfopincay );



        titulo.setText( "Estudio Pincay - Pincay" );
        direccion.setText( "Edificio Torre de la Merced piso 17" );
        correo.setText( "estudiopincay@gmail.com ");
        telf.setText( "098976789" );

        req.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( InformacionEstudioNotaPicay.this, RequisitoPermisosdeSalidaNotarial.class );
                startActivity( intent );
            }
        } );
    }


}
