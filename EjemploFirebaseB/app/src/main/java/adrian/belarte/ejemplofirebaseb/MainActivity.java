package adrian.belarte.ejemplofirebaseb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adrian.belarte.ejemplofirebaseb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myPersona;
    private ArrayList<Persona> listaPersonas;
    private DatabaseReference myListPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaPersonas = new ArrayList<>();


        database = FirebaseDatabase.getInstance("https://ejemplo-fire-base-b-default-rtdb.europe-west1.firebasedatabase.app");
        myRef = database.getReference("frase");
        myPersona = database.getReference("persona");
        myListPersonas = database.getReference("listapersonas");
        binding.btnGuardarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(binding.txtFraseMain.getText().toString());
                int edad = (int)(Math.random()*100);
                Persona p = new Persona(binding.txtFraseMain.getText().toString(),edad);
                myPersona.setValue(p);
                listaPersonas.add(p);
                myListPersonas.setValue(listaPersonas);
            }
        });

        myPersona.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Persona p  = snapshot.getValue(Persona.class);
                binding.lbLeerMain.setText(p.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.toException().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        myListPersonas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<Persona>> gti =
                        new GenericTypeIndicator<ArrayList<Persona>>(){};

                ArrayList<Persona> lista = snapshot.getValue(gti);
                binding.lbLeerMain.setText("Elementos en la lista "+ lista.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}