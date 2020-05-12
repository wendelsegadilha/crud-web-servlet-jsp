package br.com.callcentervendas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.callcentervendas.dao.FornecedorDao;
import br.com.callcentervendas.model.Fornecedor;

/**
 * Servlet implementation class FornecedorService
 */
@WebServlet("/fornecedorService")
public class FornecedorService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FornecedorDao fornecedorDao = new FornecedorDao();
		try {
			List<Fornecedor> fornecedores = fornecedorDao.getFornecedores();
			response.getWriter().print(new Gson().toJson(fornecedores));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


}
