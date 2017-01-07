package com.example.silenthunter.mylogin.zorkifCharts.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.silenthunter.mylogin.AppConfig.URL_JOB_STATUS;

//import com.xxmassdeveloper.mpchartexample.R;

public class PieChartFrag extends SimpleFragment {

    public static PieChart mChart;
    public ArrayList<PieEntry> entriestwo = new ArrayList<PieEntry>();
    protected PieData pieresponse;
    private Typeface tf;
    private SQLiteHandler db;
    private SessionManager session;
    private int countjasonobj;
    private String jason;

    public static Fragment newInstance() {
        return new PieChartFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        callvolley(URL_JOB_STATUS);
        View v = inflater.inflate(R.layout.frag_simple_pie, container, false);
        
        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        
        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(tf);
         
        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
//        callvolley(URL_JOB_STATUS);


        callvolley(URL_JOB_STATUS);
//        mChart.setData(generatePieDataone(entriestwo));


        return v;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Zorkif One");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    //volley changes start
    public void callvolley(String URLL) {

        db = new SQLiteHandler(getActivity().getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());


        //changs for volley
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());

//        String url= "http://www.flightradar2?4.com/AirportInfoService.php?airport=ORY&type=in";

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URLL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d("statistics", "statisticsFirst Response: " + response.toString());


                        try {
                            String a = response.toString();
                            ArrayList<PieEntry> entriesjason = new ArrayList<PieEntry>();
                            JSONArray rjarry = new JSONArray(a);
                            countjasonobj = rjarry.length();
                            JSONObject rjsonobj;
                            for (int x = 0; x < countjasonobj; x++) {
                                rjsonobj = rjarry.getJSONObject(0);
                                int valstatus = Integer.parseInt(rjsonobj.getString("NoOfStatus"));
                                entriesjason.add(new PieEntry(valstatus, rjsonobj.getString("Status")));


                            }
                            PieDataSet ds1 = new PieDataSet(entriesjason, "Zorkif One 2016");
                            ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
                            ds1.setSliceSpace(2f);
                            ds1.setValueTextColor(Color.WHITE);
                            ds1.setValueTextSize(12f);

                            PieData d = new PieData(ds1);
                            d.setValueTypeface(tf);
                            pieresponse = d;
                            mChart.setData(d);
                            mChart.animate();
//                            JSONObject c=b.getJSONObject(0);
//                            String d= c.getString("Status");
//                            Log.d("asad", d);
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
                        Log.d("statistics", "error handling ");
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
    //volley changes ends

    protected PieData generatePieDataone(ArrayList<PieEntry> entries1) {

//        int count = 4;

//        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();

//        for(int i = 0; i < count; i++) {
//            entries1.add(new PieEntry(NoOfStatus, status));
//        }

        PieDataSet ds1 = new PieDataSet(entries1, "Zorkif One 2016");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }




}
