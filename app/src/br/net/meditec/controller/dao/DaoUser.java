package br.net.meditec.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.net.meditec.controller.dp.Banco;
import br.net.meditec.controller.dp.C0026T;
import br.net.meditec.controller.dp.DataBaseHelper;
import br.net.meditec.model.User;
import java.util.ArrayList;
import java.util.List;

public class DaoUser extends C0026T implements DAO<User> {
    private DataBaseHelper banco;

    public DaoUser(DataBaseHelper banco) {
        this.banco = banco;
    }

    public DaoUser(Context context) {
        this.banco = Banco.banco(context);
    }

    public boolean insert(User t) {
        try {
            this.banco.getWritableDatabase().insert(C0026T.TB_USER, null, values(t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertAll(List<User> t) {
        this.banco.getWritableDatabase().beginTransaction();
        try {
            for (User user : t) {
                insert(user);
            }
            this.banco.getWritableDatabase().setTransactionSuccessful();
            this.banco.getWritableDatabase().endTransaction();
            return true;
        } catch (Exception e) {
            this.banco.getWritableDatabase().endTransaction();
            return false;
        }
    }

    public void delete() {
        this.banco.getWritableDatabase().execSQL("DELETE FROM tb_user");
    }

    public ContentValues values(User t) {
        ContentValues values = new ContentValues();
        values.put(C0026T.USE_LOGIN, t.getLogin());
        values.put(C0026T.USE_SENHA, t.getSenha());
        return values;
    }

    public User cursorToObject(Cursor c) {
        User user = new User();
        user.setLogin(c.getString(c.getColumnIndex(C0026T.USE_LOGIN)));
        user.setSenha(c.getString(c.getColumnIndex(C0026T.USE_SENHA)));
        return user;
    }

    public List<User> cursorToList(Cursor c) {
        List<User> users = new ArrayList();
        while (c.moveToNext()) {
            users.add(cursorToObject(c));
        }
        c.close();
        return users;
    }

    public User selectUser() {
        Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_user", null);
        c.moveToFirst();
        User u = cursorToObject(c);
        c.close();
        return u;
    }

    public boolean isUser() {
        Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_user", null);
        if (c.getCount() == 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public boolean isEvents() {
        Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_evento", null);
        if (c.getCount() == 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }
}
