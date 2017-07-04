function carregarMesas() {
	$.ajax({
		url : "ws/mesaws/listarTodas/",
		type : "GET",
		success : function(data) {
			tratarRetornoMesas(data);
		},
		cache : false,
		contentType : "application/json",
		processData : true
	});
}

function tratarRetornoMesas(mesas) {
	if (mesas.length > 0) {
		preencherTelaDeMesas(mesas);
	}
}

function preencherTelaDeMesas(mesas) {
	var html = getLinhasDeMesas(mesas);
	;
	$("#labelListaDeMesas").html(html);
}

/* LÓGICA PARA LISTAGEM DAS MESAS NA PÁGINA DE RESERVA */
function getLinhasDeMesas(mesas) {
	var i = 1;
	var j = 1;
	var linha = 1;
	var html = "";
	var linhaMesas = "";
	for (x in mesas) {
		linhaMesas += getColunaMesa(mesas[x]);
		if (i == 6
				|| (mesas.length < 6 && i == mesas.length)
				|| (mesas.length <= 6 * linha && i == 1 + mesas.length - 6
						* linha) || (mesas.length == j)) {
			html += "<div class='row'>";
			html += linhaMesas;
			html += "</div>";
			linhaMesas = "";
			i = 0;
			linha++;
		}
		i++;
		j++;
	}
	return html;
}

function getColunaMesa(mesa) {
	var html = "";
	html += "<div class='col l2 m4 s6'>";
	html += "<div class='grey lighten-4 z-depth-1'>";
	html += "<div class=''>";
	html += "<p class='center-align mesa-label'>" + mesa.identificacao + "</p>";
	html += "<p class='center-align' onclick='abrirAgenda("
			+ JSON.stringify(mesa) + ")'>";
	html += "<img src='" + getIconeMesa(mesa.status)
			+ "' alt='Mesa 1' class='responsive-img'/>";
	html += "</p>";
	html += "</div>";
	html += "<div class='divider'></div>";
	html += "<div class='row mesa-options'>";
	html += "<a href='#' title='Visualizar informações da mesa' class='col l6 m6 s6 right-align' onclick='abrirInformacoesDaMesa("
			+ JSON.stringify(mesa) + ")'>";
	html += "<i class='fa fa-info-circle' aria-hidden='true'></i>";
	html += "</a>";
	html += "<a href='#' class='col l6 m6 s6 left-align' onclick='abrirAgenda("
			+ JSON.stringify(mesa) + ")'>";
	html += "<i class='fa fa-clock-o' aria-hidden='true'></i>";
	html += "</a>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	return html;
}

function getIconeMesa(status) {
	if (status == 'L') {
		return "img/mesa/mesa_verde.svg";
	} else if (status == 'R') {
		return "img/mesa/mesa_laranja.svg";
	} else if (status == 'O') {
		return "img/mesa/mesa_vermelha.svg";
	}
}

/* =================================================================================== */
function selectPromocoes() {
	$
			.ajax({
				url : "ws/promocaows/listarTodos/",
				type : 'GET',
				success : function(data) {
					$('select').material_select('destroy');
					if (data.length > 0) {
						preencherSelectPromocoes(data);
					} else {
						$("#promocaoDaReserva")
								.html(
										'<option value="" selected="selected">NENHUMA</option>');
					}
					$(document).ready(function() {
						$('select').material_select();
					})
				}
			});
}

function preencherSelectPromocoes(promocoes) {
	var html = '<option value="" selected="selected">NENHUMA</option>';
	for (i = 0; i < promocoes.length; i++) {
		html += getPromocao(promocoes[i]);
	}
	$("#promocaoDaReserva").html(html);
}

function getPromocao(promocao) {
	return '<option value="' + promocao.chave + '">' + promocao.titulo
			+ '</option>';
}
/* =================================================================================== */
var eventos = [
		{
			id : 1,
			title : 'Nome do cliente: Adriano da Silva Santos \nTelefone:(61) 9999-9999',
			start : '2017-04-16T11:00:00',
			end : '2017-04-16T13:00:00'
		},
		{
			id : 2,
			title : 'Nome do cliente: Kleber Silveira Santos \nTelefone:(61) 9999-9999',
			start : '2017-04-16T13:00:00',
			end : '2017-04-16T14:00:00'
		},
		{
			id : 3,
			title : 'Nome do cliente: Maria Cristina da Silva \nTelefone:(61) 9999-9999',
			start : '2017-04-16T14:00:00',
			end : '2017-04-16T14:45:00'
		},
		{
			id : 3,
			title : 'Nome do cliente: José Antonio da Costa \nTelefone:(61) 9999-9999',
			start : '2017-04-18T19:00:00',
			end : '2017-04-18T20:30:00'
		},
		{
			title : 'Nome do cliente: Carlos Eduardo Pinheiro \nTelefone:(61) 9999-9999',
			start : '2017-04-18T20:30:00',
			end : '2017-04-18T21:30:00'
		} ];

