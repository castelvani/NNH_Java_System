/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author alunocmc
 */
public class Pedido {
    
    private int id;
    private int quantidade;
    private String status;
    private Item item;
    private Reserva reserva;

    public Pedido() {
    }

    public Pedido(int id, int quantidade, String status, Item item, Reserva reserva) {
        this.id = id;
        this.quantidade = quantidade;
        this.status = status;
        this.item = item;
        this.reserva = reserva;
    }
    
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    

}
