package br.net.meditec.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import br.net.meditec.R;
import br.net.meditec.controller.adapter.AdapterMyEvent;
import br.net.meditec.controller.dao.DaoEvento;
import br.net.meditec.controller.dao.DaoUser;
import br.net.meditec.controller.update.UpdateManager;
import br.net.meditec.model.Evento;
import br.net.meditec.model.MyEvent;
import br.net.meditec.model.User;
import br.net.meditec.view.dialog.DialogEvent;
import java.util.List;

public class MyEventFragment extends Fragment implements OnChildClickListener, OnClickListener {
    private ExpandableListView list;
    private List<MyEvent> myEvents;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_my_event, null);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.list = (ExpandableListView) getActivity().findViewById(R.id.screen_my_event_list);
        ((ImageButton) getActivity().findViewById(R.id.screen_my_event_bt_update)).setOnClickListener(this);
        this.myEvents = new DaoEvento(getActivity()).selectMyEvent();
        this.list.setAdapter(new AdapterMyEvent(this.myEvents, getActivity()));
        this.list.setOnChildClickListener(this);
    }

    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        new DialogEvent(getActivity(), (Evento) ((MyEvent) this.myEvents.get(groupPosition)).getEventos().get(childPosition)).show();
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_my_event_bt_update:
                UpdateManager manager = new UpdateManager(getActivity());
                User u = new DaoUser(getActivity()).selectUser();
                manager.myEventDialog(u.getLogin(), u.getSenha());
            default:
        }
    }
}
