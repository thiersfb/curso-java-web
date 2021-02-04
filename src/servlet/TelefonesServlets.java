package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import dao.DaoUsuarioTelefone;
import models.UsuarioModel;
import models.UsuarioTelefoneModel;

@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	
	private DaoUsuarioTelefone daoUsuarioTelefone = new DaoUsuarioTelefone();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			
			String acao = request.getParameter("acao");
			
			String user = request.getParameter("user"); // recupera o parâmetro de nome "user" enviado pelo front-end
			
			if (user != null) {
				UsuarioModel usuario = daoUsuario.consultar(user);
				
				if(acao.equals("addFone")) {
		
					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);
					
					RequestDispatcher view = request.getRequestDispatcher("/usuarioTelefones.jsp");
					
					request.setAttribute("telefones", daoUsuarioTelefone.listar(usuario.getId()));	//carrega lista de dados cadastrados ao acessar a tela
					//request.setAttribute("msg", "Salvo com sucesso"); // "msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
	
					view.forward(request, response);
	
				} else if(acao.equals("deleteFone")) {
					
					String foneId = request.getParameter("foneId");
					daoUsuarioTelefone.delete(foneId);
					
					UsuarioModel usuarioModel = (UsuarioModel) request.getSession().getAttribute("userEscolhido");
					
					RequestDispatcher view = request.getRequestDispatcher("/usuarioTelefones.jsp");
					
					request.setAttribute("telefones", daoUsuarioTelefone.listar(usuarioModel.getId()));	//carrega lista de dados cadastrados ao acessar a tela
					request.setAttribute("msg", "Removido com sucesso"); // "msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
	
					view.forward(request, response);
				
				/*
				} else if(acao.equals("reset")) {
					try {
						RequestDispatcher view = request.getRequestDispatcher("/usuarioTelefones.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
						//request.setAttribute("usuarios", daoUsuario.listar());
						request.setAttribute("telefones", daoUsuarioTelefone.listar(usuario.getId()));
						view.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				*/	
				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		
		try {
						
				//recupera sessão
				UsuarioModel usuarioModel = (UsuarioModel) request.getSession().getAttribute("userEscolhido");
				
				String numero = request.getParameter("numero");
				String tipo = request.getParameter("tipo");
				String acao = request.getParameter("acao");
				
				if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {
					
						if (numero == null || (numero != null && numero.isEmpty())) {
						
							RequestDispatcher view = request.getRequestDispatcher("/usuarioTelefones.jsp");
							request.setAttribute("telefones", daoUsuarioTelefone.listar(usuarioModel.getId()));
							request.setAttribute("msg", "Número não foi preenchido"); // "msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
							//request.setAttribute("userEscolhido", user);
							view.forward(request, response);
						
						} else {
						
							UsuarioTelefoneModel usuarioTelefoneModel = new UsuarioTelefoneModel();
							
							usuarioTelefoneModel.setNumero(numero);
							usuarioTelefoneModel.setTipo(tipo);
							usuarioTelefoneModel.setUsuario(usuarioModel.getId());
						
							daoUsuarioTelefone.salvar(usuarioTelefoneModel);
							
							//recupera sessão e define atributos para que não seja perdido pela ServLet
							request.getSession().setAttribute("userEscolhido", usuarioModel);
							request.setAttribute("userEscolhido", usuarioModel);
							
							RequestDispatcher view = request.getRequestDispatcher("/usuarioTelefones.jsp");
							request.setAttribute("telefones", daoUsuarioTelefone.listar(usuarioModel.getId()));
							request.setAttribute("msg", "Salvo com sucesso"); // "msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
							//request.setAttribute("userEscolhido", user);
							view.forward(request, response);
						}
				} else {
				
					try {
						RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa redirecionamento para a tela de cadastro de usuario
						request.setAttribute("usuarios", daoUsuario.listar());
						view.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
