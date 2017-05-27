package br.com.gg.mypi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GraficoActivity extends AppCompatActivity {

    private int foreignKey = LoginActivity.FKey;
    LineChart lineChart;

    private class LongOperation extends AsyncTask<Object, Object, ArrayList<Entry>> {

        @Override
        protected ArrayList<Entry> doInBackground(Object... params) {

            Connection db2 = null;
            ArrayList<Entry> y = null;
            ResultSet lg2;
            Statement st2;
            try {
                Thread.sleep(500);
                Class.forName("org.postgresql.Driver");
                db2 = DriverManager.getConnection("jdbc:postgresql://ec2-184-73-199-72.compute-1.amazonaws.com:5432/d9krqs4b40hebl?ssl=true&sslmode=require&sslfactory=org.postgresql.ssl.NonValidatingFactory", "ipsjzpheswtzlh", "a5f4879460047281d282829f6e0b6fa4f0771722744aafaf627a4da8279127a8");
                st2 = db2.createStatement();
                lg2 = st2.executeQuery("SELECT idd,dados,id_fk FROM tbdados Where id_fk ="+foreignKey);

                int i = 0;
                y = new ArrayList<>();
                while (lg2.next()) {
                        int idd = lg2.getInt("idd");
                        Array dados = (lg2.getArray("dados"));
                        int idfk2 = lg2.getInt("id_fk");
                        y.add(new Entry (i,idd));
                        i++;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                db2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return y;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        lineChart = (LineChart)findViewById(R.id.lineChart);

        ArrayList<Entry> x = new ArrayList<>();
        ArrayList<Entry> y = new ArrayList<>();
        new LongOperation().execute("");
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
            finish();
        }
    }

}
