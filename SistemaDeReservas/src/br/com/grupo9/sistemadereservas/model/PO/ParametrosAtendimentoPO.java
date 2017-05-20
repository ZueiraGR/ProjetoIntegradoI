package br.com.grupo9.sistemadereservas.model.PO;

import java.io.Serializable;
import java.util.Date;
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
	private Date horario_inicio;
	@Temporal(TemporalType.TIME)
	private Date horario_fim;
	

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
	public Date getInicio() {
		return horario_inicio;
	}
	public void setInicio(Date horario_inicio) {
		this.horario_inicio = horario_inicio;
	}
	public Date getFim() {
		return horario_fim;
	}
	public void setFim(Date horario_fim) {
		this.horario_fim = horario_fim;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
	   
}
