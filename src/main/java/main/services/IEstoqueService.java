package main.services;

import main.domain.Estoque;
import main.exceptions.DAOException;
import main.exceptions.TipoChaveNaoEncontradaException;

public interface IEstoqueService {

    Boolean cadastrar(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException;

    void excluir(Long id) throws DAOException;

    Estoque buscarPorId(Long id) throws DAOException;

    void alterar(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException;

    Boolean validarDisponibilidade(String idProduto, Integer quantidade) throws DAOException;

    void debitarEstoque(String idProduto, Integer quantidade) throws DAOException;
}
