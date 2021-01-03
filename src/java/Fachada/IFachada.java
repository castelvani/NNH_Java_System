/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;

import Model.Item;
import Model.Pedido;
import Model.Quarto;
import Model.Reserva;
import Model.Usuario;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author alunocmc
 */
public interface IFachada {

    public Usuario realizarLogin(String email, String senha);

    public Usuario realizarCadastro(String email, String senha, String nome, Date dtNasc);

    public Usuario getUsuario();

    public Quarto getQuarto();

    public boolean inserirQuarto(int numero, Float valorDiaria, String tipo, String status);
    public void excluirQuarto(int id);
    public Quarto alterarQuarto(int id, int numero, float valordiaria, String tipo, String status, int desconto);
    public Quarto iniciarAlteracao(int id);
    public Quarto alterarStatusQuarto(int idQuarto, String status);

    public boolean inserirUsuario(String email, String senha, String nome, Date dtNasc, String perfil);
    public void excluirUsuario(int id);
    public Usuario alterarUsuario(int id, String email, String senha, String nome, Date dtNasc, String perfil);
    public Usuario iniciarAlteracaoUsuario(int id);

    public boolean inserirReserva(Usuario usuario, Quarto quarto, Date dt_entrada, Date dt_saida, String situacao, float orcamento, String tipo);
    public Reserva cancelarReserva(int id_reserva, String situacao);
    public Reserva alterarReserva(int idReserva, String reservaSituacao, Quarto quarto, Usuario usuario);
    public Reserva iniciarAlteracaoReserva(int id);
    public void CheckoutAntecipadoReserva(int idReserva, String situacao);
    public void Checkout(int idReserva);
    public void Checkin(int idReserva);
    public float calculaOrcamento(Date dt_saida, Date dt_entrada);
    
    public List<Reserva> consultarReservaDatas(Date dt_entrada,Date dt_saida);
    public List<Reserva> consultarReservaIdUsuario(int id);
    public List<Reserva> consultarReservaIdUsuarioPedido(int id);
    public List<Quarto> consultarQuartosDisponiveis();
    public List<Quarto> consultarOutrosQuartos(int idQuartosReservados);
    public List<Quarto> consultarQuarto();
    public List<Usuario> consultarUsuario();
    public List<Usuario> consultarEmailUsuarios();
    public List<Reserva> consultarReserva();
    public List<Reserva> relatiorioReservaMes(int mes);
    public List<Reserva> relatiorioReservaAno(int ano);
    
    public boolean inserirItem(String nome, float preco, String tipo, String descricao);
    public List<Item> consultarItens();
    public void excluirItem(int id);
    public Item consultarIdItem(int id);
    public Item alterarItem(int id,String nome, float preco, String tipo, String descricao);
    
    public void inserirPedido(int id_item, int quantidade, int id_reserva);
    public boolean inserirPedidoReserva(int id_reserva);
    public List<Pedido> consultarPedidoReserva(int id_reserva);
    public Pedido cancelarPedido(int id_pedido);
    public Pedido atualizarPedido(String status, int id_pedido);
    public List<Pedido> consultarPedidos();
    public List<Reserva> consultarPedidosReserva();
    public Pedido consultarPedidoId(int id_pedido);
    public List<Item> relatorioMaisPedidos();
}
