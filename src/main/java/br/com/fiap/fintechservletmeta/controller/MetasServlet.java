package br.com.fiap.fintechservletmeta.controller;

import br.com.fiap.fintechservletmeta.dao.MetaDao;
import br.com.fiap.fintechservletmeta.exception.DBException;
import br.com.fiap.fintechservletmeta.factory.DaoFactory;
import br.com.fiap.fintechservletmeta.model.Meta;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet ("/meta")
public class MetasServlet extends HttpServlet {

    private MetaDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getProdutoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        String nome = req.getParameter("nome");
        String descricao = String.valueOf(req.getParameter("descricao"));
        double valor = Double.valueOf(req.getParameter("valor"));
        LocalDate data = LocalDate.parse(req.getParameter("data"));

        Meta meta = new Meta(
                0,
                nome,
                valor,
                data,
                descricao
        );


            dao.cadastrar(meta);
            req.setAttribute("mensagem", "Meta cadastrada com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar meta");

            req.getRequestDispatcher("cadastro-meta.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        switch (acao){
            case "listar":
                List<Meta> lista = dao.listar();
                req.setAttribute("metas", lista);
                req.getRequestDispatcher("lista-meta.jsp").forward(req, resp);
                break;
            case "abrir-form-edicao":
                int id = Integer.parseInt(req.getParameter("codigo"));
                Meta meta = dao.buscar(id);
                req.setAttribute("meta", meta);
                req.getRequestDispatcher("editar-produto.jsp")
                        .forward(req, resp);
        }



    }
}