package br.net.meditec.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import br.net.meditec.R;
import br.net.meditec.controller.adapter.AdapterDate;
import br.net.meditec.controller.dao.DaoEvento;
import br.net.meditec.view.fragment.interfase.Comunication;

public class MenuFragmentList extends Fragment implements OnItemClickListener {
    private AdapterDate adapter;
    private Comunication comunication;
    private ListView list;
    private View f4v;

    public MenuFragmentList() {
        this.f4v = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.screen_menu_list, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        this.adapter = new AdapterDate(getActivity(), new DaoEvento(getActivity()).selectDataEvent());
        this.list = (ListView) getActivity().findViewById(R.id.screen_menu_list_list);
        this.list.setOnItemClickListener(this);
        this.list.setAdapter(this.adapter);
        super.onActivityCreated(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.comunication = (Comunication) activity;
        } catch (ClassCastException e) {
        }
    }

    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
        if (this.f4v != null) {
            this.f4v.setBackgroundResource(R.drawable.background_list_event);
            ((ImageView) this.f4v.findViewById(R.id.layout_date_img)).setVisibility(4);
        }
        this.comunication.data((String) adapter.getItemAtPosition(position));
        v.setBackgroundResource(R.drawable.backgroung_intem_list_select);
        ((ImageView) v.findViewById(R.id.layout_date_img)).setVisibility(0);
        this.f4v = v;
    }
}
