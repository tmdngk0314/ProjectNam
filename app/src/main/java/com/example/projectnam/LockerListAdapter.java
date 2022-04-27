package com.example.projectnam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LockerListAdapter extends BaseAdapter {
    private Context context;
    private List<LockerInfo> lockerlist;

    public LockerListAdapter(Context context, List<LockerInfo> lockerlist) {
        this.context = context;
        this.lockerlist = lockerlist;

    }

    @Override
    public int getCount() {
        return lockerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return lockerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice_shape, null);
           //태그를 붙여줌
        return v;

    }
}
