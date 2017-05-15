package com.example.sample.hackethon_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhushan.Jagtap on 7/21/2016.
 */
public class SurveyObjectAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public SurveyObjectAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Object object) {
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
        SurveyHolder seveyHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            seveyHolder = new SurveyHolder();
            seveyHolder.tx_key = (TextView) row.findViewById(R.id.key_survey);
            seveyHolder.tx_title = (TextView) row.findViewById(R.id.title_survey);

            row.setTag(seveyHolder);
        } else {
            seveyHolder = (SurveyHolder) row.getTag();

        }

        SurveyObject surveyObject = (SurveyObject) this.getItem(position);

        seveyHolder.tx_key.setText(surveyObject.getKey());
        seveyHolder.tx_title.setText(surveyObject.getTitle());

        return row;
    }


    static class SurveyHolder {
        TextView tx_key, tx_title;

    }
}
