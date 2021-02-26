package com.example.jaimepaqui.helper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.jaimepaqui.modelos.Docente;

import java.util.ArrayList;
import java.util.List;


public class HelperBD extends SQLiteOpenHelper {

    public HelperBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE docente(id INTEGER PRIMARY KEY AUTOINCREMENT, cedula TEXT, apellido TEXT, nombres TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertar(Docente docente){
        ContentValues valores = new ContentValues();
        valores.put("cedula", docente.getCedula());
        valores.put("apellidos", docente.getApellidos());
        valores.put("nombres",docente.getCedula());
        this.getWritableDatabase().insert("docente",null, valores);
    }

    public void modificar(Docente docente){
        ContentValues valores = new ContentValues();
        valores.put("apellidos", docente.getApellidos());
        valores.put("nombres",docente.getNombres());
        this.getWritableDatabase().update("docente",valores,"cedula='" + docente.getCedula() + "'",null);
    }

    public void eliminarTodos(){
        this.getWritableDatabase().delete("docente",null,null);
    }

    public void eliminarCedula(String cedula){
        this.getWritableDatabase().delete("docente","cedula='" + cedula + "'", null);
    }

    public String leerTodos(){
        String consulta = "";
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM docente", null);
        if(cursor.moveToFirst()){
            do {
                String cedula = cursor.getString(cursor.getColumnIndex("cedula"));
                String apellidos = cursor.getString(cursor.getColumnIndex("apellidos"));
                String nombres = cursor.getString(cursor.getColumnIndex("nombres"));
                consulta += "[" + cedula + " " + apellidos + " " + nombres + "]\n";
            } while (cursor.moveToNext());
        }
        return consulta;
    }

    public String leerCedula(String ced) {
        String consulta = "";
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM docente WHERE cedula = '" + ced + "'", null);
        if (cursor.moveToFirst()) {
            do {
                String cedula = cursor.getString(cursor.getColumnIndex("cedula"));
                String apellidos = cursor.getString(cursor.getColumnIndex("apellidos"));
                String nombres = cursor.getString(cursor.getColumnIndex("nombres"));
                consulta += "["+ cedula + " " + apellidos + " " + nombres + "]\n";
            } while (cursor.moveToNext());
        }
        return consulta;
    }

    public List<Docente> getAllDocentes() {
        List<Docente> lista = new ArrayList<Docente>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM docente",null);
        if (cursor.moveToFirst()) {
            do {
                Docente docente = new Docente();
                docente.setCedula(cursor.getString(cursor.getColumnIndex("cedula")));
                docente.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
                docente.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
                lista.add(docente);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}