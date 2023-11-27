package adrian.belarte.ejercicio1sharedprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import adrian.belarte.ejercicio1sharedprefences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}