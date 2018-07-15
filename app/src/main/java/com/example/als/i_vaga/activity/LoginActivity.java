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

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText txtEmail, txtSenha;
    Button btnLogin;
    TextView textViewLinkCadastro;
    InputValidation inputValidation = new InputValidation(activity);
    DaoUsuario daoUsuario = new DaoUsuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtEmail = findViewById(R.id.editTextEmail);
        txtSenha = findViewById(R.id.editTextSenha);
        btnLogin = findViewById(R.id.buttonLogin);
        textViewLinkCadastro = findViewById(R.id.linkCadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewLinkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(abreCadastro);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                Intent abreUsuario = new Intent(LoginActivity.this, UsuarioActivity.class);
                if (!inputValidation.isInputEditTextFilled(txtEmail, getString(R.string.email_vazio))){
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(txtSenha, getString(R.string.senha_vazio))){
                    return;
                }

                Usuario logado = daoUsuario.checkEmailPassword(email,senha);
                if (logado != null){
                    abreUsuario.putExtra("usuario", logado);
                    Toast.makeText(getApplicationContext(), getString(R.string.login_ok), Toast.LENGTH_LONG).show();
                    startActivity(abreUsuario);
                    emptyLoginEditText();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.fazer_login), Toast.LENGTH_LONG).show();
                    emptyLoginEditText();
                }
            }
        });
    }

    private void emptyLoginEditText() {
        txtEmail.setText(null);
        txtSenha.setText(null);
    }
}