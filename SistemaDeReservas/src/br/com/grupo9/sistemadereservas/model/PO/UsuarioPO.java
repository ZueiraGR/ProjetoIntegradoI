package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*As anotações @nome utilizadas nesta classa são para o mapeamento do model e entidade no banco de dados.
 *Estas anotações são utilizadas pelo Hibernate para identificar a tabela e os seus atributos no 
 *banco de dados.
*/
@Entity
@Table(name="usuario")
public class UsuarioPO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	
	@Column(name="chave_cliente", nullable= true)
	//@OneToOne
	//@JoinColumn(name="chave_cliente")
	private Integer chaveCliente;
	@Column(name="chave_funcionario", nullable= true)
	//@OneToOne
	//@JoinColumn(name="chave_funcionario")
	private Integer chaveFuncionario;
	
	private String senha;
	private char status;
	private char tipo;
	@Column(name="data_criacao", nullable= false)
        @Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Column(name="data_inativacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataInativacao;
	@Column(name="data_exclucao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Integer getChaveCliente() {
		return chaveCliente;
	}
	public void setChaveCliente(Integer chaveCliente) {
		this.chaveCliente = chaveCliente;
	}
	
	public Integer getChaveFuncionario() {
		return chaveFuncionario;
	}
	public void setChaveFuncionario(Integer chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Calendar getDataInativacao() {
		return dataInativacao;
	}
	public void setDataInativacao(Calendar dataInativacao) {
		this.dataInativacao = dataInativacao;
	}
	
	public Calendar getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	
}
