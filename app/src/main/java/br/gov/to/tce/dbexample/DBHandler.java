package br.gov.to.tce.dbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private String[] scripts = new String[2];
    private Context context = null;
    RecyclerView recycler = null;

    public DBHandler(Context context, String dbname, int version) {
        super(context, dbname, null, version);

        this.context = context;

        scripts[0] = "CREATE TABLE REG (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, imagepath TEXT NOT NULL, rating REAL);";
        scripts[1] = "DROP TABLE IF EXISTS REG;";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scripts[0]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scripts[1]);
    }

    public ArrayList<Reg> getRegs() {
        ArrayList<Reg> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("REG", new String[]{"name", "imagepath", "rating", "_id"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {

            list.add(new Reg(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("imagepath")),
                    cursor.getFloat(cursor.getColumnIndex("rating"))));
        }

        return list;
    }

    public void insert(Reg reg) {
        ContentValues data = new ContentValues();
        data.put("name", reg.getName());
        data.put("imagepath", reg.getImagepath());
        data.put("rating", reg.getRating());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("REG", null, data);
        Toast.makeText(context, "Inserção realizada.", Toast.LENGTH_SHORT).show();
    }

    public void insert(ContentValues data) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("REG", null, data);
        Toast.makeText(context, "Inserção realizada.", Toast.LENGTH_SHORT).show();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("REG", "_id=?", new String[]{String.valueOf(id)});
        Toast.makeText(context, "Item deletado.", Toast.LENGTH_SHORT).show();
    }

}
