package br.net.meditec.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import br.net.meditec.R;
import br.net.meditec.controller.dao.DaoEvento;
import br.net.meditec.model.Evento;
import br.net.meditec.view.fragment.interfase.Comunication;

public class MenuFragmentSpinner extends Fragment implements OnItemSelectedListener {
    private Comunication comunication;
    private Spinner spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_menu_spinner, null);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.comunication = (Comunication) activity;
        } catch (ClassCastException e) {
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaoEvento daoEvento = new DaoEvento(getActivity());
        this.spinner = (Spinner) getActivity().findViewById(R.id.screen_menu_spinner_menu_spinner);
        this.spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.text_spiner, daoEvento.selectDataEvent());
        adapter.setDropDownViewResource(17367049);
        this.spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {
        String data = (String) adapter.getItemAtPosition(position);
        if (!data.equals(Evento.SELECIONE_DATE)) {
            this.comunication.data(data);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
