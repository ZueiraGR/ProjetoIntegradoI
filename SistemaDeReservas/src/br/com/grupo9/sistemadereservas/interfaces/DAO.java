package br.com.grupo9.sistemadereservas.interfaces;

import java.util.List;

public interface DAO<ClassePO> {
	
	public boolean cadastrar(ClassePO entidade);
	public ClassePO capturarPorId(ClassePO entidade);
	public boolean atualizar(ClassePO entidade);
	public List<ClassePO> listar();
	public boolean excluir(ClassePO entidade);
	
}
