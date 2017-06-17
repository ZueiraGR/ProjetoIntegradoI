package br.com.grupo9.sistemadereservas.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

public interface DAO<ClassePO> {

	public boolean cadastrar(ClassePO entidade);
	public ClassePO capturarPorId(ClassePO entidade);
	public boolean atualizar(ClassePO entidade);
	public List<ClassePO> listar(Integer pagina, Integer qtdRegistros, String filtro);
	public boolean excluir(ClassePO entidade);
	public void fecharManager();
	
}
