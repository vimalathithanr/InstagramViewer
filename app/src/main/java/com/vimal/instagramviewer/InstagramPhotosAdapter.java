package com.vimal.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.pretty.time.PrettyTime;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by FearMeNot on 2/2/2016.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    @Bind(R.id.tvCaption) TextView tvCaption;
    @Bind(R.id.ivPhoto) ImageView ivPhoto;
    @Bind(R.id.ivProfilePhoto) ImageView ivProfilePhoto;
    @Bind(R.id.tvUserName) TextView tvUserName;
    @Bind(R.id.tvLikes)TextView tvLikes;
    @Bind(R.id.tvTime) TextView tvTime;
    @Bind(R.id.tvComment1)TextView tvComment1;
    @Bind(R.id.tvComment2)TextView tvComment2;

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        ButterKnife.bind(this, convertView);

        tvCaption.setText(photo.username+ ": " +photo.caption);
        ivPhoto.setImageResource(0);
        ivProfilePhoto.setImageResource(0);
        tvUserName.setText(photo.username);
        tvLikes.setText(String.valueOf(photo.likesCount) + " likes");
        tvComment1.setText(photo.comment[0]);
        tvComment2.setText(photo.comment[1]);



        String prettyTimeString = new PrettyTime().format(new Date(photo.timeCreated * 1000));
        String prettyTime = null;

        if (prettyTimeString.contains("minutes")) {
            prettyTime = prettyTimeString.replace(" minutes ago", "m");
        } else if (prettyTimeString.contains("minute")) {
            prettyTime = prettyTimeString.replace(" minute ago", "m");
        } else if (prettyTimeString.contains("hours")) {
            prettyTime = prettyTimeString.replace(" hours ago", "h");
        } else if (prettyTimeString.contains("hour")) {
            prettyTime = prettyTimeString.replace(" hour ago", "h");
        }


        tvTime.setText(prettyTime);
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.placeholder).into(ivPhoto);
        Picasso.with(getContext()).load(photo.profileUrl).into(ivProfilePhoto);

        return convertView;
    }
}
