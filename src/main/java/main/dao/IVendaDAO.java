
package main.dao;

import main.domain.Venda;
import main.exceptions.DAOException;
import main.exceptions.TipoChaveNaoEncontradaException;
import main.dao.generic.IGenericDAO;


public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
