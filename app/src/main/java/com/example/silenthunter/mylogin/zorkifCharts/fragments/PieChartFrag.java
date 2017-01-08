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
import android.view.MotionEvent;
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
import com.example.silenthunter.mylogin.zorkifCharts.custom.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;
import static com.example.silenthunter.mylogin.AppConfig.URL_JOB_BAR_CHARTS;
import static com.example.silenthunter.mylogin.AppConfig.URL_JOB_STATUS;

//import com.xxmassdeveloper.mpchartexample.R;

public class PieChartFrag extends SimpleFragment implements OnChartGestureListener {

    public PieChart mChart;
    public ArrayList<PieEntry> entriestwo = new ArrayList<PieEntry>();
    private BarChart bChart;
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
        mChart.setCenterTextSize(7f);//10f
        mChart.setCenterTextTypeface(tf);
        mChart.setBackgroundColor(WHITE);
        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(20f);//45
        mChart.setTransparentCircleRadius(30f);//50

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        callvolley(URL_JOB_STATUS);
//        callvolleybar(URL_JOB_BAR_CHARTS);
        mChart.animateX(1500);

        // create a new chart object
        bChart = (BarChart) v.findViewById(R.id.pieChart2);
//        bChart = new BarChart(getActivity());
//        bChart = (BarChart) v.findViewById(R.id.pieChart2);
        bChart.getDescription().setEnabled(false);
//        bChart.setOnChartGestureListener(this);

        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(bChart); // For bounds control
        bChart.setMarker(mv);

        bChart.setDrawGridBackground(false);
        bChart.setDrawBarShadow(false);

//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"OpenSans-Light.ttf");

//        bChart.setData(generateBarData(1, 20000, 10));
        callvolleybar(URL_JOB_BAR_CHARTS);
        Legend lb = bChart.getLegend();
        lb.setTypeface(tf);

        YAxis leftAxis = bChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        bChart.getAxisRight().setEnabled(false);

        XAxis xAxis = bChart.getXAxis();
        xAxis.setEnabled(false);

        // programatically add the chart
        bChart.animateX(1000);




//        mChart.startLayoutAnimation();

//        nChart=   (PieChart) v.findViewById(R.id.pieChart2);
//
//        nChart.getDescription().setEnabled(false);
//
//
//        nChart.setCenterTextTypeface(tf);
//        nChart.setCenterText(generateCenterText());
//        nChart.setCenterTextSize(7f);//10f
//        nChart.setCenterTextTypeface(tf);
//        nChart.setBackgroundColor(WHITE);
//        // radius of the center hole in percent of maximum radius
//        nChart.setHoleRadius(20f);//45
//        nChart.setTransparentCircleRadius(30f);//50
//
//        Legend l2 = nChart.getLegend();
//        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l2.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l2.setDrawInside(false);
//        nChart.animateX(1500);
//        nChart.setData(generatePieDataone());
//        mChart.startLayoutAnimation();







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
//          PieData dd =new PieData ();

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
                                rjsonobj = rjarry.getJSONObject(x);
                                int valstatus = Integer.parseInt(rjsonobj.getString("NoOfStatus"));
                                entriesjason.add(new PieEntry(valstatus, rjsonobj.getString("Status")));
//                                mChart.setData(generatePieDataone(entriestwo));

                            }

                            PieDataSet ds1 = new PieDataSet(entriesjason, "Zorkif One 2016");
                            ds1.setColors(ColorTemplate.JOYFUL_COLORS);
                            ds1.setSliceSpace(2f);
                            ds1.setValueTextColor(WHITE);
                            ds1.setValueTextSize(12f);

                            PieData d = new PieData(ds1);
                            d.setValueTypeface(tf);
//                            pieresponse = d;
//
                            mChart.setData(d);
//                            mChart.setHoleColor(GREEN);

//                            mChart.animate();
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
//second volley call starts
    public void callvolleybar(String URLL) {

        db = new SQLiteHandler(getActivity().getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());
//          PieData dd =new PieData ();

        //changs for volley
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());

