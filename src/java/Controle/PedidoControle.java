/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Credenciais.PerfilDeAcesso;
import Fachada.Fachada;
import Model.Item;
import Model.Pedido;
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
 * @author Gabriel
 */
@WebServlet(name = "PedidoControle", urlPatterns = {"/consultarPedidos", "/inserirPedido", "/consultarQuartoPedido", "/cancelarPedido", "/gerenciarPedidos", ""
    + "/gerenciarPedidosReserva", "/atualizarPedido", "/cancelarPedido", "/consultarPedidoId", "/cancelarPedidoReserva"})
public class PedidoControle extends HttpServlet {

    Fachada f = new Fachada();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/cancelarPedido")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {

                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/gerenciarPedidos")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Pedido> pedidos = f.consultarPedidoReserva(id_reserva);
                    request.setAttribute("pedidos", pedidos);
                    request.getRequestDispatcher("./funcionario/gerirPedidos.jsp").forward(request, response);
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

            if (uri.equals(request.getContextPath() + "/consultarPedidos")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id_usuario"));
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                int id_quarto = Integer.parseInt(request.getParameter("id_quarto"));
                List<Item> itens = f.consultarItens();
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    if (itens != null) {
                        List<Pedido> pedidos = f.consultarPedidoReserva(id_reserva);
                        //se for 0 
                        int mensagemVazio = 0;
                        if (pedidos == null || pedidos.isEmpty()) {
                            //Mensagem de pedidos vazios
                            mensagemVazio = 1;
                        } else {
                            request.setAttribute("pedidos", pedidos);
                        }
                        request.setAttribute("mensagemVazio", mensagemVazio);
                        request.setAttribute("itens", itens);
                        request.setAttribute("id_reserva", id_reserva);
                        request.setAttribute("id_quarto", id_quarto);
                        request.setAttribute("id", id);
                        request.getRequestDispatcher("/jsp/hospedePedidos.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/consultarQuartoPedido")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = (int) sessaoUsuario.getAttribute("idUsuario");
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    List<Reserva> listaReservaIdPedido = f.consultarReservaIdUsuarioPedido(id);
                    sessaoUsuario.setAttribute("listaReservaIdPedido", listaReservaIdPedido);
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("/jsp/hospedeQuartosPedido.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/inserirPedido")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id_item = Integer.parseInt(request.getParameter("id_item"));
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    f.inserirPedido(id_item, quantidade, id_reserva);
                    List<Pedido> pedidos = f.consultarPedidoReserva(id_reserva);
                    List<Item> itens = f.consultarItens();
                    //se for 0 
                    int mensagemVazio = 0;
                    if (pedidos != null) {
                        System.out.println(pedidos);
                        request.setAttribute("pedidos", pedidos);
                    } else {
                        //Mensagem de pedidos vazios
                        mensagemVazio = 1;
                    }
                    request.setAttribute("id", id_usuario);
                    request.setAttribute("id_reserva", id_reserva);
                    request.setAttribute("mensagemVazio", mensagemVazio);
                    request.setAttribute("itens", itens);
                    request.getRequestDispatcher("/jsp/hospedePedidos.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }

            } else if (uri.equals(request.getContextPath() + "/cancelarPedido")) {
                HttpSession sessaoUsuario = request.getSession(true);
                Item item = new Item();
                Reserva reserva = new Reserva();
                int id_item = Integer.parseInt(request.getParameter("id_item"));
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                item.setId(id_item);
                reserva.setId(id_reserva);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    f.cancelarPedido(id_pedido);
                    List<Pedido> pedidos = f.consultarPedidoReserva(id_reserva);
                    List<Item> itens = f.consultarItens();
                    //se for 0 
                    int mensagemVazio = 0;
                    if (pedidos != null) {
                        System.out.println(pedidos);
                        request.setAttribute("pedidos", pedidos);
                    } else {
                        //Mensagem de pedidos vazios
                        mensagemVazio = 1;
                    }
                    request.setAttribute("mensagemVazio", mensagemVazio);
                    request.setAttribute("id", id_usuario);
                    request.setAttribute("id_reserva", id_reserva);
                    request.setAttribute("itens", itens);
                    request.getRequestDispatcher("/jsp/hospedePedidos.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/gerenciarPedidosReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Reserva> reservas = f.consultarPedidosReserva();
                    request.setAttribute("reservas", reservas);
                    request.getRequestDispatcher("./funcionario/gerirPedidoReserva.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/atualizarPedido")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
                    String status = request.getParameter("status");
                    int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                    Pedido pedido = f.atualizarPedido(status, id_pedido);

                    if (pedido != null) {
                        response.sendRedirect("./gerenciarPedidos?id_reserva=" + id_reserva);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }

            } else if (uri.equals(request.getContextPath() + "/consultarPedidoId")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
                    int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                    Pedido pedido = f.consultarPedidoId(id_pedido);

                    if (pedido != null) {
                        request.setAttribute("id_reserva", id_reserva);
                        request.setAttribute("pedido", pedido);
                        request.getRequestDispatcher("./funcionario/atualizarPedido.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (uri.equals(request.getContextPath() + "/cancelarPedidoReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
                    int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                    Pedido pedido = f.cancelarPedido(id_pedido);
                    if (pedido != null) {
                        request.setAttribute("id_reserva", id_reserva);
                        request.setAttribute("pedido", pedido);
                        response.sendRedirect("./gerenciarPedidos?id_reserva=" + id_reserva);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                }else{
                    response.sendRedirect("index.jsp");
                }
            } else {
                
            }
        } catch (Exception e) {
        }
    }

}
