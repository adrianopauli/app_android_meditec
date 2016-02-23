package br.net.meditec.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.net.meditec.controller.dp.Banco;
import br.net.meditec.controller.dp.C0026T;
import br.net.meditec.controller.dp.DataBaseHelper;
import br.net.meditec.model.Palestrante;
import java.util.ArrayList;
import java.util.List;

public class DaoPalestrante extends C0026T implements DAO<Palestrante> {
    private DataBaseHelper banco;

    public DaoPalestrante(DataBaseHelper banco) {
        this.banco = banco;
    }

    public DaoPalestrante(Context context) {
        this.banco = Banco.banco(context);
    }

    public boolean insert(Palestrante t) {
        try {
            this.banco.getWritableDatabase().insert(C0026T.TB_PALESTRANTE, null, values(t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertAll(List<Palestrante> t) {
        this.banco.getWritableDatabase().beginTransaction();
        try {
            for (Palestrante palestrante : t) {
                insert(palestrante);
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
        this.banco.getWritableDatabase().execSQL("DELETE FROM tb_palestrante");
    }

    public ContentValues values(Palestrante t) {
        ContentValues values = new ContentValues();
        values.put(C0026T.PAL_DESCRIPTION, t.getDescription());
        values.put(C0026T.PAL_ID, Long.valueOf(t.getId()));
        values.put(C0026T.PAL_NAME, t.getName());
        return values;
    }

    public Palestrante cursorToObject(Cursor c) {
        Palestrante palestrante = new Palestrante();
        c.moveToFirst();
        palestrante.setDescription(c.getString(c.getColumnIndex(C0026T.PAL_DESCRIPTION)));
        palestrante.setId(c.getLong(c.getColumnIndex(C0026T.PAL_ID)));
        palestrante.setName(c.getString(c.getColumnIndex(C0026T.PAL_NAME)));
        return palestrante;
    }

    public List<Palestrante> cursorToList(Cursor c) {
        List<Palestrante> palestrantes = new ArrayList();
        while (c.moveToNext()) {
            palestrantes.add(cursorToObject(c));
        }
        c.close();
        return palestrantes;
    }

    public Palestrante selectById(long id) {
        Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_palestrante WHERE pal_id = " + id, null);
        Palestrante palestrante = cursorToObject(c);
        c.close();
        return palestrante;
    }
}