//        String url= "http://www.flightradar2?4.com/AirportInfoService.php?airport=ORY&type=in";

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URLL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d("statistics", "statisticsFirst Response for bar: " + response.toString());


                        try {
                            String a = response.toString();
//                            ArrayList<PieEntry> entriesjason = new ArrayList<PieEntry>();
                            JSONArray rjarry = new JSONArray(a);
//                            countjasonobj = rjarry.length();
                            JSONObject rjsonobj = rjarry.getJSONObject(0);

                            int vSurvey = Integer.parseInt(rjsonobj.getString("Survey"));
                            int vMaterials = Integer.parseInt(rjsonobj.getString("Materials"));
                            int vPermissions = Integer.parseInt(rjsonobj.getString("Permissions"));
                            int vCoordination = Integer.parseInt(rjsonobj.getString("Coordination"));
                            int vPermits = Integer.parseInt(rjsonobj.getString("Permits"));
                            int vPlanning = Integer.parseInt(rjsonobj.getString("Planning"));
                            int vJobProgress = Integer.parseInt(rjsonobj.getString("JobProgress"));
                            int vQualityControl = Integer.parseInt(rjsonobj.getString("QualityControl"));
                            int vPermitClearance = Integer.parseInt(rjsonobj.getString("PermitClearance"));
//                                mChart.setData(generatePieDataone(entriestwo));


                            ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

                            for (int i = 0; i < 1; i++) {

                                ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");
                                BarDataSet ds;

                                entries.add(new BarEntry(0, vSurvey));
                                ds = new BarDataSet(entries, "Survey");
                                ds.setColor(BLUE);

                                sets.add(ds);
                                entries.add(new BarEntry(1, vPermissions));
                                ds = new BarDataSet(entries, "Permissions");
                                ds.setColor(RED);
                                sets.add(ds);
                                entries.add(new BarEntry(2, vMaterials));
                                ds = new BarDataSet(entries, "Materials");
                                ds.setColor(YELLOW);
                                sets.add(ds);
                                entries.add(new BarEntry(3, vCoordination));
                                ds = new BarDataSet(entries, "Coordination");
                                ds.setColor(GREEN);
                                sets.add(ds);
                                entries.add(new BarEntry(4, vPermits));
                                ds = new BarDataSet(entries, "Permits");
                                ds.setColor(GRAY);
                                sets.add(ds);
                                entries.add(new BarEntry(5, vPlanning));
                                ds = new BarDataSet(entries, "Planning");
                                ds.setColor(BLACK);
                                sets.add(ds);
                                entries.add(new BarEntry(6, vJobProgress));
                                ds = new BarDataSet(entries, "JobProgress");
                                ds.setColor(MAGENTA);
                                sets.add(ds);
                                entries.add(new BarEntry(7, vQualityControl));
                                ds = new BarDataSet(entries, "QualityControl");
                                ds.setColor(DKGRAY);
                                sets.add(ds);
                                entries.add(new BarEntry(8, vPermitClearance));
                                ds = new BarDataSet(entries, "PermitClearance");
                                ds.setColor(LTGRAY);
                                sets.add(ds);
                                ds.setColors(ColorTemplate.COLORFUL_COLORS);


//                ds.setColors(ColorTemplate.COLORFUL_COLORS);

//            ds = new BarDataSet(entries, getLabel(i));
//            BarDataSet ds = new BarDataSet(entries, getLabel(i));

//            sets.add(ds);
                            }

                            BarData d = new BarData(sets);
                            d.setValueTypeface(tf);
                            bChart.setData(d);
                            bChart.animateX(1500);
                            Log.i("asad", "end of bar chart functions");
//                            PieDataSet ds1 = new PieDataSet(entriesjason, "Zorkif One 2016");
//                            ds1.setColors(ColorTemplate.JOYFUL_COLORS);
//                            ds1.setSliceSpace(2f);
//                            ds1.setValueTextColor(WHITE);
//                            ds1.setValueTextSize(12f);
//
//                            PieData d = new PieData(ds1);
//                            d.setValueTypeface(tf);
////                            pieresponse = d;
////
//                            mChart.setData(d);
//                            mChart.setHoleColor(GREEN);

//                            mChart.animate();
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
                        Log.d("statistics bar ", "error handling ");
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
//second volley call ends


    protected PieData generatePieDataone() {

//        callvolley(URL_JOB_STATUS);

        int count = 4;

        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            entries1.add(new PieEntry((float) ((Math.random() * 60) + 40), "Quarter " + (i + 1)));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);


        return d;
    }


    //for bar chart
    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END");
        mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    //end of bar chart

}
