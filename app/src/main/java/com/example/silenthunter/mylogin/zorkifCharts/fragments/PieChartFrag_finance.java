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
import static android.graphics.Color.YELLOW;
import static com.example.silenthunter.mylogin.AppConfig.URL_FINANCE_CHART_ONE;
import static com.example.silenthunter.mylogin.AppConfig.URL_FINANCE_INCOME_DETAILS;

//import com.xxmassdeveloper.mpchartexample.R;

public class PieChartFrag_finance extends SimpleFragment implements OnChartGestureListener {

    public PieChart mChart;
    public ArrayList<PieEntry> entriestwo = new ArrayList<PieEntry>();
    private BarChart bChart;
    private BarChart cChart;
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
        View v = inflater.inflate(R.layout.frag_simple_pie_finance, container, false);
        // create a new chart object
        cChart = (BarChart) v.findViewById(R.id.barChart_finance);
//        bChart = new BarChart(getActivity());
//        bChart = (BarChart) v.findViewById(R.id.pieChart2);
        cChart.getDescription().setEnabled(false);
//        bChart.setOnChartGestureListener(this);

        MyMarkerView cmv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        cmv.setChartView(bChart); // For bounds control
        cChart.setMarker(cmv);

        cChart.setDrawGridBackground(false);
        cChart.setDrawBarShadow(false);

//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"OpenSans-Light.ttf");

//        bChart.setData(generateBarData(1, 20000, 10));
        callvolley(URL_FINANCE_CHART_ONE);
        Legend clb = cChart.getLegend();
        clb.setTypeface(tf);

        YAxis leftAxis = cChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        cChart.getAxisRight().setEnabled(false);

        XAxis xAxis = cChart.getXAxis();
        xAxis.setEnabled(false);
        cChart.animateX(1000);


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");


        bChart = (BarChart) v.findViewById(R.id.barCharttwo_finance);
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
        callvolleybar(URL_FINANCE_INCOME_DETAILS);
        Legend lb = bChart.getLegend();
        lb.setTypeface(tf);

        YAxis bleftAxis = bChart.getAxisLeft();
        bleftAxis.setTypeface(tf);
        bleftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        bChart.getAxisRight().setEnabled(false);

        XAxis bxAxis = bChart.getXAxis();
        bxAxis.setEnabled(false);

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
                        Log.d("statistics", "statisticsFirst Response first: " + response.toString());


//                        try {
                        String a = response.toString();
                        int strlen = a.length();
                        int vJAN = 0;
                        int vFEB = 0;
                        int vMAR = 0;
                        int vAPR = 0;
                        int vMAY = 0;
                        int vJUN = 0;
                        int vJUL = 0;
                        int vAUG = 0;
                        int vSEP = 0;
                        int vOCT = 0;
                        int vNOV = 0;
                        int vDEC = 0;
                        if (a.length() == 25) {
                            Log.i("asad in loop", String.valueOf(a.length()));
                            Log.i("asad in loop", a.substring(1, 2));
                            Log.i("asad in loop", a.substring(2, 2));
                            Log.i("asad in loop", a.substring(2, 3));
                            Log.i("asad in loop", a.substring(3, 4));
                            Log.i("asad in loop", a.substring(5, 6));
                            Log.i("asad in loop", a.substring(7, 8));
                            Log.i("asad in loop", a.substring(9, 10));
                            Log.i("asad in loop", a.substring(11, 12));
                            Log.i("asad in loop", a.substring(13, 14));
                            Log.i("asad in loop", a.substring(15, 16));
                            Log.i("asad in loop", a.substring(17, 18));
                            Log.i("asad in loop", a.substring(19, 20));
                            Log.i("asad in loop", a.substring(21, 22));
                            Log.i("asad in loop", a.substring(23, 24));

                            vJAN = Integer.parseInt(a.substring(1, 2));
                            vFEB = Integer.parseInt(a.substring(3, 4));
                            vMAR = Integer.parseInt(a.substring(5, 6));
                            vAPR = Integer.parseInt(a.substring(7, 8));
                            vMAY = Integer.parseInt(a.substring(9, 10));
                            vJUN = Integer.parseInt(a.substring(11, 12));
                            vJUL = Integer.parseInt(a.substring(13, 14));
                            vAUG = Integer.parseInt(a.substring(15, 16));
                            vSEP = Integer.parseInt(a.substring(17, 18));
                            vOCT = Integer.parseInt(a.substring(19, 20));
                            vNOV = Integer.parseInt(a.substring(21, 22));
                            vDEC = Integer.parseInt(a.substring(23, 24));
//                                mChart.setData(generatePieDataone(entriestwo));
                        }

                        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

                        for (int i = 0; i < 1; i++) {

                            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");
                            BarDataSet ds;

                            entries.add(new BarEntry(0, vJAN));
                            ds = new BarDataSet(entries, "JAN");
                            ds.setColor(BLUE);

                            sets.add(ds);
                            entries.add(new BarEntry(1, vFEB));
                            ds = new BarDataSet(entries, "FEB");
                            ds.setColor(RED);
                            sets.add(ds);
                            entries.add(new BarEntry(2, vMAR));
                            ds = new BarDataSet(entries, "MAR");
                            ds.setColor(YELLOW);
                            sets.add(ds);
                            entries.add(new BarEntry(3, vAPR));
                            ds = new BarDataSet(entries, "APR");
                            ds.setColor(GREEN);
                            sets.add(ds);
                            entries.add(new BarEntry(4, vMAY));
                            ds = new BarDataSet(entries, "MAY");
                            ds.setColor(GRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(5, vJUN));
                            ds = new BarDataSet(entries, "JUN");
                            ds.setColor(BLACK);
                            sets.add(ds);
                            entries.add(new BarEntry(6, vJUL));
                            ds = new BarDataSet(entries, "JUL");
                            ds.setColor(MAGENTA);
                            sets.add(ds);
                            entries.add(new BarEntry(7, vAUG));
                            ds = new BarDataSet(entries, "AUG");
                            ds.setColor(DKGRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vSEP));
                            ds = new BarDataSet(entries, "SEP");
                            ds.setColor(LTGRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vOCT));
                            ds = new BarDataSet(entries, "OCT");
                            ds.setColor(GRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vNOV));
                            ds = new BarDataSet(entries, "NOV");
                            ds.setColor(LTGRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vDEC));
                            ds = new BarDataSet(entries, "DEC");
                            ds.setColor(MAGENTA);
                            sets.add(ds);
                            ds.setColors(ColorTemplate.COLORFUL_COLORS);



