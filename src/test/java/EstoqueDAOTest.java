import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import main.dao.EstoqueDAO;
import main.dao.IEstoqueDAO;
import main.domain.Estoque;
import main.exceptions.DAOException;
import main.exceptions.MaisDeUmRegistroException;
import main.exceptions.TableException;
import main.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueDAOTest {

    private IEstoqueDAO estoqueDao;

    public EstoqueDAOTest() {
        this.estoqueDao = new EstoqueDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Estoque> list = estoqueDao.buscarTodos();
        list.forEach(est -> {
            try {
                estoqueDao.excluir(est.getId());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    private Estoque criarEstoque() {
        Estoque estoque = new Estoque();
        estoque.setProduto("Televisão");
        estoque.setQuantidade(50);
        return estoque;
    }

    @Test
    public void salvarEstoque() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Estoque estoque = criarEstoque();
        Boolean sucesso = estoqueDao.cadastrar(estoque);
        Assert.assertTrue(sucesso);

        Estoque encontrado = estoqueDao.consultar(estoque.getId());
        Assert.assertNotNull(encontrado);
    }

    @Test
    public void consultarEstoque() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Estoque estoque = criarEstoque();
        estoqueDao.cadastrar(estoque);

        Estoque encontrado = estoqueDao.consultar(estoque.getId());
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(estoque.getProduto(), encontrado.getProduto());
    }

    @Test
    public void alterarEstoque() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Estoque estoque = criarEstoque();
        estoqueDao.cadastrar(estoque);

        Estoque salvo = estoqueDao.consultar(estoque.getId());
        salvo.setQuantidade(75);
        estoqueDao.alterar(salvo);

        Estoque alterado = estoqueDao.consultar(salvo.getId());
        Assert.assertEquals(Integer.valueOf(75), alterado.getQuantidade());
    }

    @Test
    public void excluirEstoque() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Estoque estoque = criarEstoque();
        estoqueDao.cadastrar(estoque);

        Estoque encontrado = estoqueDao.consultar(estoque.getId());
        Assert.assertNotNull(encontrado);

        estoqueDao.excluir(estoque.getId());

        Estoque excluido = estoqueDao.consultar(estoque.getId());
        Assert.assertNull(excluido);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Estoque e1 = criarEstoque();
        Estoque e2 = criarEstoque();
        e2.setProduto("Televisão");

        estoqueDao.cadastrar(e1);
        estoqueDao.cadastrar(e2);

        Collection<Estoque> todos = estoqueDao.buscarTodos();
        Assert.assertNotNull(todos);
        Assert.assertEquals(2, todos.size());
    }
}
