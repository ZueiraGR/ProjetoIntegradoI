package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cargo
 *
 */
@Entity
@Table(name="cargo")
public class CargoPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int chave;
	private String nome;
	@Column(name="nivel_acesso")
	private char nivelAcesso;
	

	public CargoPO() {
		super();
	}   
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
	public char getNivelAcesso() {
		return this.nivelAcesso;
	}

	public void setNivelAcesso(char nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
   
}
