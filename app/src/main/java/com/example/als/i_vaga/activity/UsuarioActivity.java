package com.example.als.i_vaga.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.als.i_vaga.R;
import com.example.als.i_vaga.model.Usuario;
import com.example.als.i_vaga.sql.DaoUsuario;

public class UsuarioActivity extends AppCompatActivity {
    TextView nome, telefone, endereco, complemento, bairro, cidade, estado;
    ImageButton edit, delet;
    ToggleButton garagem;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DaoUsuario[] daoUsuario = {new DaoUsuario(getApplicationContext())};
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        nome = findViewById(R.id.nomeCompleto);
        telefone = findViewById(R.id.telefoneCompleto);
        endereco = findViewById(R.id.enderecoCompleto);
        complemento = findViewById(R.id.complementoCompleto);
        bairro = findViewById(R.id.bairroCompleto);
        cidade = findViewById(R.id.cidadeCompleto);
        estado = findViewById(R.id.estadoCompleto);
        edit = findViewById(R.id.btnEdit);
        delet = findViewById(R.id.btnDelet);
        garagem = findViewById(R.id.toggleButtonGaragem);
        garagem.setChecked(usuario.getGaragem());


        nome.setText(getString(R.string.welcome) + " " + usuario.getNome().toString() + " " + usuario.getSobrenome().toString());
        telefone.setText(getString(R.string.hint_telefone) + " " + "(" + Integer.parseInt(String.valueOf(usuario.getDdd())) + ")" + " " + Long.parseLong(String.valueOf(usuario.getTelefone())));
        endereco.setText(getString(R.string.endereco) + " " + usuario.getRua().toString() + " " + getString(R.string.num) + " " + Long.parseLong(String.valueOf(usuario.getNumero())));
        if (usuario.getComplemento() == null){
            complemento.setText(" ");
        }
        else {
            complemento.setText(getString(R.string.hint_complemento) + " " + usuario.getComplemento().toString());
        }
        bairro.setText(getString(R.string.hint_bairro) + " " + usuario.getBairro().toString());
        cidade.setText(getString(R.string.hint_cidade) + " " + usuario.getCidade().toString());
        estado.setText(getString(R.string.hint_estado) + " " + usuario.getEstado().toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editaUsuario = new Intent(UsuarioActivity.this, CadastroActivity.class);
                editaUsuario.putExtra("user", usuario);
                startActivity(editaUsuario);
            }
        });

        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deletaUsuario = new Intent(UsuarioActivity.this, ListActivity.class);
                daoUsuario[0] = new DaoUsuario(UsuarioActivity.this);
                daoUsuario[0].deletUsuario(usuario);
                Toast.makeText(getApplicationContext(), getString(R.string.delet_usuario) , Toast.LENGTH_LONG).show();
                startActivity(deletaUsuario);
                finish();
            }
        });

        garagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (garagem.isChecked()){
                    DaoUsuario garagemDao = new DaoUsuario(getApplicationContext());
                    garagemDao.updateGaragem(usuario, 1);
                }
                else {
                    DaoUsuario garagemDao =new DaoUsuario(getApplicationContext());
                    garagemDao.updateGaragem(usuario, 0);
                }
            }
        });
    }
}