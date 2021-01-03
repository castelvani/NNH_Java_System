/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Item;
import Model.Quarto;
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
public class ItemDAO {
    
    public Item inserirItem(Item item) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into item (nome, preco, tipo, descricao , id) values (?,?,?,?,(nextval('seq_item')))");
        comando.setString(1, item.getNome());
        comando.setFloat(2, item.getPreco());
        comando.setString(3, item.getTipo());
        comando.setString(4, item.getDescricao());
        System.out.println(comando);
        comando.execute();
        con.close();
        return item;
    }
    
    public List<Item> consultarItens() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select * from item");
        ResultSet resultado = comando.executeQuery();

        List<Item> itens = new ArrayList<>();
        while (resultado.next()) {
            Item item = new Item();
            item.setId(resultado.getInt("id"));
            item.setNome(resultado.getString("nome"));            
            item.setTipo(resultado.getString("tipo"));
            item.setPreco(resultado.getFloat("preco"));
            item.setDescricao(resultado.getString("descricao"));
            itens.add(item);
        }
        con.close();
        return itens;
    }
    
    public List<Item> relatorioMaisPedidos() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("SELECT i.id,i.nome,i.tipo,i.preco,i.descricao,COUNT(i.nome) FROM pedido p, item i where i.id = p.id_itemfk group by  i.id, i.nome, i.tipo,i.preco,i.descricao");
        ResultSet resultado = comando.executeQuery();

        List<Item> itens = new ArrayList<>();
        while (resultado.next()) {
            Item item = new Item();
            item.setId(resultado.getInt("id"));
            item.setNome(resultado.getString("nome"));            
            item.setTipo(resultado.getString("tipo"));
            item.setPreco(resultado.getFloat("preco"));
            item.setDescricao(resultado.getString("descricao"));
            item.setVezesPedido(resultado.getInt("count"));
            itens.add(item);
        }
        con.close();
        return itens;
    }
    
    public void excluirItem(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("delete from item where id = ?");
        comando.setInt(1, id);

        comando.execute();
        con.close();
    }
    
    public Item consultarIdItem(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        Item item = new Item();

        PreparedStatement comando = con.prepareStatement("select * from item where id=?");
        comando.setInt(1, id);

        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            item.setId(resultado.getInt("id"));
            item.setNome(resultado.getString("nome"));
            item.setTipo(resultado.getString("tipo"));
            item.setPreco(resultado.getFloat("preco"));
            item.setDescricao(resultado.getString("descricao"));
        }
        con.close();
        return item;

    }
    
    public Item alterarItem(Item item) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update item set nome = ? ,tipo=? ,preco=?, descricao=? where id = ?");

        comando.setString(1, item.getNome());
        comando.setString(2, item.getTipo());
        comando.setFloat(3, item.getPreco());
        comando.setString(4, item.getDescricao());
        comando.setInt(5, item.getId());

        comando.execute();

        con.close();
        return item;
    }
}
