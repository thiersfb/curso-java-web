package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoUsuario;
import models.UsuarioModel;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete") && user != null) {
				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa
																								// redirecionamento,
																								// porem neste caso fica
																								// na mesma tela.
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				// daoUsuario.delete(user);
				UsuarioModel usuarioModel = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa
																								// redirecionamento,
																								// porem neste caso fica
																								// na mesma tela.
				request.setAttribute("user", usuarioModel); // "user" é o nome da variavel do loop da pagina em que é
															// redirecionada
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa
																								// redirecionamento,
																								// porem neste caso fica
																								// na mesma tela.
				request.setAttribute("usuarios", daoUsuario.listar()); // "usuarios" é o nome do atributo do items do
																		// loop da pagina em que é redirecionada
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("download")) {
				UsuarioModel usuarioModel = daoUsuario.consultar(user);
				
				if (usuarioModel != null) {
					
					String nomeArq = "";
					String contentType = "";
					byte[] arquivoBytes = null; 
					
					String tipo = request.getParameter("tipo");
					
					if (tipo.equalsIgnoreCase("Img")) {
						
						/* Converte a base64 da imagem do bancho para byte[] */
						nomeArq = usuarioModel.getFotoNome();
						contentType = usuarioModel.getContentType();
						arquivoBytes = new Base64().decodeBase64(usuarioModel.getFotoBase64());
						//arquivoBytes = new Base64().decodeBase64(usuarioModel.getFotoBase64miniatura().split("\\,")[1]);
						
					} else if (tipo.equalsIgnoreCase("Doc")) {
						nomeArq = usuarioModel.getDocumentoNome();
						contentType = usuarioModel.getContentTypeDocumento();
						arquivoBytes = new Base64().decodeBase64(usuarioModel.getDocumentoBase64());
						
					}
					
					
					//response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuarioModel.getContentType().split("\\/")[1]);
					//response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);
					response.setHeader("Content-Disposition", "attachment;filename=" + nomeArq.split("\\.")[0] +  "." + contentType.split("\\/")[1]);
					
					//response.setHeader("Content-Disposition", "attachment;filename=" + nomeArq);
					
					
					/* Coloca os bytes em um objeto de entrada para processamento */
					InputStream is = new ByteArrayInputStream(arquivoBytes);
					
					/* Início da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
					
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

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa
																								// redirecionamento,
																								// porem neste caso fica
																								// na
																								// mesma tela.
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// recebe os dados vindos da tela a partir do submit do botão
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			/*String telefone = request.getParameter("telefone");*/

			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");
			
			//System.out.println(request.getParameter("ativo")); // on | null

			//monta o objeto
			UsuarioModel usuario = new UsuarioModel();
			//usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			//usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null); // verifica se id ñ é vazio para realizar conversão, se é vazio define como 0
			usuario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			
			// usuario.setId(Long.parseLong(id));
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			/*usuario.setTelefone(telefone);*/
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);

			if (request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) { // usuário está ativo
				usuario.setAtivo(true);
			} else {
				usuario.setAtivo(false);
			}
			
			try {
				
				/* Início File upload de arquivos */
				
				if (ServletFileUpload.isMultipartContent(request)) {
					
					Part imagemFoto = request.getPart("foto"); // checa o input de nome "foto"
					Part documento = request.getPart("documento"); // checa o input de nome "documento"
					
					String nomeImagem = imagemFoto.getSubmittedFileName();
					String nomeDocumento = documento.getSubmittedFileName();
					
					/* Início Processamento Foto */
					if(imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						
						//byte[] bytesImagem = converteStreamParaByte(imagemFoto.getInputStream());
						
						//codifica a imagem para o formato base64
						String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));
						//String fotoBase64 = new Base64().encodeBase64String(bytesImagem); 
						
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
						usuario.setFotoNome(nomeImagem);
						
						/* Início Miniatura imagem */
						
						// Transforma em um bufferedImage 
						byte[] imageByteDecode = new Base64().decode(fotoBase64); //decodifica a imagem em formato base64
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						// Pega o tipo da imagem
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: bufferedImage.getType();
						
						// Cria imagem em miniatura
						BufferedImage resizedImage = new BufferedImage(100, 100, type); //largura, altura, tipo
						Graphics2D graph = resizedImage.createGraphics();
						graph.drawImage(bufferedImage, 0, 0, 100, 100, null);
						
						graph.dispose();

						// Escrevendo novamente a imagem
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
						
						//System.out.println(miniaturaBase64);
						usuario.setFotoBase64miniatura(miniaturaBase64);
						
						/* Fim Miniatura imagem */
						
						
						
					} else {
						/*
						usuario.setFotoBase64(request.getParameter("fotoTemp"));
						usuario.setFotoBase64miniatura(request.getParameter("fotoMiniaturaTemp"));
						usuario.setContentType(request.getParameter("contentTypeTemp"));
						usuario.setFotoNome(request.getParameter("fotoNomeTemp"));
						*/
						usuario.setAtualizarImg(false);
					}
					/* Fim Processamento Foto */
					
					/* Início Processamento Documento */
					if (documento != null && documento.getInputStream().available() > 0) {
						
						String documentoBase64 = new Base64().encodeBase64String(converteStreamParaByte(documento.getInputStream()));
						
						usuario.setDocumentoBase64(documentoBase64);
						usuario.setContentTypeDocumento(documento.getContentType());
						usuario.setDocumentoNome(nomeDocumento);
						
					} else {
						/*
						usuario.setDocumentoBase64(request.getParameter("documentoTemp"));
						usuario.setContentTypeDocumento(request.getParameter("documentoContentTypeTemp"));
						usuario.setDocumentoNome(request.getParameter("documentoNomeTemp"));
						*/
						usuario.setAtualizarDoc(false);
					}
					/* Fim Processamento Documento */
				}
				
				/* Fim File upload de arquivos */

				String msg = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login não preenchido";
					podeInserir = false;

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha não preenchida";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome não preenchido";
					podeInserir = false;

				/*
				} else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone não preenchido";
					podeInserir = false;
				*/	
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "Login já em uso por outro usuário";
					podeInserir = false;
					
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "Senha já em uso por outro usuário!";
					podeInserir = false;
					
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if ((id == null || id.isEmpty()) && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha) && podeInserir) {
					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);
					/*
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						msg = "Login já em uso por outro usuário!";
						podeInserir = false;
					} else if (!daoUsuario.validarSenhaUpdate(senha, id)) {
						msg = "Senha já em uso por outro usuário!";
						podeInserir = false;
					} else {
						daoUsuario.atualizar(usuario);
					}
					*/
				}
				
				/*
				if (msg != null) { request.setAttribute("msg", msg); }
				*/
				
				// mantém os dados digitados na tela caso os dados não sejam gravados
				if (!podeInserir) {
					request.setAttribute("user", usuario); //user é o nome da variavel na impressao da tabela de registros
				}
				
				
				// mantém os dados digitados na tela caso os dados não sejam gravados
				/*
				if (!daoUsuario.validarLoginUpdate(login, id) && !daoUsuario.validarSenhaUpdate(senha, id)) {
					request.setAttribute("user", usuario);
				}
				*/

				// try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // executa
																								// redirecionamento,
																								// porem neste caso
																								// fica
																								// na
																								// mesma tela.
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Salvo com sucesso");	//"msg" é um atributo no front-end recuperado no back-end, que define a mensagem a ser impressa
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/* Converte a entrada do fluxo de dados da imagem para byte[] */
	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int reads = imagem.read();
		while(reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		
		return baos.toByteArray();

	}

}
