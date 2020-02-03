package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import tramitaseguro.sqlite.tramita_tramitador.adapter.AdapaterTramites;
import tramitaseguro.sqlite.tramita_tramitador.objetos.Tramites;

public class Transito extends AppCompatActivity {

public ListView listView;

public TextView texttramites;
Button btnEje;
AdapaterTramites adpater;
ArrayList<Tramites> listTramites=new ArrayList<Tramites>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transito);



        listView=findViewById(R.id.listatramites);
        adpater = new AdapaterTramites(getApplicationContext(), listTramites);
        listView.setAdapter(adpater);
        new ServicioJson().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Tramites  item = (Tramites) adpater.getItem(position);

                Intent intent = new Intent(Transito.this, EstudioJuridicoActivity.class);
                isConnectedToInternet();
                intent.putExtra("tramitesxestudio", item.getId() );
                startActivity(intent);
            }


        });


    }

    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }


        public  class ServicioJson extends AsyncTask<String, String, String> {





            @SuppressLint("WrongThread")
            protected String doInBackground(String... strings) {







                HttpURLConnection urlConnection = null;

                try {
                    URL url = new URL("https://jsonappget.herokuapp.com/tramites");
                    urlConnection = (HttpURLConnection) url.openConnection();

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
                        String content = jsonObject2.optString("tramites");

                        jsonArray= jsonObject2.getJSONArray("tramites");
                        Log.i("json",jsonArray.toString());

                        String men= "";
                        String DT="";
                        String año="";
                        String tro="";


                        for (int i =0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Log.i("jsonObj",jsonObject.toString());

                            men = " " + jsonObject.getString("descripcion");
                            DT = " " + jsonObject.getString("id");
                            Tramites tramites= new Tramites();
                            tramites.setId(DT);
                            tramites.setDescriocion(men);
                            listTramites.add(tramites);





                        }

                        adpater.notifyDataSetChanged();







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




}
