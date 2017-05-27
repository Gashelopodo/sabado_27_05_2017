package com.gashe.myservicedream;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class AjustesActivity extends AppCompatActivity {

    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        String category = sp.getString("category", "animals");

        switch (category){
            case "animals":
                radioButton = (RadioButton) findViewById(R.id.animals);
                radioButton.setChecked(true);
                break;
            case "cadizcf":
                radioButton = (RadioButton) findViewById(R.id.cadizcf);
                radioButton.setChecked(true);
                break;
            case "foods":
                radioButton = (RadioButton) findViewById(R.id.foods);
                radioButton.setChecked(true);
                break;
        }

    }



    public void buttonSelected(View view){
        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        log("BOTÓN SELECCIONADO");
        int id = view.getId();
        switch (id){
            case R.id.animals:
                log("ANIMALES");
                editor.putString("category", "animals");
                break;
            case R.id.cadizcf:
                log("CADIZCF");
                editor.putString("category", "cadizcf");
                break;
            case R.id.foods:
                log("COMIDAS");
                editor.putString("category", "foods");
                break;
        }

        editor.commit();
        finish();

    }

    public void log(String s){
        Log.d(getClass().getCanonicalName(), s);
    }

}
