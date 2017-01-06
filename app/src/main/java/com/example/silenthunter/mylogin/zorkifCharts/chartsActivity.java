package com.example.silenthunter.mylogin.zorkifCharts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import static com.example.silenthunter.mylogin.AppConfig.URL_JOB_STATUS;

/**
 * Created by SilentHunter on 1/7/2017.
 */

public class chartsActivity extends Fragment  {

    private SQLiteHandler db;
    private SessionManager session;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        callvolley(URL_JOB_STATUS);


        return inflater.inflate(R.layout.fragment_frg_charts, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Statistics one");
    }


    public void callvolley(String URLL) {
        db = new SQLiteHandler(getActivity().getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());


        //changs for volley
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());

//        String url= "http://www.flightradar2?4.com/AirportInfoService.php?airport=ORY&type=in";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d("statistics", "statisticsFirst Response: " + response.toString());

                        try {

                            JSONObject o = new JSONObject(response);
                            JSONArray values = o.getJSONArray("flights");

                            for (int i = 0; i < values.length(); i++) {

                                JSONObject sonuc = values.getJSONObject(i);

//                                sb.append("Flight: " + sonuc.getString("flight") + "\n");
//                                sb.append("name: " + sonuc.getString("name") + "\n\n");

                            }

//                            txt.setText(sb.toString());


                        } catch (JSONException ex) {
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
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
        rq.add(stringRequest);
        //volley changes ends


}
}


