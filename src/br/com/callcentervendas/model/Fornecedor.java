package br.com.callcentervendas.model;

import br.com.callcentervendas.util.ValidacaoException;

public class Fornecedor extends Pessoa {
	
	private String razaoSocial;
	private String cnpj;
	
	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer codigo, String nome, String email, String razaoSocial, String cnpj) {
		super(codigo, nome, email);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}
	
	public void valida() throws ValidacaoException{
		super.valida();
		if(razaoSocial == null || razaoSocial.equals("")){
			throw new ValidacaoException("O campo Razão Social é obrigatório");
		}
		if(cnpj == null || cnpj.equals("")){
			throw new ValidacaoException("O campo CNPJ é obrigatório");
		}
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
