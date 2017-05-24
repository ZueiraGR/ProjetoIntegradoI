package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MesaPO
 *
 */
@Entity
@Table(name="mesa")
public class MesaPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer chave;
	@Column(unique=true)
	private String identificacao;
	@Column(name="qtd_cadeiras")
	private Integer quantidadeDeCadeiras;
	private String descricao;
	private String imagem;
	

	public MesaPO() {
		super();
	}   
	public Integer getChave() {
		return this.chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}   
	public String getIdentificacao() {
		return this.identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}   
	public Integer getQuantidadeDeCadeiras() {
		return this.quantidadeDeCadeiras;
	}

	public void setQuantidadeDeCadeiras(Integer quantidadeDeCadeiras) {
		this.quantidadeDeCadeiras = quantidadeDeCadeiras;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
   
}
