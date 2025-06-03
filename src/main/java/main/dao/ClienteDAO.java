
package main.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.domain.Cliente;
import main.dao.generic.GenericDAO;


public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super();
	}

	@Override
	public Class<Cliente> getTipoClasse() {
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente clienteNovo, Cliente clienteDB) {
		clienteDB.setNome(clienteNovo.getNome());
		clienteDB.setCpf(clienteNovo.getCpf());
		clienteDB.setTel(clienteNovo.getTel());
		clienteDB.setEnd(clienteNovo.getEnd());
		clienteDB.setNumero(clienteNovo.getNumero());
		clienteDB.setCidade(clienteNovo.getCidade());
		clienteDB.setEstado(clienteNovo.getEstado());
		clienteDB.setEmail(clienteNovo.getEmail());
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_CLIENTE ");
		sb.append("(ID, NOME, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO, EMAIL)");
		sb.append("VALUES (nextval('sq_cliente'),?,?,?,?,?,?,?,?)");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente clienteNovo) throws SQLException {
		stmInsert.setString(1, clienteNovo.getNome());
		stmInsert.setLong(2, clienteNovo.getCpf());
		stmInsert.setLong(3, clienteNovo.getTel());
		stmInsert.setString(4, clienteNovo.getEnd());
		stmInsert.setLong(5, clienteNovo.getNumero());
		stmInsert.setString(6, clienteNovo.getCidade());
		stmInsert.setString(7, clienteNovo.getEstado());
		stmInsert.setString(8, clienteNovo.getEmail());
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_CLIENTE WHERE CPF = ?";
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) throws SQLException {
		stmExclusao.setLong(1, valor);
	}
	
	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_CLIENTE ");
		sb.append("SET NOME = ?,");
		sb.append("TEL = ?,");
		sb.append("ENDERECO = ?,");
		sb.append("NUMERO = ?,");
		sb.append("CIDADE = ?,");
		sb.append("ESTADO = ?");
		sb.append("EMAIL = ?");
		sb.append(" WHERE CPF = ?");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente clienteNovo) throws SQLException {
		stmUpdate.setString(1, clienteNovo.getNome());
		stmUpdate.setLong(7, clienteNovo.getCpf());
		stmUpdate.setLong(2, clienteNovo.getTel());
		stmUpdate.setString(3, clienteNovo.getEnd());
		stmUpdate.setLong(4, clienteNovo.getNumero());
		stmUpdate.setString(5, clienteNovo.getCidade());
		stmUpdate.setString(6, clienteNovo.getEstado());
		stmUpdate.setString(8, clienteNovo.getEmail());
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
	}

}