function agendaStart(horaInicio, horaFim) {
	var initialLocaleCode = "pt-br";

	$('#calendario')
			.fullCalendar(
					{
						header : {
							left : "prev,next today",
							center : "title",
							right : "month,agendaWeek,agendaDay"
						},
						locale : initialLocaleCode,
						defaultView : "agendaDay",
						// firstDay: 1,
						// defaultDate: "2017-06-25',
						timeFormat : "H:mm",
						hiddenDays : [ 2, 4 ],
						// minTime: horaInicio,
						// maxTime: horaFim,
						slotMinutes : "15",
						allDaySlot : false,
						navLinks : true, // can click day/week names to
											// navigate views
						editable : false,
						selectable : true,
						eventLimit : true, // allow "more" link when too many
											// events
						events : eventos,
						dayClick : function(date, jsEvent, view, resourceObj) {

							$('#calendario').fullCalendar('changeView',
									'agendaDay', date);

						},
						eventClick : function(calEvent, jsEvent, view) {
							var html;
							html = '<h5 class="center-align">DADOS DO AGENDAMENTO</h5>';
							html += '<p class="cen"><b>Cliente:</b> '
									+ calEvent.title.substring(15, 40);
							html += '<br>';
							html += '<b>Telefone:</b> (61)99999-9999';
							html += '<br>';
							html += '<b>Login:</b> teste.teste';
							html += '<br>';
							html += '<b>Mesa: </b> 1A';
							html += '<br>';
							html += '<b>Horário marcado para o inicio do atendimento:</b> '
									+ calEvent.start.format('HH:mm');
							html += '<br>';
							html += '<b>Horário previsto para o fim do atendimento:</b> '
									+ calEvent.end.format('HH:mm');
							html += '</p>';
							$("#corpoModalMostrarDadosAgendamento").html(html);
							$("#modalMostrarDadosAgendamento").modal('open');
						},
						select : function(time) {
							var hora = time.format('HH:mm');
							var data = time.format('DD/MM/YYYY');
							$("#horaInicioH").val(hora);
							$("#dataReservada").val(data);
							$("#chaveMesa").val(mesaSelecionada.chave);
							$("#qtdCadeirasH").val(
									mesaSelecionada.quantidadeDeCadeiras);
							$("#mesaH").val(mesaSelecionada.identificacao);
							$("#modalFormAgendamentoHorario").modal('open');
						}
					});

}

/* ============================================================================================== */

var paginaAtualReservas = 1;
var qtdRegistrosReservas = 10;
var qtdRegistrosReservasObtidos = 0;
var urlReservas = "ws/reservaws/listar/";
var urlUsuarios = "ws/reservaws/listarUsuarios/";

$("#agendamentoRapidoForm").submit(function(event) {
	if (capturarDadosDoForm() != null) {
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url : "ws/reservaws/cadastrar/",
			type : 'POST',
			data : formData,
			success : function(data) {
				tratarRetornoServidor(data);
			},
			cache : false,
			contentType : "application/json",
			processData : true
		});
	}
	return false;
});

function capturarDadosDoForm() {
	var usuario = getUsuarioDaSessao();
	var qtdPessoas = parseInt($("#qtdPessoas").val());
	var chavePromocao = $("#promocaoDaReserva").val();
	var isAtendimentoImediato = $("#isAtendimentoImediato").is(':checked');
	var dataInicio = $("#dataInicio").val();
	var horaInicio = $("#horaInicio").val();
	var inicio;
	var reserva;
	if (isDadosValidos(qtdPessoas,isAtendimentoImediato,dataInicio,horaInicio, usuario.tipo)) {
		inicio = getDateTime(dataInicio,horaInicio);
		reserva = {"inicio":inicio.getTime(),
				   "quantidadeDePessoas":qtdPessoas,
				   "promocao": chavePromocao > 0 ? {"chave":chavePromocao}: null,
				   "usuario": usuario};
	} else {
		reserva = null;
	}
	return reserva;
}
function isDadosValidos(qtdPessoas,isAtendimentoImediato,dataInicio,horaInicio, tipoUsuario) {
	var mensagem;
	var retorno = true;
	if (qtdPessoas == null || qtdPessoas < 1) {
		mensagem += "<li>É obrigatório preencher o campo QUANTIDADE DE PESSOAS com quantidade maior que 0.</li>";
		retorno = false;
	}
	if ((tipoUsuario == 'F' && !isAtendimentoImediato) || tipoUsuario == 'C' ) {
		mensagem += "<li>É obrigatório preencher o campo SOBRENOME</li>";
		if (dataInicio == null || dataInicio == "" || dataInicio.length < 10) {
			mensagem += "<li>É obrigatório selecionar o campo DATA com uma data válida em formado DD/MM/YYYY</li>";
			retorno = false;
		}
		if (horaInicio == null || horaInicio == "" || horaInicio.length < 5) {
			mensagem += "<li>É obrigatório preencher o campo HORA com uma hora válida em formato HH:MM e dentro do período de 00:00 até 23:59</li>";
			retorno = false;
		}
	}
	return retorno;
}

