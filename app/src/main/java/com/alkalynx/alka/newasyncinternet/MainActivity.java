package com.alkalynx.alka.newasyncinternet;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternetTask c1;
    static TextView myText, inputan;
    private Spinner spinnerx;
    String protocol;

    ConnectivityManager myConnManager;
    NetworkInfo myInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView) findViewById(R.id.myResult);
        inputan= (TextView) findViewById(R.id.inputan);

        //mmebuat tempat kosong dari protocol
        protocol = "";

        //Dapatkan value di Spinner
        spinnerx=(Spinner)findViewById(R.id.spinnr);
        spinnerx.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        //Cek koneksi
        myConnManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        myInfo = myConnManager.getActiveNetworkInfo();

        if(myInfo != null && myInfo.isConnected()){
            Toast.makeText(this, "Terkoneksi", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
        }

    }

    public void doSomething(View view) {
            myText.setText("");
            c1 = new ConnectInternetTask(this);
            c1.execute(protocol.toString() + inputan.getText().toString());
            Toast.makeText(this, protocol.toString() + inputan.getText().toString(), Toast.LENGTH_SHORT).show();
    }



    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinnerx.getSelectedItem());
        //Memilih spiner sesuai posisi
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinnerx.getSelectedItem()))) {
                //memasukan spiner yang di klik ke dalam variabel protocol
                protocol = parent.getItemAtPosition(pos).toString();
            }else{
                //memasukan spiner yang di klik ke dalam variabel protocol
                protocol = parent.getItemAtPosition(pos).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }




}
