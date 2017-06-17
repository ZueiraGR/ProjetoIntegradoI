package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cargo
 *
 */
@Entity(name="cargo")
@Table(name="cargo")
public class CargoPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer chave;
	private String nome;
	@Column(name="data_exclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;

	@Column(name="nivel_acesso")
	private Character nivelAcesso;
	public Integer getChave() {
		return this.chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}   
	public Character getNivelAcesso() {
		return this.nivelAcesso;
	}

	public void setNivelAcesso(Character nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Calendar getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
   
}
