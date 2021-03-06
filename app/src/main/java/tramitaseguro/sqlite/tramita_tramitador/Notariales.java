package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tramitaseguro.sqlite.tramita_tramitador.syncronizacion.Consulta;

public class Notariales extends AppCompatActivity {

    private ListView lista;

    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notariales );

        boton= (Button) findViewById( R.id.Consultar );

        lista= (ListView) findViewById( R.id.listanotariales );

        boton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( Notariales.this, Consulta.class );
                startActivity( intent );
            }
        } );

        final List<String> namenotariales= new ArrayList<String>(  );
        namenotariales.add( "CERTIFICACIÓN DE DOCUMENTOS" );
        namenotariales.add( "PERMISOS DE SALIDA DEL PAIS" );

        namenotariales.add( "COMPRA Y VENTA DE BIENES" );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namenotariales);

        lista.setAdapter( adapter );

        lista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(namenotariales.get(position )=="CERTIFICACIÓN DE DOCUMENTOS"){

                    Intent intent= new Intent( Notariales.this, EstudioJuridicoNotarial.class );
                    startActivity( intent );


                }else if(namenotariales.get(position )=="PERMISOS DE SALIDA DEL PAIS"){

                    Intent intent= new Intent( Notariales.this, EstudioJuridicoNotarial.class );
                    startActivity( intent );
                }


                else if(namenotariales.get(position )=="COMPRA Y VENTA DE BIENES"){
                    Intent intent= new Intent( Notariales.this, EstudioJuridicoNotarial.class );
                    startActivity( intent );
                }


            }
        } );

    }
}
