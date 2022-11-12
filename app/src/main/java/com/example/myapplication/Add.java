package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Add extends AppCompatActivity {

    ListView listView;
    String komputer;
    String myszka;
    String klawiatura;
    String kamerka;
    String cena;
    String cena_koszyk = MainActivity.getKoszyk_cena();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if(MainActivity.getLang().getText().toString().equals("ILOŚĆ")){
            komputer = "Komputer: ";
            myszka = "Myszka: ";
            klawiatura = "Klawiatura: ";
            kamerka = "Kamerka: ";
            cena = "Cena: ";
        }else{
            komputer = "Computer: ";
            myszka = "Mouse: ";
            klawiatura = "Keyboard: ";
            kamerka = "Camera: ";
            cena = "Price: ";
        }

        listView = findViewById(R.id.listView);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(komputer + MainActivity.getKoszyk_pc());
        arrayList.add(myszka + MainActivity.getKoszyk_myszka());
        arrayList.add(klawiatura + MainActivity.getKoszyk_klawiatura());
        arrayList.add(kamerka + MainActivity.getKoszyk_kamerka());
        arrayList.add(cena + cena_koszyk + "pln");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);
    }
}