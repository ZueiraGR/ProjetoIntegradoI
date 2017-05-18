package br.com.grupo9.sistemadereservas.interfaces;

import java.util.List;

public interface DAO<ClassePO> {
	
	public void cadastrar(ClassePO entidade);
	public ClassePO capturarPorId(ClassePO entidade);
	public void atualizar(ClassePO entidade);
	public List<ClassePO> listar();
	public void excluir(ClassePO entidade);
	
}
