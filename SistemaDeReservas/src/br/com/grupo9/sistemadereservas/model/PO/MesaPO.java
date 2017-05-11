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
	private int chave;
	@Column(unique=true)
	private String identificacao;
	@Column(name="qtd_cadeiras")
	private int quantidadeDeCadeiras;
	private String descricao;
	private String imagem;
	

	public MesaPO() {
		super();
	}   
	public int getChave() {
		return this.chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}   
	public String getIdentificacao() {
		return this.identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}   
	public int getQuantidadeDeCadeiras() {
		return this.quantidadeDeCadeiras;
	}

	public void setQuantidadeDeCadeiras(int quantidadeDeCadeiras) {
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
