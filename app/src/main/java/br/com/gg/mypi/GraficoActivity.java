package br.com.gg.mypi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GraficoActivity extends AppCompatActivity {

    //private int foreignKey = LoginActivity.FKey;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        lineChart = (LineChart)findViewById(R.id.lineChart);

        ArrayList<Entry> x = new ArrayList<>();
        ArrayList<Entry> y = new ArrayList<>();
        /*try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection db = null;
        try {
            db = DriverManager.getConnection("jdbc:postgresql://ec2-184-73-199-72.compute-1.amazonaws.com:5432/d9krqs4b40hebl?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","ipsjzpheswtzlh","a5f4879460047281d282829f6e0b6fa4f0771722744aafaf627a4da8279127a8");
            Statement st = db.createStatement();
            ResultSet lg = st.executeQuery("SELECT idd FROM tbdados");
            int i=0;
            while(lg.next()){
                y.add(new Entry(i,lg.getInt(0)));
                //y.add(new Entry (i,lg.getBigDecimal("dados").intValueExact()));
                i++;
            }
            lg.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        ArrayList<Entry> t = new ArrayList<>();
        for (int i = 1; i < 12 ; i++) {
            t.add(new Entry (i,i));
        }

        LineDataSet Tdataset = new LineDataSet(t,"Potencia");
        LineDataSet Ydataset = new LineDataSet(y,"Potencia");

        Ydataset.setDrawCircles(false);
        Ydataset.setDrawValues(false);
        Ydataset.setFillAlpha(65);
        Ydataset.setDrawCircleHole(false);
        //lineChart.clear();
        Ydataset.setDrawValues(false);
        lineChart.setData(new LineData(Tdataset));
        if(lineChart.isEmpty())
        {
            //finish();
        }
    }

}
