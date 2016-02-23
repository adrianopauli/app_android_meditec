package br.net.meditec.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import br.net.meditec.R;
import br.net.meditec.controller.adapter.AdapterEvents;
import br.net.meditec.controller.dao.DaoEvento;
import br.net.meditec.controller.update.UpdateManager;
import br.net.meditec.model.Evento;
import br.net.meditec.view.dialog.DialogEvent;
import br.net.meditec.view.fragment.interfase.Comunication;

public class EventsFragment extends Fragment implements OnItemClickListener, OnClickListener {
    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_event, null);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    public void onActivityCreated(Bundle arguments) {
        super.onActivityCreated(arguments);
        this.listView = (ListView) getActivity().findViewById(R.id.screen_event_list);
        ((ImageButton) getActivity().findViewById(R.id.screen_event_bt_update)).setOnClickListener(this);
        if (getArguments() != null) {
            String date = getArguments().getString(Comunication.KEY_DATE_EVENT);
            this.listView.setAdapter(new AdapterEvents(getActivity(), new DaoEvento(getActivity()).selectEventsByDate(date)));
            this.listView.setOnItemClickListener(this);
        }
    }

    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
        new DialogEvent(getActivity(), (Evento) adapter.getItemAtPosition(position)).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_event_bt_update:
                new UpdateManager(getActivity()).updateDialog();
            default:
        }
    }
}
