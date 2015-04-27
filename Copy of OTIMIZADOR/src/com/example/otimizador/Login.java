package com.example.otimizador;

import java.io.Serializable;
import java.security.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;


public class Login implements Serializable
{
	private static final long serialVersionUID= 1L;
	private Long idLogin;
	private String name;
	private String email;
	private String password_2;
	private int facebook;
	private String registerDate;

	public String getName() 
	{
		return name;
	}
	public void setName(String login)
	{
		this.name = login;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String password) 
	{
		this.email = password;
	}
	public String getPassword_2() 
	{
		return password_2;
	}
	public void setPassword_2(String nome) 
	{
		this.password_2 = nome;
	}
	public int getFacebook() 
	{
		return facebook;
	}
	public void setFacebook(int funcao)
	{
		this.facebook = funcao;
	}
	public String getRegisterDate() 
	{
		return registerDate;
	}
//			private String getDateTime() 
//			{
//		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//		        Date date = new Date();
//		        return dateFormat.format(date);
//			}
	public void setRegisterDate(String ultimoLogon) 
	{
		this.registerDate = ultimoLogon;
	}
	public Long getIdLogin() 
	{
		return idLogin;
	}
	public void setIdLogin(Long id) 
	{
		this.idLogin = id;
	}
}