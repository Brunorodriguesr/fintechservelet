package br.com.fiap.fintechservletmeta.dao.impl;

import br.com.fiap.fintechservletmeta.dao.ConnectionManager;
import br.com.fiap.fintechservletmeta.dao.MetaDao;
import br.com.fiap.fintechservletmeta.exception.DBException;
import br.com.fiap.fintechservletmeta.model.Meta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleMetaDao implements MetaDao {

    private Connection conexao;

    @Override
    public void cadastrar(Meta meta) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO TB_META " +
                    "(COD_META, NOME_META, DESC_META, " +
                    "VALOR_META, DATA_META) " +
                    "VALUES (SQ_TB_META.NEXTVAL, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, meta.getNome());
            stmt.setString(2, meta.getDescricao());
            stmt.setDouble(3, meta.getValor());
            stmt.setDate(4, Date.valueOf(meta.getDataMeta()));
            stmt.executeUpdate();

            System.out.println("Meta Cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void atualizar(Meta meta) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_META SET " +
                    "NOME_META = ?, " +
                    "DESC_META = ?, " +
                    "VALOR_META = ?, " +
                    "DATA_META = ? " +
                    "WHERE COD_META = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, meta.getNome());
            stmt.setString(2, meta.getDescricao());
            stmt.setDouble(3, meta.getValor());
            stmt.setDate(4, Date.valueOf(meta.getDataMeta()));
            stmt.setInt(5, meta.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remover(int codigo) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM TB_META WHERE COD_META = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Meta buscar(int id) {

        Meta meta = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_META WHERE COD_META = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("COD_META");
                String nome = rs.getString("NOME_META");
                String desc = rs.getString("DESC_META");
                double valor = rs.getDouble("VALOR_META");
                LocalDate data = rs.getDate("DATA_META").toLocalDate();

                meta = new Meta(codigo, nome, valor, data, desc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meta;
    }

    @Override
    public List<Meta> listar() {

        List<Meta> lista = new ArrayList<Meta>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_META";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_META");
                String nome = rs.getString("NOME_META");
                String desc = rs.getString("DESCRICAO_META");
                double valor = rs.getDouble("VALOR_META");
                java.sql.Date data = rs.getDate("DATA_META");
                LocalDate dataMeta = rs.getDate("DATA_META")
                        .toLocalDate();

                Meta meta =
                        new Meta(codigo, nome, valor, dataMeta, desc);
                lista.add(meta);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
