package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Dane extends AppCompatActivity {
    Button zapisz_dane;
    EditText numer_tel;
    public static String num_tel;


    public Button getZapisz_dane() {
        return zapisz_dane;
    }

    public EditText getNumer_tel() {
        return numer_tel;
    }

    public static String getNum_tel() {
        return num_tel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dane);
        zapisz_dane = findViewById(R.id.zapisz_dane);
        numer_tel = findViewById(R.id.numer_telefonu);

        zapisz_dane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_tel = numer_tel.getText().toString();

                if(MainActivity.getLang().getText().toString().equals("ILOŚĆ")) {
                    Toast.makeText(getApplicationContext(), "Zapisano dane kontaktowe", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Contact details saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}