package br.net.meditec.view;
import br.net.meditec.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Activict inical da aplicacao. Ela tem a funcionalidade de fazer a abertura da app.
 * 
 * @author Adriano Pauli
 *
 */
public class SplashActivity extends Activity implements Runnable {
    private ImageView imgLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        new Handler().postDelayed(this, 3000);
        this.imgLogo = (ImageView) findViewById(R.id.screen_splash_img_logo);
        this.imgLogo.setAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_logo));
    }

    public void run() {
        startActivity(new Intent(this, MeditecActivity.class));
        finish();
        overridePendingTransition(R.animator.anim_in, R.animator.anim_out);
    }
}
