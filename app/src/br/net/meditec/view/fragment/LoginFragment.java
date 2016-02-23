package br.net.meditec.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.net.meditec.R;
import br.net.meditec.controller.update.CriptografarMD5;
import br.net.meditec.controller.update.UpdateManager;

/**
 * 
 * @author Adriano Pauli
 *
 */
public class LoginFragment extends Fragment implements OnClickListener {
    private Button btSend;
    private EditText editLogin;
    private EditText editSenha;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_login, null);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.editLogin = (EditText) getActivity().findViewById(R.id.screen_login_edit_login);
        this.editSenha = (EditText) getActivity().findViewById(R.id.screen_login_edit_password);
        this.btSend = (Button) getActivity().findViewById(R.id.screen_login_bt_login);
        this.btSend.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_login_bt_login:
                if (validing()) {
                    String senha = this.editSenha.getText().toString().trim();
                    new UpdateManager(getActivity()).myEventDialog(login(), new CriptografarMD5().criptografar(senha));
                    return;
                }
                Toast.makeText(getActivity(), getActivity().getString(R.string.toast_login_never), 0).show();
            default:
        }
    }

    private String login() {
        String login = this.editLogin.getText().toString().trim();
        String part1 = login.substring(0, 3);
        String part2 = login.substring(3, 6);
        String part3 = login.substring(6, 9);
        return new StringBuilder(String.valueOf(part1)).append(".").append(part2).append(".").append(part3).append("-").append(login.substring(9, 11)).toString();
    }

    protected boolean validing() {
        boolean chave = true;
        if (this.editLogin.getText().toString().trim().length() <= 10) {
            chave = false;
        }
        if (this.editSenha.getText().toString().length() < 5) {
            return false;
        }
        return chave;
    }
}
