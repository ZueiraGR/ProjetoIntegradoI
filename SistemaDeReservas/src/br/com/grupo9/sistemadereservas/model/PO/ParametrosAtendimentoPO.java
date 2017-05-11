package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ParametrosAtendimentos
 *
 */
@Entity
@Table(name="parametros_atendimento")
public class ParametrosAtendimentoPO implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@Column(name="dia_da_semana")
	private int diaDaSemana;
	@Column(name="chave_funcionario")
	private int chaveFuncionario;
	@Temporal(TemporalType.TIME)
	private Calendar inicio;
	@Temporal(TemporalType.TIME)
	private Calendar fim;
	

	public ParametrosAtendimentoPO() {
		super();
	}   
	public int getDiaDaSemana() {
		return this.diaDaSemana;
	}

	public void setDiaDaSemana(int diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}   
	public int getChaveFuncionario() {
		return this.chaveFuncionario;
	}
	public void setChaveFuncionario(int chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
	}
	public Calendar getInicio() {
		return inicio;
	}
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFim() {
		return fim;
	}
	public void setFim(Calendar fim) {
		this.fim = fim;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
	   
}
