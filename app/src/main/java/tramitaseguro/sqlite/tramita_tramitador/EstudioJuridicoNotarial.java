package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EstudioJuridicoNotarial extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_estudio_juridico_notarial );

        lista= (ListView) findViewById( R.id.listaestudionotarial );

        final List<String> namenoestudionota= new ArrayList<String>(  );

        namenoestudionota.add( "Estudio Pincay - Pincay" );


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namenoestudionota);

        lista.setAdapter( adapter );

        lista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(namenoestudionota.get(position )=="Estudio Pincay - Pincay"){

                    Intent intent= new Intent( EstudioJuridicoNotarial.this, InformacionEstudioNotaPicay.class );
                    startActivity( intent );
                }





            }
        } );
    }
}
