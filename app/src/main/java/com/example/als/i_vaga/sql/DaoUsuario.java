package com.example.als.i_vaga.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.als.i_vaga.model.Usuario;

import java.util.ArrayList;

public class DaoUsuario {
    private SQLiteDatabase bd;
    private DataBaseHelper sql;

    public DaoUsuario(Context context){
        sql = new DataBaseHelper(context);
    }

    public String addUsuario(Usuario usuario){
        String msg = " ";
        bd = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome"       , usuario.getNome());
        values.put("sobrenome"  , usuario.getSobrenome());
        values.put("email"      , usuario.getEmail());
        values.put("ddd"        , usuario.getDdd());
        values.put("telefone"   , usuario.getTelefone());
        values.put("senha"      , usuario.getSenha());
        values.put("rua"        , usuario.getRua());
        values.put("numero"     , usuario.getNumero());
        values.put("complemento", usuario.getComplemento());
        values.put("bairro"     , usuario.getBairro());
        values.put("cidade"     , usuario.getCidade());
        values.put("estado"     , usuario.getEstado());
        if (usuario.getGaragem()){
            values.put("garagem",1);
        }
        else {
            values.put("garagem",0);
        }

        long ins = bd.insert("usuarios", null, values);
        if (ins == -1){
            msg = "Erro";
        }
        else {
            msg = "OK";
        }
        return msg;
    }

    public ArrayList<Usuario> getList(){
        String[] columns = {"id", "nome", "sobrenome", "email", "ddd", "telefone", "senha", "rua", "numero", "complemento", "bairro", "cidade", "estado", "garagem"};
        String where = "garagem=1";
        Cursor cursor = sql.getWritableDatabase().query("usuarios",
                columns, where, null, null,
                null, null, null);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId           (cursor.getLong     (0));
            usuario.setNome         (cursor.getString   (1));
            usuario.setSobrenome    (cursor.getString   (2));
            usuario.setEmail        (cursor.getString   (3));
            usuario.setDdd          (cursor.getInt      (4));
            usuario.setTelefone     (cursor.getLong     (5));
            usuario.setSenha        (cursor.getString   (6));
            usuario.setRua          (cursor.getString   (7));
            usuario.setNumero       (cursor.getLong     (8));
            usuario.setComplemento  (cursor.getString   (9));
            usuario.setBairro       (cursor.getString   (10));
            usuario.setCidade       (cursor.getString   (11));
            usuario.setEstado       (cursor.getString   (12));
            boolean garag;
            if (cursor.getInt(13) == 0){
                garag = false;
            }
            else {
                garag = true;
            }

            usuarios.add(usuario);
        }
        return usuarios;
    }

    public void editUsuario(Usuario usuario){
        bd = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome"       , usuario.getNome());
        values.put("sobrenome"  , usuario.getSobrenome());
        values.put("email"      , usuario.getEmail());
        values.put("ddd"        , usuario.getDdd());
        values.put("telefone"   , usuario.getTelefone());
        values.put("senha"      , usuario.getSenha());
        values.put("rua"        , usuario.getRua());
        values.put("numero"     , usuario.getNumero());
        values.put("complemento", usuario.getComplemento());
        values.put("bairro"     , usuario.getBairro());
        values.put("cidade"     , usuario.getCidade());
        values.put("estado"     , usuario.getEstado());

        String [] arg = {String.valueOf(usuario.getId())};
        bd.update("usuarios", values, "id = ?", arg);
        bd.close();
    }

    public String deletUsuario(Usuario usuario){
        String [] arg = {String.valueOf(usuario.getId())};
        bd = sql.getWritableDatabase();
        bd.delete("usuarios", "id = ?", arg);
        bd.close();

        return usuario.getNome();
    }

    public boolean checkEmail(String email) {
        bd = sql.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM usuarios WHERE email = ?",
                new String[]{email});
        if (cursor.getCount() > 0){
            return false;
        }else {
            return true;
        }
    }

    public Usuario checkEmailPassword(String email, String senha){
        bd = sql.getReadableDatabase();

        Cursor cursor = bd.rawQuery("SELECT * FROM usuarios WHERE email = ? AND senha = ?", new String[]{email, senha});
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){

                long id = cursor.getLong(0);
                String nome = cursor.getString(1);
                String sobrenome = cursor.getString(2);
                String emai = cursor.getString(3);
                int ddd = cursor.getInt(4);
                long telefone = cursor.getLong(5);
                String senh = cursor.getString(6);
                String rua = cursor.getString(7);
                long numero = cursor.getLong(8);
                String complemento = cursor.getString(9);
                String bairro = cursor.getString(10);
                String cidade = cursor.getString(11);
                String estado = cursor.getString(12);
                int garagem = cursor.getInt(13);
                boolean gar;
                if (garagem == 0){
                    gar = false;
                }
                else {
                    gar = true;
                }
                Usuario user= new Usuario(id, nome, sobrenome, emai, ddd, telefone, senh, rua, numero, complemento, bairro, cidade, estado, gar);

                return user;
            }
        }
        else {
            return null;
        }
        return null;
    }

    public String updateGaragem(Usuario u, int status){
        ContentValues valor;
        String where;
        bd = sql.getWritableDatabase();
        where = "id = " + u.getId();
        valor = new ContentValues();
        valor.put("garagem", status);
        bd.update("usuarios", valor, where, null);
        bd.close();

        return "Ok";
    }
}