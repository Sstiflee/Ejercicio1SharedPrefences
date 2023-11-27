package adrian.belarte.ejercicio1sharedprefences;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import adrian.belarte.ejercicio1sharedprefences.configuracion.Constantes;
import adrian.belarte.ejercicio1sharedprefences.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    private SharedPreferences sp;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences(Constantes.USUARIOS,MODE_PRIVATE);

        binding.lbUsuarioSecond.setText(sp.getString(Constantes.USUARIO,""));
        String password = descodificarPassword();
        binding.lbPasswordSecond.setText(password);

        binding.btnLogoutSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String descodificarPassword() {
        String texto = sp.getString(Constantes.PASSWORD,"");
        String resultado = null;
        try{
            resultado = new String(Base64.getDecoder().decode(texto.getBytes("UTF8")),"UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        return String.valueOf(resultado);
    }
}