package br.net.meditec.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import br.net.meditec.R;
import br.net.meditec.model.Evento;

public class DialogEvent extends Dialog {
    public DialogEvent(Context context, Evento e) {
        super(context);
        getWindow().requestFeature(1);
        setContentView(R.layout.dialog_event);
        setCancelable(true);
        ((TextView) findViewById(R.id.dialog_event_text_type)).setText(e.getType());
        ((TextView) findViewById(R.id.dialog_event_text_title)).setText(e.getTitle());
        ((TextView) findViewById(R.id.dialog_event_text_title)).setText(e.getTitle());
        ((TextView) findViewById(R.id.dialog_event_text_evento_sobre)).setText(e.getDescription());
        String timer = e.getInicio() + " - " + e.getFim();
        ((TextView) findViewById(R.id.dialog_event_text_date)).setText(e.getDate());
        ((TextView) findViewById(R.id.dialog_event_text_timer)).setText(timer);
        ((TextView) findViewById(R.id.dialog_event_text_sala)).setText(e.getLocal());
        ((TextView) findViewById(R.id.dialog_event_text_palestrante)).setText(e.getPalestrante().getName());
        ((TextView) findViewById(R.id.dialog_event_text_palestrante_sobre)).setText(e.getPalestrante().getDescription());
    }
}
