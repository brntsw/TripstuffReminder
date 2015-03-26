package com.mit.bruno.tripstuffreminder;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mit.bruno.tripstuffreminder.interfaces.IComponents;

import java.util.ArrayList;

/**
 * Created by techresult on 19/02/2015.
 */
public class AddTripActivity extends Activity implements IComponents {
    private Spinner spinnerCountry;
    private Spinner spinnerTransportation;
    private Spinner spinnerState;
    private Spinner spinnerCity;
    private RadioGroup radioKnowState;
    private EditText editThingsToBring;
    private EditText editThingsToDo;
    private ImageButton btnAddBring;
    private ImageButton btnAddToDo;
    private Button btnSave;

    private int linearLayoutBringId = 100;
    private int linearLayoutToDoId = 500;
    private int editTextBringId = 1000;
    private int editTextToDoId = 1500;
    private int btnBringId = 2000;
    private int btnToDoId = 2500;

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

        editThingsToBring.setId(R.id.editTextBringId);
        editThingsToDo.setId(R.id.editTextToDoId);
        btnAddBring.setId(R.id.btnBringId);
        btnAddToDo.setId(R.id.btnToDoId);
    }

    protected void onResume(){
        super.onResume();

        //Spinner Country data
        ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapterCountry);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //GET THE STATES FROM THE SELECTED COUNTRY
                Log.d("Selected country", spinnerCountry.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //RadioGroup - know the state
        radioKnowState.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LinearLayout linearState = (LinearLayout) findViewById(R.id.linearState);

                if (checkedId == R.id.radioYes) {
                    linearState.setVisibility(View.VISIBLE);
                } else {
                    //GET ALL THE CITIES FROM THE COUNTRY (WEBSERVICE)
                    //SET THE CITIES TO THE CITIES SPINNER
                    linearState.setVisibility(View.GONE);
                }
            }
        });

        //Spinner State
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //GET THE CITIES FROM THE SELECTED STATE
                //SET THE CITIES TO THE CITIES SPINNER
                Log.d("Selected state", spinnerState.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner City
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Selected city", spinnerCity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Transportation data
        ArrayList<String> transport = new ArrayList<String>();
        transport.add("Select");
        transport.add("Airplane");
        transport.add("Bus");
        transport.add("Vehicle");

        ArrayAdapter<String> adapterTransportation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transport);
        spinnerTransportation.setAdapter(adapterTransportation);

        //SPINNER TRANSPORTATION - On Item select
        spinnerTransportation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //ACCORDING TO THE SELECTED TRANSPORTATION, A SPECIFIC ACTIVITY IS EXECUTED
                /*
                * VEHICLE: if the vehicle is selected (it can be a car, motorcycle, etc.), the system will give orientations before the trip like reminding
                * to make a full check up, to put gas 1 day before the trip, etc.
                *
                * AIRPLANE: if the airplane is selected, the system will give orientations before the trip like reminding to get the passport and to get
                * the visa if it is an international trip and give a link to see how to request the visa.
                *
                * BUS: if the bus is selected, the system will give orientations before the trip like reminding to get the bus ticket and some things to
                * bring on the bus.
                *
                * */
                Log.d("Selected transportation", spinnerTransportation.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Button Add Things to bring
        btnAddBring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutThings = (LinearLayout) findViewById(R.id.layoutThings);

                if(layoutThings.getChildCount() == 1){
                    LinearLayout layoutWrap = (LinearLayout) layoutThings.getChildAt(0);

                    ImageButton btnAdd = (ImageButton)layoutWrap.getChildAt(1);
                    changeImageButtonBackgroundToDelete(btnAdd);

                    LinearLayout newLayoutWrap = createLinearLayout(layoutWrap.getId());
                    newLayoutWrap.setId(linearLayoutBringId++);

                    EditText editTextThing = createEditText();
                    editTextThing.setId(editTextBringId++);

                    ImageButton btnBring = createImageButton();
                    btnBring.setId(btnBringId++);

                    //Add the new EditText and ImageButton to the newLayoutWrap
                    newLayoutWrap.addView(editTextThing);
                    newLayoutWrap.addView(btnBring);

                    //Add the newLayoutWrap below the layoutWrap
                    layoutWrap.addView(newLayoutWrap);
                }
                else{
                    if(layoutThings.getChildCount() > 1){

                    }
                }
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

    public LinearLayout createLinearLayout(int id){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(AddTripActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);

        return linearLayout;
    }

    public EditText createEditText(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.topMargin = 10;
        EditText editText = new EditText(AddTripActivity.this);
        editText.setPadding(10, 0, 10, 0);
        editText.setLayoutParams(params);

        return editText;
    }

    public ImageButton createImageButton(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageButton imageButton = new ImageButton(AddTripActivity.this);
        imageButton.setImageResource(android.R.drawable.ic_input_add);
        imageButton.setLayoutParams(params);

        return imageButton;
    }

    public void changeImageButtonBackgroundToDelete(ImageButton imageButton){
        imageButton.setImageResource(android.R.drawable.ic_delete);
    }

    @Override
    public void initializeComponents() {
        spinnerCountry = (Spinner) findViewById(R.id.spCountry);
        spinnerTransportation = (Spinner) findViewById(R.id.spTransportation);
        spinnerState = (Spinner) findViewById(R.id.spState);
        spinnerCity = (Spinner) findViewById(R.id.spCity);
        radioKnowState = (RadioGroup) findViewById(R.id.radioKnowState);
        editThingsToBring = (EditText) findViewById(R.id.etThing);
        editThingsToDo = (EditText) findViewById(R.id.etThingToDo);
        btnAddBring = (ImageButton) findViewById(R.id.btnAddBring);
        btnAddToDo = (ImageButton) findViewById(R.id.btnAddToDo);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}
