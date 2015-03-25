package com.mit.bruno.tripstuffreminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mit.bruno.tripstuffreminder.R;
import com.mit.bruno.tripstuffreminder.classes.Trip;
import com.mit.bruno.tripstuffreminder.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by techresult on 24/03/2015.
 */
public class TripAdapter extends ArrayAdapter<Trip> {

    private Context context;
    private ArrayList<Trip> values;

    public TripAdapter(Context context, ArrayList<Trip> values) {
        super(context, R.layout.trip_list_item_green, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        DateUtils dateUtils = new DateUtils();
        Trip trip = values.get(position);

        String currentDate = dateUtils.convertCurrentTimeToDate();
        String tripDate = trip.getDepartureDate();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;

        if(dateUtils.isBefore(currentDate, tripDate)){
            rowView = inflater.inflate(R.layout.trip_list_item_green, parent, false);
        }
        else{
            rowView = inflater.inflate(R.layout.trip_list_item_red, parent, false);
        }

        TextView textView = (TextView) rowView.findViewById(R.id.place_title);
        textView.setText(trip.getCity());

        return rowView;
    }

}
