package br.net.meditec.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.net.meditec.R;

public class AboutFragment extends Fragment implements OnClickListener {
    private Button btEmail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_about, null);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.btEmail = (Button) getActivity().findViewById(R.id.screen_about_bt_email);
        this.btEmail.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent enviaremail = new Intent("android.intent.action.SEND");
        enviaremail.setType("plain/text");
        enviaremail.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.about_email)});
        enviaremail.putExtra("android.intent.extra.SUBJECT", "");
        enviaremail.putExtra("android.intent.extra.TEXT", "");
        startActivity(Intent.createChooser(enviaremail, "Selecione"));
    }
}
