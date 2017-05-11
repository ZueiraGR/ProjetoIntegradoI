package br.com.grupo9.sistemadereservas.controle.Dominio;

public enum TipoUsuario {
	CLIENTE('C',"Cliente"),
	FUNCIONARIO('F',"Funcion�rio");
	
	private char codigo;
	private String descricao;
	
	TipoUsuario(char codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	public char getCodigo(){
		return this.codigo;
	}
}
