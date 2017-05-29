package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*As anotações @nome utilizadas nesta classa são para o mapeamento do model e entidade no banco de dados.
 *Estas anotações são utilizadas pelo Hibernate para identificar a tabela e os seus atributos no 
 *banco de dados.
*/
@Entity(name="usuario")
@Table(name="usuario")
public class UsuarioPO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	@OneToOne
	@JoinColumn(name="cliente_chave",referencedColumnName="chave", nullable= true, unique=true)
	private ClientePO cliente;
	@OneToOne
	@JoinColumn(name="funcionario_chave",referencedColumnName="chave", nullable= true, unique=true)
	private FuncionarioPO funcionario;
	private String senha;
	private Character status;
	private Character tipo;
	@Column(name="data_criacao", nullable= false)
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Column(name="data_inativacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataInativacao;
	@Column(name="data_exclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public ClientePO getCliente() {
		return cliente;
	}
	public void setCliente(ClientePO cliente) {
		this.cliente = cliente;
	}
	public FuncionarioPO getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioPO funcionario) {
		this.funcionario = funcionario;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Character getTipo() {
		return tipo;
	}
	public void setTipo(Character tipo) {
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
