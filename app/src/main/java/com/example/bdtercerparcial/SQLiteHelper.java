package com.example.bdtercerparcial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Genera las tablas
        String query = "create table usuarios(id integer primary key autoincrement, nombre text, apellido text, correo text," +
                "telefono text, nombreUsuario text, password text);";
        db.execSQL(query);
        String querys = "create table nota(id integer primary key autoincrement, compra_id integer, subtotal text, " +
                "total text)";
        db.execSQL(querys); //Seguro esto puede causar problemas
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) { //Refresca las tablas
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS nota");
        onCreate(db);
    }

    public boolean insertNote(String compra_id, String subTotal, String total){
        ContentValues values = new ContentValues();
        values.put("compra_id", compra_id);
        values.put("subtotal", subTotal);
        values.put("total", total);
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.insert("nota", null, values);
        if(res>0)
            return true;
        else
            return false;
    }

    public boolean insertarRegistro(String nombre, String apellido, String correo, String telefono, String nombreUsuario,
                                    String password1){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("apellido", apellido);
        valores.put("correo", correo);
        valores.put("telefono", telefono);
        valores.put("nombreUsuario", nombreUsuario);
        valores.put("password", password1);
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.insert("usuarios", null, valores);
        if (res >0)
            return true;
        else
            return false;
    }

    public Cursor ConsultarUsuPas(String usu, String pass) throws SQLException{
        Cursor mcursor=null;
        //im going to remove id here
        //mcursor=this.getReadableDatabase().query("usuarios", new String[]{"id", "nombre", ""});
        mcursor=this.getReadableDatabase().query("usuarios",
                new String[]{"nombre", "apellido","correo", "telefono", "nombreUsuario", "password"},
                "nombreUsuario like'"+usu+"' and password like '"+pass+"'", null, null, null, null);
        return mcursor;
    }

    public Cursor allData(String nombreUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] ar = new String[]{nombreUsuario};
        Cursor cursor = db.rawQuery("select * from usuarios where nombreUsuario=?", ar);
        return cursor;
    }

    public Cursor idConsult(String nombreUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] ar = new String[]{nombreUsuario};
        Cursor cursor = db.rawQuery("select id from usuarios where nombreUsuario=?", ar);
        return cursor;
    }

    public Cursor consultNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select subtotal, total from nota where id="+id, null);
        return cursor;
    }

}

