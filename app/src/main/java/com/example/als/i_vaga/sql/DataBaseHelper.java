package com.example.als.i_vaga.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "usuarios.bd";
    private static final int VERSION = 1;
    SQLiteDatabase db;

    public DataBaseHelper (Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usuario = "CREATE TABLE usuarios(" +
                "id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome           TEXT    NOT NULL," +
                "sobrenome      TEXT    NOT NULL," +
                "email          TEXT    NOT NULL," +
                "ddd            INTEGER NOT NULL," +
                "telefone       INTEGER NOT NULL," +
                "senha          TEXT    NOT NULL," +
                "rua            TEXT    NOT NULL," +
                "numero         INTEGER NOT NULL," +
                "complemento    TEXT,            " +
                "bairro         TEXT    NOT NULL," +
                "cidade         TEXT    NOT NULL," +
                "estado         TEXT    NOT NULL," +
                "garagem        INTEGER);";
        db.execSQL(usuario);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String usuario = "DROP TABLE IF EXISTS usuarios";
        db.execSQL(usuario);
        this.onCreate(db);
    }
}