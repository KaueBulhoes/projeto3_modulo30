package main.services;

import main.dao.IEstoqueDAO;
import main.domain.Estoque;
import main.exceptions.DAOException;
import main.exceptions.MaisDeUmRegistroException;
import main.exceptions.TableException;
import main.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueService implements IEstoqueService {

    private IEstoqueDAO estoqueDAO;

    public EstoqueService(IEstoqueDAO dao) {
        this.estoqueDAO = dao;
    }

    @Override
    public Boolean cadastrar(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException {
        return estoqueDAO.cadastrar(estoque);
    }

    @Override
    public void excluir(Long id) throws DAOException {
        estoqueDAO.excluir(id);
    }

    @Override
    public Estoque buscarPorId(Long id) throws DAOException {
        try {
            return estoqueDAO.consultar(id);
        } catch (MaisDeUmRegistroException | TableException | DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void alterar(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException {
        estoqueDAO.alterar(estoque);
    }

    @Override
    public Boolean validarDisponibilidade(Long idProduto, Integer quantidade) throws DAOException {
        for (Estoque est : estoqueDAO.buscarTodos()) {
            if (est.getProduto().equals(idProduto) && est.getQuantidade() >= quantidade) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void debitarEstoque(Long idProduto, Integer quantidade) throws DAOException {
        for (Estoque est : estoqueDAO.buscarTodos()) {
            if (est.getProduto().equals(idProduto)) {
                est.setQuantidade(est.getQuantidade() - quantidade);
                try {
                    estoqueDAO.alterar(est);
                } catch (TipoChaveNaoEncontradaException | DAOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return;
            }
        }
        throw new DAOException("Produto não encontrado no estoque", null);
    }
}
