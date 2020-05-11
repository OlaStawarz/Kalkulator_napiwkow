package com.example.kalkulatornapiwkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText kwota;
    private SeekBar seekbar;
    private TextView ocena;
    private TextView wartoscProponowanyNapiwek;
    private TextView wartoscNapiwek;
    private TextView wartoscZamówienie;
    private Button btnWyczysc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kwota = (EditText) findViewById(R.id.editKwota);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        ocena = (TextView) findViewById(R.id.textOcena);
        wartoscProponowanyNapiwek = (TextView) findViewById(R.id.wartośćProponowanyNapiwek);
        wartoscNapiwek = (TextView) findViewById(R.id.wartośćNapiwek);
        wartoscZamówienie = (TextView) findViewById(R.id.wartośćZamówienie);
        btnWyczysc = (Button) findViewById(R.id.buttonWyczysc);

        wartoscProponowanyNapiwek.setVisibility(View.INVISIBLE);
        wartoscNapiwek.setVisibility(View.INVISIBLE);
        wartoscZamówienie.setVisibility(View.INVISIBLE);



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                 if (TextUtils.isEmpty(kwota.getText())){
                    kwota.setError("To pole nie może być puste");
                    Log.i("WPISANA_KWOTA", "Brak kwoty");
                    return;
                }

                 Log.i("WPISANA_KWOTA", "Wpisanie kwoty");

                wartoscProponowanyNapiwek.setVisibility(View.VISIBLE);
                seekbar.setMax(10);
                ocena.setText("" + progress + "/10");
                Log.i("WARTOSC_NAPIWKU", "Wypisanie wartości");
                wartoscProponowanyNapiwek.setText("" + progress + "%");
                Log.i("WARTOSC_NAPIWKU", "Ustalenie wartosci napiwku na " + String.valueOf(progress));

                calculate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnMethod();


    }

    private void calculate(int napiwek){

        Log.i("CALCULATE", "Obliczanie wartości");

        double valNapiwek = Double.parseDouble(kwota.getText().toString()) * napiwek * 0.01;
        valNapiwek *= 100;
        valNapiwek = Math.round(valNapiwek);
        valNapiwek /= 100;
        String strNapiwek = String.valueOf(valNapiwek);

        double valZamowienie = valNapiwek + Double.parseDouble(kwota.getText().toString());
        valZamowienie *= 100;
        valZamowienie = Math.round(valZamowienie);
        valZamowienie /= 100;
        String strZamowienie = String.valueOf(valZamowienie);

        wartoscNapiwek.setText(strNapiwek);
        wartoscZamówienie.setText(strZamowienie);
        wartoscNapiwek.setVisibility(View.VISIBLE);
        wartoscZamówienie.setVisibility(View.VISIBLE);
        Log.i("PRZELICZONE WARTOSCI", "Napiwek: " + String.valueOf(strNapiwek) + ", zamówienie: " + String.valueOf(strZamowienie));

    }

    private void btnMethod(){
        btnWyczysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PRZYCISK_WYCZYSC", "Resetowanie wartości");
                kwota.getText().clear();
                wartoscNapiwek.setText("");
                wartoscZamówienie.setText("");
                wartoscProponowanyNapiwek.setText("");
                ocena.setText("");
            }
        });
    }


}
