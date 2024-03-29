package AppFinal.elvis.app_final.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import AppFinal.elvis.app_final.entidades.Contactos;

public class DbContactos extends DbHelper {

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaContacto(String nombre, String telefono, String correo_electronico,
                                String mascota, String raza, String fecha){

        long id = 0;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            values.put("mascota", mascota);
            values.put("raza", raza);
            values.put("fecha", fecha);

            Log.i("valor","valor" );
            Log.i("value",correo_electronico );

            id = db.insert(TABLE_CONTACTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Contactos> mostrarContactos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);

        if (cursorContactos.moveToFirst()){
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                contacto.setMascota(cursorContactos.getString(4));
                contacto.setRaza(cursorContactos.getString(5));
                contacto.setFecha(cursorContactos.getString(6));

                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());
        }
        cursorContactos.close();

        return listaContactos;
    }


    public Contactos verContacto(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
            contacto.setMascota(cursorContactos.getString(4));
            contacto.setRaza(cursorContactos.getString(5));
            contacto.setFecha(cursorContactos.getString(6));
        }
        cursorContactos.close();

        return contacto;
    }


    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico,
        String mascota, String raza, String fecha){

       boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        try{
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '"+ nombre +"', " +
                    "telefono = '"+ telefono +"', " +
                    "correo_electronico = '"+ correo_electronico +"', " +
                    "mascota = '"+ mascota +"', " +
                    "raza = '"+ raza +"', " +
                    "fecha = '"+ fecha +"' " +
                    "WHERE id = '"+ id +"' ");

            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
