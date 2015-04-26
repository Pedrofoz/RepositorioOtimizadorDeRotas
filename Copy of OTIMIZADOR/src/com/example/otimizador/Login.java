package com.example.otimizador;

import java.io.Serializable;


public class Login implements Serializable
{
	private static final long serialVersionUID= 1L;
	private Long id;
	private String login;
	private String password;
	private String nome;
	private String funcao;
	private String ultimoLogon;

	public String getLogin() 
	{
		return login;
	}
	public void setLogin(String login)
	{
		this.login = login;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getFuncao() 
	{
		return funcao;
	}
	public void setFuncao(String funcao)
	{
		this.funcao = funcao;
	}
	public String getUltimoLogon() 
	{
		return ultimoLogon;
	}
	public void setUltimoLogon(String ultimoLogon) 
	{
		this.ultimoLogon = ultimoLogon;
	}
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id= id;
	}
}