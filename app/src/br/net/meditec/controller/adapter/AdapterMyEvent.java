package br.net.meditec.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import br.net.meditec.model.Evento;
import br.net.meditec.model.MyEvent;
import java.util.List;

public class AdapterMyEvent extends BaseExpandableListAdapter {
    private Context context;
    private List<MyEvent> myEvent;

    public AdapterMyEvent(List<MyEvent> myEvent, Context context) {
        this.myEvent = myEvent;
        this.context = context;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return ((MyEvent) this.myEvent.get(groupPosition)).getEventos().get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return ((Evento) ((MyEvent) this.myEvent.get(groupPosition)).getEventos().get(childPosition)).getId();
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return new MontEventos(this.context).view(convertView, (Evento) ((MyEvent) this.myEvent.get(groupPosition)).getEventos().get(childPosition));
    }

    public int getChildrenCount(int groupPosition) {
        if (((MyEvent) this.myEvent.get(groupPosition)).getEventos() != null) {
            return ((MyEvent) this.myEvent.get(groupPosition)).getEventos().size();
        }
        return 0;
    }

    public Object getGroup(int groupPosition) {
        return this.myEvent.get(groupPosition);
    }

    public int getGroupCount() {
        return this.myEvent.size();
    }

    public long getGroupId(int groupPosition) {
        return 0;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return new MountDate(this.context).view(convertView, ((MyEvent) this.myEvent.get(groupPosition)).getDate());
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
