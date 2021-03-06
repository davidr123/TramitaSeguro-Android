package tramitaseguro.sqlite.tramita_tramitador.tareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import tramitaseguro.sqlite.tramita_tramitador.Provider.UsuarioContract;
import tramitaseguro.sqlite.tramita_tramitador.Provider.UsuarioHelper;
import tramitaseguro.sqlite.tramita_tramitador.objetos.Usuarios;

public class TareaInternet extends AsyncTask<Integer, Integer, String> {

    public Context contex;
    UsuarioHelper dbUser;
    String responseText;
    Usuarios user;
    StringBuffer response;
    public final static String path = "https://jsonappget.herokuapp.com/register/registro";
    ArrayList<Usuarios> listUsuario=new ArrayList<Usuarios>();
    public TareaInternet(Context contex) {
        this.contex = contex;
        dbUser= new UsuarioHelper(contex.getApplicationContext());
        user= new Usuarios();
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Integer... integers) {
        while(true){
            if(isConnectedToInternet()){
                if(subirDatos()){
                    //return "ok";
                    break;
                }else{
                    break;
                }
            }
        }
        return "ok";
    }

    private boolean subirDatos(){
        obterData();
        //   Log.i("id",user.getId());
        if(user.getId()!=null) {


            if (getWebServiceResponseData()) {
                if (updateUser())
                    return true;
            }
        }
        return false;
    }
    protected boolean getWebServiceResponseData() {



        ArrayList<JSONObject> listString= new ArrayList<JSONObject>();
        JSONArray jsonA=null;
        jsonA=new JSONArray();
        Log.i("respuesta", "nada");
        Log.i("respuesta", user.toString());


        JSONObject parametrosPost= new JSONObject();



        try {
            parametrosPost.put("name", user.getName());
            parametrosPost.put("email", user.getEmail());
            parametrosPost.put("password",user.getPassword());

        } catch (JSONException e) {
            e.printStackTrace();





        }



        String result = null;

        URL url= null;
        HttpURLConnection urlConnection=null;

        try {

            //se crea conocexionde al api
            url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();
            //crear json para enviar post


            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.setRequestMethod("POST"); // SE puede cambiar por delete, get, put etc;
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTEENR RESUTADO DE REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();
            int code = urlConnection.getResponseCode();
            Log.i("data", code + "");
            if (code != 200) {

                //  throw new IOException("Invalid response from server: " + code);
                return false;
            } else {

                StringBuffer response = new StringBuffer();
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("data", line);


                    response.append(line);

                    String input;

                    while ((input = rd.readLine()) != null) {
                        response.append(input);
                    }


                    String json = "";
                    json = response.toString();
                    JSONObject jsonObject2 = new JSONObject(json);



                    Log.i("mens", "tu madre");
                    return true;

                    // Toast.makeText(httpContext, "Bienvenido"+email+"", Toast.LENGTH_SHORT).show();

                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }


        return true;
    }
    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;



        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {

            String key = itr.next();
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
    private boolean updateUser(){
        SQLiteDatabase db = dbUser.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsuarioContract.UsuariosEntry.GUARDADO,"true");
        Log.i("actalizando", "updateUser: "+user.getId());

        long newRowId =  db.update(UsuarioContract.UsuariosEntry.TABLE_NAME, contentValues, "_ID = ?",new String[] { user.getId() });
        Log.i("actalizando", "updateUser: "+newRowId);
        return true;
    }
    private void obterData() {
        SQLiteDatabase db = dbUser.getWritableDatabase();
        //Log.i("usernew",ingresanombre.getText()+"");
        String[] projection = {
                BaseColumns._ID,
                UsuarioContract.UsuariosEntry.name,
                UsuarioContract.UsuariosEntry.email,

                UsuarioContract.UsuariosEntry.password,
                UsuarioContract.UsuariosEntry.GUARDADO,

        };
        String selection = UsuarioContract.UsuariosEntry.GUARDADO+ " = 'false'";
        Cursor cursor = db.query(
                UsuarioContract.UsuariosEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,          // The values for the WHERE clause
                null,          // The vadatoslues for the WHERE clause
                null,                   // don't filter by row groups
                null,                   // don't filter by row groups
                null             // The sort order
        );

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry._ID));
            String nombre=cursor.getString(
                    cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.name));
            String email=cursor.getString(
                    cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.email));
            String password=cursor.getString(
                    cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.password));




            Log.i("usernew",nombre);
            Log.i("usernew",email);
            // user= new Usuario();
            user.setName(nombre);
            user.setEmail(email);
            user.setPassword(password);

            user.setId(itemId+"");
            listUsuario.add(user);

        }





    }




    @Override
    protected void onPostExecute(String nombre) {
        super.onPostExecute(nombre);

        Log.i("datos",listUsuario.toString());

    }

    public boolean isConnectedToInternet(){

        ConnectivityManager connectivity = (ConnectivityManager)contex.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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

}
