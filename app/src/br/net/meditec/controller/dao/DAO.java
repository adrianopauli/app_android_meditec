package br.net.meditec.controller.dao;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;

public interface DAO<T> {
    List<T> cursorToList(Cursor cursor);

    T cursorToObject(Cursor cursor);

    void delete();

    boolean insert(T t);

    boolean insertAll(List<T> list);

    ContentValues values(T t);
}
