
package main.services;

import main.domain.Produto;
import main.services.generic.GenericService;
import main.dao.IProdutoDAO;


public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
