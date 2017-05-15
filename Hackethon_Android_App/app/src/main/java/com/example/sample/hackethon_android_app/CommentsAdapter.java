package com.example.sample.hackethon_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhushan.Jagtap on 7/21/2016.
 */
public class CommentsAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public CommentsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Comments object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        CommentsHolder commentsHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_comment, parent, false);
            commentsHolder = new CommentsHolder();
            commentsHolder.txt = (TextView) row.findViewById(R.id.tx_name);
            row.setTag(commentsHolder);
        } else {
            commentsHolder = (CommentsHolder) row.getTag();

        }

        Comments comments = (Comments) this.getItem(position);

        commentsHolder.txt.setText(comments.getText());

        return row;

    }

    static class CommentsHolder {
        TextView txt;

    }
}
