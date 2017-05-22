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
	@Column(name="horario_inicio", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date horarioInicio;
	@Column(name="horario_fim", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date horarioFim;
	  
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
	public Date getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public Date getHorarioFim() {
		return horarioFim;
	}
	public void setHorarioFim(Date horarioFim) {
		this.horarioFim = horarioFim;
	}
	
	
	   
}
