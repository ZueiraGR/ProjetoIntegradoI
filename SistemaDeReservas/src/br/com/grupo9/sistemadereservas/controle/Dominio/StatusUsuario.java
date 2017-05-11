package br.com.grupo9.sistemadereservas.controle.Dominio;
/**
* Classes de dominio se
* @author Marcos
*/
public enum StatusUsuario {
	
	ATIVO('A'){
		@Override
            public String getDescricao(){
                return "Ativo";
            }
        },
	INATIVO('I'){
        	@Override
            public String getDescricao(){
                return "Inativo";
            }
        },
	EXCLUIDO('E'){
        	@Override
            public String getDescricao(){
                return "Excluido";
            }
        };
	
	private char codigo;

	private StatusUsuario(char codigo){
            this.codigo = codigo;
        }
        
	public char getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){ return "";}
        
        public void setCodigo(char codigo){
            this.codigo=codigo;
        }
}
