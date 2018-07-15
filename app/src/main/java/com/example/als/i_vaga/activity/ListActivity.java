package com.example.als.i_vaga.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.als.i_vaga.R;
import com.example.als.i_vaga.model.Usuario;
import com.example.als.i_vaga.sql.DaoUsuario;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView list;
    AdapterDadosActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = findViewById(R.id.list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregarGaragem();
    }

    public void carregarGaragem(){
        DaoUsuario daoList = new DaoUsuario(getApplicationContext());
        ArrayList<Usuario> list_garagem = daoList.getList();
        adapter = new AdapterDadosActivity(getApplicationContext(), list_garagem);
        if (list_garagem != null){
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                    Usuario usuarioEscolhido = (Usuario) adapter.getItemAtPosition(position);
                    Intent abreDetalhes = new Intent(ListActivity.this, DetalheActivity.class);
                    abreDetalhes.putExtra("user", usuarioEscolhido);
                    startActivity(abreDetalhes);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_login: {
                Intent abreLogin = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(abreLogin);
                break;
            }
            case R.id.menu_cadastro:
                Intent abreCadastro = new Intent(ListActivity.this, CadastroActivity.class);
                startActivity(abreCadastro);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}