package de.ghse.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.ghse.smarthome.objects.*;

public class MainActivity extends AppCompatActivity {

    // Anfang Attribute
    private Spinner spinnerHouse;
    private Spinner spinnerRoom;
    private final List<House> arrayListHouse = new ArrayList<House>();
    private final List<String> arrayListSpinnerHouse = new ArrayList<String>();
    private final List<String> arrayListSpinnerRooms = new ArrayList<String>();
    private ArrayAdapter<String> adapterHouse;
    // Ende Attribute


    // Anfang Methoden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerHouse = (Spinner) findViewById(R.id.spinnerHouse);
        spinnerRoom  = (Spinner) findViewById(R.id.spinnerHouse);

        arrayListSpinnerHouse.add("~Hinzufügen~");
        arrayListSpinnerRooms.add("~Hinzufügen~");
        adapterHouse =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayListSpinnerHouse); //new String ersetzen mit List<String> der Häusernamen
        adapterHouse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHouse.setAdapter(adapterHouse);
        spinnerHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                if(position == 0){
                    Toast.makeText(MainActivity.this.getBaseContext(),"Eintrag hinzufügen",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    private void addSpinnerItem(){

    }
    // Ende Methoden
}
