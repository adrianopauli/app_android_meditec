package br.net.meditec.controller.update;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.net.meditec.R;
import br.net.meditec.controller.dao.DaoUser;
import br.net.meditec.controller.update.server.Connection;
import br.net.meditec.model.User;

public class LoginAsyncTask extends AsyncTask<String, Void, String> {
    private Activity f1a;
    private Dialog dialogProgress;
    private String login;
    private String senha;

    public LoginAsyncTask(Activity a) {
        this.f1a = a;
    }

    protected void onPreExecute() {
        this.dialogProgress = new Dialog(this.f1a);
        this.dialogProgress.setContentView(R.layout.dialog_progress);
        this.dialogProgress.setCancelable(true);
        this.dialogProgress.show();
        super.onPreExecute();
    }

    protected String doInBackground(String... str) {
        this.login = str[0];
        this.senha = str[1];
        Update.update(this.f1a);
        return Login.execute(this.login, this.senha, this.f1a);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.dialogProgress.dismiss();
        if (result.equals(Connection.SERVER_LOGIN_STATUS_OK)) {
            DaoUser daoUser = new DaoUser(this.f1a);
            daoUser.delete();
            User user = new User();
            user.setLogin(this.login);
            user.setSenha(this.senha);
            daoUser.insert(user);
            Toast.makeText(this.f1a, R.string.text_update, 1).show();
        } else if (result.equals(Login.USER_INVALIDO)) {
            new DaoUser(this.f1a).delete();
            Toast.makeText(this.f1a, this.f1a.getString(R.string.toast_login_never), 0).show();
        } else if (result.equals(Connection.SERVER_LOGIN_STATUS_OK)) {
            Toast.makeText(this.f1a, this.f1a.getString(R.string.toast_server_never), 0).show();
        }
        this.f1a.startActivity(this.f1a.getIntent());
        this.f1a.finish();
    }
}
