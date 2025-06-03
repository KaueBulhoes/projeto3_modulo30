import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.EstoqueDaoMock;
import main.domain.Estoque;
import main.exceptions.DAOException;
import main.exceptions.TipoChaveNaoEncontradaException;
import main.services.EstoqueService;
import main.services.IEstoqueService;

public class EstoqueServiceTest {

    private IEstoqueService estoqueService;
    private Estoque estoque;

    @Before
    public void init() {
        estoqueService = new EstoqueService(new EstoqueDaoMock());

        estoque = new Estoque();
        estoque.setProduto("Televisão");
        estoque.setQuantidade(20);
    }

    @Test
    public void salvarEstoque() throws TipoChaveNaoEncontradaException, DAOException {
        Boolean resultado = estoqueService.cadastrar(estoque);
        assertTrue(resultado);

        Estoque buscado = estoqueService.buscarPorId(estoque.getId());
        assertNotNull(buscado);
    }

    @Test
    public void alterarEstoque() throws TipoChaveNaoEncontradaException, DAOException {
        estoqueService.cadastrar(estoque);

        estoque.setQuantidade(15);
        estoqueService.alterar(estoque);

        Estoque atualizado = estoqueService.buscarPorId(estoque.getId());
        assertEquals(Integer.valueOf(15), atualizado.getQuantidade());
    }

    @Test
    public void excluirEstoque() throws TipoChaveNaoEncontradaException, DAOException {
        estoqueService.cadastrar(estoque);
        estoqueService.excluir(estoque.getId());

        Estoque excluido = estoqueService.buscarPorId(estoque.getId());
        assertNull(excluido);
    }

    @Test
    public void validarDisponibilidade_True() throws TipoChaveNaoEncontradaException, DAOException {
        estoque.setQuantidade(10);
        estoqueService.cadastrar(estoque);

        Boolean disponivel = estoqueService.validarDisponibilidade(estoque.getProduto(), 5);
        assertTrue(disponivel);
    }

    @Test
    public void validarDisponibilidade_False() throws TipoChaveNaoEncontradaException, DAOException {
        estoque.setQuantidade(3);
        estoqueService.cadastrar(estoque);

        Boolean disponivel = estoqueService.validarDisponibilidade(estoque.getProduto(), 5);
        assertFalse(disponivel);
    }

    @Test
    public void debitarEstoque() throws TipoChaveNaoEncontradaException, DAOException {
        estoque.setQuantidade(10);
        estoqueService.cadastrar(estoque);

        estoqueService.debitarEstoque(estoque.getProduto(), 4);
        Estoque atualizado = estoqueService.buscarPorId(estoque.getId());

        assertEquals(Integer.valueOf(6), atualizado.getQuantidade());
    }
}
