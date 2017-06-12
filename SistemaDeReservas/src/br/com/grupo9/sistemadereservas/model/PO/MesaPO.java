package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MesaPO
 *
 */
@Entity(name="mesa")
@Table(name="mesa")
public class MesaPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer chave;
	@Column(name="identificacao")
	private String identificacao;
	@Column(name="qtd_cadeiras")
	private Integer qtd_cadeiras;
	@Column(name="descricao")
	private String descricao;
	@Column(name="imagem")
	private String imagem;
	
/*	@ManyToMany(mappedBy="mesas")
	private List<ReservaPO> reservas;*/
	
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
	 
	public Integer getQtd_cadeiras() {
		return qtd_cadeiras;
	}
	public void setQtd_cadeiras(Integer qtd_cadeiras) {
		this.qtd_cadeiras = qtd_cadeiras;
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
/*	public List<ReservaPO> getReservas() {
		return reservas;
	}
	public void setReservas(List<ReservaPO> reservas) {
		this.reservas = reservas;
	}	*/
   
}
