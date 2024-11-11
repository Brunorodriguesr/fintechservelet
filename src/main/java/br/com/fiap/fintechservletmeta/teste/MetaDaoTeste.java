package br.com.fiap.fintechservletmeta.teste;

import br.com.fiap.fintechservletmeta.dao.MetaDao;
import br.com.fiap.fintechservletmeta.exception.DBException;
import br.com.fiap.fintechservletmeta.factory.DaoFactory;
import br.com.fiap.fintechservletmeta.model.Meta;

import java.time.LocalDate;
import java.util.List;

public class MetaDaoTeste {

    public static void main(String[] args) {

        // Cadastrar uma meta
        MetaDao dao = DaoFactory.getProdutoDAO();

        Meta meta = new Meta(
                0,
                "Carro",
                100000.00,
                LocalDate.of(2026, 10, 21),
                "BMW"

        );

        try {
            dao.cadastrar(meta);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        //Buscar um produto pelo código e atualizar
        meta = dao.buscar(1);
        meta.setNome("Reforma");
        meta.setValor(1000.99);
        try {
            dao.atualizar(meta);
            System.out.println("Meta atualizada.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        //Listar as metas
        List<Meta> lista = dao.listar();
        for (Meta item : lista) {
            System.out.println(
                    item.getNome() + " " +
                            item.getDescricao() + " " +
                            item.getValor());
        }

        //Remover um produto
        try {
            dao.remover(1);
            System.out.println("Meta removida.");
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}

