package com.example.silenthunter.mylogin.zorkifCharts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.silenthunter.mylogin.R;
import com.example.silenthunter.mylogin.SQLiteHandler;
import com.example.silenthunter.mylogin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.silenthunter.mylogin.AppConfig.URL_NOTIFICATION;

public class BasicNotificationActivity extends AppCompatActivity {

    private SQLiteHandler db;
    private SessionManager session;
    private int countjasonobj;
    private String jason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        callvolleybar(URL_NOTIFICATION);
    }


    //second volley call starts
    public void callvolleybar(String URLL) {

        db = new SQLiteHandler(getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();
        // session manager
        session = new SessionManager(getApplicationContext());
//          PieData dd =new PieData ();

        //changs for volley
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

//        String url= "http://www.flightradar2?4.com/AirportInfoService.php?airport=ORY&type=in";

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URLL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d("Basic Notificatin Unit", " Response for bar: " + response.toString());


                        try {
                            String a = response.toString();
//                            ArrayList<PieEntry> entriesjason = new ArrayList<PieEntry>();
                            JSONArray rjarry = new JSONArray(a);
                            countjasonobj = rjarry.length();
                            Log.i("basicNotifacation", String.valueOf(countjasonobj));

                            for (int i = 0; i < countjasonobj; i++) {
                                JSONObject rjsonobj = rjarry.getJSONObject(i);
                                int vNotifID = Integer.parseInt(rjsonobj.getString("UserNotificationID"));
                                int vNotifStatusID = Integer.parseInt(rjsonobj.getString("NotificationStatusID"));
                                String vNotifTitle = rjsonobj.getString("NotificationTitle");
                                String vNotifDesc = rjsonobj.getString("Description");
                                String vNotifIcon = rjsonobj.getString("NotificationStatusIcon");
                                String vNotifDare = rjsonobj.getString("Dated");
                                String vNotifUserData = rjsonobj.getString("UserData");
                                Log.i("basicNotifacation", String.valueOf(vNotifID));
                                Log.i("basicNotifacation", String.valueOf(vNotifStatusID));
                                Log.i("basicNotifacation", vNotifTitle);
                                Log.i("basicNotifacation", vNotifDesc);
                                Log.i("basicNotifacation", vNotifIcon);
                                Log.i("basicNotifacation", vNotifDare);
                                Log.i("basicNotifacation", vNotifUserData);
                            }

//                            int vMaterials = Integer.parseInt(rjsonobj.getString("Materials"));
//                            int vPermissions = Integer.parseInt(rjsonobj.getString("Permissions"));
//                            int vCoordination = Integer.parseInt(rjsonobj.getString("Coordination"));
//                            int vPermits = Integer.parseInt(rjsonobj.getString("Permits"));
//                            int vPlanning = Integer.parseInt(rjsonobj.getString("Planning"));
//                            int vJobProgress = Integer.parseInt(rjsonobj.getString("JobProgress"));
//                            int vQualityControl = Integer.parseInt(rjsonobj.getString("QualityControl"));
//                            int vPermitClearance = Integer.parseInt(rjsonobj.getString("PermitClearance"));
//                                mChart.setData(generatePieDataone(entriestwo));


                        } catch (JSONException e) {
                            Log.d("statistics", "error ");
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.d("notifcaiton", "error handling ");
                    }
                }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String sessionid = user.get("uidd");
                headers.put("Cookie", "PHPSESSID=" + sessionid);
                return headers;
            }
        };
        rq.add(jsObjRequest);
        //volley changes ends

    }
}
