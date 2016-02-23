package br.net.meditec.controller.dp;

import android.content.Context;

public abstract class Banco {
    private static DataBaseHelper instacia;

    public static synchronized DataBaseHelper banco(Context context) {
        DataBaseHelper dataBaseHelper;
        synchronized (Banco.class) {
            if (instacia == null) {
                instacia = new DataBaseHelper(context);
            }
            dataBaseHelper = instacia;
        }
        return dataBaseHelper;
    }

    public static void close() {
        if (instacia != null) {
            instacia.close();
        }
    }
}
