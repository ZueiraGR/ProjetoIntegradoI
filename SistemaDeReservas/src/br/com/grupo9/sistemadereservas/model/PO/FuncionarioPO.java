package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FuncionarioPO
 *
 */
@Entity
@Table(name="funcionario")
public class FuncionarioPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int chave;
	@Column(name="chave_cargo")
	private int chaveCargo;
	private String nome;
	private String sobrenome;
	private String cpf;
	

	public FuncionarioPO() {
		super();
	}   
	public int getChave() {
		return this.chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}   
	public int getChaveCargo() {
		return this.chaveCargo;
	}

	public void setChaveCargo(int chaveCargo) {
		this.chaveCargo = chaveCargo;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}   
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
   
}
