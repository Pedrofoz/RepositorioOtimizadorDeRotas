package com.example.otimizador;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginDAO 
{
	private DBHelperLogin dbHelper;
	
	public LoginDAO(Context context) 
	{
		dbHelper= new DBHelperLogin(context);
	}
	
	/**
	* Adiciona objeto no banco de dados.
	**/
	public void adiciona(Login objeto) 
	{
		// Encapsula no objeto do tipo ContentValues os valores a serem persistidos no banco de dados
		ContentValues values = new ContentValues();
		values.put("name", objeto.getName());
		values.put("email", objeto.getEmail());
		values.put("password_2", objeto.getPassword_2());
		values.put("facebook", objeto.getFacebook());
		values.put("registerDate", objeto.getRegisterDate());
		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// Insere o registro no banco de dados
		long id = db.insert("login_valido", null, values);
		objeto.setIdLogin(id);
		// Encerra a conexão com o banco de dados
		db.close();
	}
	
	/**
	* Lista todos os registros da tabela “objeto_emprestado”
	*/
	public List<Login> listaTodos() 
	{
	// Cria um List para guardar os objetos consultados no banco de dados
		List<Login> objetos = new ArrayList<Login>();
	// Instancia uma nova conexão com o banco de dados em modo leitura
		SQLiteDatabase db = dbHelper.getReadableDatabase();
	// Executa a consulta no banco de dados
		Cursor c = db.query("login_valido", null, null, null, null, null, "name ASC");
	/**
	* Percorre o Cursor, injetando os dados consultados em um objeto 
	* do tipo ObjetoEmprestado e adicionando-os na List
	*/
		try{
			while(c.moveToNext())
			{
				Login objeto = new Login();
				objeto.setIdLogin(c.getLong(c.getColumnIndex("idLogin")));
				objeto.setName(c.getString(c.getColumnIndex("name")));
				objeto.setEmail(c.getString(c.getColumnIndex("email")));
				objeto.setPassword_2(c.getString(c.getColumnIndex("password_2")));
				objeto.setFacebook(c.getInt(c.getColumnIndex("facebook")));
				objeto.setRegisterDate(c.getString(c.getColumnIndex("registerDate")));
				objetos.add(objeto);
			}
			} finally
			{
			// Encerra o Cursor
				c.close();
			}
			// Encerra a conexão com o banco de dados
			db.close();
			// Retorna uma lista com os objetos consultados
			return objetos;
		}
	/**
	* Altera o registro no banco de dados.
	*/
	public void atualiza(Login objeto) 
	{
		// Encapsula no objeto do tipo ContentValues os valores a serem atualizados no banco de dados
		ContentValues values = new ContentValues();
		values.put("name", objeto.getName());
		values.put("email", objeto.getEmail());
		values.put("password_2", objeto.getPassword_2());
		values.put("facebook", objeto.getFacebook());
		values.put("registerDate", objeto.getRegisterDate());
		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper. getWritableDatabase();
		// Atualiza o registro no banco de dados
		db.update("login_valido", values, "idLogin=?", new String[] { objeto.getIdLogin().toString() });
		// Encerra a conexão com o banco de dados
		db.close();
	}
	
	/**
	* Salva objeto no banco de dados.
	* Caso o objeto não exista no banco de dados, ele o adiciona.
	* Caso o objeto exista no banco de dados, apenas atualiza os valores dos campos modificados.
	* 
	* @paramobjeto
	*/
	public void salva(Login objeto) 
	{
		/**
		* Se o ID do objeto é nulo é porque ele ainda não existe no banco de dados,
		* logo subentende-se que queremos adicionar o objeto no banco de dados.
		* Sendo assim, chamaremos o método adiciona()já definido no DAO.
		*/
		if(objeto.getIdLogin() == null) 
		{
			adiciona(objeto);
		/**
		* Caso o objeto possua um ID é porque ele já existe no banco de dados, logo 
		* subentende-se que queremos alterar seus dados no banco de dados.
		* Sendo assim, chamaremos o método atualiza()já definido no DAO.
		*/
		} 
		else
		{
			atualiza(objeto);
		}
	}
	/**
	* Remove um registro no banco de dados.
	*/
	public void remove(Login objeto) 
	{
		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// Remove o registro no banco de dados
		db.delete("login_valido", "idLogin=?", new String[] { objeto.getIdLogin().toString() });
		// Encerra a conexão com o banco de dados
		db.close();
	}
}