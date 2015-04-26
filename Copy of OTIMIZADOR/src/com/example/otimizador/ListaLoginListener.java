package com.example.otimizador;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class ListaLoginListener implements AdapterView.OnItemClickListener  
{
		private final ListaLoginActivity activity;
		/**
		* Nosso Construtor dever� receber como par�metro a inst�ncia da Activity 
		* ListaObjetosEmprestadosActivity para ter acesso ao objeto ListView contendo 
		* a lista de objetos emprestados. Isto nos permitir� pegar na lista o item que foi selecionado 
		* pelo usu�rio.
		* 
		*/
		public ListaLoginListener(ListaLoginActivity activity) 
		{
			this.activity = activity;
		}
		/**
		* M�todo que ser� disparado quando o usu�rio selecionar o item na ListView.
		*/
		public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idPosicao) 
		{
			// Declarando a Intent que ir� invocar a Activity "CadastraObjetoEmprestadoActivity"
			Intent i = new Intent(activity, LoginScreen.class);
			/**
			* Passando o objeto selecionado pelo usu�rio, na ListView, como par�metro 
			* para a Activity "CadastraObjetoEmprestadoActivity", para que esta
			* possa editar as informa��es do registro selecionado.
			*/
			i.putExtra("itemSelecionadoParaEdicao", (Login)activity.getListaMateriais().getItemAtPosition(posicao));
			// Invoca a Activity definida em nossa Intent
			activity.startActivity(i);
			activity.finish();
		}
}