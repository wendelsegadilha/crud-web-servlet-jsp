package br.com.callcentervendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.callcentervendas.model.Fornecedor;
import br.com.callcentervendas.util.ValidacaoException;

public class FornecedorDao {
	
	public List<Fornecedor> getFornecedores() throws SQLException, ClassNotFoundException{
		Connection conexao = ConexaoJDBCFactory.getConexao();
		PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TB_FORNECEDOR");
		ResultSet rs = ps.executeQuery();
		List<Fornecedor> fornecedores = new ArrayList<>();
		while (rs.next()) {
			fornecedores.add(new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return fornecedores;
	}

	public void salvar(Fornecedor fornecedor) throws SQLException, ClassNotFoundException{
		Connection conexao = ConexaoJDBCFactory.getConexao();
		PreparedStatement ps = conexao.prepareStatement("INSERT INTO TB_FORNECEDOR(NM_FORNECEDOR, DS_RAZAO_SOCIAL, DS_CNPJ, DS_EMAIL) VALUES(?,?,?,?)");
		ps.setString(1, fornecedor.getNome());
		ps.setString(2, fornecedor.getRazaoSocial());
		ps.setString(3, fornecedor.getCnpj());
		ps.setString(4, fornecedor.getEmail());
		ps.execute();
	}

	public void excluir(Integer codFornecedor)throws SQLException, ClassNotFoundException{
		Connection conexao = ConexaoJDBCFactory.getConexao();
		PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_FORNECEDOR WHERE CD_FORNECEDOR = ?");
		ps.setInt(1, codFornecedor);
		ps.execute();
	}

	public Fornecedor getFornecedorId(Integer codFornecedor)throws ValidacaoException, SQLException, ClassNotFoundException{
		Connection conexao = ConexaoJDBCFactory.getConexao();
		PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TB_FORNECEDOR WHERE CD_FORNECEDOR = ?");
		ps.setInt(1, codFornecedor);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		throw new ValidacaoException("Não foi encontrado nenhum fornecedor com o código " + codFornecedor);
	}

	public void editar(Fornecedor fornecedor)  throws SQLException, ClassNotFoundException{
		Connection conexao = ConexaoJDBCFactory.getConexao();
		PreparedStatement ps = conexao.prepareStatement("UPDATE TB_FORNECEDOR SET NM_FORNECEDOR = ?, DS_RAZAO_SOCIAL = ?, DS_CNPJ = ?, DS_EMAIL = ? WHERE CD_FORNECEDOR = ?");
		ps.setString(1, fornecedor.getNome());
		ps.setString(2, fornecedor.getRazaoSocial());
		ps.setString(3, fornecedor.getCnpj());
		ps.setString(4, fornecedor.getEmail());
		ps.setInt(5, fornecedor.getCodigo());
		ps.execute();
		
	}

}
