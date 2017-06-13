package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FuncionarioPO
 *
 */
@Entity(name="funcionario")
@Table(name="funcionario")
public class FuncionarioPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer chave;
	@ManyToOne
	@JoinColumn(name="cargo_chave", referencedColumnName="chave", nullable= false)
	private CargoPO cargo;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private String email;
	@Column(nullable=false)
	private Character status;

	public Integer getChave() {
		return this.chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}   
	 
	public CargoPO getCargo() {
		return cargo;
	}
	public void setCargo(CargoPO cargoPO) {
		this.cargo = cargoPO;
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
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}


}
