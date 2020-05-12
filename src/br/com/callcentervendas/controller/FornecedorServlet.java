package br.com.callcentervendas.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.callcentervendas.dao.FornecedorDao;
import br.com.callcentervendas.model.Fornecedor;
import br.com.callcentervendas.util.ValidacaoException;

/**
 * Servlet implementation class FornecedorServlet
 */
@WebServlet("/fornecedorServlet")
public class FornecedorServlet extends HttpServlet {

	private FornecedorDao fornecedorDao;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FornecedorServlet() {
		super();
		this.fornecedorDao = new FornecedorDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String codigo = request.getParameter("codigo");

		try {
			if (acao != null && acao.equals("excluir")) {
				Integer codFornecedor = Integer.parseInt(codigo);
				fornecedorDao.excluir(codFornecedor);
				request.setAttribute("mensagem", "Fornecedor exclu�do com sucesso");
			} else if (acao != null && acao.equals("editar")) {
				Integer codFornecedor = Integer.parseInt(codigo);
				Fornecedor fornecedor = fornecedorDao.getFornecedorId(codFornecedor);
				request.setAttribute("fornecedor", fornecedor);
			}
		} catch (ValidacaoException e) {
			request.setAttribute("mensagem", e.getMessage());
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("mensagem", "Erro de banco de dados: " + e.getMessage());
		}

		exibirDados(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/paginas/fornecedores.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String razaoSocial = request.getParameter("razaoSocial");
		String email = request.getParameter("email");
		String cnpj = request.getParameter("cnpj");
		String codigo = request.getParameter("codigo");
		
		Fornecedor fornecedor = new Fornecedor(null, nome, email, razaoSocial, cnpj);
		
		if(codigo != null && !codigo.equals("")){
			fornecedor.setCodigo(Integer.parseInt(codigo));
		}
		try {
			fornecedor.valida();
			if(fornecedor.getCodigo() != null){
				fornecedorDao.editar(fornecedor);
				request.setAttribute("mensagem", "Fornecedor atualizado com sucesso");
			}else{
				fornecedorDao.salvar(fornecedor);
				request.setAttribute("mensagem", "Fornecedor salvo com sucesso");
			}
			exibirDados(request);
		} catch (ValidacaoException e) {
			request.setAttribute("mensagem", "Erro de valida��o dos campos: " + e.getMessage());
			request.setAttribute("fornecedor", fornecedor);
			exibirDados(request);
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("mensagem", "Erro de banco de dados: " + e.getMessage());
			request.setAttribute("fornecedor", fornecedor);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/paginas/fornecedores.jsp");
		dispatcher.forward(request, response);

	}

	private void exibirDados(HttpServletRequest request) {
		try {
			request.setAttribute("fornecedores", fornecedorDao.getFornecedores());
		} catch (SQLException | ClassNotFoundException e) {
			request.setAttribute("mensagem", "Erro de banco de dados: " + e.getMessage());
		}
	}

}
