package com.shubham.mypersistancestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editAddress;
    private EditText editNumber;
    private EditText editEmail;

    private RadioGroup radioFavouritePartOfDay;
    private RadioButton btnMorning;
    private RadioButton btnAfternoon;
    private RadioButton btnEvening;
    private RadioButton btnNight;

//    All Caps because it is final
    public static final String MYPREFS = "mySharedPreferences";
    private  String favouritePartOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAdress);
        editNumber = findViewById(R.id.editNumber);
        editEmail = findViewById(R.id.editEmail);

        radioFavouritePartOfDay = findViewById(R.id.radioFavouritePartOfDay);
        btnMorning = findViewById(R.id.btnMorning);
        btnAfternoon = findViewById(R.id.btnAfternoon);
        btnEvening=findViewById(R.id.btnEvening);
        btnNight = findViewById(R.id.btnNight);


        this.loadPreferenses();

    }
    public void loadPreferenses(){
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences muSharedPreferenses = getSharedPreferences(MYPREFS, mode);
        editName.setText(muSharedPreferenses.getString("name", ""));
        editAddress.setText(muSharedPreferenses.getString("address", ""));
        editNumber.setText(muSharedPreferenses.getString("number", ""));
        editEmail.setText(muSharedPreferenses.getString("email", ""));

        favouritePartOfDay = muSharedPreferenses.getString("favouritePartOfDay", "m");

        loadradioButtonPreferenses();
    }
    public void loadradioButtonPreferenses(){
        if (favouritePartOfDay.equals("m")){
            radioFavouritePartOfDay.check(R.id.btnMorning);
        }
        else if (favouritePartOfDay.equals("a")){
            radioFavouritePartOfDay.check(R.id.btnAfternoon);

        }
        else if (favouritePartOfDay.equals("e")){
            radioFavouritePartOfDay.check(R.id.btnEvening);

        }
        else if (favouritePartOfDay.equals("n")){
            radioFavouritePartOfDay.check(R.id.btnNight);

        }
        else{
            radioFavouritePartOfDay.check(R.id.btnMorning);
        }
    }
    public void onClick(View view){
        if (btnMorning.isChecked()){
            favouritePartOfDay = "m";
        }
        else if (btnAfternoon.isChecked()){
            favouritePartOfDay = "a";
        }
        else if (btnEvening.isChecked()){
            favouritePartOfDay = "e";
        }
        else if (btnNight.isChecked()){
            favouritePartOfDay = "n";
        }
        else{
            favouritePartOfDay = "";
        }
    }
    protected void savePreferenses(){
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferenses = getSharedPreferences(MYPREFS, mode);
        android.content.SharedPreferences.Editor editor = mySharedPreferenses.edit();
        editor.putString("name", editName.getText().toString());
        editor.putString("address", editAddress.getText().toString());
        editor.putString("number", editNumber.getText().toString());
        editor.putString("email", editEmail.getText().toString());
        editor.putString("favouritePartOdDay", favouritePartOfDay);
        editor.commit();



    }
    protected void onStop(){
        super.onStop();
        this.savePreferenses();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate(fill) the menu, this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_shared_preferenses_main, menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item click here.The action bar will
        // automatically handle clicks on Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        //no inspection simplifiableIfStatement
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}