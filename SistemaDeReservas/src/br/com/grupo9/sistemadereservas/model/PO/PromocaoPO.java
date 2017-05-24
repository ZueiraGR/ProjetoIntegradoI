package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PromocaoPO
 *
 */
@Entity
@Table(name="promocao")
public class PromocaoPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer chave;
	@Column(name="chave_funcionario")
	private Integer chaveFuncionario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fim;
	private String titulo;
	private String descricao;
	private String informacao;
	private String imagem;
	

	public PromocaoPO() {
		super();
	}   
	public Integer getChave() {
		return this.chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}   
	public Integer getChaveFuncionario() {
		return this.chaveFuncionario;
	}

	public void setChaveFuncionario(Integer chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
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
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public String getInformacao() {
		return this.informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}   
	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
   
}
