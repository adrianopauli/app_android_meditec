package br.net.meditec.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.net.meditec.R;
import br.net.meditec.model.Evento;

public class MontEventos {
    public Context context;

    public MontEventos(Context context) {
        this.context = context;
    }

    public View view(View convertView, Evento e) {
        LinearLayout convertView2 = (LinearLayout) LinearLayout.inflate(this.context, R.layout.layout_eventos, null);
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_type)).setText(e.getType());
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_title)).setText(e.getTitle());
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_palestrante)).setText(e.getPalestrante().getName());
        String timer = e.getInicio() + " - " + e.getFim();
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_date)).setText(e.getDate());
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_timer)).setText(timer);
        ((TextView) convertView2.findViewById(R.id.layout_eventos_text_sala)).setText(e.getLocal());
        return convertView2;
    }
}
