package com.siddharth.crickethighs;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String url = "https://cricapi.com/api/cricketScore?apikey=iIouHePCnKW6Ux6BSbsCGwboKBL2&unique_id=1144529";
    TextView tstat,tscore,tdescription,tteam1,tteam2,tmatchstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tstat=findViewById(R.id.stat);
        tscore=findViewById(R.id.score);
        tdescription=findViewById(R.id.description);
        tteam1=findViewById(R.id.team1);
        tteam2=findViewById(R.id.team2);
        tmatchstarted=findViewById(R.id.matchstarted);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    final JSONObject jsonObject = new JSONObject(response);
                    String score=jsonObject.getString("score");
                    String stat=jsonObject.getString("stat");
                    String description=jsonObject.getString("description");
                    String matchStarted=jsonObject.getString("matchStarted");
                    String team1=jsonObject.getString("team-1");
                    String team2=jsonObject.getString("team-2");
                    tscore.setText(score);
                    tstat.setText(stat);
                    tteam1.setText(team1);
                    tteam2.setText(team2);
                    tdescription.setText(description);
                    tmatchstarted.setText(matchStarted);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Display the first 500 characters of the response string.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"That didn't work!",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}
