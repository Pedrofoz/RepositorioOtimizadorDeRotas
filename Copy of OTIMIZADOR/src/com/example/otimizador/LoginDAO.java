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
		values.put("login", objeto.getLogin());
		values.put("password", objeto.getPassword());
		values.put("nome", objeto.getNome());
		values.put("funcao", objeto.getFuncao());
		values.put("ultimo_logon", objeto.getUltimoLogon());
		// Instancia uma conex�o com o banco de dados, em modo de grava��o
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// Insere o registro no banco de dados
		long id = db.insert("login_valido", null, values);
		objeto.setId(id);
		// Encerra a conex�o com o banco de dados
		db.close();
	}
	
	/**
	* Lista todos os registros da tabela �objeto_emprestado�
	*/
	public List<Login> listaTodos() 
	{
	// Cria um List para guardar os objetos consultados no banco de dados
		List<Login> objetos = new ArrayList<Login>();
	// Instancia uma nova conex�o com o banco de dados em modo leitura
		SQLiteDatabase db = dbHelper.getReadableDatabase();
	// Executa a consulta no banco de dados
		Cursor c = db.query("login_valido", null, null, null, null, null, "nome ASC");
	/**
	* Percorre o Cursor, injetando os dados consultados em um objeto 
	* do tipo ObjetoEmprestado e adicionando-os na List
	*/
		try{
			while(c.moveToNext())
			{
				Login objeto = new Login();
				objeto.setId(c.getLong(c.getColumnIndex("_id")));
				objeto.setLogin(c.getString(c.getColumnIndex("login")));
				objeto.setPassword(c.getString(c.getColumnIndex("password")));
				objeto.setNome(c.getString(c.getColumnIndex("nome")));
				objeto.setFuncao(c.getString(c.getColumnIndex("funcao")));
				objeto.setUltimoLogon(c.getString(c.getColumnIndex("ultimo_logon")));
				objetos.add(objeto);
			}
			} finally
			{
			// Encerra o Cursor
				c.close();
			}
			// Encerra a conex�o com o banco de dados
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
		values.put("login", objeto.getLogin());
		values.put("password", objeto.getPassword());
		values.put("nome", objeto.getNome());
		values.put("funcao", objeto.getFuncao());
		values.put("ultimo_logon", objeto.getUltimoLogon());
		// Instancia uma conex�o com o banco de dados, em modo de grava��o
		SQLiteDatabase db = dbHelper. getWritableDatabase();
		// Atualiza o registro no banco de dados
		db.update("login_valido", values, "_id=?", new String[] { objeto.getId().toString() });
		// Encerra a conex�o com o banco de dados
		db.close();
	}
	
	/**
	* Salva objeto no banco de dados.
	* Caso o objeto n�o exista no banco de dados, ele o adiciona.
	* Caso o objeto exista no banco de dados, apenas atualiza os valores dos campos modificados.
	* 
	* @paramobjeto
	*/
	public void salva(Login objeto) 
	{
		/**
		* Se o ID do objeto � nulo � porque ele ainda n�o existe no banco de dados,
		* logo subentende-se que queremos adicionar o objeto no banco de dados.
		* Sendo assim, chamaremos o m�todo adiciona()j� definido no DAO.
		*/
		if(objeto.getId() == null) 
		{
			adiciona(objeto);
		/**
		* Caso o objeto possua um ID � porque ele j� existe no banco de dados, logo 
		* subentende-se que queremos alterar seus dados no banco de dados.
		* Sendo assim, chamaremos o m�todo atualiza()j� definido no DAO.
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
		// Instancia uma conex�o com o banco de dados, em modo de grava��o
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// Remove o registro no banco de dados
		db.delete("login_valido", "_id=?", new String[] { objeto.getId().toString() });
		// Encerra a conex�o com o banco de dados
		db.close();
	}
}