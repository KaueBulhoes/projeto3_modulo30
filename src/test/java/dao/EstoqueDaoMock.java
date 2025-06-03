package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import main.dao.IEstoqueDAO;
import main.domain.Estoque;
import main.exceptions.DAOException;
import main.exceptions.MaisDeUmRegistroException;
import main.exceptions.TableException;
import main.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueDaoMock implements IEstoqueDAO {

    private Map<Long, Estoque> map = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public Boolean cadastrar(Estoque entity) throws TipoChaveNaoEncontradaException, DAOException {
        entity.setId(idCounter++);
        map.put(entity.getId(), entity);
        return true;
    }

    @Override
    public void excluir(Long valor) throws DAOException {
        map.remove(valor);
    }

    @Override
    public void alterar(Estoque entity) throws TipoChaveNaoEncontradaException, DAOException {
        map.put(entity.getId(), entity);
    }

    @Override
    public Estoque consultar(Long valor) throws MaisDeUmRegistroException, TableException, DAOException {
        return map.get(valor);
    }

    @Override
    public Collection<Estoque> buscarTodos() throws DAOException {
        return map.values();
    }
}
