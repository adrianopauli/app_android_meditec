package br.net.meditec.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.net.meditec.controller.dp.Banco;
import br.net.meditec.controller.dp.C0026T;
import br.net.meditec.controller.dp.DataBaseHelper;
import br.net.meditec.model.Evento;
import br.net.meditec.model.MyEvent;
import java.util.ArrayList;
import java.util.List;

public class DaoEvento extends C0026T implements DAO<Evento> {
    private DataBaseHelper banco;

    public DaoEvento(DataBaseHelper banco) {
        this.banco = banco;
    }

    public DaoEvento(Context context) {
        this.banco = Banco.banco(context);
    }

    public boolean insert(Evento t) {
        try {
            this.banco.getWritableDatabase().insert(C0026T.TB_EVENTO, null, values(t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertAll(List<Evento> t) {
        this.banco.getWritableDatabase().beginTransaction();
        try {
            for (Evento evento : t) {
                insert(evento);
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
        this.banco.getWritableDatabase().execSQL("DELETE FROM tb_evento");
    }

    public ContentValues values(Evento t) {
        ContentValues values = new ContentValues();
        values.put(C0026T.EVE_ID, Long.valueOf(t.getId()));
        values.put(C0026T.EVE_DATE, t.getDate());
        values.put(C0026T.EVE_DESCRIPTION, t.getDescription());
        values.put(C0026T.EVE_DURATION, t.getDuration());
        values.put(C0026T.EVE_INICIO, t.getInicio());
        values.put(C0026T.EVE_FIM, t.getFim());
        values.put(C0026T.EVE_TITLE, t.getTitle());
        values.put(C0026T.EVE_TYPE, t.getType());
        values.put(C0026T.EVE_LOCAL, t.getLocal());
        values.put(C0026T.EVE_ID_PALESTRANTE, Long.valueOf(t.getPalestrante().getId()));
        return values;
    }

    public Evento cursorToObject(Cursor c) {
        Evento evento = new Evento();
        evento.setDate(c.getString(c.getColumnIndex(C0026T.EVE_DATE)));
        evento.setDuration(c.getString(c.getColumnIndex(C0026T.EVE_DURATION)));
        evento.setDescription(c.getString(c.getColumnIndex(C0026T.EVE_DESCRIPTION)));
        evento.setId(c.getLong(c.getColumnIndex(C0026T.EVE_ID)));
        evento.setTitle(c.getString(c.getColumnIndex(C0026T.EVE_TITLE)));
        evento.setType(c.getString(c.getColumnIndex(C0026T.EVE_TYPE)));
        evento.setPalestrante(new DaoPalestrante(this.banco).selectById(c.getLong(c.getColumnIndexOrThrow(C0026T.EVE_ID_PALESTRANTE))));
        evento.setInicio(c.getString(c.getColumnIndex(C0026T.EVE_INICIO)));
        evento.setFim(c.getString(c.getColumnIndex(C0026T.EVE_FIM)));
        evento.setLocal(c.getString(c.getColumnIndex(C0026T.EVE_LOCAL)));
        return evento;
    }

    public List<Evento> cursorToList(Cursor c) {
        List<Evento> eventos = new ArrayList();
        while (c.moveToNext()) {
            eventos.add(cursorToObject(c));
        }
        c.close();
        return eventos;
    }

    public List<Evento> selectEventByUser() {
        return cursorToList(this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_evento,tb_user_event WHERE useeve_id_evento = evn_id", null));
    }

    public List<String> selectDataEvent() {
        List<String> dates = new ArrayList();
        Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT DISTINCT evn_date FROM tb_evento", null);
        if (c.getCount() == 0) {
            dates.add(Evento.SELECIONE_DATE);
        } else {
            dates.add(Evento.MY_EVENT);
        }
        while (c.moveToNext()) {
            dates.add(c.getString(c.getColumnIndex(C0026T.EVE_DATE)));
        }
        c.close();
        return dates;
    }

    public List<Evento> selectEventsByDate(String date) {
        return cursorToList(this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_evento WHERE evn_date =?", new String[]{date}));
    }

    public List<MyEvent> selectMyEvent() {
        List<MyEvent> myEvents = new ArrayList();
        for (String data : selectDataEvent()) {
            MyEvent event = new MyEvent(data);
            Cursor c = this.banco.getWritableDatabase().rawQuery("SELECT * FROM tb_user_event,tb_evento WHERE evn_id=useeve_id_evento AND evn_date=?", new String[]{data});
            if (c.getCount() > 0) {
                event.setEventos(cursorToList(c));
                myEvents.add(event);
            }
            c.close();
        }
        return myEvents;
    }
}
