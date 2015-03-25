package com.mit.bruno.tripstuffreminder.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mit.bruno.tripstuffreminder.AddTripActivity;
import com.mit.bruno.tripstuffreminder.R;
import com.mit.bruno.tripstuffreminder.adapter.TripAdapter;
import com.mit.bruno.tripstuffreminder.classes.Trip;
import com.mit.bruno.tripstuffreminder.interfaces.IComponents;
import com.mit.bruno.tripstuffreminder.sqlite.SQLiteHelper;
import com.mit.bruno.tripstuffreminder.sqlite.TripSqlite;
import com.mit.bruno.tripstuffreminder.utils.CustomFab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by techresult on 24/03/2015.
 */
public class FragmentHome extends Fragment implements IComponents {
    View rootView;

    private TripSqlite tripSqlite;
    private ListView listTrips;
    private CustomFab customFab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.home, container, false);

        initializeComponents();

        tripSqlite.open();

        registerForContextMenu(listTrips);

        return rootView;
    }

    public void onResume(){
        super.onResume();

        Trip trip1 = new Trip();
        trip1.setCity("Miami");
        trip1.setState("California");
        trip1.setCountry("United States");
        trip1.setTransportation("Airplane");
        trip1.setDepartureDate("15/10/2015");
        trip1.setArrivalDate("16/10/2015");
        trip1.setDepartureTime("02:00");
        trip1.setArrivalTime("11:00");

        Trip trip2 = new Trip();
        trip2.setCity("Boston");
        trip2.setState("Massachusetts");
        trip2.setCountry("United States");
        trip2.setTransportation("Airplane");
        trip2.setDepartureDate("11/06/2015");
        trip2.setArrivalDate("16/10/2015");
        trip2.setDepartureTime("01:00");
        trip2.setArrivalTime("10:00");

        Trip trip3 = new Trip();
        trip3.setCity("Ratingen");
        trip3.setState("Dusseldorf");
        trip3.setCountry("Germany");
        trip3.setTransportation("Airplane");
        trip3.setDepartureDate("11/02/2015");
        trip3.setArrivalDate("16/10/2015");
        trip3.setDepartureTime("21:00");
        trip3.setArrivalTime("09:00");

        ArrayList<Trip> trips = new ArrayList<Trip>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);

        TripAdapter adapter = new TripAdapter(getActivity(), trips);
        listTrips.setAdapter(adapter);

        listTrips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Access directly the edit screen with the clicked item data
                Toast.makeText(getActivity(), "Item click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        customFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTripActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();

        tripSqlite.close();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Edit")){
            //Calls the Edit screen
        }

        if(item.getTitle().equals("Delete")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Delete");
            builder.setMessage("The trip will be deleted. Are you sure?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void initializeComponents() {
        tripSqlite = new TripSqlite(getActivity());
        listTrips = (ListView) rootView.findViewById(R.id.listTrips);
        customFab = (CustomFab) rootView.findViewById(R.id.customFabAdd);
    }
}
