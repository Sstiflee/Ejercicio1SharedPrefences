package adrian.belarte.ejercicio1sharedprefences;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import adrian.belarte.ejercicio1sharedprefences.configuracion.Constantes;
import adrian.belarte.ejercicio1sharedprefences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    // variable para guardar en xml
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //declaro sp con la constantes
        sp = getSharedPreferences(Constantes.USUARIOS,MODE_PRIVATE);

        comprobarLogin();

        binding.btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(binding.txtUsuarioMain.getText().toString().equalsIgnoreCase("Progresa")
                    && binding.txtPasswordMain.getText().toString().equals("Alumno")){
                    //para editar el xml
                    SharedPreferences.Editor editor = sp.edit();
                     String password = codificarPassword(binding.txtPasswordMain.getText().toString());
                    editor.putString(Constantes.USUARIO,binding.txtUsuarioMain.getText().toString());
                    editor.putString(Constantes.PASSWORD, password);
                    //para aplicarlo
                    editor.apply();

                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    binding.txtUsuarioMain.setText("");
                    binding.txtPasswordMain.setText("");

                }else{
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String codificarPassword(String texto) {
        texto = binding.txtPasswordMain.getText().toString();
        String resultado = null;

        try{
            resultado = new String(Base64.getEncoder().encode(texto.getBytes("UTF8")),"UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }

        return String.valueOf(resultado);
    }

    private void comprobarLogin() {

        if(sp.contains(Constantes.USUARIO) && sp.contains(Constantes.PASSWORD)){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

    }
}