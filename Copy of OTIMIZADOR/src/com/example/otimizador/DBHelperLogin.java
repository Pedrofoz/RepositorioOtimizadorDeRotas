package com.example.otimizador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperLogin extends SQLiteOpenHelper 
{
	// Nome do banco de dados
	private static final String NOME_DO_BANCO="Login";
	// Vers�o atual do banco de dados
	private static final int VERSAO_DO_BANCO=1;
	
	public DBHelperLogin(Context context) 
	{
		super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
	}
	/**
	* Cria a tabela �controle_de_obra� no banco de dados, caso ela n�o exista.
	*/
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String sql = "CREATE TABLE login_valido ("+"idLogin INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+",name TEXT NOT NULL"+
				 ",email TEXT NOT NULL"+",password_2 TEXT NOT NULL"+",facebook INTEGER NOT NULL"+",registerDate TEXT NOT NULL"+");";
	db.execSQL(sql);
	}
	/**
	* Atualiza a estrutura da tabela no banco de dados, caso sua vers�o tenha mudado.
	*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		String sql = "DROP TABLE IF EXISTS login_valido";
		db.execSQL(sql);
		onCreate(db);
	}
}