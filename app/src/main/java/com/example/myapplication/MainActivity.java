package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    public static String getKoszyk_pc() {
        return koszyk_pc;
    }

    public static String getKoszyk_myszka() {
        return koszyk_myszka;
    }

    public static String getKoszyk_klawiatura() {
        return koszyk_klawiatura;
    }

    public static String getKoszyk_kamerka() {
        return koszyk_kamerka;
    }

    public static String getKoszyk_cena() {
        return koszyk_cena;
    }

    public static TextView getLang() {
        return lang;
    }

    int ilosc_zest;
    public static String koszyk_pc;
    public static String koszyk_myszka;
    public static String koszyk_klawiatura;
    public static String koszyk_kamerka;
    public static String koszyk_cena;
    String myszka_k;
    String klawiatura_k;
    String kamerka_k;
    String dodatki;
    public static TextView lang;
    String pc;
    String myszka;
    String klawiatura;
    String kamerka;
    String pc2;
    String myszka2;
    String klawiatura2;
    String kamerka2;
    String zamowienie;
    EditText dane;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Button zamow;
    TextView cena;
    TextView dodatek;
    TextView ilosc;
    SeekBar seekBar;
    String email;
    DatabaseHandler myDB;
    ArrayList<String> dane1, dane2, dodatek1, cena1;
    CustomAdapter customAdapter;
    CheckBox checkBox_myszki;
    CheckBox checkBox_klawiatury;
    CheckBox checkBox_kamerki;
    int cena_pc;
    int cena_myszka;
    int cena_klawiatura;
    int cena_kamerka;
    int cena_ostateczna;
    String cena_za_zestaw;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String data;
    //-----------

    public ArrayList<String> wiadomosci = new ArrayList<>();
    public ArrayList<String> numery = new ArrayList<>();
    String destinationAddress = "";
    String text = null;
    String text2 = null;
    String phoneNumber;
    SmsManager smsManager;
    private static final int MY_PERMISSION_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSION_REQUEST_RECEIVE_SMS = 2;
    Context context;

    final List <String> opisy_pcty = Arrays.asList(
        "Intel_I512400f_5099pln",
        "Intel_I713700K_14959pln",
        "Ryzen_5600X_4859pln"
    );

    List <String> opisy_myszki = Arrays.asList(
            "Krypton_200_69pln",
            "Krypton_290_89pln",
            "Krypton_770_199pln"
    );

    List <String> opisy_klawiatur = Arrays.asList(
            "Thor_303_TKL_229pln",
            "Thor_400_RGB_349pln",
            "Thor_300_TKL_219pln"
    );

    List <String> opisy_kamerek = Arrays.asList(

            "BRIO_500_599pln",
            "C290s_PRO_549pln",
            "C922_PRO_569pln"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        SpinnerAdapter adapter1 = new SpinnerAdapter(getApplicationContext(), opisy_pcty);
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner.setAdapter(adapter1);

        spinner2 = findViewById(R.id.spinner_myszki);
        SpinnerAdapter adapter = new SpinnerAdapter(getApplicationContext(), opisy_myszki);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner2.setAdapter(adapter);

        spinner3 = findViewById(R.id.spinner_klawiatury);
        SpinnerAdapter adapter2 = new SpinnerAdapter(getApplicationContext(), opisy_klawiatur);
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner3.setAdapter(adapter2);

        spinner4 = findViewById(R.id.spinner_kamerki);
        SpinnerAdapter adapter3 = new SpinnerAdapter(getApplicationContext(), opisy_kamerek);
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner4.setAdapter(adapter3);

        checkBox_myszki = findViewById(R.id.checkbox_1);
        checkBox_klawiatury = findViewById(R.id.checkbox_2);
        checkBox_kamerki = findViewById(R.id.checkbox_3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                koszyk_pc = opisy_pcty.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    klawiatura_k = opisy_klawiatur.get(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    kamerka_k = opisy_kamerek.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    myszka_k = opisy_myszki.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        dane = findViewById(R.id.editText);
        cena = findViewById(R.id.cena1);
        dodatek = findViewById(R.id.cena1);
        zamow = findViewById(R.id.zamow);
        myDB = new DatabaseHandler(MainActivity.this);
        dane1 = new ArrayList<>();
        dane2 = new ArrayList<>();
        dodatek1 = new ArrayList<>();
        cena1 = new ArrayList<>();
        customAdapter = new CustomAdapter(MainActivity.this, dane1, dane2, dodatek1, cena1);
        email = "Dane: " + dane.getText().toString() + "\nModel: " + dane.getText().toString() + "\nDodatek: " + dodatek.getText().toString() + "\nCena: " + cena.getText().toString();
        context = MainActivity.this;
        seekBar = findViewById(R.id.seekBar);
        ilosc = findViewById(R.id.ilosc);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data = simpleDateFormat.format(calendar.getTime());
        lang = findViewById(R.id.a);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ilosc.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        zamow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dane.getText().toString().equals("")) {
                    if(lang.getText().toString().equals("ILOŚĆ")) {
                        Toast.makeText(getApplicationContext(), "Podaj swoje dane żeby złożyć zamówienie! (menu -> Dane)", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Enter your details to place an order! (menu -> Information)", Toast.LENGTH_LONG).show();
                    }

                } else {
                    cena();
                    sendSMS();
                    if(lang.getText().toString().equals("ILOŚĆ")) {
                        Toast.makeText(MainActivity.this, "Złożono zamówienie", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "An order has been placed", Toast.LENGTH_SHORT).show();
                    }
                    cena.setText(cena_za_zestaw);
                    SaveInDatabase();
                    reset();
                    seekBar.setProgress(0);
                }
            }
        });

        myDB = new DatabaseHandler(MainActivity.this);
        dane1 = new ArrayList<>();
        dane2 = new ArrayList<>();
        dodatek1 = new ArrayList<>();
        cena1 = new ArrayList<>();

    }

    public void reset(){
        if(checkBox_myszki.isChecked()){
            checkBox_myszki.toggle();
        }

        if(checkBox_klawiatury.isChecked()){
            checkBox_klawiatury.toggle();
        }

        if(checkBox_kamerki.isChecked()){
            checkBox_kamerki.toggle();
        }
    }

    public void koszyk(){
        if(checkBox_myszki.isChecked()){
            koszyk_myszka = myszka_k;
        }else {
            koszyk_myszka = "brak";
        }

        if(checkBox_klawiatury.isChecked()){
            koszyk_klawiatura = klawiatura_k;
        }else {
            koszyk_klawiatura = "brak";
        }

        if(checkBox_kamerki.isChecked()){
            koszyk_kamerka = kamerka_k;
        }else {
            koszyk_kamerka = "brak";
        }
    }


    public void SaveInDatabase(){
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        db.dodajZamowienie(
                dane.getText().toString().trim(),
                pc2.trim(),
                dodatki.trim(),
                Integer.parseInt(cena.getText().toString().trim())

        );

    }

    public void sendSMS(){
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            String message1 = "Zestaw:\n" + zamowienie;
            String message2 = "Data: " + data + "\nCena: " + cena_za_zestaw + "zł" + "\nDane: " + dane.getText();
            text2 = message2;
            text = message1;
            phoneNumber = Dane.getNum_tel();
            destinationAddress = phoneNumber;
            if (!destinationAddress.equals("") && !text.equals("") && !text2.equals("")) {
                smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(
                        destinationAddress,
                        null,
                        text,
                        null,
                        null
                );

                smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(
                        destinationAddress,
                        null,
                        text2,
                        null,
                        null
                );
            }
        }
    }

    public void cena(){
        pc = spinner.getSelectedItem().toString();
        myszka = spinner2.getSelectedItem().toString();
        klawiatura = spinner3.getSelectedItem().toString();
        kamerka = spinner4.getSelectedItem().toString();

        switch (pc){
            case "Intel_I512400f_5099pln":
                cena_pc = 5099;
                pc2 = "Intel I512400f, ";
                break;

            case "Intel_I713700K_14959pln":
                cena_pc = 14959;
                pc2 = "Intel I713700K, ";
                break;

            case "Ryzen_5600X_4859pln":
                cena_pc = 4859;
                pc2 = "Ryzen 5600X, ";
                break;
        }

        if(checkBox_myszki.isChecked()){
            switch (myszka){
                case "Krypton_200_69pln":
                    cena_myszka = 69;
                    myszka2 = "Krypton 200, ";
                    break;

                case "Krypton_290_89pln":
                    cena_myszka = 89;
                    myszka2 = "Krypton 290, ";
                    break;

                case "Krypton_770_199pln":
                    cena_myszka = 199;
                    myszka2 = "Krypton 770, ";
                    break;
            }
        }else {
            myszka2 = "";
        }

        if(checkBox_klawiatury.isChecked()){
            switch (klawiatura){
                case "Thor_303_TKL_229pln":
                    cena_klawiatura = 223;
                    klawiatura2 = "Thor 303 TKL, ";
                    break;

                case "Thor_400_RGB_349pln":
                    cena_klawiatura = 349;
                    klawiatura2 = "Thor 400 RGB, ";
                    break;

                case "Thor_300_TKL_219pln":
                    cena_klawiatura = 219;
                    klawiatura2 = "Thor 300 TKL, ";
                    break;
            }
        }else {
            klawiatura2 = "";
        }

        if(checkBox_kamerki.isChecked()){
            switch (kamerka){
                case "BRIO_500_599pln":
                    cena_kamerka = 599;
                    kamerka2 = "BRIO 500";
                    break;

                case "C290s_PRO_549pln":
                    cena_kamerka = 549;
                    kamerka2 = "C290s PRO";
                    break;

                case "C922_PRO_569pln":
                    cena_kamerka = 569;
                    kamerka2 = "C922 PRO";
                    break;
            }
        }else {
            kamerka2 = "";
        }
        ilosc_zest = Integer.parseInt(ilosc.getText().toString());
        cena_ostateczna = (cena_pc + cena_myszka + cena_klawiatura + cena_kamerka) * ilosc_zest;
        cena_za_zestaw = Integer.toString(cena_ostateczna);
        zamowienie = pc2 + myszka2 + klawiatura2 + kamerka2;
        dodatki = myszka2 + klawiatura2 + kamerka2;
        if(!dodatki.equals("")){
            dodatki = myszka2 + klawiatura2 + kamerka2;
        }else{
            dodatki = "brak";
        }
        koszyk_cena = cena_za_zestaw;
    }

    private Boolean checkPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ((MainActivity) context).requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                cena();
                koszyk();
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
                break;

            case R.id.item2:
                if (dane.getText().toString().equals("")) {
                    if(lang.getText().toString().equals("ILOŚĆ")) {
                        Toast.makeText(getApplicationContext(), "Podaj swoje dane żeby wysłać SMS! (menu -> Dane)", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Enter your details (or name) to send SMS! (menu -> Information)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    cena();
                    sendSMS();
                    if(lang.getText().toString().equals("ILOŚĆ")) {
                        Toast.makeText(MainActivity.this, "Wysłano wiadomość SMS", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "An SMS has been sent", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.item3:
                Toast.makeText(getApplicationContext(), "WIP", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item4:
                Intent intent1 = new Intent(MainActivity.this, Dane.class);
                startActivity(intent1);
                break;

            case R.id.item5:
                if(lang.getText().equals("ILOŚĆ")) {
                    setLocal(MainActivity.this, "en");
                    finish();
                    startActivity(getIntent());
                }else{
                    setLocal(MainActivity.this, "pl");
                    finish();
                    startActivity(getIntent());
                }
                break;

            case R.id.item6:
                Intent intent2 = new Intent(MainActivity.this, About.class);
                startActivity(intent2);
                break;

            case R.id.item7:
                Toast.makeText(getApplicationContext(), "WIP", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}