/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Item;
import Model.Pedido;
import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class PedidoDAO {

    public void inserirPedido(int id_item, int quantidade, int id_reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into pedido (id, id_itemfk, quantidade, status, id_reservafk) values ((nextval('seq_pedido')),(select id from item where id=?),?,'Realizado',(select id from reserva where id=?))");
        comando.setInt(1, id_item);
        comando.setInt(2, quantidade);
        comando.setInt(3, id_reserva);
        comando.executeQuery();
        con.close();
    }

    public Pedido cancelarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("update pedido set status = 'Cancelado' where id = ?");
        comando.setInt(1, pedido.getId());
        comando.executeQuery();
        con.close();
        return pedido;
    }
    
    public Pedido atualizarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("update pedido set status = ? where id = ?");
        comando.setString(1, pedido.getStatus());
        comando.setInt(2, pedido.getId());
        comando.executeQuery();
        con.close();
        return pedido;
    }
    
    public Pedido consultarPedidoId(int id_pedido) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        Pedido pedido = new Pedido();
        PreparedStatement comando = con.prepareStatement("select * from pedido where id = ?");
        comando.setInt(1, id_pedido);
        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            pedido.setId(resultado.getInt("id"));
            pedido.setQuantidade(resultado.getInt("quantidade"));
            pedido.setStatus(resultado.getString("status"));
        }
        return pedido;
    }

    public boolean inserirPedidoReserva(int id_reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into pedido_reserva (id_reservafk, id_pedidofk) values ((select id from reserva where id =?),(select id from pedido where id =?))");
        comando.setInt(1, id_reserva);
        comando.executeQuery();
        con.close();
        return true;
    }

    public List<Pedido> consultarPedidoReserva(int id_reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select i.*,p.quantidade, p.status, p.id_reservafk, p.id as id_pedido from item i, pedido p where i.id = p.id_itemfk and p.status != 'Cancelado' and p.id_reservafk = ?");
        comando.setInt(1, id_reserva);
        ResultSet resultadoPedidoReserva = comando.executeQuery();

        List<Pedido> pedidos = new ArrayList<>();

        while (resultadoPedidoReserva.next()) {
            ItemDAO idao = new ItemDAO();
            ReservaDAO rdao = new ReservaDAO();
            Pedido pedido = new Pedido();

            int idItem = resultadoPedidoReserva.getInt("id");
            Item item = idao.consultarIdItem(idItem);
            pedido.setItem(item);

            int idReserva = resultadoPedidoReserva.getInt("id_reservafk");
            Reserva reserva = rdao.ConsultarIdReserva(idReserva);
            pedido.setReserva(reserva);

            pedido.setId(resultadoPedidoReserva.getInt("id_pedido"));
            pedido.setQuantidade(resultadoPedidoReserva.getInt("quantidade"));
            pedido.setStatus(resultadoPedidoReserva.getString("status"));
            pedidos.add(pedido);
        }

        con.close();
        return pedidos;
    }
    
    public List<Pedido> consultarPedidos() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from pedido where status != 'Cancelado'");
        ResultSet resultadoPedidoReserva = comando.executeQuery();

        Pedido pedido = new Pedido();

        List<Pedido> pedidos = new ArrayList<>();

        while (resultadoPedidoReserva.next()) {
            ItemDAO idao = new ItemDAO();
            ReservaDAO rdao = new ReservaDAO();

            int idItem = resultadoPedidoReserva.getInt("id_itemfk");
            Item item = idao.consultarIdItem(idItem);
            pedido.setItem(item);

            int idReserva = resultadoPedidoReserva.getInt("id_reservafk");
            Reserva reserva = rdao.ConsultarIdReserva(idReserva);
            pedido.setReserva(reserva);

            pedido.setId(resultadoPedidoReserva.getInt("id"));
            pedido.setQuantidade(resultadoPedidoReserva.getInt("quantidade"));
            pedido.setStatus(resultadoPedidoReserva.getString("status"));
            pedidos.add(pedido);
        }

        con.close();
        return pedidos;
    }
    
    public List<Reserva> consultarPedidosReserva()throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from reserva where situacao = 'Ocorrendo'");
        ResultSet resultado = comando.executeQuery();

        List<Reserva> reservas = new ArrayList<>();
        while (resultado.next()) {
            QuartoDAO qdao = new QuartoDAO();
            UsuarioDAO udao = new UsuarioDAO();
            Reserva reserva = new Reserva();

            reserva.setId(resultado.getInt("id"));

            int idQuarto = resultado.getInt("id_quartofk");
            Quarto quarto = qdao.ConsultarIdQuarto(idQuarto);
            reserva.setQuarto(quarto);

            int idUsuario = resultado.getInt("id_usuariofk");
            Usuario usuario = udao.ConsultarIdUsuario(idUsuario);
            reserva.setUsuario(usuario);

            reserva.setDt_entrada(resultado.getDate("dtEntrada"));
            reserva.setDt_saida(resultado.getDate("dtSaida"));
            
            reserva.setSituacao(resultado.getString("situacao"));
            reservas.add(reserva);
        }
        return reservas;
    }
}
