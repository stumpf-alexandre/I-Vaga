package com.example.als.i_vaga.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.als.i_vaga.R;
import com.example.als.i_vaga.model.Usuario;

public class DetalheActivity extends AppCompatActivity {
    TextView nome;
    TextView endereco;
    TextView complemento;
    TextView bairro;
    TextView cidade;
    TextView telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("user");
        nome = findViewById(R.id.nomeDetalhe);
        endereco = findViewById(R.id.enderecoDetalhe);
        complemento = findViewById(R.id.complementoDetalhe);
        bairro = findViewById(R.id.bairroDetalhe);
        cidade = findViewById(R.id.cidadeDetalhe);
        telefone = findViewById(R.id.telefoneDetalhe);

        nome.setText(getString(R.string.hint_nome) + ": " + usuario.getNome().toString() + " " + usuario.getSobrenome().toString());
        endereco.setText(getString(R.string.hint_rua) + ": " + usuario.getRua().toString() + ", " + getString(R.string.hint_numero) + ": " + String.valueOf(usuario.getNumero()).toString());
        complemento.setText(getString(R.string.hint_complemento) + ": " + usuario.getComplemento().toString());
        bairro.setText(getString(R.string.hint_bairro) + ": " + usuario.getBairro().toString());
        cidade.setText(getString(R.string.hint_cidade) + ": " + usuario.getCidade().toString() + ", " + getString(R.string.hint_estado) + ": " + usuario.getEstado().toString());
        telefone.setText("(" + String.valueOf(usuario.getDdd()).toString() + ") " + String.valueOf(usuario.getTelefone()).toString());
        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telefone.toString()));
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });
    }
}