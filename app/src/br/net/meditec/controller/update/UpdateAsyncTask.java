package br.net.meditec.controller.update;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.net.meditec.R;

public class UpdateAsyncTask extends AsyncTask<String, String, Boolean> {
    private Activity activity;
    private Dialog dialogProgress;

    public UpdateAsyncTask(Activity a) {
        this.activity = a;
    }

    protected void onPreExecute() {
        this.dialogProgress = new Dialog(this.activity);
        this.dialogProgress.setContentView(R.layout.dialog_progress);
        this.dialogProgress.setCancelable(true);
        this.dialogProgress.show();
        super.onPreExecute();
    }

    protected Boolean doInBackground(String... arg0) {
        return Boolean.valueOf(Update.update(this.activity));
    }

    protected void onPostExecute(Boolean result) {
        this.dialogProgress.dismiss();
        if (result.booleanValue()) {
            this.activity.getIntent();
            this.activity.startActivity(this.activity.getIntent());
            this.activity.finish();
            Toast.makeText(this.activity, R.string.text_update, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.activity, R.string.text_update_err, Toast.LENGTH_LONG).show();
        }
        super.onPostExecute(result);
    }
}
