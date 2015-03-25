package com.mit.bruno.tripstuffreminder;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mit.bruno.tripstuffreminder.interfaces.IComponents;

import java.util.ArrayList;

/**
 * Created by techresult on 19/02/2015.
 */
public class AddTripActivity extends Activity implements IComponents {
    private Spinner spinnerCountry;
    private Spinner spinnerTransportation;
    private Button btnSave;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.layout_add_trip);

        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            if(getActionBar() != null){
                getActionBar().setHomeButtonEnabled(true);
            }
        }

        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initializeComponents();
    }

    protected void onResume(){
        super.onResume();

        //Spinner Country data
        ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapterCountry);

        //Spinner Transportation data
        ArrayList<String> transport = new ArrayList<String>();
        transport.add("Select");
        transport.add("Airplane");
        transport.add("Bus");
        transport.add("Vehicle");

        ArrayAdapter<String> adapterTransportation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transport);
        spinnerTransportation.setAdapter(adapterTransportation);

        spinnerTransportation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Selected transportation", spinnerTransportation.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the data on the SQLite database
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initializeComponents() {
        spinnerCountry = (Spinner) findViewById(R.id.spCountry);
        spinnerTransportation = (Spinner) findViewById(R.id.spTransportation);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}
