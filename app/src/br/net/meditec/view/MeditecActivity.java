package br.net.meditec.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.net.meditec.R;
import br.net.meditec.controller.dao.DaoUser;
import br.net.meditec.controller.update.UpdateManager;
import br.net.meditec.model.Evento;
import br.net.meditec.view.dialog.DialogEditUser;
import br.net.meditec.view.fragment.AboutFragment;
import br.net.meditec.view.fragment.EventsFragment;
import br.net.meditec.view.fragment.LoginFragment;
import br.net.meditec.view.fragment.MyEventFragment;
import br.net.meditec.view.fragment.interfase.Comunication;

/**
 * 
 * @author Adriano Pauli
 *
 */
public class MeditecActivity extends FragmentActivity implements Comunication {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_aplication);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DaoUser daoUser = new DaoUser(getApplication());
		if (daoUser.isUser()) {
			ft.replace(R.id.screen_aplication_agenda, new MyEventFragment());
		} else {
			ft.replace(R.id.screen_aplication_agenda, new LoginFragment());
		}
		if (!daoUser.isEvents()) {
			new UpdateManager(this).updateDialog();
		}
		ft.commit();
	}

	public void data(String date) {
		Fragment f = getSupportFragmentManager().findFragmentById(
				R.id.screen_aplication_agenda);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (f != null) {
			ft.remove(f);
		}
		if (date.equals(Evento.MY_EVENT)) {
			if (new DaoUser(getApplication()).isUser()) {
				ft.add((int) R.id.screen_aplication_agenda,
						new MyEventFragment());
			} else {
				ft.add((int) R.id.screen_aplication_agenda, new LoginFragment());
			}
			ft.commit();
		} else if (date.equals(Evento.SELECIONE_DATE)) {
			new UpdateManager(this).updateDialog();
		} else {
			Fragment list = new EventsFragment();
			Bundle bundle = new Bundle();
			bundle.putString(Comunication.KEY_DATE_EVENT, date);
			list.setArguments(bundle);
			ft.add((int) R.id.screen_aplication_agenda, list);
			ft.commit();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.menu.update:
			new UpdateManager(this).updateDialog();
			break;
		case R.menu.about:
			Fragment f = getSupportFragmentManager().findFragmentById(
					R.id.screen_aplication_agenda);
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			if (f != null) {
				ft.remove(f);
			}
			ft.add((int) R.id.screen_aplication_agenda, new AboutFragment());
			ft.commit();
			break;
		case R.menu.user:
			editUser();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void editUser() {
		new DialogEditUser(this).show();
	}
}
