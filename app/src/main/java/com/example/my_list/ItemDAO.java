package com.example.my_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public ItemDAO(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public ItemDAO open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME};
        return db.query(DatabaseHelper.TABLE, columns, null, null, null, null, null, null);
    }

    public List<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            items.add(new Item(id, name));
        }
        cursor.close();
        return items;
    }

    public long getCount() {
        return DatabaseUtils.queryNumEntries(db, DatabaseHelper.TABLE);
    }

    public Item getItem(long id) {
        Item item = null;
        String query = String.format("select * from %s where %s=?", DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()) {
            String name = cursor.getString(1);
            item = new Item(id, name);
        }
        cursor.close();
        return item;
    }

    public long insert(Item item) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, item.getName());

        return db.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long update(Item item) {
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + item.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, item.getName());
        return db.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }

    public long delete(long itemId) {
        String whereClause = "_id=?";
        String[] whereArgs = new String[]{String.valueOf(itemId)};
        return db.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }
}
