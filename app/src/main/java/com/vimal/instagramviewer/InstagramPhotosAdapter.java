package com.vimal.instagramviewer;

import android.content.Context;
import android.util.Log;
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
import java.util.Locale;

/**
 * Created by FearMeNot on 2/2/2016.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivProfilePhoto = (ImageView) convertView.findViewById(R.id.ivProfilePhoto);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);

        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);
        ivProfilePhoto.setImageResource(0);
        tvUserName.setText(photo.username);
        tvLikes.setText("Likes: " + String.valueOf(photo.likesCount));

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

        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        Picasso.with(getContext()).load(photo.profileUrl).into(ivProfilePhoto);

        return convertView;
    }
}
