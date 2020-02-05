package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RequisitoPermisosdeSalidaNotarial extends AppCompatActivity {
    private ListView lista;

    private Button req2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_requisito_permisosde_salida_notarial );

        lista= (ListView) findViewById( R.id.listarequsitossalida );

        final List<String> namenotariales= new ArrayList<String>(  );
        namenotariales.add( "REQUISITOS" );
        namenotariales.add( "Partidad de Nacimiento de o de los menores" );

        namenotariales.add( "Copia a Cores de las cedulas de los padres " );
        namenotariales.add( "Fecha en que sale del país y de regreso " );
        namenotariales.add( "Dirección donde el menor se va a hospedar " );
        namenotariales.add( "Nombre de la aereolinea" );
        namenotariales.add( "NOTA GENRAL" );
        namenotariales.add( "para todo trámite de persona jurídica se debe traer copia de RUC, copia de documento de identidad y nombramiento actualizado del representante legal." );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namenotariales);

        lista.setAdapter( adapter );



    }
}
