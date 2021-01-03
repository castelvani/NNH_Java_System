/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudUsuario;

import Credenciais.PerfilDeAcesso;
import DAO.FabricaConexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gabriel
 */
public class CadastroUsuarioTeste {

    private UsuarioDAO dao;
    private Connection conexao;

    @BeforeAll
    public void inicializa() throws SQLException, ClassNotFoundException {
        conexao = FabricaConexao.getConexao();
        conexao.setAutoCommit(false);

        dao = new UsuarioDAO();
    }

    @AfterAll
    public void finaliza() throws SQLException {
        conexao.rollback();
    }

    @Test
    public void AcessaTeste() throws ParseException, ClassNotFoundException, SQLException {
        Usuario usuario = new Usuario();

        usuario.setEmail("teste");
        usuario.setSenha("teste");
        usuario.setNome("teste");        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("1998-01-01");
        
        java.sql.Date dtNasc = new java.sql.Date(date.getTime());
        
        usuario.setDtNasc(dtNasc);
        usuario.setPerfil(PerfilDeAcesso.valueOf("ADMINISTRADOR"));
        
        dao.cadastrar((Usuario) usuario);
        
        Usuario listaUsuarios = dao.ConsultarUsuarios().get(0);
        
        assertEquals("teste", listaUsuarios.getEmail());
        assertEquals("teste", listaUsuarios.getSenha());
        assertEquals("teste", listaUsuarios.getNome());
        assertEquals("1998-01-01", listaUsuarios.getDtNasc());
        assertEquals("ADMINISTRADOR", listaUsuarios.getPerfil());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
