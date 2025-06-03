package main.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.dao.generic.GenericDAO;
import main.domain.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque, Long> implements IEstoqueDAO{
    
    public EstoqueDAO() {
        super();
    }

    @Override
    public Class<Estoque> getTipoClasse() {
        return Estoque.class;
    }

    @Override
    public void atualizarDados(Estoque estoqueNovo, Estoque estoqueDB) {
        estoqueDB.setProduto(estoqueNovo.getProduto());
        estoqueDB.setQuantidade(estoqueNovo.getQuantidade());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_ESTOQUE ");
        sb.append("(PRODUTO, QUANTIDADE)");
        sb.append("VALUES (?,?)");
        return sb.toString();
	}

    @Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque estoqueNovo) throws SQLException {
		stmInsert.setString(1, estoqueNovo.getProduto());
		stmInsert.setLong(2, estoqueNovo.getQuantidade());
	}

    @Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_ESTOQUE WHERE ID = ?";
	}

    @Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) throws SQLException {
		stmExclusao.setLong(1, valor);
	}

    @Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_ESTOQUE ");
		sb.append("SET PRODUTO = ?,");
		sb.append("QUANTIDADE = ?,");
		sb.append(" WHERE ID = ?");
		return sb.toString();
	}

    @Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque estoqueNovo) throws SQLException {
		stmUpdate.setString(1, estoqueNovo.getProduto());
		stmUpdate.setLong(2, estoqueNovo.getQuantidade());
	}

    @Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
	}
}
