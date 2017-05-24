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
	private Integer chave;
	@Column(name="chave_cliente")
	private Integer chaveCliente;
	@Column(name="chave_funcionario")
	private Integer chaveFuncionario;
	@Column(name="chave_mesa")
	private Integer chaveMesa;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fim;
	@Column(name="qtd_pessoas")
	private Integer quantidadeDePessoas;
	

	public ReservaPO() {
		super();
	}   
	public Integer getChave() {
		return this.chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}   
	public Integer getChaveCliente() {
		return this.chaveCliente;
	}

	public void setChaveCliente(Integer chaveCliente) {
		this.chaveCliente = chaveCliente;
	}   
	public Integer getChaveFuncionario() {
		return this.chaveFuncionario;
	}

	public void setChaveFuncionario(Integer chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
	}   
	public Integer getChaveMesa() {
		return this.chaveMesa;
	}

	public void setChaveMesa(Integer chaveMesa) {
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
	public Integer getQuantidadeDePessoas() {
		return this.quantidadeDePessoas;
	}

	public void setQuantidadeDePessoas(Integer quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}
   
}
