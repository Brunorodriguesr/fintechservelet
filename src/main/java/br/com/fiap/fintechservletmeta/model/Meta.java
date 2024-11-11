package br.com.fiap.fintechservletmeta.model;

import javax.swing.*;
import java.time.LocalDate;

public class Meta {

    private int codigo;
    private String nome;
    private double valor;
    private LocalDate dataMeta;
    private String descricao;

    public Meta() {

    }

    public Meta(int codigo, String nome, double valor,
                LocalDate dataMeta, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.dataMeta = dataMeta;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataMeta() {
        return dataMeta;
    }

    public void setDataMeta(LocalDate datMeta) {
        this.dataMeta = dataMeta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

