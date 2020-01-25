package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UsuarioLogin extends AppCompatActivity {

    public static final String usuariologueado= "nombre";
    TextView txtusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_login);

        txtusuario= (TextView) findViewById(R.id.textView);


        String usuario= getIntent().getStringExtra("nombre");
        txtusuario.setText("Bienvenido" + usuario);
    }
}
