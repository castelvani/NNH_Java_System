package DAO;

import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public List<Reserva> ConsultarReservasPorData(Date dt_entrada, Date dt_saida) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        //Query antiga sem poder depois das 12h
        //select id,id_quartofk,dtentrada,dtsaida from reserva where (dtentrada between ? and ? or dtsaida between ? and ?) and situacao='Reservada' ORDER BY id asc
        //Query para mostrar quartos que foram reservados nas datas escolhidas
        PreparedStatement comandoReserva = con.prepareStatement("select id,id_quartofk,dtentrada,dtsaida,situacao from reserva where \n" +
"(dtentrada >= ? and dtsaida <= ?)\n" +
"and (situacao='Reservada' or situacao='Ocorrendo') ORDER BY id asc");
        comandoReserva.setDate(1, dt_entrada);
        comandoReserva.setDate(2, dt_saida);
//        comandoReserva.setDate(3, dt_entrada);
//        comandoReserva.setDate(4, dt_saida);

        ResultSet resultadoReserva = comandoReserva.executeQuery();
        
        List<Reserva> reservas = new ArrayList<>();
        
        while (resultadoReserva.next()) {
            QuartoDAO qdao = new QuartoDAO();
            Reserva reserva = new Reserva();
            
            reserva.setId(resultadoReserva.getInt("id"));
            reserva.setDt_entrada(dt_entrada);
            reserva.setDt_saida(dt_saida);
            
            int idQuarto = resultadoReserva.getInt("id_quartofk");
            Quarto quarto = qdao.ConsultarIdQuarto(idQuarto);
            reserva.setQuarto(quarto);
            reserva.setSituacao(resultadoReserva.getString("situacao"));
            
            reservas.add(reserva);
        }
        con.close();
        return reservas;
    }
    
    public Reserva cadastrarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into reserva (id, id_quartofk, id_usuariofk, dtEntrada, dtSaida, situacao, orcamento, tipopagamento) values ((nextval('seq_reserva')),(select id from quarto where id=?),(select id from usuario where id=?),?,?,?,?,?)");
        comando.setInt(1, reserva.getQuarto().getId());
        comando.setInt(2, reserva.getUsuario().getId());
//        comando.setString(3, reserva.getDt_reserva());
        comando.setDate(3, reserva.getDt_entrada());
        comando.setDate(4, reserva.getDt_saida());
        comando.setString(5, reserva.getSituacao());
        comando.setFloat(6, reserva.getOrcamento());
        comando.setString(7, reserva.getTipoPagamento());
        comando.executeQuery();
        con.close();
        return reserva;
    }

    public Reserva AlterarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update reserva set situacao = ? ,id_quartofk=(select id from quarto where numero = ?), id_usuariofk =(select id from usuario where nome=?) where id = ?");

        comando.setString(1, reserva.getSituacao());
        comando.setInt(2, reserva.getQuarto().getNumero());
        comando.setString(3, reserva.getUsuario().getNome());
        comando.setInt(4, reserva.getId());
        comando.executeQuery();

        comando.execute();
        con.close();
        return reserva;
    }

    public Reserva CancelarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("update reserva set situacao = ? where id = ?");
        
        comando.setString(1, reserva.getSituacao());
        comando.setInt(2, reserva.getId());

        comando.execute();
        con.close();
        return reserva;
    }

    public List<Reserva> ConsultarReservas() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from reserva where situacao != 'Cancelada'");
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
    
    public List<Reserva> RelatiorioReservaMes(int mes) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from reserva where extract(month from dtentrada) = ?");
        comando.setInt(1, mes);
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
    
    public List<Reserva> RelatiorioReservaAno(int ano) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from reserva where extract(year from dtentrada) = ?");
        comando.setInt(1, ano);
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

    public Reserva ConsultarIdReserva(int idReserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        Reserva reserva = new Reserva();
        
        PreparedStatement comando = con.prepareStatement("select * from reserva where id=?");
        comando.setInt(1, idReserva);

        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            QuartoDAO qdao = new QuartoDAO();
            UsuarioDAO udao = new UsuarioDAO();
            reserva.setId(resultado.getInt("id"));
            int idQuarto = resultado.getInt("id_quartofk");
            Quarto quarto = qdao.ConsultarIdQuarto(idQuarto);
            reserva.setQuarto(quarto);

            int idUsuario = resultado.getInt("id_usuariofk");
            Usuario usuario = udao.ConsultarIdUsuario(idUsuario);
            reserva.setUsuario(usuario);
            reserva.setSituacao(resultado.getString("situacao"));
            reserva.setDt_entrada(resultado.getDate("dtEntrada"));
            reserva.setDt_saida(resultado.getDate("dtSaida"));
        }

        return reserva;

    }
    public List<Reserva> ConsultarReservasIdUsuarioPedido(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select u.id as idHospede, r.*,q.numero,q.tipo from usuario u, reserva r, quarto q where u.id = ? and r.id_usuariofk = ? "
                + "and q.id = r.id_quartofk and r.situacao = 'Ocorrendo'");
        comando.setInt(1, id);
        comando.setInt(2, id);
        
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
            reserva.setOrcamento(resultado.getFloat("orcamento"));
            reservas.add(reserva);
        }
        return reservas;
    }
    public List<Reserva> ConsultarReservasIdUsuario(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select u.id as idHospede, r.*,q.numero,q.tipo from usuario u, reserva r, quarto q where u.id = ? and r.id_usuariofk = ? "
                + "and q.id = r.id_quartofk and (r.situacao = 'Ocorrendo' or r.situacao = 'Reservada')");
        comando.setInt(1, id);
        comando.setInt(2, id);
        
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
            reserva.setOrcamento(resultado.getFloat("orcamento"));
            reserva.setTipoPagamento(resultado.getString("tipopagamento"));
            reservas.add(reserva);
        }
        return reservas;
    }
    
    public List<Reserva> ConsultarReservasOcorrendo ()throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from reserva where situacao = 'Ocorrendo'");
        
        ResultSet resultado = comando.executeQuery();

        List<Reserva> reservas = new ArrayList<>();
        while (resultado.next()) {
            Reserva reserva = new Reserva();

            reserva.setId(resultado.getInt("id"));
            reserva.setDt_entrada(resultado.getDate("dtentrada"));
            reserva.setDt_saida(resultado.getDate("dtsaida"));
            reservas.add(reserva);
        }
        return reservas;    
    }
    public void CheckoutAntecipadoReserva(int idReserva, String situacao) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        
        PreparedStatement comando = con.prepareStatement("update reserva set situacao = ? where id = ?");
        comando.setString(1, situacao);
        comando.setInt(2, idReserva);
        
        comando.executeQuery();
        con.close();
    }
    public void Checkout(int idReserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update reserva set situacao = 'Finalizada' where id = ?");
        comando.setInt(1, idReserva);
        
        comando.executeQuery();
        con.close();
    }

    public void Checkin(int idReserva) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update reserva set situacao = 'Ocorrendo' where id = ?");
        comando.setInt(1, idReserva);
        
        comando.executeQuery();
        con.close();
    }
}
