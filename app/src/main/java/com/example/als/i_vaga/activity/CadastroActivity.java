package com.example.als.i_vaga.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.als.i_vaga.R;
import com.example.als.i_vaga.helper.InputValidation;
import com.example.als.i_vaga.model.Usuario;
import com.example.als.i_vaga.sql.DaoUsuario;

public class CadastroActivity extends AppCompatActivity {
    EditText txtNome, txtSobrenome, txtEmail, txtDdd, txtTelefone, txtSenha, txtConfirmar, txtRua, txtNumero, txtComplemento, txtBairro, txtCidade, txtEstado;
    Button btnCadastro;
    TextView textViewLinkLogin;
    DaoUsuario usuarioDao;
    Usuario usuario, editUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usuario = new Usuario();
        usuarioDao = new DaoUsuario(this);
        Intent intent = getIntent();
        editUsuario = (Usuario) intent.getSerializableExtra("user");

        txtNome = findViewById(R.id.editTextNome);
        txtSobrenome = findViewById(R.id.editTextSobrenome);
        txtEmail = findViewById(R.id.editTextEmaill);
        txtDdd = findViewById(R.id.editTextDdd);
        txtTelefone = findViewById(R.id.editTextTelefone);
        txtSenha = findViewById(R.id.editTextSenhaa);
        txtConfirmar = findViewById(R.id.editTextConfirmar);
        txtRua = findViewById(R.id.editTextRua);
        txtNumero = findViewById(R.id.editTextNumero);
        txtComplemento = findViewById(R.id.editTextComplemento);
        txtBairro = findViewById(R.id.editTextBairro);
        txtCidade = findViewById(R.id.editTextCidade);
        txtEstado = findViewById(R.id.editTextEstado);
        btnCadastro = findViewById(R.id.buttonCadastro);
        textViewLinkLogin = findViewById(R.id.linkLogin);

        textViewLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreLogin = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(abreLogin);
            }
        });

        if (editUsuario != null) {
            btnCadastro.setText(getString(R.string.text_button_modificar));
            txtNome.setText(editUsuario.getNome());
            txtSobrenome.setText(editUsuario.getSobrenome());
            txtEmail.setText(editUsuario.getEmail());
            txtDdd.setText(String.valueOf(editUsuario.getDdd()));
            txtTelefone.setText(String.valueOf(editUsuario.getTelefone()));
            txtSenha.setText(editUsuario.getSenha());
            txtConfirmar.setText(editUsuario.getSenha());
            txtRua.setText(editUsuario.getRua());
            txtNumero.setText(String.valueOf(editUsuario.getNumero()));
            txtComplemento.setText(editUsuario.getComplemento());
            txtBairro.setText(editUsuario.getBairro());
            txtCidade.setText(editUsuario.getCidade());
            txtEstado.setText(editUsuario.getEstado());

            usuario.setId(editUsuario.getId());
        } else {
            btnCadastro.setText(getString(R.string.text_button_cadastrar));
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputValidation inputValidation = new InputValidation(CadastroActivity.this);
                usuario.setNome(txtNome.getText().toString());
                usuario.setSobrenome(txtSobrenome.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setDdd(Integer.parseInt(txtDdd.getText().toString()));
                usuario.setTelefone(Long.parseLong(txtTelefone.getText().toString()));
                usuario.setSenha(txtSenha.getText().toString());
                if (txtSenha.getText().toString().equals(txtConfirmar.getText().toString())) {
                    usuario.setSenha(txtSenha.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.comparar_senha), Toast.LENGTH_LONG).show();
                    txtSenha.setText(" ");
                    txtConfirmar.setText(" ");
                }
                usuario.setRua(txtRua.getText().toString());
                usuario.setNumero(Long.parseLong(txtNumero.getText().toString()));
                if (txtComplemento.getText().toString().length() != 0) {
                    usuario.setComplemento(txtComplemento.getText().toString());
                } else {
                    usuario.setComplemento(" ");
                }
                usuario.setBairro(txtBairro.getText().toString());
                usuario.setCidade(txtCidade.getText().toString());
                usuario.setEstado(txtEstado.getText().toString());
                if (!inputValidation.isInputEditTextFilled(txtNome, getString(R.string.erro_nome))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtSobrenome, getString(R.string.erro_sobrenome))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtEmail, getString(R.string.erro_email))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtDdd, getString(R.string.erro_ddd))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtTelefone, getString(R.string.erro_telefone))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtSenha, getString(R.string.erro_senha))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtConfirmar, getString(R.string.erro_confirmar))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtRua, getString(R.string.erro_rua))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtNumero, getString(R.string.erro_numero))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtBairro, getString(R.string.erro_bairro))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtCidade, getString(R.string.erro_cidade))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtEstado, getString(R.string.erro_estado))) {
                    return;
                }
                Intent abreLogin = new Intent(CadastroActivity.this, LoginActivity.class);
                if (btnCadastro.getText().toString().equals(getString(R.string.text_button_cadastrar))) {
                    boolean checkout = usuarioDao.checkEmail(txtEmail.getText().toString());
                    if (checkout == true) {
                        usuarioDao.addUsuario(usuario);
                        Toast.makeText(getApplicationContext(), getString(R.string.text_cadastro), Toast.LENGTH_LONG).show();
                        startActivity(abreLogin);
                        emptyEditText();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.email_existente), Toast.LENGTH_LONG).show();
                        emptyEditText();
                    }
                } else {
                    usuarioDao.editUsuario(usuario);
                    Toast.makeText(getApplicationContext(), getString(R.string.text_cadastro), Toast.LENGTH_LONG).show();
                    startActivity(abreLogin);
                    emptyEditText();
                    finish();
                }
            }
        });
    }

    private void emptyEditText() {
        txtNome.setText(null);
        txtSobrenome.setText(null);
        txtEmail.setText(null);
        txtDdd.setText(null);
        txtTelefone.setText(null);
        txtSenha.setText(null);
        txtConfirmar.setText(null);
        txtRua.setText(null);
        txtNumero.setText(null);
        txtComplemento.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        txtEstado.setText(null);
    }
}