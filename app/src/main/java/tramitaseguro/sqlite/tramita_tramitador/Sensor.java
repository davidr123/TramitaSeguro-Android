package tramitaseguro.sqlite.tramita_tramitador;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accessibilityservice.FingerprintGestureController;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Sensor extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "Freddy Rada";
    private Cipher cipher;
    private TextView text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED){

            return;
        }


        if (!fingerprintManager.isHardwareDetected())
            Toast.makeText(this, "Autenticación por huella digital no habilitada", Toast.LENGTH_SHORT).show();
        else {
            if (!fingerprintManager.hasEnrolledFingerprints())
                Toast.makeText(this, "la seguridad de la pantalla de registro no está habilitada en la configuración", Toast.LENGTH_SHORT).show();

            else {
                if (!keyguardManager.isKeyguardSecure())
                    Toast.makeText(this, "la seguridad de la pantalla de registro no está habilitada en la configuración", Toast.LENGTH_SHORT).show();
                else
                    genKey();

                if (cipherInit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuthentication(fingerprintManager, cryptoObject);

                }

            }
        }
    }

    private boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();

        }

            try {
                keyStore.load(null);
                SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return true;
            } catch (CertificateException ex) {

                ex.printStackTrace();
                return false;
            } catch (IOException ex) {

                ex.printStackTrace();
                return false;
            } catch (NoSuchAlgorithmException ex) {

                ex.printStackTrace();
                return false;
            } catch (UnrecoverableKeyException ex) {

                ex.printStackTrace();
                return false;
            } catch (KeyStoreException ex) {

                ex.printStackTrace();
                return false;
            } catch (InvalidKeyException ex) {

                ex.printStackTrace();
                return false;
            }



    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("NewApi")
    private void genKey(){
try {
    keyStore= KeyStore.getInstance("AndroidKeyStore");

} catch (KeyStoreException e) {
    e.printStackTrace();
}

        KeyGenerator keyGenerator = null;

        try {
            keyGenerator= KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT| KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build()

            );
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {

            e.printStackTrace();
        }


        keyGenerator.generateKey();
    }



    }

