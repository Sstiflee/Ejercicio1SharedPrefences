package montacer.elfazazi.ejemplobindin5pmdmtema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityAddAlumnoBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityEditAlumnoBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.modelos.Alumno;

public class EditAlumnoActivity extends AppCompatActivity {
    private ActivityEditAlumnoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();



        Alumno alumno = (Alumno)bundle.getSerializable("ALUMNO");
        rellenarInformacion(alumno);
    }

    private void rellenarInformacion(Alumno alumno) {
        binding.
    }
}