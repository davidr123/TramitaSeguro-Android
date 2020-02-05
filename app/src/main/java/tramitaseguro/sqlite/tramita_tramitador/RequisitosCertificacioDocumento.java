package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RequisitosCertificacioDocumento extends AppCompatActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_requisitos_certificacio_documento );

        lista= (ListView) findViewById( R.id.listarequsito1 );

        final List<String> namenotariales= new ArrayList<String>(  );
        namenotariales.add( "REQUISITOS" );
        namenotariales.add( "Original de los documentos" );

        namenotariales.add( "Copia por duplicado de documentos" );
        namenotariales.add( "NOTA GENRAL" );
        namenotariales.add( "para todo trámite de persona jurídica se debe traer copia de RUC, copia de documento de identidad y nombramiento actualizado del representante legal." );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namenotariales);

        lista.setAdapter( adapter );

    }
}
