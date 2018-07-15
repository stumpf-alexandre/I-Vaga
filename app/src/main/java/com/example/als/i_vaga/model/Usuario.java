package com.example.als.i_vaga.model;

import java.io.Serializable;

public class Usuario implements Serializable{
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private int ddd;
    private long telefone;
    private String senha;
    private String rua;
    private long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean garagem;

    public Usuario(){}

    public Usuario(String nome, String sobrenome, String email, int ddd, long telefone, String senha, String rua, long numero, String complemento, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.ddd = ddd;
        this.telefone = telefone;
        this.senha = senha;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Usuario(long id, String nome, String sobrenome, String email, int ddd, long telefone, String senha, String rua, long numero, String complemento, String bairro, String cidade, String estado, boolean garagem) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.ddd = ddd;
        this.telefone = telefone;
        this.senha = senha;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.garagem = garagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public String setSenha(String senha) {
        this.senha = senha;
        return senha;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getGaragem(){
        return garagem;
    }

    public void setGaragem(boolean garagem){
        this.garagem = garagem;
    }
}