/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Credenciais.PerfilDeAcesso;
import Fachada.Fachada;
import Model.Email;
import Model.Pedido;
import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "ReservaControle", urlPatterns = {"/verificaSessao", "/inserirReserva", "/consultarQuartosDisponiveis", ""
    + "/reservarQuarto", "/consultarReserva", "/alterarReserva", "/cancelarReserva", "/checkoutAntecipado", "/checkoutAntecipadoTela", ""
    + "/alterarReservaInsert", "/checkin", "/relatorioReservaMes", "/relatorioReservaAno"})
public class ReservaControle extends HttpServlet {

    Fachada f = new Fachada();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/reservarQuarto")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    int idQuarto = Integer.parseInt(request.getParameter("idQuarto"));
                    Quarto quarto = f.iniciarAlteracao(idQuarto);
                    if (quarto != null) {
                        request.setAttribute("quarto", quarto);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                    
                    String daterange = (String) sessaoUsuario.getAttribute("daterange");
                    request.getRequestDispatcher("jsp/reservarQuarto.jsp").forward(request, response);
                    PrintWriter pw = response.getWriter();
                    pw.print(daterange);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/consultarQuartosDisponiveis")) {
                //Converte datas de entrada e saida em string
                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
                String data_entrada = request.getParameter("dt_entrada");
                Date parsed1 = formatar.parse(data_entrada);
                java.sql.Date dt_entrada = new java.sql.Date(parsed1.getTime());

                String data_saida = request.getParameter("dt_saida");
                Date parsed2 = formatar.parse(data_saida);
                java.sql.Date dt_saida = new java.sql.Date(parsed2.getTime());
                System.out.println(dt_entrada);
                List<Reserva> listaReservados = f.consultarReservaDatas(dt_entrada, dt_saida);

                long dias = (long) f.calculaOrcamento(dt_saida, dt_entrada);

                HttpSession sessaoUsuario = request.getSession(true);

                //Se lista de reserva pela data desejada for nula, procura quartos
                if (listaReservados.size() == 0) {

                    List<Quarto> listaQuarto = f.consultarQuartosDisponiveis();

                    //Se lista de quartos não for nula, lista os quartos disponiveis
                    if (listaQuarto != null) {
                        request.setAttribute("listaQuarto", listaQuarto);

                        sessaoUsuario.setAttribute("dias", dias);
                        sessaoUsuario.setAttribute("dt_entrada", dt_entrada);
                        sessaoUsuario.setAttribute("dt_saida", dt_saida);

                        if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                            Usuario usuario = new Usuario();
                            sessaoUsuario.setAttribute("usuario", usuario.getId());
                        }
                        request.getRequestDispatcher("jsp/quartosDisponiveis.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("erro.jsp");
                    }
                } else {
                    //Para comparar com os quartos encontrados na reserva entre os dias
                    List<Quarto> listaQuartoParaReserva = f.consultarQuartosDisponiveis();
                    //Id dos que estão reservados
                    List<Integer> listaIdReservados = new ArrayList<>();
                    //Id de todos os quartos que existem e estão disponiveis
                    List<Integer> listaIdQuartos = new ArrayList<>();
                    if (listaQuartoParaReserva.size() != 0) {
                        //adiciona os reservados na lista
                        for (Reserva reservas : listaReservados) {
                            listaIdReservados.add(reservas.getQuarto().getId());
                        }
                        //adiciona os disponiveis na lista
                        for (Quarto quartos : listaQuartoParaReserva) {
                            listaIdQuartos.add(quartos.getId());
                        }

                        //Rotina para remover quartos que estão reservados da lista e formar uma lista apenas com os que estão livres
                        List<Integer> uniao = new ArrayList<>(listaIdReservados);
                        uniao.addAll(listaIdQuartos);
                        //Move os iguais para alista inter
                        List<Integer> iguais = new ArrayList<>(listaIdReservados);
                        iguais.retainAll(listaIdQuartos);
                        //Remove os iguais a lista inter
                        uniao.removeAll(iguais);

                        List<Quarto> listaQuartosFinais = new ArrayList<>();

                        //Guarda os quartos que sobraram na listaQuartosFinais
                        for (int i = 0; i < uniao.size(); i++) {
//                            List<Integer> testess = new ArrayList<>();
                            List<Quarto> quartosDisponiveis = f.consultarOutrosQuartos(uniao.get(i));
                            listaQuartosFinais.add(quartosDisponiveis.get(0));
                        }
                        request.setAttribute("listaQuartosFinais", listaQuartosFinais);

                        sessaoUsuario.setAttribute("dias", dias);
                        sessaoUsuario.setAttribute("dt_entrada", dt_entrada);
                        sessaoUsuario.setAttribute("dt_saida", dt_saida);

                        request.getRequestDispatcher("jsp/quartosDisponiveis.jsp").forward(request, response);
                    }
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/verificaSessao")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("idQuarto"));
                Quarto quarto = f.iniciarAlteracao(id);

                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    int idd = (int) sessaoUsuario.getAttribute("idUsuario");
                    Usuario usuario = f.iniciarAlteracaoUsuario(idd);
                    long dias = (long) sessaoUsuario.getAttribute("dias");

                    sessaoUsuario.setAttribute("dias", dias);
                    sessaoUsuario.setAttribute("idUsuario", id);
                    sessaoUsuario.setAttribute("usuario", usuario);
                    sessaoUsuario.setAttribute("quarto", quarto);
                    request.getRequestDispatcher("jsp/reservarQuarto.jsp").forward(request, response);

                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/consultarReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Reserva> reservas = f.consultarReserva();
                    request.setAttribute("reservas", reservas);
                    request.getRequestDispatcher("funcionario/consultarReserva.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/relatorioReservaAno")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int ano = Integer.parseInt(request.getParameter("ano"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Reserva> lista = f.relatiorioReservaAno(ano);
                    if (lista != null) {
                        request.setAttribute("ano", ano);
                        request.setAttribute("lista", lista);
                        request.getRequestDispatcher("funcionario/relatorioReservaAno.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("jsp/login.jsp");
                    }
                    response.sendRedirect("jsp/login.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/relatorioReservaMes")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int mes = Integer.parseInt(request.getParameter("mes"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    List<Reserva> lista = f.relatiorioReservaMes(mes);
                    if (lista != null) {
                        request.setAttribute("mes", mes);
                        request.setAttribute("lista", lista);
                        request.getRequestDispatcher("funcionario/relatorioReservaMes.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("jsp/login.jsp");
                    }
                    response.sendRedirect("jsp/login.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/cancelarReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                String situacao = "Cancelada";

                Reserva reserva = f.cancelarReserva(id_reserva, situacao);

                if (reserva != null) {
                    if (sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.COMUM) {

                        sessaoUsuario.setAttribute("aviso", "Cancelamento de reserva realizado com sucesso!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("./consultarReserva?");
                    }
                } else {
                    response.sendRedirect("error.jsp");
                }
                /*
                ===============================================================================
                 */
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

            if (uri.equals(request.getContextPath() + "/inserirReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                Quarto quarto = new Quarto();
                Usuario usuario = new Usuario();

//                PrintWriter pw = response.getWriter();
//                pw.print(sessaoUsuario.getAttribute("usuarioLogado"));
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                int idQuarto = Integer.parseInt(request.getParameter("idQuarto"));
                String tipo = request.getParameter("tipo");
                sessaoUsuario.setAttribute("idUsuario", idUsuario);
                int desconto = Integer.parseInt(request.getParameter("desconto"));
                //Converte string para date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String data_entrada = request.getParameter("dt_entrada");
                Date parsed1 = format.parse(data_entrada);
                java.sql.Date dt_entrada = new java.sql.Date(parsed1.getTime());

                String data_saida = request.getParameter("dt_saida");
                Date parsed2 = format.parse(data_saida);
                java.sql.Date dt_saida = new java.sql.Date(parsed2.getTime());

                String situacao = "Reservada";

                float orcamento = Float.parseFloat(request.getParameter("orcamentoDesconto"));

                if (desconto == 0) {
                    if (tipo.toString().equals("Inteiro")) {
                        orcamento = orcamento - (orcamento / 100 * 10);
                    }
                }

                usuario.setId(idUsuario);
                quarto.setId(idQuarto);

                if (f.inserirReserva(usuario, quarto, dt_entrada, dt_saida, situacao, orcamento, tipo)) {
                    //Envia email de confirmacao de reserva
                    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
                    String emailReservaConcluida = usuarioLogado.getEmail();

                    Email emailUsuario = new Email();

                    emailUsuario.EmailReserva(emailReservaConcluida, dt_entrada, dt_saida);
                    PrintWriter pw = response.getWriter();
                    pw.print(emailReservaConcluida);
                    sessaoUsuario.setAttribute("aviso", "Reserva realizada com sucesso!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    response.sendRedirect("erro.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/checkoutAntecipado")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {

                    int idReserva = Integer.parseInt(request.getParameter("idReserva"));
                    String situacao = request.getParameter("situacao");
                    System.out.println(idReserva);
                    f.CheckoutAntecipadoReserva(idReserva, situacao);

                    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
                    List<Reserva> listaReservaId = (List<Reserva>) sessaoUsuario.getAttribute("listaReservaId");

                    int numeroQuarto = listaReservaId.get(0).getQuarto().getNumero();
                    String emailUsuario = usuarioLogado.getEmail();
                    Email email = new Email();

                    email.EmailCheckoutAntecipado(emailUsuario, numeroQuarto);
                    if (situacao.toString().equals("Finalizada")) {
                        sessaoUsuario.setAttribute("aviso", "Checkout realizado com sucesso!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        sessaoUsuario.setAttribute("aviso", "Checkout antecipado realizado com sucesso!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/checkoutAntecipadoTela")) {
                HttpSession sessaoUsuario = request.getSession(true);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {
                    int idReserva = Integer.parseInt(request.getParameter("idReserva"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String data_entrada = request.getParameter("dt_entrada");
                    Date parsed1 = format.parse(data_entrada);
                    java.sql.Date dt_entrada = new java.sql.Date(parsed1.getTime());

                    String data_saida = request.getParameter("dt_saida");
                    Date parsed2 = format.parse(data_saida);
                    java.sql.Date dt_saida = new java.sql.Date(parsed2.getTime());
                    double diaria = Double.parseDouble(request.getParameter("diaria"));
                    double desconto = Double.parseDouble(request.getParameter("desconto"));
                    long dias = (long) f.calculaOrcamento(dt_saida, dt_entrada);
                    String tipo_pagamento = request.getParameter("tipo_pagamento");
                    List<Pedido> pedidos = f.consultarPedidoReserva(idReserva);
                    String situacao = request.getParameter("situacao");
                    request.setAttribute("situacao", situacao);
                    request.setAttribute("pedidos", pedidos);
                    request.setAttribute("tipo_pagamento", tipo_pagamento);
                    request.setAttribute("dias", dias);
                    request.setAttribute("idReserva", idReserva);
                    request.setAttribute("dt_entrada", dt_entrada);
                    request.setAttribute("dt_saida", dt_saida);
                    request.setAttribute("diaria", diaria);
                    request.setAttribute("desconto", desconto);
                    request.getRequestDispatcher("jsp/checkoutAntecipado.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/alterarReserva")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("reserva_id"));
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Reserva reserva = f.iniciarAlteracaoReserva(id);
                    List<Quarto> quartos = f.consultarQuartosDisponiveis();
                    List<Usuario> usuarios = f.consultarUsuario();

                    if (reserva != null) {
                        request.setAttribute("id", id);
                        request.setAttribute("hospedes", usuarios);
                        request.setAttribute("quartos", quartos);
                        request.setAttribute("reserva", reserva);
                        request.getRequestDispatcher("./funcionario/alterarReserva.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/alterarReservaInsert")) {
                HttpSession sessaoUsuario = request.getSession(true);
                int id = Integer.parseInt(request.getParameter("id"));
                String reservaSituacao = request.getParameter("reservaSituacao");
                String hospedeNome = request.getParameter("hospedeNome");
                int quartoNumero = Integer.parseInt(request.getParameter("quartoNumero"));

                String quarto_tipo = request.getParameter("quarto_tipo");
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                int idQuarto = Integer.parseInt(request.getParameter("idQuarto"));

                Usuario usuario = new Usuario();
                Quarto quarto = new Quarto();

                usuario.setId(idUsuario);
                usuario.setNome(hospedeNome);
                quarto.setTipo(quarto_tipo);
                quarto.setId(idQuarto);
                quarto.setNumero(quartoNumero);
                if (sessaoUsuario.getAttribute("usuarioLogado") != null && sessaoUsuario.getAttribute("perfil") == PerfilDeAcesso.ADMINISTRADOR) {
                    Reserva reserva = f.alterarReserva(id, reservaSituacao, quarto, usuario);

                    if (reserva != null) {
//                    request.setAttribute("reserva", reserva);
//                    request.getRequestDispatcher("./funcionario/alterarReserva.jsp").forward(request, response);
                        response.sendRedirect("./consultarReserva?");
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
                /*
                ===============================================================================
                 */
            } else if (uri.equals(request.getContextPath() + "/checkin")) {
                HttpSession sessaoUsuario = request.getSession(true);

                if (sessaoUsuario.getAttribute("usuarioLogado") != null) {

                    int idReserva = Integer.parseInt(request.getParameter("idReserva"));
                    f.Checkin(idReserva);

                    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
                    List<Reserva> listaReservaId = (List<Reserva>) sessaoUsuario.getAttribute("listaReservaId");

                    int numeroQuarto = listaReservaId.get(0).getQuarto().getNumero();
                    String emailUsuario = usuarioLogado.getEmail();
                    Email email = new Email();

                    email.EmailCheckin(emailUsuario, numeroQuarto);
                    sessaoUsuario.setAttribute("aviso", "Checkin realizado com sucesso!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    response.sendRedirect("jsp/login.jsp");
                }
            }
        } catch (Exception ex) {
            response.sendRedirect("erro.jsp");
        }
    }
}
