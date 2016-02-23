package br.net.meditec.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.net.meditec.model.Evento;
import java.util.List;

public class AdapterEvents extends BaseAdapter {
    private Context context;
    private List<Evento> eventos;

    public AdapterEvents(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    public int getCount() {
        return this.eventos.size();
    }

    public Object getItem(int position) {
        return this.eventos.get(position);
    }

    public long getItemId(int position) {
        return ((Evento) this.eventos.get(position)).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return new MontEventos(this.context).view(convertView, (Evento) this.eventos.get(position));
    }
}
