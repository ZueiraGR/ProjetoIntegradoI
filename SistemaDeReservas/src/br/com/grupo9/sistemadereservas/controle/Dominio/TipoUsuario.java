package br.com.grupo9.sistemadereservas.controle.Dominio;

public enum TipoUsuario {
	CLIENTE('C',"Cliente"),
	FUNCIONARIO('F',"Funcionario");
	
	private char codigo;
	private String descricao;
	
	TipoUsuario(char codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	public String getDescricao(char codigo){
		String retorno = "Não definido";
		if(TipoUsuario.CLIENTE.getCodigo() == codigo){
			retorno = TipoUsuario.CLIENTE.getDescricao();
		}else if(TipoUsuario.FUNCIONARIO.getCodigo() == codigo){
			retorno = TipoUsuario.FUNCIONARIO.getDescricao();
		}
		return retorno;
	}
	public char getCodigo(){
		return this.codigo;
	}
}