function tratarRetornoServidor(data) {
	$('#modalAgendamento').modal('close');
	var qtdCadeiras = $("#qtdCadeiras").val();
	Materialize.toast('Reserva realizada para a mesa 1A.'+ '<br>Tempo de espera previsto: 15 minutos.', 4000);
}

function limparCamposFormCadastro() {
	$("#nomeReserva").val("");
	$("#sobrenomeReserva").val("");
	$("#cpfReserva").val("");
	$("#loginReserva").val("");
	$("#telefoneReserva").val("");
	$("#emailReserva").val("");
	$("#senhaReserva").val("");
	$("#confirmaSenhaReserva").val("");
	$("#cargoReserva").val("");
}

function carregarReservas(pagina) {
	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosReservas = parseInt($("#qtdRegistrosReservas").val());
	$.ajax({
		url : "ws/reservaws/listar/" + pagina + "/" + qtdRegistrosReservas,
		type : 'GET',
		success : function(data) {
			qtdRegistrosReservasObtidos = data.length;
			$("#barraCarregando").addClass("hiddendiv");
			if (data.length > 0) {
				preencherTabelaReservas(data);
			} else {
				$("#tabelaReservas").html("");
			}
		}
	});
	paginaAtualReservas = pagina;
	listarOpPaginas(idDivPaginacaofuncioanrio, paginaAtualReservas,
			qtdRegistrosReservas, qtdRegistrosReservasObtidos,
			"carregarReservas");
}

function preencherTabelaReservas(arrayDeReservas) {
	var html = "";
	for (i = 0; i < arrayDeReservas.length; i++) {
		html += getLinhaReserva(arrayDeReservas[i]);
	}
	$("#tabelaReservas").html(html);
}

function getLinhaReserva(reserva) {
	linha = "<tr>";
	linha += "<td>" + reserva.nome + "</td>";
	linha += "<td>" + reserva.cargo.nome + "</td>";
	linha += "<td>" + (reserva.email == null ? "" : reserva.email) + "</td>";
	linha += "<td>" + getAcoesReserva(reserva) + "</td>";
	linha += "</tr>";
	return linha;
}
function getAcoesReserva(reserva) {
	var html = getBtnInfoReserva(reserva);
	html += getBtnEditarReserva(reserva);
	html += getBtnAtivarInativar(reserva);
	html += getBtnExcluirReserva(reserva);
	return html;
}

function getBtnInfoReserva(reserva) {
	var html = "<a href='#' onclick='abrirInformacoesReserva("
			+ JSON.stringify(reserva)
			+ ")' title='Mais informações'><i class='fa fa-info-circle fa-lg blue-text text-darken-1 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnEditarReserva(reserva) {
	var html = "<a href='#' onclick='limparFormReserva() , editarReserva("
			+ JSON.stringify(reserva)
			+ ")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnAtivarInativar(reserva) {
	var html = "";
	if (reserva.status != 'A') {
		html = '<a href="#" onclick="bloquearOuDesbloquearReserva('
				+ reserva.chave
				+ ')" title="Desbloquear"><i class="fa fa-check fa-lg green-text text-darken-1 hoverable" aria-hidden="true"></i></a>';
	} else {
		html = '<a href="#" onclick="bloquearOuDesbloquearReserva('
				+ reserva.chave
				+ ')" title="Bloquear"><i class="fa fa-ban fa-lg red-text text-darken-1 hoverable" aria-hidden="true"></i></a>';
	}
	return html;
}

function getBtnExcluirReserva(reserva) {
	var html = '<a href="#"	onclick="excluirReserva('
			+ reserva.chave
			+ ')" title="Excluir"><i class="fa fa-trash-o fa-lg orange-text text-darken-3 hoverable" aria-hidden="true"></i></a>';
	return html;
}