//                ds.setColors(ColorTemplate.COLORFUL_COLORS);

//            ds = new BarDataSet(entries, getLabel(i));
//            BarDataSet ds = new BarDataSet(entries, getLabel(i));

//            sets.add(ds);
                        }

                        BarData d = new BarData(sets);
                        d.setValueTypeface(tf);
                        cChart.setData(d);
                        cChart.animateX(1500);
                        Log.i("asad", "end of bar chart functions");


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


//                        try {
                        String a = response.toString();


                        int strlen = a.length();
                        int vJAN = 0;
                        int vFEB = 0;
                        int vMAR = 0;
                        int vAPR = 0;
                        int vMAY = 0;
                        int vJUN = 0;
                        int vJUL = 0;
                        int vAUG = 0;
                        int vSEP = 0;
                        int vOCT = 0;
                        int vNOV = 0;
                        int vDEC = 0;
                        if (a.length() == 25) {
                            Log.i("asad in loop", String.valueOf(a.length()));
                            Log.i("asad in loop", a.substring(1, 2));
                            Log.i("asad in loop", a.substring(2, 2));
                            Log.i("asad in loop", a.substring(2, 3));
                            Log.i("asad in loop", a.substring(3, 4));
                            Log.i("asad in loop", a.substring(5, 6));
                            Log.i("asad in loop", a.substring(7, 8));
                            Log.i("asad in loop", a.substring(9, 10));
                            Log.i("asad in loop", a.substring(11, 12));
                            Log.i("asad in loop", a.substring(13, 14));
                            Log.i("asad in loop", a.substring(15, 16));
                            Log.i("asad in loop", a.substring(17, 18));
                            Log.i("asad in loop", a.substring(19, 20));
                            Log.i("asad in loop", a.substring(21, 22));
                            Log.i("asad in loop", a.substring(23, 24));

                            vJAN = Integer.parseInt(a.substring(1, 2));
                            vFEB = Integer.parseInt(a.substring(3, 4));
                            vMAR = Integer.parseInt(a.substring(5, 6));
                            vAPR = Integer.parseInt(a.substring(7, 8));
                            vMAY = Integer.parseInt(a.substring(9, 10));
                            vJUN = Integer.parseInt(a.substring(11, 12));
                            vJUL = Integer.parseInt(a.substring(13, 14));
                            vAUG = Integer.parseInt(a.substring(15, 16));
                            vSEP = Integer.parseInt(a.substring(17, 18));
                            vOCT = Integer.parseInt(a.substring(19, 20));
                            vNOV = Integer.parseInt(a.substring(21, 22));
                            vDEC = Integer.parseInt(a.substring(23, 24));
//                                mChart.setData(generatePieDataone(entriestwo));
                        }

                        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

                        for (int i = 0; i < 1; i++) {

                            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");
                            BarDataSet ds;

                            entries.add(new BarEntry(0, vJAN));
                            ds = new BarDataSet(entries, "JAN");
                            ds.setColor(BLUE);

                            sets.add(ds);
                            entries.add(new BarEntry(1, vFEB));
                            ds = new BarDataSet(entries, "FEB");
                            ds.setColor(RED);
                            sets.add(ds);
                            entries.add(new BarEntry(2, vMAR));
                            ds = new BarDataSet(entries, "MAR");
                            ds.setColor(YELLOW);
                            sets.add(ds);
                            entries.add(new BarEntry(3, vAPR));
                            ds = new BarDataSet(entries, "APR");
                            ds.setColor(GREEN);
                            sets.add(ds);
                            entries.add(new BarEntry(4, vMAY));
                            ds = new BarDataSet(entries, "MAY");
                            ds.setColor(GRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(5, vJUN));
                            ds = new BarDataSet(entries, "JUN");
                            ds.setColor(BLACK);
                            sets.add(ds);
                            entries.add(new BarEntry(6, vJUL));
                            ds = new BarDataSet(entries, "JUL");
                            ds.setColor(MAGENTA);
                            sets.add(ds);
                            entries.add(new BarEntry(7, vAUG));
                            ds = new BarDataSet(entries, "AUG");
                            ds.setColor(DKGRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vSEP));
                            ds = new BarDataSet(entries, "SEP");
                            ds.setColor(LTGRAY);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vOCT));
                            ds = new BarDataSet(entries, "OCT");
                            ds.setColor(GREEN);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vNOV));
                            ds = new BarDataSet(entries, "NOV");
                            ds.setColor(MAGENTA);
                            sets.add(ds);
                            entries.add(new BarEntry(8, vDEC));
                            ds = new BarDataSet(entries, "DEC");
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
//                        } catch (JSONException e) {
//                            Log.d("statistics", "error ");
//                            e.printStackTrace();
//                        }


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
