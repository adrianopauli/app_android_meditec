package br.net.meditec.controller.dp;

import android.database.sqlite.SQLiteDatabase;

public abstract class Table extends C0026T {
    public static void create(SQLiteDatabase db) {
        insert(db);
    }

    private static void insert(SQLiteDatabase db) {
        db.execSQL(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("CREATE TABLE " + "tb_evento (")).append("evn_id INTEGER NOT NULL PRIMARY KEY,").toString())).append("evn_id_palestrante INTEGER REFERENCES tb_palestrante(pal_id),").toString())).append("evn_title TEXT,").toString())).append("evn_description TEXT,").toString())).append("evn_date TEXT,").toString())).append("evn_duration TEXT,").toString())).append("evn_inicio TEXT,").toString())).append("evn_fim TEXT,").toString())).append("evn_local TEXT,").toString())).append("evn_type TEXT);").toString());
        db.execSQL(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("CREATE TABLE " + "tb_palestrante (")).append("pal_id INTEGER NOT NULL PRIMARY KEY,").toString())).append("pal_name TEXT,").toString())).append("pal_description TEXT);").toString());
        db.execSQL(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("CREATE TABLE " + "tb_user (")).append("use_login TEXT,").toString())).append("use_senha TEXT);").toString());
        db.execSQL(new StringBuilder(String.valueOf("CREATE TABLE " + "tb_user_event (")).append("useeve_id_evento INTEGER REFERENCES tb_evento(evn_id));").toString());
    }

    public static void update(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("DROP TABLE tb_user_event");
        db.execSQL("DROP TABLE tb_user");
        db.execSQL("DROP TABLE tb_palestrante");
        db.execSQL("DROP TABLE tb_evento");
        insert(db);
    }
}
