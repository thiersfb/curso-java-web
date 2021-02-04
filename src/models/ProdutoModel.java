package models;

import java.math.BigDecimal;

//import javafx.util.converter.BigDecimalStringConverter;

//import java.util.Date;

public class ProdutoModel {

	private Long id;
	private String nome, descricao;
	private int qtdEstoque;
	//private float preco;
	private BigDecimal preco;
	// private Date ;
	private Long categoria_id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}

	/*
	public double getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	*/
	
	/*
	//public BigDecimal getPrecoEmTexto() {
	public String getPrecoEmTexto() {
		//return Double.toString(preco).replace('.',',');
		
		//String strPreco = preco.toString().replace('.', ',');
		//strPreco = strPreco.replace('.', ',');
				
		return preco.toString().replace('.', ',');
	}
	*/
	
	

}
