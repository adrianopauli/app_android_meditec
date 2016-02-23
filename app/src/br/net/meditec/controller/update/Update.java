package br.net.meditec.controller.update;

import android.content.Context;
import br.net.meditec.controller.dao.DaoUser;
import br.net.meditec.controller.dp.Banco;
import br.net.meditec.controller.dp.DataBaseHelper;
import br.net.meditec.controller.update.server.Connection;
import br.net.meditec.controller.update.server.EventServer;
import br.net.meditec.controller.update.server.PalestrantesServer;
import br.net.meditec.model.User;

public abstract class Update {
    public static final String STATUS_UPDATE_NOT = "not";
    public static final String STATUS_UPDATE_OK = "ok";

    public static boolean update(Context context) {
        DataBaseHelper banco = Banco.banco(context);
        try {
            banco.getWritableDatabase().beginTransaction();
            PalestrantesServer palestrantesServer = new PalestrantesServer(Connection.SERVER_PALESTRANTES);
            boolean chave = palestrantesServer.connection();
            chave = palestrantesServer.insertData(context);
            EventServer eventServer = new EventServer(Connection.SERVER_EVENTOS);
            chave = eventServer.connection();
            chave = eventServer.insertData(context);
            banco.getWritableDatabase().setTransactionSuccessful();
            banco.getWritableDatabase().endTransaction();
            return chave;
        } catch (Exception e) {
            banco.getWritableDatabase().endTransaction();
            e.printStackTrace();
            return false;
        }
    }

    public static String login(Context context) {
        DaoUser daoUser = new DaoUser(context);
        if (!daoUser.isUser()) {
            return "";
        }
        User u = daoUser.selectUser();
        return Login.execute(u.getLogin(), u.getSenha(), context);
    }
}
