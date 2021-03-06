package com.mit.bruno.tripstuffreminder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.mit.bruno.tripstuffreminder.fragments.FragmentConfiguration;
import com.mit.bruno.tripstuffreminder.fragments.FragmentHelp;
import com.mit.bruno.tripstuffreminder.fragments.FragmentHome;
import com.mit.bruno.tripstuffreminder.utils.NavigationDrawerFragment;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loads the home fragment
        Fragment fragment = new FragmentHome();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    protected void onResume(){
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()){
            case R.id.action_settings:
                //Shows a screen to configure the color of the app
                return true;
            case R.id.action_search:
                //Shows a screen to search a Trip
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                //Home fragment
                fragment = new FragmentHome();
                break;
            case 1:
                //Configuration fragment
                fragment = new FragmentConfiguration();
                break;
            case 2:
                //Help fragment
                fragment = new FragmentHelp();
                break;
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
