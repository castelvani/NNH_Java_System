/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Credenciais.PerfilDeAcesso;
import Fachada.Fachada;
import Model.Email;
import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
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
 * @author alunocmc
 */
@WebServlet(name = "QuartoControle", urlPatterns = {"/inserirQuarto", "/excluirQuarto", "/alterarQuarto", "/consultarQuarto", "/atualizar"})
public class QuartoControle extends HttpServlet {

    Fachada f = new Fachada();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/excluirQuarto")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    f.excluirQuarto(id);

                    response.sendRedirect("./consultarQuarto");
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/consultarQuarto")) {
                HttpSession sessaoUsuario = request.getSession(true);

                List<Quarto> lista = f.consultarQuarto();
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    if (lista != null) {
                        request.setAttribute("lista", lista);
                        request.getRequestDispatcher("./funcionario/consultarQuarto.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("jsp/login.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {

            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/inserirQuarto")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int numero = Integer.parseInt(request.getParameter("numero"));
                float valorDiaria = Float.parseFloat(request.getParameter("valorDiaria"));
                String tipo = request.getParameter("tipo");
                String status = request.getParameter("status");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    if (f.inserirQuarto(numero, valorDiaria, tipo, status)) {
                        request.getSession(true).setAttribute("quartoInserido", f.getQuarto());
                        response.sendRedirect("funcionario/gerirQuarto.jsp");
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/alterarQuarto")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("quarto_id"));
                Quarto quarto = f.iniciarAlteracao(id);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    if (quarto != null) {
                        request.setAttribute("quarto", quarto);
                        request.getRequestDispatcher("./funcionario/alterarQuarto.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/atualizar")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                int numero = Integer.parseInt(request.getParameter("numero"));
                float valorDiaria = Float.parseFloat(request.getParameter("valorDiaria"));
                String tipo = request.getParameter("tipo");
                String status = request.getParameter("status");
                int desconto = Integer.parseInt(request.getParameter("desconto"));

                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {

                    //Não permite desconto negativo e nem a cima de 50%
                    if (desconto >= 51 || desconto < 0) {
                        desconto = 0;
                    }

                    Quarto quarto = f.alterarQuarto(id, numero, valorDiaria, tipo, status, desconto);

                    if (quarto != null) {
                        Email emailUsuario = new Email();

                        List<Usuario> UsuariosEmail = f.consultarEmailUsuarios();
                        if (desconto != 0) {
                            emailUsuario.EmailDesconto(UsuariosEmail, desconto);
                        }
                        request.getSession(true).setAttribute("quartoAlterado", quarto);
                        response.sendRedirect("./consultarQuarto");
                    } else {
                        request.setAttribute("msgErro", "Dados incorretos de login! Tente novamente.");
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
