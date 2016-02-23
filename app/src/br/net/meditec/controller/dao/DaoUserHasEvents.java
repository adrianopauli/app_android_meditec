package br.net.meditec.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import br.net.meditec.controller.dp.Banco;
import br.net.meditec.controller.dp.C0026T;
import br.net.meditec.controller.dp.DataBaseHelper;
import java.util.List;

public class DaoUserHasEvents extends C0026T {
    private DataBaseHelper banco;

    public DaoUserHasEvents(DataBaseHelper banco) {
        this.banco = banco;
    }

    public DaoUserHasEvents(Context context) {
        this.banco = Banco.banco(context);
    }

    public boolean insert(String t) {
        try {
            this.banco.getWritableDatabase().insert(C0026T.TB_USER_EVENT, null, values(t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertAll(List<String> t) {
        this.banco.getWritableDatabase().beginTransaction();
        try {
            for (String userEvent : t) {
                insert(userEvent);
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
        this.banco.getWritableDatabase().execSQL("DELETE FROM tb_user_event");
    }

    public ContentValues values(String t) {
        ContentValues values = new ContentValues();
        values.put(C0026T.USEEVE_ID_EVENTO, t);
        return values;
    }
}
