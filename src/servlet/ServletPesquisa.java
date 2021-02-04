package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import models.UsuarioModel;

@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private DaoUsuario daoUsuario = new DaoUsuario();
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	*/

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String descricaoPesquisa = request.getParameter("consulta"); //campo de nome "consulta" vindo do front-end
		System.out.println(descricaoPesquisa);
		
		if (descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
			try {

				List<UsuarioModel> listaPesquisa = daoUsuario.listar(descricaoPesquisa);
			
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
				request.setAttribute("usuarios", listaPesquisa);
				//request.setAttribute("msg", "Salvo com sucesso");	//"msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
