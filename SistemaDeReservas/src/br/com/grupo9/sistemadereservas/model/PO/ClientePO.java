package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@Table(name="cliente")
public class ClientePO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int chave;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String telefone;
	private String email;
	
	public int getChave() {
		return this.chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
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
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
