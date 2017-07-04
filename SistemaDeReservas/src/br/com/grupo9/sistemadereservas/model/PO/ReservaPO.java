package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReservaPO
 *
 */
@Entity(name="reserva")
@Table(name="reserva")
public class ReservaPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer chave;
	@ManyToOne(optional=false)
	private UsuarioPO usuario;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="mesa_reservada",
			joinColumns = @JoinColumn(name="reserva_chave"),
			inverseJoinColumns= @JoinColumn(name="mesa_chave"))
	private List<MesaPO> mesas;
	@Column(name="inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;
	@Column(name="fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fim;
	@Column(name="qtd_pessoas")
	private Integer quantidadeDePessoas;
	
	@ManyToOne(optional=true)
	private PromocaoPO promocao;
	

	public ReservaPO() {
		super();
	}   
	public Integer getChave() {
		return this.chave;
	}
	public void setChave(Integer chave) {
		this.chave = chave;
	}
	public UsuarioPO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioPO usuario) {
		this.usuario = usuario;
	}
	public List<MesaPO> getMesas() {
		return mesas;
	}
	public void setMesas(List<MesaPO> mesas) {
		this.mesas = mesas;
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
	public Integer getQuantidadeDePessoas() {
		return this.quantidadeDePessoas;
	}
	public void setQuantidadeDePessoas(Integer quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}
	public PromocaoPO getPromocao() {
		return promocao;
	}
	public void setPromocao(PromocaoPO promocao) {
		this.promocao = promocao;
	}
	
	
}
