package servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import models.ProdutoModel;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			/*String acao = request.getParameter("acao");*/
			
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos" ; // se nulo, atribuir acao "listartodos" como padrão
			String product = request.getParameter("product");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(product);
				request.setAttribute("produtos", daoProduto.listar());
			} else if (acao.equalsIgnoreCase("editar")) {
				ProdutoModel produtoModel = daoProduto.consultar(product);
				request.setAttribute("product", produtoModel); // "product" é o nome da variavel do loop forEach da pagina em que é redirecionada para listar os registros encontrados na base
			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("produtos", daoProduto.listar()); 
			} else {
				request.setAttribute("produtos", daoProduto.listar()); 
			}
			
			request.setAttribute("categorias", daoProduto.listarCategoriaProduto()); //atributo "categorias" vem do loop forEach da pagina para alimentar o combobox
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
				request.setAttribute("categorias", daoProduto.listarCategoriaProduto());
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// recebe os dados vindos da tela a partir do submit do botão
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String strPreco = request.getParameter("preco");
			String strQtdEstoque = request.getParameter("qtdEstoque");
			String categoria = request.getParameter("categoria_id");
			
			BigDecimal preco = null;
			Integer qtdEstoque = null;
			
			//monta o objeto
			ProdutoModel produto = new ProdutoModel();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : 0); // verifica se id ñ é vazio para realizar conversão, se é vazio define como 0
			produto.setNome(nome);
			produto.setDescricao(descricao);
			produto.setCategoria_id(Long.parseLong(categoria));
			
			if (strQtdEstoque != null && !strQtdEstoque.isEmpty()) {
				qtdEstoque = Integer.parseInt(strQtdEstoque);
				produto.setQtdEstoque(qtdEstoque);
			}
			
			if (strPreco != null && !strPreco.isEmpty()) {
				//preco = new BigDecimal(strPreco);
				//preco = new BigDecimal(strPreco).setScale(2);
				//produto.setPreco(preco);
				
				strPreco = strPreco.replaceAll("\\.", "");
				strPreco = strPreco.replaceAll("\\,", ".");
				
				preco = new BigDecimal(strPreco);
				produto.setPreco(preco);
			}
			
			//produto.setPreco(preco);
			//produto.setQtdEstoque(qtdEstoque);

			try {
				
				String msg = null;
				boolean podeInserir = true;

				if (nome == null || nome.isEmpty()) {
					msg = "Nome não preenchido";
					podeInserir = false;

				} else if (descricao == null || descricao.isEmpty()) {
					msg = "Descrição não preenchida";
					podeInserir = false;

				} else if (strPreco == null || strPreco.isEmpty()) {
					msg = "Preço não preenchido";
					podeInserir = false;

				} else if (strQtdEstoque == null  || strQtdEstoque.isEmpty()) {
					msg = "Quantidade em estoque não preenchido";
					podeInserir = false;
					
				} else if (categoria == null || categoria.equalsIgnoreCase("0") || categoria.isEmpty()) {
					msg = "Categoria do produto não selecionada";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					msg = "Nome já em uso por outro produto!";
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoProduto.validarDescricao(descricao)) {
					msg = "Descrição já em uso por outro produto!";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && daoProduto.validarDescricao(descricao)
						&& podeInserir) {
					
					msg = "Produto salvo com sucesso!";
					daoProduto.salvar(produto);
					
				} else if (id != null && !id.isEmpty() && podeInserir) {
					
					msg = "Produto atualizado com sucesso!";
					daoProduto.atualizar(produto);
					/*
					if (!daoProduto.validarNomeUpdate(nome, id)) {
						msg = "Nome já em uso por outro produto!";
						podeInserir = false;
					} else if (!daoProduto.validarDescricaoUpdate(descricao, id)) {
						msg = "Descrição já em uso por outro produto!";
						podeInserir = false;
					} else {
						daoProduto.atualizar(produto);
					}
					*/
				}
				
				// mantém os dados digitados na tela caso os dados não sejam gravados
				if (!podeInserir) {
					request.setAttribute("product", produto); //product é o nome da variavel na impressao da tabela de registros
				}
				

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp"); // executa redirecionamento, porem neste caso fica na mesma tela.
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listarCategoriaProduto());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
