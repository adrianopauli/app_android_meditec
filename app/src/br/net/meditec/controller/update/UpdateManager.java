package br.net.meditec.controller.update;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.widget.Toast;
import br.net.meditec.R;

public class UpdateManager {
    private Activity f3a;

    public UpdateManager(Activity a) {
        this.f3a = a;
    }

    public void updateDialog() {
        if (isConnection()) {
            new UpdateAsyncTask(this.f3a).execute(new String[]{""});
        }
    }

    public void myEventDialog(String login, String senha) {
        if (isConnection()) {
            new LoginAsyncTask(this.f3a).execute(new String[]{login, senha});
        }
    }

    protected boolean isConnection() {
        ConnectivityManager manager = (ConnectivityManager) this.f3a.getSystemService("connectivity");
        if (!(manager.getNetworkInfo(1).isConnected() || manager.getNetworkInfo(0).isConnected())) {
            Toast.makeText(this.f3a, this.f3a.getString(R.string.text_not_connection), 0).show();
        }
        return true;
    }
}
