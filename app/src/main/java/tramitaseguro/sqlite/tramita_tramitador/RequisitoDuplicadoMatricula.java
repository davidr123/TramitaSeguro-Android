package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import java.util.List;

import tramitaseguro.sqlite.tramita_tramitador.adapter.AdapterEstudioJuridico;
import tramitaseguro.sqlite.tramita_tramitador.adapter.AdapterRequisito;
import tramitaseguro.sqlite.tramita_tramitador.objetos.Requisitos;
import tramitaseguro.sqlite.tramita_tramitador.objetos.TramiteEstudioJuridico;
import tramitaseguro.sqlite.tramita_tramitador.objetos.Tramites;
import tramitaseguro.sqlite.tramita_tramitador.objetos.TramitesSingle;

public class RequisitoDuplicadoMatricula extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ListView listView;
    public  String valor;
    private TextView titulo, consideracion, notageneral;
private RelativeLayout relativeLayoutMain ;
public ProgressBar progressBar;
    AdapterRequisito adpater;
    ArrayList<Requisitos> listrequisitos=new ArrayList<Requisitos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisito_duplicado_matricula);

        // relativeLayoutMain=findViewById( R.id.contenMain );
        //progressBar=findViewById( R.id.progressBar );
              listView= (ListView) findViewById(R.id.listarequsitos);

        titulo= (TextView) findViewById( R.id.textreqtitulo );
        consideracion=(TextView) findViewById( R.id. textreqconsideracion );
        notageneral= (TextView) findViewById( R.id.textreqnotageneral );


        adpater = new AdapterRequisito(getApplicationContext(), listrequisitos);
        listView.setAdapter(adpater);


        TramitesSingle tramitesSingle= TramitesSingle.getInstance();

        valor = tramitesSingle.id;
     //   new ServicioJson(getApplicationContext()).execute();
        new ServicioJson().execute();
        Log.i("valor",valor);

    }





    public  class ServicioJson extends AsyncTask<String, String, String> {
        private Context httpContext;
/*
        public  ServicioJson(Context context){
            this.httpContext = context;

        }*/
/*
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog=ProgressDialog.show(RequisitoDuplicadoMatricula.this, "Realizando Consulta", "por favor, espere");
        }
*/

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            String result = null;
            String wsURL= "https://jsonappget.herokuapp.com/tramites/requisitos";
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

                  //  progressBar.setVisibility( View.GONE );
                    //relativeLayoutMain.setVisibility( View.VISIBLE );


                    String json="";
                    json= response.toString();
                    JSONObject jsonObject2 = new JSONObject(json);
                    JSONArray jsonArray= null;
                    // jsonObject2= jsonObject2.optJSONObject("tramites");
                    String content = jsonObject2.optString("direccion");

                    jsonArray= jsonObject2.getJSONArray("tramites");
                    Log.i("json2",jsonArray.toString());

                    String men= "";
                    String DT="";
                    String año="";
                    String tro="";



                    for (int i =0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Log.i("jsonObj",jsonObject.toString());

                        men += " " + jsonObject.getString("descripcion");
                        DT += " " + jsonObject.getString("requisitotramites");
                        año += " " + jsonObject.getString("consideracionestramte");
                        tro += " " + jsonObject.getString("notageneral");
                    TramiteEstudioJuridico tramites= new TramiteEstudioJuridico();
                      //  tramites.setId(DT);

                       // tramites.setDireccion(men);


                        Requisitos requisitos= new Requisitos();
                        requisitos.setId(jsonObject.getString("id_requisito"));
                        requisitos.setRequisitotramites( DT );

                                            Tramites tramite= new Tramites( );
                    tramite.setDescriocion( men );

                    tramite.setConsideración(año);
                    tramite.setNotageneral(tro);

                    titulo.setText( men );


                        listrequisitos.add( requisitos );

                        consideracion.setText(año);
                        notageneral.setText(tro);


                        adpater.notifyDataSetChanged();


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
/*
        @Override
        protected void onPostExecute(String e){
            super.onPostExecute(e);
            progressDialog.dismiss();

            // Toast.makeText(httpContext, mensaje, Toast.LENGTH_SHORT).show();



            //Toast.makeText(httpContext, resultadoapi, Toast.LENGTH_LONG).show();
        }

*/

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

            result.append( URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }





}
