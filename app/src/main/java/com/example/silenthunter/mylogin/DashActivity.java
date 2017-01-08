package com.example.silenthunter.mylogin;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silenthunter.mylogin.zorkifCharts.fragments.PieChartFrag;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class DashActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;
    private SQLiteHandler db;
    private SessionManager session;
    private FirebaseAuth mAuthD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        db = new SQLiteHandler(getApplicationContext());
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }//btn_login

//        String dashtitle = user.get("name").toString();
//        Log.i("asad here",dashtitle);
//        Log.i("asad here",dashtitle);
//        Log.i("asad here",dashtitle);
//        Log.i("asad here",dashtitle);
//        Log.i("asad here",dashtitle);
//        Log.i("asad here",dashtitle);




//        this.setTitle(dashtitle);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//this is comment
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView txtnavView = (TextView)findViewById(R.id.txtnavemail);
        String navtxt = user.get("username");
//        String navtxt = "later";
        View v = navigationView.getHeaderView(0);
        Log.i("asad here",navtxt);
        TextView avatarContainer = (TextView ) v.findViewById(R.id.txtnavemail);
        avatarContainer.setText(navtxt);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_logout) {
            logoutUser();
            FirebaseAuth.getInstance().signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_stats) {
//            fragment = new chartsActivity();
            fragment = new PieChartFrag();
            // Handle the camera action
//            Intent intent = new Intent(DashActivity.this, statisticsActivity.class);
//            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
//            Fragment fragmentthis = new Fragment();
//             getFragmentManager().findFragmentById(R.id.pieChart1);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragment);
//                    detach(fragment);

            ft.commit();
            Log.i("asad", "pi chart closing fragment");
            Toast.makeText(getApplicationContext(),
                    "Under construction!", Toast.LENGTH_LONG)
                    .show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();
         FirebaseAuth.getInstance().signOut();
        // Launching the login activity
        Intent intent = new Intent(DashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public String getsessinid() {
        String sid;
        db = new SQLiteHandler(getApplicationContext());
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        sid = user.get("username");


        return sid;
    }

}
