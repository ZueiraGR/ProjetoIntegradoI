package br.com.grupo9.sistemadereservas.model.BO;

import java.util.List;

public class Configuracoes {
	private ValorDeCobranca valorTaxaReserva;
	private List<Dia> horariosDeFuncionamento;
	public ValorDeCobranca getValorTaxaReserva() {
		return valorTaxaReserva;
	}
	public void setValorTaxaReserva(ValorDeCobranca valorTaxaReserva) {
		this.valorTaxaReserva = valorTaxaReserva;
	}
	public List<Dia> getHorariosDeFuncionamento() {
		return horariosDeFuncionamento;
	}
	public void setHorariosDeFuncionamento(List<Dia> horariosDeFuncionamento) {
		this.horariosDeFuncionamento = horariosDeFuncionamento;
	}
	
}
