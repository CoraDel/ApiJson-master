package fr.wcs.recyclerviewjsonexample;

import android.widget.TextView;

public class ExampleItem {
    private String mImageUrl;
    private String mCreatorName;
    private int mLikes;

    public ExampleItem(String imageUrl, String creatorName, int likes) {
        this.mImageUrl = imageUrl;
        this.mCreatorName = creatorName;
        this.mLikes = likes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getCreatorName() {
        return mCreatorName;
    }

    public void setCreatorName(String mCreatorName) {
        this.mCreatorName = mCreatorName;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int mLikes) {
        this.mLikes = mLikes;
    }



}
