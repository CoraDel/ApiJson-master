package fr.wcs.recyclerviewjsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdaptor.OnItemClickListener{
    public static final String EXTRA_URL = "imageUrl";
    public static final String CREATOR = "creator";
    public static final String EXTRA_LIKES = "likesCount_extra";

    private RecyclerView mRecyclerView;
    private ExampleAdaptor mExampleAdaptor;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson(){
        String url = "https://pixabay.com/api/?key=1035094-c5270f707bede4ec873b90d32&q=flower&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likesCount = hit.getInt("likes");

                        mExampleList.add(new ExampleItem(imageUrl, creatorName, likesCount));
                    }

                    mExampleAdaptor = new ExampleAdaptor(MainActivity.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdaptor);
                    mExampleAdaptor.setOnItemClickListener(MainActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickItem.getImageUrl());
        detailIntent.putExtra(CREATOR, clickItem.getCreatorName());
        detailIntent.putExtra(EXTRA_LIKES, clickItem.getLikes());

        startActivity(detailIntent);
    }
}
