package fr.wcs.recyclerviewjsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static fr.wcs.recyclerviewjsonexample.MainActivity.CREATOR;
import static fr.wcs.recyclerviewjsonexample.MainActivity.EXTRA_LIKES;
import static fr.wcs.recyclerviewjsonexample.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(CREATOR);
        int likesCount = intent.getIntExtra(EXTRA_LIKES, 0);

        ImageView imageViewUrl = findViewById(R.id.iv_detail);
        TextView textViewCreator = findViewById(R.id.tv_creator_detail);
        TextView textViewLikes = findViewById(R.id.tv_likes_detail);

        Picasso.get().load(imageUrl).fit().centerCrop().into(imageViewUrl);
        textViewCreator.setText(creatorName);
        textViewLikes.setText("Likes :"+likesCount);




    }
}
