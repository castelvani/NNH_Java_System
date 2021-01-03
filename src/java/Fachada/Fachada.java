/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;

import DAO.ReservaDAO;
import Credenciais.PerfilDeAcesso;
import DAO.ItemDAO;
import DAO.PedidoDAO;
import DAO.QuartoDAO;
import DAO.UsuarioDAO;
import Model.Item;
import Model.Pedido;
import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunocmc
 */
public class Fachada implements IFachada {

    @Override
    public Usuario realizarLogin(String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioDAO dao = new UsuarioDAO();

        try {
            usuario = dao.validarLogin(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }

    }

    @Override
    public Usuario realizarCadastro(String email, String senha, String nome, Date dtNasc) {
        Usuario usuario = new Usuario();
        usuario.setDtNasc(dtNasc);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setPerfil(PerfilDeAcesso.COMUM);

        UsuarioDAO dao = new UsuarioDAO();
        try {
            usuario = dao.cadastrar(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }
    }

    @Override
    public boolean inserirUsuario(String email, String senha, String nome, Date dtNasc, String perfil) {
        Usuario usuario = new Usuario();
        usuario.setDtNasc(dtNasc);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        if (perfil.equalsIgnoreCase("ADMINISTRADOR")) {
            usuario.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
        } else {
            usuario.setPerfil(PerfilDeAcesso.COMUM);
        }

        UsuarioDAO dao = new UsuarioDAO();
        try {
            usuario = dao.cadastrar(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (usuario == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        return usuario;
    }

    @Override
    public boolean inserirQuarto(int numero, Float valorDiaria, String tipo, String status) {
        Quarto quarto = new Quarto();
        quarto.setNumero(numero);
        quarto.setValorDiaria(valorDiaria);
        quarto.setTipo(tipo);
        quarto.setStatus(status);

        QuartoDAO dao = new QuartoDAO();
        try {
            quarto = dao.Inserir(quarto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (quarto == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void excluirQuarto(int id) {
        QuartoDAO dao = new QuartoDAO();
        try {
            dao.Excluir(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Item consultarIdItem(int id) {
        Item item = new Item();
        item.setId(id);
        ItemDAO dao = new ItemDAO();
        try {
            item = dao.consultarIdItem(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }
    
    @Override
    public Quarto iniciarAlteracao(int id) {
        Quarto quarto = new Quarto();
        quarto.setId(id);
        QuartoDAO dao = new QuartoDAO();
        try {
            quarto = dao.ConsultarIdQuarto(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quarto;
    }

    @Override
    public Quarto alterarQuarto(int id, int numero, float valordiaria, String tipo, String status, int desconto) {
        Quarto quarto = new Quarto();
        quarto.setId(id);
        quarto.setNumero(numero);
        quarto.setValorDiaria(valordiaria);
        quarto.setTipo(tipo);
        quarto.setStatus(status);
        quarto.setDesconto(desconto);

        QuartoDAO dao = new QuartoDAO();
        try {
            dao.Alterar(quarto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        consultarQuarto();
        return quarto;
    }

    @Override
    public Quarto alterarStatusQuarto(int idQuarto, String status) {
        Quarto quarto = new Quarto();
        quarto.setId(idQuarto);
        quarto.setStatus(status);

        QuartoDAO dao = new QuartoDAO();

        try {
            dao.AlterarStatus(quarto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
//        consultarQuarto();
        return quarto;
    }

    @Override
    public List<Quarto> consultarQuarto() {
        QuartoDAO dao = new QuartoDAO();
        List<Quarto> lista;
        lista = null;
        try {
            lista = dao.Consultar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Quarto getQuarto() {
        Quarto quarto = new Quarto();
        return quarto;
    }

    @Override
    public void excluirUsuario(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            dao.ExcluirUsuario(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario alterarUsuario(int id, String email, String senha, String nome, Date dtNasc, String perfil) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setDtNasc(dtNasc);
        if (perfil.equalsIgnoreCase("ADMINISTRADOR")) {
            usuario.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
        } else {
            usuario.setPerfil(PerfilDeAcesso.COMUM);
        }

        UsuarioDAO dao = new UsuarioDAO();
        try {
            dao.AlterarUsuario(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        consultarUsuario();
        return usuario;
    }

    @Override
    public Usuario iniciarAlteracaoUsuario(int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        UsuarioDAO dao = new UsuarioDAO();
        try {
            usuario = dao.ConsultarIdUsuario(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public List<Usuario> consultarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista;
        lista = null;
        try {
            lista = dao.ConsultarUsuarios();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Quarto> consultarQuartosDisponiveis() {
        QuartoDAO dao = new QuartoDAO();

        List<Quarto> lista;

        lista = null;
        try {
            lista = dao.ConsultarQuartosDisponiveis();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void inserirPedido(int id_item, int quantidade, int id_reserva){        
        PedidoDAO dao = new PedidoDAO();
        try {
            dao.inserirPedido(id_item, quantidade,id_reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean inserirPedidoReserva(int id_reserva){
        PedidoDAO dao = new PedidoDAO();
        boolean teste = false;
        try {
            teste = dao.inserirPedidoReserva(id_reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    @Override
    public List<Pedido> consultarPedidos(){
        PedidoDAO dao = new PedidoDAO();
        List<Pedido> pedidos;
        pedidos = null;
        
        try {
            pedidos = dao.consultarPedidos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }
    
    @Override
    public List<Pedido> consultarPedidoReserva(int id_reserva){
        PedidoDAO dao = new PedidoDAO();
        List<Pedido> pedidos;
        pedidos = null;
        
        try {
            pedidos = dao.consultarPedidoReserva(id_reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }
    
    @Override
    public Pedido consultarPedidoId(int id_pedido){
        Pedido pedido = new Pedido();
        PedidoDAO dao = new PedidoDAO();
        try {
            pedido = dao.consultarPedidoId(id_pedido);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    @Override
    public List<Item> relatorioMaisPedidos(){
        ItemDAO dao = new ItemDAO();
        List<Item> itens;
        itens = null;
        try {
            itens = dao.relatorioMaisPedidos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itens;
    }
    
    @Override
    public Pedido cancelarPedido(int id_pedido){
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = new Pedido();
        pedido.setId(id_pedido);
        try {
            dao.cancelarPedido(pedido);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    @Override
    public Pedido atualizarPedido(String status, int id_pedido){
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = new Pedido();
        pedido.setId(id_pedido);
        pedido.setStatus(status);
        try {
            dao.atualizarPedido(pedido);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    @Override
    public boolean inserirReserva(Usuario usuario, Quarto quarto, Date dt_entrada, Date dt_saida, String situacao, float orcamento, String tipo) {
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setQuarto(quarto);
//        reserva.getQuarto().setId(idQuarto);
//        reserva.getUsuario().setId(idUsuario);
//        reserva.setDt_reserva();
        reserva.setDt_entrada(dt_entrada);
        reserva.setDt_saida(dt_saida);
        reserva.setSituacao(situacao);
        reserva.setOrcamento(orcamento);
        reserva.setTipoPagamento(tipo);

        ReservaDAO dao = new ReservaDAO();
        try {
            reserva = dao.cadastrarReserva(reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (reserva == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Reserva cancelarReserva(int id_reserva, String situacao) {
        Reserva reserva = new Reserva();
        reserva.setId(id_reserva);
        reserva.setSituacao(situacao);
        ReservaDAO dao = new ReservaDAO();

        try {
            dao.CancelarReserva(reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reserva;
    }

    @Override
    public Reserva alterarReserva(int idReserva, String reservaSituacao, Quarto quarto, Usuario usuario) {
        Reserva reserva = new Reserva();

        reserva.setId(idReserva);
        reserva.setSituacao(reservaSituacao);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        ReservaDAO dao = new ReservaDAO();
        try {
            dao.AlterarReserva(reserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        consultarUsuario();
        return reserva;
    }

    @Override
    public Reserva iniciarAlteracaoReserva(int id) {
        Reserva reserva = new Reserva();
        reserva.setId(id);
        ReservaDAO dao = new ReservaDAO();
        try {
            reserva = dao.ConsultarIdReserva(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reserva;
    }

    @Override
    public List<Reserva> consultarPedidosReserva(){
        PedidoDAO dao = new PedidoDAO();
        List<Reserva> reservas;
        reservas = null;
        try {
            reservas = dao.consultarPedidosReserva();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }
    
    @Override
    public List<Reserva> consultarReserva() {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> lista;
        lista = null;
        try {
            lista = dao.ConsultarReservas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Reserva> relatiorioReservaMes(int mes) {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> lista;
        lista = null;
        try {
            lista = dao.RelatiorioReservaMes(mes);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Reserva> relatiorioReservaAno(int ano) {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> lista;
        Pedido pedido = new Pedido();
        lista = null;
        try {
            lista = dao.RelatiorioReservaAno(ano);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Reserva> consultarReservaDatas(Date dt_entrada, Date dt_saida) {
        ReservaDAO dao = new ReservaDAO();
        Reserva reserva = new Reserva();
        reserva.setDt_entrada(dt_entrada);
        reserva.setDt_saida(dt_saida);
        List<Reserva> lista;
        lista = null;
        try {
            lista = dao.ConsultarReservasPorData(dt_entrada, dt_saida);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Quarto> consultarOutrosQuartos(int idQuartosReservados) {
        Quarto quarto = new Quarto();
        quarto.setId(idQuartosReservados);
        QuartoDAO dao = new QuartoDAO();
        List<Quarto> quartos;
        quartos = null;
        try {
            quartos = dao.ConsultarOutrosQuartos(idQuartosReservados);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quartos;
    }

    @Override
    public List<Reserva> consultarReservaIdUsuario(int id) {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> reservas;
        reservas = null;
        try {
            reservas = dao.ConsultarReservasIdUsuario(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }
    
    @Override
    public List<Reserva> consultarReservaIdUsuarioPedido(int id) {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> reservas;
        reservas = null;
        try {
            reservas = dao.ConsultarReservasIdUsuarioPedido(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }

    @Override
    public void CheckoutAntecipadoReserva(int idReserva, String situacao) {
        ReservaDAO dao = new ReservaDAO();
        try {
            dao.CheckoutAntecipadoReserva(idReserva, situacao);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Checkout(int idReserva) {
        ReservaDAO dao = new ReservaDAO();
        try {
            dao.Checkout(idReserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Checkin(int idReserva) {
        ReservaDAO dao = new ReservaDAO();
        try {
            dao.Checkin(idReserva);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public float calculaOrcamento(Date dt_saida, Date dt_entrada) {
        float diff = dt_saida.getTime() - dt_entrada.getTime();
        //numero de dias entre as datas
        float dias = (TimeUnit.DAYS.convert((long) diff, TimeUnit.MILLISECONDS));
        System.out.println(dias);
        return dias;
    }

    @Override
    public List<Usuario> consultarEmailUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios;
        usuarios = null;
        try {
            usuarios = dao.ConsultarEmails();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public boolean inserirItem(String nome, float preco, String tipo, String descricao) {
        Item item = new Item();
        item.setNome(nome);
        item.setPreco(preco);
        item.setTipo(tipo);
        item.setDescricao(descricao);
        
        ItemDAO dao = new ItemDAO();
        try {
            item = dao.inserirItem(item);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (item == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public List<Item> consultarItens() {
        ItemDAO dao = new ItemDAO();
        List<Item> itens;
        itens = null;
        try {
            itens = dao.consultarItens();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itens;
    }
    
    @Override
    public void excluirItem(int id) {
        ItemDAO dao = new ItemDAO();
        try {
            dao.excluirItem(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Item alterarItem(int id,String nome, float preco, String tipo, String descricao) {
        Item item = new Item();
        item.setId(id);
        item.setNome(nome);
        item.setPreco(preco);
        item.setTipo(tipo);
        item.setDescricao(descricao);
        
        ItemDAO dao = new ItemDAO();
        try {
            dao.alterarItem(item);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return item;
    }
}
