package br.com.grupo9.sistemadereservas.controle.Dominio;

public enum StatusMesa {
	LIVRE('L',"Livre"),
	RESERVADA('R',"Reservada"),
	OCUPADA('O',"Ocupada"),
	EXCLUIDA('E',"Excluída");
	
	private char codigo;
	private String descricao;
	
	private StatusMesa(char codigo, String descricao){
		this.codigo= codigo;
		this.descricao = descricao;
	}
	
	public char getCodigo(){
		return this.codigo;
	}
	public String getDescricao(){
		return this.descricao;
	}
	
	public boolean equals(Character codigo){
		return codigo.charValue() == this.codigo ? true : false;
	}
	
	public static String getDescricao(Character codigo){
		String retorno;
		if(LIVRE.equals(codigo)){
			retorno= LIVRE.getDescricao();
		}else if(RESERVADA.equals(codigo)){
			retorno = RESERVADA.getDescricao();
		}else if(OCUPADA.equals(codigo)){
			retorno = OCUPADA.getDescricao();
		}else if(EXCLUIDA.equals(codigo)){
			retorno = EXCLUIDA.getDescricao();
		}else{
			retorno = "Não definido.";
		}
		return retorno;
	}
}