function abrirInformacoesReserva(reserva) {
	$.ajax({
		url : "ws/reservaws/capturarUsuario/" + reserva.chave,
		type : 'GET',
		success : function(usuario) {
			$("#loginReservaV").html(usuario.login);
		},
		data : "",
		contentType : "application/json",
	});

	$("#chaveReservaV").val(reserva.chave);
	$("#statusReservaV").html(reserva.status);
	$("#nomeReservaV").html(reserva.nome);
	$("#sobrenomeReservaV").html(reserva.sobrenome);
	$("#telefoneReservaV").html(reserva.telefone);
	$("#emailReservaV").html(reserva.email);
	$("#cpfReservaV").html(reserva.cpf);
	$("#cargoReservaV").html(reserva.cargo.nome);
	$("#informacoesDoReserva").modal('open');
}

function cadastrarReserva() {
	$('#tituloFomunlarioReserva').html("Formulário de cadastro de funcionário");
	$("#hide-alterar").show();
	$("#cadastrarReserva").modal('open');
	selectCargos();
}

function editarReserva(reserva) {
	$("#chaveReservaA").val(reserva.chave);
	$("#statusReservaA").val(reserva.status);
	$("#nomeReservaA").val(reserva.nome);
	$("#sobrenomeReservaA").val(reserva.sobrenome);
	$("#telefoneReservaA").val(reserva.telefone);
	$("#emailReservaA").val(reserva.email);
	$("#cpfReservaA").val(reserva.cpf);
	$("#alterarReserva").modal('open');
}

function limparFormReserva() {
	$("#chaveReserva").val("");
	$("#nomeReserva").val("");
	$("#sobrenomeReserva").val("");
	$("#telefoneReserva").val("");
	$("#cpfReserva").val("");
	$("#emailReserva").val("");
	$("#senhaReserva").val("");
	$("#confirmaSenhaReserva").val("");
}

function bloquearOuDesbloquearReserva(reserva) {
	// $("#mesagemBloqueio").html('<p class="center-align">Deseja realmente
	// "+acao+' o cliente Maria José Vieira?</p>');
	$("#confirmarBloqueioOuDesbloqueioDoReserva").modal('open');
}

function excluirReserva(chave) {
	$("#confirmarExclusaoDoReserva").modal('open');
	$("#reservaID").val(chave);
}
$("#confirmarExclusaoReserva").submit(function(event) {
	var chave = $("#reservaID").val();
	$.ajax({
		url : "ws/reservaws/excluir/" + chave,
		type : 'GET',
		data : "",
		contentType : "application/json"
	});
	$("#confirmarExclusaoDoReserva").modal('close');
	return false;
});

function selectCargos() {
	$.ajax({
		url : "ws/cargows/listarTodos/",
		type : 'GET',
		success : function(data) {
			$('select').material_select('destroy');
			if (data.length > 0) {
				preencherSelectCargos(data);
			} else {
				$("#cargoReserva").html("");
			}
			$(document).ready(function() {
				$('select').material_select();
			})
		}
	});
}

function preencherSelectCargos(arrayDeCargos) {
	var html = '<option disabled="disabled" selected="selected">Selecione um cargo</option>';
	for (i = 0; i < arrayDeCargos.length; i++) {
		html += getCargo(arrayDeCargos[i]);
	}
	$("#cargoReserva").html(html);
}

function getCargo(cargo) {
	return '<option value="' + cargo.chave + '">' + cargo.nome + '</option>';
}

$("#AlterarDadosReserva").submit(function(event) {
	if (DadosDoFormAlterar() != null) {
		var formData = JSON.stringify(DadosDoFormAlterar());
		$.ajax({
			url : "ws/reservaws/alterar/",
			type : 'POST',
			data : formData,
			cache : false,
			contentType : "application/json",
			processData : true
		});
	}
	return false;
});

function DadosDoFormAlterar() {
	var chave = $("#chaveReservaA").val();
	var nome = $("#nomeReservaA").val();
	var sobrenome = $("#sobrenomeReservaA").val();
	var cpf = $("#cpfReservaA").val();
	var telefone = $("#telefoneReservaA").val();
	var email = $("#emailReservaA").val();
	var status = $("#statusReservaA").val();
	var reserva = {
		"chave" : chave,
		"nome" : nome,
		"sobrenome" : sobrenome,
		"cpf" : cpf,
		"telefone" : telefone,
		"email" : email,
		"status" : status
	};
	return reserva;
}
