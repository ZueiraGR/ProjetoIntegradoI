var configuracoes;

function carregarParametros(){
	$.ajax({
		url: "ws/parametrosws/capturar/",
        type: 'GET',
        success: function (data) {
        	if(data != null && data !=""){
        		configuracoes = data;
        		setarDadosNoFormDeConfig(data);
        	}
        }
	});
}

$("#manterHorarioAtendimentoForm").submit(function(){
	var formData = JSON.stringify(capturarConfiguracoesDoForm());
	$.ajax({
		url: "ws/parametrosws/atualizar/",
        type: 'POST',
        data: formData,
        success: function (data) {
        	if(data == "sucess"){
        		 Materialize.toast('Alterado com sucesso!', 4000);
        		 configuracoes=capturarConfiguracoesDoForm();
        	}else{
        		 Materialize.toast('correu um erro ao atualizar as configurações.', 4000);
        	}
        },
		cache: false,
	    contentType: "application/json",
	    processData: false
	});
	
	return false;
});

function reverterAlteracoesDoForm(){
	setarDadosNoFormDeConfig(configuracoes);
}

function capturarConfiguracoesDoForm(){
	var config;
	
	var valorTaxaReservaAtiva = getMarcadoDesmarcado("#checkBoxCobrancaDeTaxaHabilitada");
	var valorTaxaReserva = $("#valorTaxaReserva").val();
	var valorTaxaReserva = {"codigo":1,"descricao":"TAXA DE RESERVA","ativo":valorTaxaReservaAtiva,"valor":valorTaxaReserva};
	
	var AbertoSegunda = getMarcadoDesmarcado("#checkBoxAbertoSegunda");
	var horaInicioSegunda = $("#horaInicioSegunda").val();
	var horaFimSegunda= $("#horaFimSegunda").val();
	var segunda = {"codigo":1,"aberto":AbertoSegunda,"horaInicio":horaInicioSegunda,"horaFim":horaFimSegunda}
	
	var AbertoTerca = getMarcadoDesmarcado("#checkBoxAbertoTerca");
	var horaInicioTerca = $("#horaInicioTerca").val();
	var horaFimTerca = $("#horaFimTerca").val();
	var terca = {"codigo":2,"aberto":AbertoTerca,"horaInicio":horaInicioTerca,"horaFim":horaFimTerca}
	
	var AbertoQuarta = getMarcadoDesmarcado("#checkBoxAbertoQuarta");
	var horaInicioQuarta = $("#horaInicioQuarta").val();
	var horaFimQuarta = $("#horaFimQuarta").val();
	var quarta = {"codigo":3,"aberto":AbertoQuarta,"horaInicio":horaInicioQuarta,"horaFim":horaFimQuarta}
	
	var AbertoQuinta = getMarcadoDesmarcado("#checkBoxAbertoQuinta");
	var horaInicioQuinta = $("#horaInicioQuinta").val();
	var horaFimQuinta = $("#horaFimQuinta").val();
	var quinta = {"codigo":4,"aberto":AbertoQuinta,"horaInicio":horaInicioQuinta,"horaFim":horaFimQuinta}
	
	var AbertoSexta = getMarcadoDesmarcado("#checkBoxAbertoSexta");
	var horaInicioSexta = $("#horaInicioSexta").val();
	var horaFimSexta = $("#horaFimSexta").val();
	var sexta = {"codigo":5,"aberto":AbertoSexta,"horaInicio":horaInicioSexta,"horaFim":horaFimSexta}
	
	var AbertoSabado = getMarcadoDesmarcado("#checkBoxAbertoSabado");
	var horaInicioSabado = $("#horaInicioSabado").val();
	var horaFimSabado = $("#horaFimSabado").val();
	var sabado = {"codigo":6,"aberto":AbertoSabado,"horaInicio":horaInicioSabado,"horaFim":horaFimSabado}
	
	var AbertoDomingo = getMarcadoDesmarcado("#checkBoxAbertoDomingo");
	var horaInicioDomingo = $("#horaInicioDomingo").val();
	var horaFimSegunda = $("#horaFimDomingo").val();
	var domingo = {"codigo":7,"aberto":AbertoDomingo,"horaInicio":horaInicioDomingo,"horaFim":horaFimSegunda}
	
	var horariosDeFuncionamento = [segunda,terca,quarta,quinta,sexta,sabado,domingo];
	
	config = {"valorTaxaReserva":valorTaxaReserva,"horariosDeFuncionamento":horariosDeFuncionamento};
	
	return config;
}

function setarDadosNoFormDeConfig(config){
	/*Valor da taxa de reserva*/
	marcardesmarcar("#checkBoxCobrancaDeTaxaHabilitada",config.valorTaxaReserva.ativo);
	$("#valorTaxaReserva").val(config.valorTaxaReserva.valor);
	/*Parametrizacao dos dias de atendimento*/
	//Segunda
	marcardesmarcar("#checkBoxAbertoSegunda",config.horariosDeFuncionamento[0].aberto);
	$("#horaInicioSegunda").val(config.horariosDeFuncionamento[0].horaInicio);
	$("#horaFimSegunda").val(config.horariosDeFuncionamento[0].horaFim);
	//Terça
	marcardesmarcar("#checkBoxAbertoTerca",config.horariosDeFuncionamento[1].aberto);
	$("#horaInicioTerca").val(config.horariosDeFuncionamento[1].horaInicio);
	$("#horaFimTerca").val(config.horariosDeFuncionamento[1].horaFim);
	//Quarta
	marcardesmarcar("#checkBoxAbertoQuarta",config.horariosDeFuncionamento[2].aberto);
	$("#horaInicioQuarta").val(config.horariosDeFuncionamento[2].horaInicio);
	$("#horaFimQuarta").val(config.horariosDeFuncionamento[2].horaFim);
	//Quinta
	marcardesmarcar("#checkBoxAbertoQuinta",config.horariosDeFuncionamento[3].aberto);
	$("#horaInicioQuinta").val(config.horariosDeFuncionamento[3].horaInicio);
	$("#horaFimQuinta").val(config.horariosDeFuncionamento[3].horaFim);
	//Sexta
	marcardesmarcar("#checkBoxAbertoSexta",config.horariosDeFuncionamento[4].aberto);
	$("#horaInicioSexta").val(config.horariosDeFuncionamento[4].horaInicio);
	$("#horaFimSexta").val(config.horariosDeFuncionamento[4].horaFim);
	//Sabado
	marcardesmarcar("#checkBoxAbertoSabado",config.horariosDeFuncionamento[5].aberto);
	$("#horaInicioSabado").val(config.horariosDeFuncionamento[5].horaInicio);
	$("#horaFimSabado").val(config.horariosDeFuncionamento[5].horaFim);
	//Domingo
	marcardesmarcar("#checkBoxAbertoDomingo",config.horariosDeFuncionamento[6].aberto);
	$("#horaInicioDomingo").val(config.horariosDeFuncionamento[6].horaInicio);
	$("#horaFimDomingo").val(config.horariosDeFuncionamento[6].horaFim);
}

function getMarcadoDesmarcado(idCampo){
	return $(idCampo).is(':checked');
}

function marcardesmarcar(idCampo, valor){
    $(idCampo).each(
          function(){
             $(this).prop("checked", valor);            
           }
      );
  }
