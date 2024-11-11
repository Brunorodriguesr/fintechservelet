package br.com.fiap.fintechservletmeta.dao;

import br.com.fiap.fintechservletmeta.exception.DBException;
import br.com.fiap.fintechservletmeta.model.Meta;

import java.util.List;

public interface MetaDao {

    void cadastrar(Meta meta) throws DBException;
    void atualizar(Meta meta) throws DBException;
    void remover(int codigo) throws DBException;
    Meta buscar(int id);
    List<Meta> listar();


}
