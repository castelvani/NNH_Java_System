/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gabriel
 */
public class Item {
    private int id;
    private String nome;
    private String tipo;
    private float preco;
    private String descricao;
    private int vezesPedido;

    public Item(int id, String nome, String tipo, float preco, String descricao,int vezesPedido) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.descricao = descricao;
        this.vezesPedido = vezesPedido;
    }
    
    public Item() {
    }
    
    public int getVezesPedido() {
        return vezesPedido;
    }

    public void setVezesPedido(int vezesPedido) {
        this.vezesPedido = vezesPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }    
    
    @Override
    public boolean equals(Object obj) {
        try {
            Item p2 = (Item) obj;
            return p2.id == this.id;
        } catch (Exception e) {
            return false;
        }
    }
}
