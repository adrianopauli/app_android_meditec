package br.net.meditec.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public class AdapterDate extends BaseAdapter {
    private Context context;
    private List<String> dates;

    public AdapterDate(Context context, List<String> dates) {
        this.context = context;
        this.dates = dates;
    }

    public int getCount() {
        return this.dates.size();
    }

    public Object getItem(int position) {
        return this.dates.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return new MountDate(this.context).view(convertView, (String) this.dates.get(position));
    }
}
