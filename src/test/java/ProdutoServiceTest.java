
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.ProdutoDaoMock;
import main.domain.Produto;
import main.exceptions.DAOException;
import main.exceptions.TipoChaveNaoEncontradaException;
import main.services.IProdutoService;
import main.services.ProdutoService;
import main.dao.IProdutoDAO;


public class ProdutoServiceTest {

	private IProdutoService produtoService;
	
	private Produto produto;
	
	public ProdutoServiceTest() {
		IProdutoDAO dao = new ProdutoDaoMock();
		produtoService = new ProdutoService(dao);
	}
	
	@Before
	public void init() {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produto.setMarca("Marca Teste");
	}
	
	@Test
	public void pesquisar() throws DAOException {
		Produto produtor = this.produtoService.consultar(produto.getCodigo());
		Assert.assertNotNull(produtor);
		Assert.assertEquals("Marca Teste", produtor.getMarca());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = produtoService.cadastrar(produto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() throws DAOException {
		produtoService.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		produto.setNome("Kaue Bulhoes");
		produtoService.alterar(produto);
		produto.setMarca("Marca Alterada");
		produtoService.alterar(produto);
		Assert.assertEquals("Marca Alterada", produto.getMarca());
		
		Assert.assertEquals("Kaue Bulhoes", produto.getNome());
	}
}
