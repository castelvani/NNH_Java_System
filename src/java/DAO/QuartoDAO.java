/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Quarto;
import Model.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Gabriel
 */
public class QuartoDAO {

    public Quarto Inserir(Quarto quarto) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into quarto (numero, valorDiaria,tipo,status,desconto, id) values (?,?,?,?,0,(nextval('seq_quarto')))");
        comando.setInt(1, quarto.getNumero());
        comando.setFloat(2, quarto.getValorDiaria());
        comando.setString(3, quarto.getTipo());
        comando.setString(4, quarto.getStatus());
        System.out.println(comando);
        comando.execute();
        con.close();
        return quarto;
    }

    public Quarto Alterar(Quarto quarto) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update quarto set numero = ? ,valorDiaria=? ,tipo=?, status=?, desconto=? where id = ?");

        comando.setInt(1, quarto.getNumero());
        comando.setFloat(2, quarto.getValorDiaria());
        comando.setString(3, quarto.getTipo());
        comando.setString(4, quarto.getStatus());
        comando.setInt(5, quarto.getDesconto());
        comando.setInt(6, quarto.getId());

        comando.execute();

        con.close();
        return quarto;
    }

    public Quarto AlterarStatus(Quarto quarto) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("update quarto set status = ? where id = ?");

        comando.setString(1, quarto.getStatus());
        comando.setInt(2, quarto.getId());

        comando.execute();

        con.close();
        return quarto;
    }

    public void Excluir(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("delete from quarto where id = ?");
        comando.setInt(1, id);

        comando.execute();
        con.close();
    }

    public List<Quarto> Consultar() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select id, numero,valorDiaria,tipo,status from quarto");
        ResultSet resultado = comando.executeQuery();

        List<Quarto> quartos = new ArrayList<>();
        while (resultado.next()) {
            Quarto q = new Quarto();
            q.setId(resultado.getInt("id"));
            q.setNumero(resultado.getInt("numero"));
            q.setValorDiaria(resultado.getFloat("valorDiaria"));
            q.setTipo(resultado.getString("tipo"));
            q.setStatus(resultado.getString("status"));
            quartos.add(q);
        }
        con.close();
        return quartos;
    }

    public List<Quarto> ConsultarQuartosDisponiveis() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        // and (select status from quarto) = 'Disponivel'
        // and situacao ='Ocorrendo'"
        //select * from reserva where dtentrada between ? and ? or dtsaida between ? and ?
        //Query para mostrar quartos que foram reservados nas datas escolhidas
        PreparedStatement comandoQuarto = con.prepareStatement("select id, numero,valorDiaria,tipo,status,desconto from quarto where status='Disponivel'");
        ResultSet resultadoQuarto = comandoQuarto.executeQuery();

        //Lista de quartos
        List<Quarto> quartos = new ArrayList<>();

        while (resultadoQuarto.next()) {
            Quarto quarto = new Quarto();
            quarto.setId(resultadoQuarto.getInt("id"));
            quarto.setNumero(resultadoQuarto.getInt("numero"));
            quarto.setValorDiaria(resultadoQuarto.getFloat("valorDiaria"));
            quarto.setTipo(resultadoQuarto.getString("tipo"));
            quarto.setStatus(resultadoQuarto.getString("status"));
            quarto.setDesconto(resultadoQuarto.getInt("desconto"));
            quartos.add(quarto);
        }

        con.close();
        return quartos;
    }
    
    public List<Quarto> ConsultarOutrosQuartos(int idQuartosReservados) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        //select id, numero,valorDiaria,tipo,status from quarto where id=?
        PreparedStatement comandoQuarto = con.prepareStatement("select q.*, r.situacao from quarto q, reserva r where q.id = ? and (r.situacao != 'Ocorrendo' or r.situacao != 'Reservada')");
        comandoQuarto.setInt(1, idQuartosReservados);
        ResultSet resultadoQuarto = comandoQuarto.executeQuery();
        
        List<Quarto> quartos = new ArrayList<>();

        while (resultadoQuarto.next()) {
            Quarto quarto = new Quarto();
            quarto.setId(resultadoQuarto.getInt("id"));
            quarto.setNumero(resultadoQuarto.getInt("numero"));
            quarto.setValorDiaria(resultadoQuarto.getFloat("valorDiaria"));
            quarto.setTipo(resultadoQuarto.getString("tipo"));
            quarto.setStatus(resultadoQuarto.getString("status"));
            quarto.setDesconto(resultadoQuarto.getInt("desconto"));
            quartos.add(quarto);
        }

        con.close();
        return quartos;
    }

    public Quarto ConsultarIdQuarto(int idQuarto) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        Quarto quarto = new Quarto();

        PreparedStatement comando = con.prepareStatement("select id, numero, valorDiaria, tipo, status, desconto from quarto where id=?");
        comando.setInt(1, idQuarto);

        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            quarto.setId(resultado.getInt("id"));
            quarto.setNumero(resultado.getInt("numero"));
            quarto.setValorDiaria(resultado.getFloat("valorDiaria"));
            quarto.setTipo(resultado.getString("tipo"));
            quarto.setStatus(resultado.getString("status"));
            quarto.setDesconto(resultado.getInt("desconto"));
        }
        con.close();
        return quarto;

    }
}
