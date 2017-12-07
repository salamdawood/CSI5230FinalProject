package edu.csi5230.salamdawood.mycare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by salamdawood on 12/7/17.
 */

public class StringAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();

    public StringAdapter(Context c, ArrayList<String> description, ArrayList<String> status) {
        this.context = c;
        this.description = description;
        this.status = status;
    }

    @Override
    public int getCount() {
        return description.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup group = (ViewGroup) inflater.inflate(android.R.layout.simple_list_item_2, null, false);
            TextView textView = (TextView) group.getChildAt(0);
            TextView textView2 = (TextView) group.getChildAt(1);
            textView.setText(description.get(i));
            textView2.setText(status.get(i));
            return group;
        }

        return view;
    }
}
