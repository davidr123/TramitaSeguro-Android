package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InformacionEstudioNotaEstimaped extends AppCompatActivity {

    private ListView lista;

    TextView titulo, telf, correo, direccion;
    Button requisito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_informacion_estudio_nota );

        requisito= (Button) findViewById( R.id.requi1 );
        titulo= (TextView) findViewById( R.id.textitulotunota );
        direccion= (TextView) findViewById( R.id.textdireccnota );
        correo= (TextView) findViewById( R.id.textcorreonota );
        telf= (TextView) findViewById( R.id.texttelfonta );



        titulo.setText( "EstimaPed" );
      //  direccion.setText( "Chile 303" );
        direccion.setText( "abpedromoreiraesp@gmail.com" );
        telf.setText( "046009632" );

        requisito.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( InformacionEstudioNotaEstimaped.this, RequisitosCertificacioDocumento.class );
                startActivity( intent );
            }
        } );

    }
}
