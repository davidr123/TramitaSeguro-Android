package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Transito extends AppCompatActivity {

private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transito);

        listView= (ListView) findViewById(R.id.listview);

        final List<String> tramites = new ArrayList<String>();
        tramites.add( "TRÁMITE DUPLICADO DE MATRÍCULA");
        tramites.add( "TRÁMITE BAJA DE VEHÍCULO");
        tramites.add( "TRÁMITE CAMBIO DE CARACTERÍSTICAS");
        tramites.add( "TRÁMITE BLOQUEOS");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tramites);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               final int id2=0;

        if(tramites.get(position)=="TRÁMITE DUPLICADO DE MATRÍCULA"){
            Intent intent = new Intent(Transito.this, EstudioJuridico.class);
            Toast.makeText(Transito.this, "" +   tramites.get(position), Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
        else if((tramites.get(position)=="TRÁMITE BAJA DE VEHÍCULO")){
            Intent intent = new Intent(Transito.this, TramitesTransito.class);
            Toast.makeText(Transito.this, "" +   tramites.get(position), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }


            }
        });


    }



}
