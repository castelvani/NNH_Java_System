package Controle;

import Credenciais.PerfilDeAcesso;
import Fachada.Fachada;
import Model.Email;
import Model.Reserva;
import Model.Usuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioControle", urlPatterns = {"/login", "/logout", "/cadastro", "/inserirUsuario", "/excluirUsuario", "/alterarUsuario", ""
    + "/consultarUsuario", "/usuarioReservas", "/atualizarUsuario"})
public class UsuarioControle extends HttpServlet {

    Fachada f = new Fachada();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/login")) {
                request.getRequestDispatcher("login.jsp").forward(request, response);

                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/excluirUsuario")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                f.excluirUsuario(id);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    response.sendRedirect("./consultarUsuario");
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/consultarUsuario")) {
                HttpSession sessaoUsuario = request.getSession(true);
                List<Usuario> lista = f.consultarUsuario();
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("funcionario/consultarUsuario.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("erro.jsp");
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

            if (uri.equals(request.getContextPath() + "/login")) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Usuario usu = f.realizarLogin(email, senha);
                if (usu != null) {
                    HttpSession sessaoUsuario = request.getSession(true);
                    sessaoUsuario.setAttribute("idUsuario", usu.getId());
                    sessaoUsuario.setAttribute("perfil", usu.getPerfil());
                    sessaoUsuario.setAttribute("usuarioLogado", usu);
                    response.sendRedirect("jsp/logado.jsp");
                } else {

                    response.sendRedirect("./jsp/login.jsp");
                }

                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/logout")) {
                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.removeAttribute("usuarioLogado");
                response.sendRedirect("./");

                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/cadastro")) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String nome = request.getParameter("nome");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(request.getParameter("dtNasc"));
                java.sql.Date dtNasc = new java.sql.Date(date.getTime());
                Usuario usu = f.realizarCadastro(email, senha, nome, dtNasc);
                if (usu != null) {
                    //Envia email de cadastro realizado
                    Email emailUsuario = new Email();

                    emailUsuario.EmailCadastro(email);

                    request.getSession(true).setAttribute("usuarioLogado", f.getUsuario());
                    response.sendRedirect("./");
                } else {
                    request.setAttribute("msgErro", "Algo deu errado! Tente novamente.");
                    request.getRequestDispatcher("jsp/cadastro.jsp").forward(request, response);
                }

                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/inserirUsuario")) {
                HttpSession sessaoUsuario = request.getSession(true);
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(request.getParameter("dtNasc"));
                java.sql.Date dtNasc = new java.sql.Date(date.getTime());

                String senha = request.getParameter("senha");
                String perfil = request.getParameter("perfil");

                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    if (f.inserirUsuario(email, senha, nome, dtNasc, perfil)) {
                        request.getSession(true).setAttribute("usuarioLogado", f.getUsuario());
                        response.sendRedirect("funcionario/gerirUsuario.jsp");
                    } else {
                        request.setAttribute("msgErro", "Algo deu errado! Tente novamente.");
                        request.getRequestDispatcher("funcionario/gerirUsuario.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }

                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/alterarUsuario")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("usuario_id"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Usuario usuario = f.iniciarAlteracaoUsuario(id);

                    if (usuario != null) {
                        request.setAttribute("hospede", usuario);
                        request.getRequestDispatcher("./funcionario/alterarUsuario.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/atualizarUsuario")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String nome = request.getParameter("nome");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(request.getParameter("dtNasc"));
                java.sql.Date dtNasc = new java.sql.Date(date.getTime());
                String perfil = request.getParameter("perfil");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Usuario usuario = f.alterarUsuario(id, email, senha, nome, dtNasc, perfil);

                    if (usuario != null) {
                        request.getSession(true).setAttribute("hospede", usuario);
                        response.sendRedirect("./consultarUsuario");
                    } else {
                        request.setAttribute("msgErro", "Dados incorretos de login! Tente novamente.");
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/usuarioReservas")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = (int) sessaoUsuario.getAttribute("idUsuario");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    List<Reserva> listaReservaId = f.consultarReservaIdUsuario(id);

                    sessaoUsuario.setAttribute("perfil", sessaoUsuario.getAttribute("perfil"));
                    sessaoUsuario.setAttribute("listaReservaId", listaReservaId);

                    System.out.println(id);
                    request.getRequestDispatcher("jsp/hospedeReservas.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }

            } else {
                response.sendRedirect("erro.jsp");
            }
        } catch (Exception ex) {
            response.sendRedirect("erro.jsp");
        }
    }
}
