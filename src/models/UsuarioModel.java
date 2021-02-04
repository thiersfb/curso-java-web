package models;

public class UsuarioModel {

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String telefone;
	
	private String fotoNome;
	private String fotoBase64;
	private String fotoBase64miniatura;
	private String contentType;

	private String documentoNome;
	private String documentoBase64;
	private String contentTypeDocumento;
	
	private String cep, rua, bairro, cidade, estado, ibge;
	
	private String tempFotoUser; 
	
	private boolean ativo;
	private String sexo;
	private String perfil;
	
	private boolean atualizarImg = true;
	private boolean atualizarDoc = true;
	
	/*
	 * public boolean validarAcesso(String login, String senha) { 
	 * 		
	 * 		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) { 
	 * 			return true; 
	 * 		} else { 
	 * 			return false; 
	 *		} 
	 * }
	 */

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	
	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public String getDocumentoBase64() {
		return documentoBase64;
	}

	public void setDocumentoBase64(String documentoBase64) {
		this.documentoBase64 = documentoBase64;
	}
	

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentTypeDocumento() {
		return contentTypeDocumento;
	}

	public void setContentTypeDocumento(String contentTypeDocumento) {
		this.contentTypeDocumento = contentTypeDocumento;
	}



	public String getFotoNome() {
		return fotoNome;
	}

	public void setFotoNome(String fotoNome) {
		this.fotoNome = fotoNome;
	}

	public String getDocumentoNome() {
		return documentoNome;
	}

	public void setDocumentoNome(String documentoNome) {
		this.documentoNome = documentoNome;
	}


	public String getTempFotoUser() {
		
		tempFotoUser = "data:" + contentType + ";base64," + fotoBase64;
		
		return tempFotoUser;
	}

	public String getFotoBase64miniatura() {
		return fotoBase64miniatura;
	}
	
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public void setFotoBase64miniatura(String fotoBase64miniatura) {
		this.fotoBase64miniatura = fotoBase64miniatura;
	}

	public boolean isAtualizarImg() {
		return atualizarImg;
	}

	public void setAtualizarImg(boolean atualizarImg) {
		this.atualizarImg = atualizarImg;
	}

	public boolean isAtualizarDoc() {
		return atualizarDoc;
	}

	public void setAtualizarDoc(boolean atualizarDoc) {
		this.atualizarDoc = atualizarDoc;
	}

	
	
}
