package tramitaseguro.sqlite.tramita_tramitador.syncronizacion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import tramitaseguro.sqlite.tramita_tramitador.Registro;

public class NetworkStateChecker extends BroadcastReceiver {

    private Context context;
    private DatabaseHelper db;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        db = new DatabaseHelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        Toast.makeText(context,"checking connection",Toast.LENGTH_LONG).show();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                //getting all the unsynced names
                Cursor cursor = db.getUnsyncedNames();
                if (cursor.moveToFirst()) {
                    do {
                        Toast.makeText(context,"connected",Toast.LENGTH_LONG).show();
                        //calling the method to save the unsynced name to MySQL
                        saveName(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)),
                               cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD))

                        );
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    public void saveName(final int id, final String name,final String email,final String password) {

        class SendJsonDataTOServer extends AsyncTask<String, Void, String> {

            protected void onPreExecute(){
                super.onPreExecute();

            }

            protected String doInBackground(String... params) {
                try {
                    URL url = new URL("https://jsonappget.herokuapp.com/register/registro"); // here is your URL path



                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setReadTimeout(15000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty( "Content-Type", "application/json;charseft=UTF-8" );
                    conn.setRequestProperty( "Accept", "application/json" );

                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("email", email);
                    jsonObject.put("password", password);

                    DataOutputStream localDataOutputStream= new DataOutputStream( conn.getOutputStream() );
                    localDataOutputStream.writeBytes( jsonObject.toString() );
                    localDataOutputStream.flush();
                    localDataOutputStream.close();

                    int responseCode=conn.getResponseCode();
                    Log.i("", "Z"+responseCode);


                        BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        StringBuilder sb = new StringBuilder();
                        String line="";

                        while((line = in.readLine()) != null) {

                            sb.append(line);

                        }

                        in.close();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {



                        return sb.toString();

                    }
                    else {
                        return null;
                    }
                }
                catch(Exception e){

                    return "Exception: " + e.getMessage();
                }
            }
            /*
            @Override
            protected void onPostExecute( String result) {
                super.onPostExecute(result);

                if (result != null) {
                    try {
                        JSONObject js = new JSONObject(result);

                        if(!js.getBoolean("error")){
                            //updating the status in sqlite
                            db.updateNameStatus(id, Registro.NAME_SYNCED_WITH_SERVER);

                            //sending the broadcast to refresh the list
                            context.sendBroadcast(new Intent(Registro.DATA_SAVED_BROADCAST));

                            //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                        }
                        if(js.has("errorTrue")){
                            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            }*/
        }
        new SendJsonDataTOServer().execute();

    }







}
