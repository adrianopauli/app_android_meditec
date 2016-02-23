package br.net.meditec.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.net.meditec.R;

public class MountDate {
    public Context context;

    public MountDate(Context context) {
        this.context = context;
    }

    public View view(View convertView, String str) {
        RelativeLayout convertView2 = (RelativeLayout) RelativeLayout.inflate(this.context, R.layout.layout_date, null);
        ((TextView) convertView2.findViewById(R.id.layout_date_date)).setText(str);
        return convertView2;
    }
}
