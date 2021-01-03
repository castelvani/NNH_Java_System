/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author alunocmc
 */
public class Reserva {

    private int id;
    private Quarto quarto;
    private Usuario usuario;
    private Pedido pedido;
    private Date dt_entrada;
    private Date dt_saida;
    private String situacao;
    private float orcamento;
    private String tipoPagamento;

    public float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reserva() {
    }

    public Reserva(int id, Quarto quarto, Usuario usuario,Pedido pedido, Date dt_entrada, Date dt_saida, String situacao, float orcamento, String tipoPagamento) {
        this.id = id;
        this.quarto = quarto;
        this.usuario = usuario;
        this.pedido = pedido;
        this.dt_entrada = dt_entrada;
        this.dt_saida = dt_saida;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.tipoPagamento = tipoPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public Date getDt_reserva() {
//        return dt_reserva;
//    }
//
//    public void setDt_reserva(Date dt_reserva) {
//        this.dt_reserva = dt_reserva;
//    }
//
    public Date getDt_entrada() {
        return dt_entrada;
    }

    public void setDt_entrada(Date dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public Date getDt_saida() {
        return dt_saida;
    }

    public void setDt_saida(Date dt_saida) {
        this.dt_saida = dt_saida;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

//    public long dias(Date dt_saida, Date dt_entrada) throws ParseException {
//        long diff = dt_saida.getTime() - dt_entrada.getTime();
//        //numero de dias entre as datas
//        long dias = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//        System.out.println(dias);
//        return dias;
//    }
}
