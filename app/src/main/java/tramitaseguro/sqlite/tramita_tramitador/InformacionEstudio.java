package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import tramitaseguro.sqlite.tramita_tramitador.adapter.AdapterEstudioJuridico;
import tramitaseguro.sqlite.tramita_tramitador.objetos.TramiteEstudioJuridico;

public class InformacionEstudio extends AppCompatActivity {
    public ListView listView;

    private  Button requisitos;
    public TextView texttramites, direccion;
    public  String valor;
    Button btnEje;
    AdapterEstudioJuridico adpater;
    ArrayList<TramiteEstudioJuridico> listTramites=new ArrayList<TramiteEstudioJuridico>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_estudio);
        direccion= (TextView) findViewById(R.id.textdireccion);
requisitos= (Button) findViewById(R.id.buttonrequisitos);

requisitos.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(InformacionEstudio.this, RequisitoDuplicadoMatricula.class);

       // intent.putExtra("rquisitoduplicadomatricula", estudios.getId() );
        startActivity(intent);
    }
});
        valor = getIntent().getStringExtra("estudios");
         Log.i("valor",valor);

        //listView=findViewById(R.id.listaestudios);
        //  adpater = new AdapterEstudioJuridico(getApplicationContext(), listTramites);
        //  listView.setAdapter(adpater);
        new ServicioJson().execute();
    }
    public  class ServicioJson extends AsyncTask<String, String, String> {





        @SuppressLint("WrongThread")
        protected String doInBackground(String... strings) {




            String result = null;
            String wsURL= "https://jsonappget.herokuapp.com/tramites/estudio";
            URL url= null;
            HttpURLConnection urlConnection=null;

            try {

                //se crea conocexionde al api
                url= new URL(wsURL);
                urlConnection=(HttpURLConnection) url.openConnection();
                //crear json para enviar post


                JSONObject parametrosPost= new JSONObject();



                // parametrosPost.put("name", name);
                parametrosPost.put("id", valor);
                // parametrosPost.put("numberphone", phonenumber); //   parametrosPost.put("password", "123");
                //parametrosPost.put("password", password);






                //DEFINIR PARAMERTRSO DE CONEXION
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST"); // SE puede cambiar por delete, get, put etc;
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);


                //OBTEENR RESUTADO DE REQUEST
                OutputStream os= urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(parametrosPost));
                writer.flush();
                writer.close();
                os.close();
                int code = urlConnection.getResponseCode();
                if (code !=  200) {
                    throw new IOException("Invalid response from server: " + code);
                }

                StringBuffer response= new StringBuffer();
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("data", line);




                    response.append(line);

                    String input;

                    while((input= rd.readLine())!=null){
                        response.append(input);
                    }




                    String json="";
                    json= response.toString();
                    JSONObject jsonObject2 = new JSONObject(json);
                    JSONArray jsonArray= null;
                    // jsonObject2= jsonObject2.optJSONObject("tramites");
                    String content = jsonObject2.optString("direccion");

                    jsonArray= jsonObject2.getJSONArray("tramites");
                    Log.i("json",jsonArray.toString());

                    String men= "";
                    String DT="";
                    String año="";
                    String tro="";


                    for (int i =0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Log.i("jsonObj",jsonObject.toString());

                        men += " " + jsonObject.getString("direccion");
                        DT += " " + jsonObject.getString("id");
                        TramiteEstudioJuridico tramites= new TramiteEstudioJuridico();
                        tramites.setId(DT);

                        tramites.setDireccion(men);
                        //listTramites.add(tramites);



                        direccion.setText(tramites.getDireccion());

                    }







                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }



    }



    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

}
