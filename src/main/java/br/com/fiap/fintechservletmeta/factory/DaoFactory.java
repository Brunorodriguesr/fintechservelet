package br.com.fiap.fintechservletmeta.factory;

import br.com.fiap.fintechservletmeta.dao.MetaDao;
import br.com.fiap.fintechservletmeta.dao.impl.OracleMetaDao;
import br.com.fiap.fintechservletmeta.model.Meta;

public class DaoFactory {

    public static MetaDao getProdutoDAO() {
        return new OracleMetaDao();
    }
}
