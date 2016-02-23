package br.net.meditec.controller.dp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_NAME = "banco";
    private static final int VERSION = 2;

    public DataBaseHelper(Context context) {
        super(context, BANCO_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Table.create(db);
    }

    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        Table.update(db, newVersion, oldVersion);
    }
}
