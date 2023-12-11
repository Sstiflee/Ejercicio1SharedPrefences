package adrian.belarte.alumnossql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import adrian.belarte.alumnossql.configuracion.Configuracion;
import adrian.belarte.alumnossql.databinding.ActivityMainBinding;
import adrian.belarte.alumnossql.helpers.AlumnosHelper;
import adrian.belarte.alumnossql.modelos.Alumno;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Alumno> listaAlumnos;
    private AlumnosHelper helper;
    private Dao<Alumno, Integer> daoAlumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaAlumnos = new ArrayList<>();
        helper = new AlumnosHelper(this, Configuracion.BD_NAME,null,Configuracion.BD_VERSION);

        if (helper != null){
            daoAlumno = helper.getDaoAlunos();
            try {
                listaAlumnos.addAll(daoAlumno.queryForAll());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        binding.btnInsertarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarAlumno();
            }
        });

        binding.btnConsultarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnModificarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnEliminarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void insertarAlumno() {
        if(binding.txtNombreMain.getText().toString().isEmpty() ||
                binding.txtApellidosMain.getText().toString().isEmpty() ||
                binding.txtNota1Main.getText().toString().isEmpty() ||
                binding.txtNota2Main.getText().toString().isEmpty() ||
                binding.txtNota3Main.getText().toString().isEmpty() ){
            Toast.makeText(this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
        }else{
            Alumno a = new Alumno();
            a.setNombre(binding.txtNombreMain.getText().toString());
            a.setApellidos(binding.txtApellidosMain.getText().toString());
            a.setNota1(Float.parseFloat(binding.txtNota1Main.getText().toString()));
            a.setNota2(Float.parseFloat(binding.txtNota2Main.getText().toString()));
            a.setNota3(Float.parseFloat(binding.txtNota3Main.getText().toString()));


            try{
                daoAlumno.create(a);
                a.setId(daoAlumno.extractId(a));
                listaAlumnos.add(a);
            }catch (SQLException e){
                throw  new RuntimeException(e);
            }
        }
    }
}