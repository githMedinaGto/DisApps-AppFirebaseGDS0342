package mx.edu.utng.appfirebasegds0342;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView tvMensaje;
    private EditText etText;
    //Refererencia a la base de datos
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeref = ref.child("mimensaje");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        etText = (EditText) findViewById(R.id.etcTexto);
    }

    public void modificar(View view) {
        //Accion del boton
        String mensaje = etText.getText().toString();
        mensajeref.setValue(mensaje);
        etText.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        mensajeref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Se obtiene el valor modificado de la base de datos en tiempo real
                String value = snapshot.getValue(String.class);
                tvMensaje.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}