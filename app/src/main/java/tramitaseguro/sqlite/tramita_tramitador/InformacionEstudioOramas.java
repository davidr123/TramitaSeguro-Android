package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InformacionEstudioOramas extends AppCompatActivity {

    TextView titulo, telf, correo, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_informacion_estudio_oramas );

        titulo= (TextView) findViewById( R.id.textituloorama );
        direccion= (TextView) findViewById( R.id.textdireccorama );
        correo= (TextView) findViewById( R.id.textcorreoorama );
        telf= (TextView) findViewById( R.id.texttelfoorama );



        titulo.setText( "Estudio Oramas" );
        direccion.setText( "Chimborazo y Velez edificio Chimborazo piso 11" );
        correo.setText( "estudiooramas@gmail.com");
        telf.setText( "980987688" );
    }
}
