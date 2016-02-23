package br.net.meditec.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.net.meditec.R;
import br.net.meditec.controller.dao.DaoUser;
import br.net.meditec.model.User;

public class DialogEditUser extends Dialog implements OnClickListener {
    private Button btCancel;
    private Button btSave;
    private DaoUser daoUser;
    private EditText etLogin;
    private EditText etPassword;
    private User user;

    public DialogEditUser(Context context) {
        super(context);
        getWindow().requestFeature(1);
        setContentView(R.layout.dialog_user);
        this.btCancel = (Button) findViewById(R.id.dialog_user_bt_cancel);
        this.btCancel.setOnClickListener(this);
        this.btSave = (Button) findViewById(R.id.dialog_user_bt_salve);
        this.btSave.setOnClickListener(this);
        this.etLogin = (EditText) findViewById(R.id.dialog_user_edit_login);
        this.etPassword = (EditText) findViewById(R.id.dialog_user_edit_password);
        this.daoUser = new DaoUser(getContext());
        this.user = this.daoUser.selectUser();
        if (this.user != null) {
            this.etLogin.setText(this.user.getLogin());
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_user_bt_cancel:
                dismiss();
            default:
        }
    }
}
