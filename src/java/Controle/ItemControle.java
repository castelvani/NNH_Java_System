package Controle;

import Credenciais.PerfilDeAcesso;
import Fachada.Fachada;
import Model.Item;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "ItemControle", urlPatterns = {"/inserirItem", "/consultarItens", "/excluirItem", "/alterarItem", "/atualizarItem", "/relatorioItensMaisPedidos"})
public class ItemControle extends HttpServlet {

    Fachada f = new Fachada();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/consultarItens")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Item> itens = f.consultarItens();
                    if (itens != null) {
                        request.setAttribute("itens", itens);
                        request.getRequestDispatcher("./funcionario/consultarItens.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("jsp/login.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/excluirItem")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    f.excluirItem(id);
                    response.sendRedirect("./consultarItens");
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else {

            }

        } catch (Exception ex) {
            response.sendRedirect("erro.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/inserirItem")) {
                HttpSession sessaoUsuario = request.getSession(true);

                String nome = request.getParameter("nome");
                float preco = Float.parseFloat(request.getParameter("preco"));
                String tipo = request.getParameter("tipo");
                String descricao = request.getParameter("descricao");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    if (f.inserirItem(nome, preco, tipo, descricao)) {
                        request.getSession(true).setAttribute("itemInserido", "teste");
                        response.sendRedirect("funcionario/gerirPedido.jsp");
                    } else {
                        response.sendRedirect("funcionario/gerirPedido.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/alterarItem")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("item_id"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Item item = f.consultarIdItem(id);

                    if (item != null) {
                        request.setAttribute("item", item);
                        request.getRequestDispatcher("./funcionario/alterarItem.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/atualizarItem")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                float preco = Float.parseFloat(request.getParameter("preco"));
                String tipo = request.getParameter("tipo");
                String descricao = request.getParameter("descricao");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Item item = f.alterarItem(id, nome, preco, tipo, descricao);
                    if (item != null) {
                        request.getSession(true).setAttribute("itemAlterado", item);
                        response.sendRedirect("./consultarItens");
                    } else {
                        request.setAttribute("msgErro", "Dados incorretos de login! Tente novamente.");
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/relatorioItensMaisPedidos")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Item> itens = f.relatorioMaisPedidos();
                    request.setAttribute("itens", itens);
                    request.getRequestDispatcher("funcionario/relatorioItensMaisPedidos.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {

            }

        } catch (Exception ex) {
            response.sendRedirect("erro.jsp");
        }
    }

}
