package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import tramitaseguro.sqlite.tramita_tramitador.objetos.Tramites;

public class Bievenido extends AppCompatActivity {

    public static String usuariologueado;
    GridLayout maingrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bievenido);

maingrid= (GridLayout) findViewById(R.id.maingrid);

setSingleEvent(maingrid);

    }

    private void setSingleEvent(GridLayout maingrid) {

        for(int i=0; i<maingrid.getColumnCount();i++){


            CardView cardView= (CardView)maingrid.getChildAt(i);

            final int finalit=i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               if(finalit==0){

                 Intent intent= new Intent(Bievenido.this, Transito.class);


                  startActivity(intent);

               }
               else if(finalit==1){
                   Intent intent= new Intent(Bievenido.this, Notariales.class);
                   startActivity(intent);
               }



               
               else{
                   Toast.makeText(Bievenido.this, "", Toast.LENGTH_SHORT).show();
               }



                }
            });
        }
    }
}
