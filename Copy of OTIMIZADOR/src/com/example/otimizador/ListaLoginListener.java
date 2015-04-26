package com.example.otimizador;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class ListaLoginListener implements AdapterView.OnItemClickListener  
{
		private final ListaLoginActivity activity;
		/**
		* Nosso Construtor deverá receber como parâmetro a instância da Activity 
		* ListaObjetosEmprestadosActivity para ter acesso ao objeto ListView contendo 
		* a lista de objetos emprestados. Isto nos permitirá pegar na lista o item que foi selecionado 
		* pelo usuário.
		* 
		*/
		public ListaLoginListener(ListaLoginActivity activity) 
		{
			this.activity = activity;
		}
		/**
		* Método que será disparado quando o usuário selecionar o item na ListView.
		*/
		public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idPosicao) 
		{
			// Declarando a Intent que irá invocar a Activity "CadastraObjetoEmprestadoActivity"
			Intent i = new Intent(activity, LoginScreen.class);
			/**
			* Passando o objeto selecionado pelo usuário, na ListView, como parâmetro 
			* para a Activity "CadastraObjetoEmprestadoActivity", para que esta
			* possa editar as informações do registro selecionado.
			*/
			i.putExtra("itemSelecionadoParaEdicao", (Login)activity.getListaMateriais().getItemAtPosition(posicao));
			// Invoca a Activity definida em nossa Intent
			activity.startActivity(i);
			activity.finish();
		}
}