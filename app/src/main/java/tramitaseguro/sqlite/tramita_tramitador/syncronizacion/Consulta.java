package tramitaseguro.sqlite.tramita_tramitador.syncronizacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tramitaseguro.sqlite.tramita_tramitador.R;

public class Consulta extends AppCompatActivity  {
/*
    private DatabaseHelper db;

    //View objects
    private Button buttonSave;
    private EditText editTextName;
    private ListView listViewNames;

    //List to store all the names
    private List<ModelName> names;

    //1 means data is synced and 0 means data is not synced
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";

    //Broadcast receiver to know the sync status
    private BroadcastReceiver broadcastReceiver;

    //adapterobject for list view
    private NameAdapter nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_consulta );


        names = new ArrayList<>();
        initializeViews();
        initializeObjects();
        initializeListeners();
        loadNames();
        //saveName1();
        registerReceiver(new NetworkStateChecker(), new IntentFilter( ConnectivityManager.CONNECTIVITY_ACTION));
        //the broadcast receiver to update sync status
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),"in the receiver",Toast.LENGTH_LONG).show();
                //loading the names again
                loadNames();
            }
        };
        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));


    }

    public void initializeObjects() {
        db = new DatabaseHelper(this);
    }
    public void initializeViews() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextName = (EditText) findViewById(R.id.editTextName);
        listViewNames = (ListView) findViewById(R.id.listViewNames);

    }
    public void initializeListeners() {
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        saveNameToServer();
    }

    private void saveNameToServer() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving Name...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        final String name = editTextName.getText().toString().trim();
        saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
        progressDialog.dismiss();
    }
    private void loadNames() {
        names.clear();
        Cursor cursor = db.getNames();
        if (cursor.moveToFirst()) {
            do {
                ModelName name = new ModelName(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
                );
                names.add(name);
            } while (cursor.moveToNext());
        }

        nameAdapter = new NameAdapter(this, R.layout.name, names);
        listViewNames.setAdapter(nameAdapter);
    }

    private void refreshList() {
        nameAdapter.notifyDataSetChanged();
    }

    //saving the name to local storage
    private void saveNameToLocalStorage(String name,String email,String password, int status) {
        editTextName.setText("");
        db.addName(name,email,password, status);
        ModelName n = new ModelName(name, email, password, status);
        names.add(n);
        refreshList();
    }

 */
}
