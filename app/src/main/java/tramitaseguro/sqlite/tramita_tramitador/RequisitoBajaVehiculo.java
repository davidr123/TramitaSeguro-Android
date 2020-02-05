package tramitaseguro.sqlite.tramita_tramitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RequisitoBajaVehiculo extends AppCompatActivity {

    private ListView requisitos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisito_baja_vehiculo);

        requisitos= (ListView) findViewById(R.id.listarequsitos2);
        List<String> req= new ArrayList<String>();
        req.add("TRÁMITE BAJA DE VEHÍCULO")  ;
        req.add("REQUISITOS");
        req.add("Original de la matrícula, en caso de pérdida o robo la denuncia ante la autoridad competente.");
        req.add("Certificado de Chatarrización extendido por una empresa siderúrgica legalmente autorizada por el Ministerio de Industria y Productividad (MIPRO)\n");
        req.add("Declaración juramentada que justifique el destino del vehículo y la voluntad del interesado de dar de baja al vehículo. La declaración juramentada deberá contener los datos del propietario y las características del vehículo, tales como: marca, modelo, número de chasis y motor, color, año de producción; deberá además dejar constancia de su aceptación de que se trata de un proceso irreversible.");
        req.add("Placas originales del vehículo o denuncia de robo o pérdida presentada ante autoridad competente.");
        req.add("El pago del valor de la tasa.");
        req.add("No tener valores pendientes de matriculación, ni infracciones de tránsito.");
        req.add("En caso de ROBO de vehículo, se deberá presentar la respectiva orden de la autoridad competente (Juez – Fiscal) que especifique la acción inherente al archivo de la causa.");
        req.add("En caso de que el trámite sea realizado por un autorizado del propietario, deberá presentar una carta de autorización simple, y copia de cédula de ciudadanía de ambas personas, con excepción de que el trámite sea realizado por un familiar directo (primer grado de consanguinidad) o cónyuge bastará la presentación de copia de copia de cédula del propietario como del familiar para validarla.");
        req.add("NOTA GENERAL");
        req.add("para todo trámite de persona jurídica se debe traer copia de RUC, copia de documento de identidad y nombramiento actualizado del representante legal.");
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, req);

        requisitos.setAdapter(adapter);
    }
}
