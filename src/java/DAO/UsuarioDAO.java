package DAO;

import Credenciais.PerfilDeAcesso;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario validarLogin(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("SELECT id, email, perfil, nome, dtnasc FROM usuario WHERE email = ? AND senha = ?;");
        comando.setString(1, usuario.getEmail());
        comando.setString(2, usuario.getSenha());

        ResultSet resultado = comando.executeQuery();

        if (resultado.next()) {
            usuario.setId(resultado.getInt("id"));
            usuario.setEmail(resultado.getString("email"));
            usuario.setPerfil(PerfilDeAcesso.valueOf(resultado.getString("perfil")));
            usuario.setNome(resultado.getString("nome"));
            usuario.setDtNasc(resultado.getDate("dtnasc"));
        } else {
            usuario = null;
        }

        con.close();
        return usuario;
    }

    public Usuario cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("insert into usuario (email, senha, nome, dtNasc, perfil, id) values (?,?,?,?,?,(nextval('seq_usuario')))");
        comando.setString(1, usuario.getEmail());
        comando.setString(2, usuario.getSenha());
        comando.setString(3, usuario.getNome());
        comando.setDate(4, usuario.getDtNasc());
        comando.setString(5, usuario.getPerfil().toString());

        comando.execute();
        
        con.close();
        return usuario;
    }

    public Usuario AlterarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update usuario set nome = ? ,email=? ,dtnasc=?, senha=?, perfil=? where id = ?");

        comando.setString(1, usuario.getNome());
        comando.setString(2, usuario.getEmail());
        comando.setDate(3, usuario.getDtNasc());
        comando.setString(4, usuario.getSenha());
        comando.setString(5, usuario.getPerfil().toString());
        comando.setInt(6, usuario.getId());

        comando.execute();

        con.close();
        return usuario;
    }

    public void ExcluirUsuario(int id) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("delete from usuario where id = ?");
        comando.setInt(1, id);

        comando.execute();
        con.close();
    }

    public List<Usuario> ConsultarUsuarios() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select id, nome ,dtnasc,email,perfil from usuario");
        ResultSet resultado = comando.executeQuery();

        List<Usuario> usuarios = new ArrayList<>();
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultado.getInt("id"));
            usuario.setNome(resultado.getString("nome"));
            usuario.setDtNasc(resultado.getDate("dtnasc"));
            usuario.setEmail(resultado.getString("email"));
            usuario.setPerfil(PerfilDeAcesso.valueOf(resultado.getString("perfil")));
            usuarios.add(usuario);
        }
        con.close();
        return usuarios;
    }

    public Usuario ConsultarIdUsuario(int idUsuario) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        Usuario usuario = new Usuario();

        PreparedStatement comando = con.prepareStatement("select id, nome, dtnasc,email,perfil,senha from usuario where id=?");
        comando.setInt(1, idUsuario);

        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            usuario.setId(resultado.getInt("id"));
            usuario.setNome(resultado.getString("nome"));
            usuario.setDtNasc(resultado.getDate("dtnasc"));
            usuario.setEmail(resultado.getString("email"));
            usuario.setPerfil(PerfilDeAcesso.valueOf(resultado.getString("perfil")));
            usuario.setSenha(resultado.getString("senha"));
        }

        return usuario;

    }

    public List<Usuario> ConsultarEmails() throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();

        PreparedStatement comando = con.prepareStatement("select email from usuario");
        ResultSet resultado = comando.executeQuery();
        
        List<Usuario> usuarios = new ArrayList<>();
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(resultado.getString("email"));
            usuarios.add(usuario);
        }
        con.close();
        return usuarios;
    }
}
