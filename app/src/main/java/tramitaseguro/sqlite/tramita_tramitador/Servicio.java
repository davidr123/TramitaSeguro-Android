package tramitaseguro.sqlite.tramita_tramitador;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class Servicio extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private Context httpContext;
    public String resultadoapi="";
    public String linkrequestAPI="";
    public String email="";
    public String password="";
    public String name="";
    public String phonenumber="";
    //Constructor


    public Servicio(Context context,  String linkAPI,   String email,  String password) {
        this.httpContext = context;
        this.linkrequestAPI = linkAPI;
      //  this.name= name;
        this.email= email;
       // this.phonenumber= phonenumber;
        this.password= password;


    }

 @Override
 protected void onPreExecute(){
        super.onPreExecute();
        progressDialog=ProgressDialog.show(httpContext, "Realizando Consulta", "por favor, espere");
 }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String wsURL= linkrequestAPI;
        URL url= null;

            try {

                //se crea conocexionde al api
                url= new URL(wsURL);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                //crear json para enviar post
                JSONObject parametrosPost= new JSONObject();
               // parametrosPost.put("name", name);
                parametrosPost.put("email", email);
              // parametrosPost.put("numberphone", phonenumber); //   parametrosPost.put("password", "123");
                parametrosPost.put("password", password);

                Log.i("sss", name);


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

                int responseCode= urlConnection.getResponseCode(); //COnexion OK?
                Log.i("response",responseCode+"");
                if(responseCode== HttpURLConnection.HTTP_OK){
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    StringBuffer sb= new StringBuffer("");
                    String linea="";
                    while ((linea= in.readLine())!= null){
                        sb.append(linea);
                        break;

                    }

                    in.close();
                    result=sb.toString();
                    Log.i("response",result+"");

                }else{
                    result= new String("Error:  "+ responseCode);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        return result;
    }




    @Override
    protected void onPostExecute(String e){
        super.onPostExecute(e);
    progressDialog.dismiss();
        resultadoapi=e;
        //Toast.makeText(httpContext, resultadoapi, Toast.LENGTH_LONG).show();
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
