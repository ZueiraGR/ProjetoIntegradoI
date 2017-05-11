package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReservaPO
 *
 */
@Entity
@Table(name="reserva")
public class ReservaPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int chave;
	@Column(name="chave_cliente")
	private int chaveCliente;
	@Column(name="chave_funcionario")
	private int chaveFuncionario;
	@Column(name="chave_mesa")
	private int chaveMesa;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fim;
	@Column(name="qtd_pessoas")
	private int quantidadeDePessoas;
	

	public ReservaPO() {
		super();
	}   
	public int getChave() {
		return this.chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}   
	public int getChaveCliente() {
		return this.chaveCliente;
	}

	public void setChaveCliente(int chaveCliente) {
		this.chaveCliente = chaveCliente;
	}   
	public int getChaveFuncionario() {
		return this.chaveFuncionario;
	}

	public void setChaveFuncionario(int chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
	}   
	public int getChaveMesa() {
		return this.chaveMesa;
	}

	public void setChaveMesa(int chaveMesa) {
		this.chaveMesa = chaveMesa;
	}   
	public Calendar getInicio() {
		return this.inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}   
	public Calendar getFim() {
		return this.fim;
	}

	public void setFim(Calendar fim) {
		this.fim = fim;
	}   
	public int getQuantidadeDePessoas() {
		return this.quantidadeDePessoas;
	}

	public void setQuantidadeDePessoas(int quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}
   
}
