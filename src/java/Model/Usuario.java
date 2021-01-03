package Model;

import Credenciais.PerfilDeAcesso;
import java.sql.Date;


public class Usuario {
    private int id;
    private String nome;
    private Date dtNasc ;
    private String email;
    private String senha;
    private PerfilDeAcesso perfil;

    public PerfilDeAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDeAcesso perfil) {
        this.perfil = perfil;
    }

    public Usuario() {
    }

    public Usuario(int id, String nome, Date dtNasc, String email, String senha, PerfilDeAcesso perfil) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
