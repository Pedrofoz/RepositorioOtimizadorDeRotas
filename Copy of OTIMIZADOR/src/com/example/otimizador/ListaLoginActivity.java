package com.example.otimizador;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListaLoginActivity extends Activity
{
	/**
	* Variável de instância do tipo ListView que fará referência à ListView do 
	* nosso Resource Layout e será manipulado via código para 
	* ser preenchido com os dados consultados no banco de dados. 
	*/
	private ListView listaMateriais;
	// ID da opção "Apagar" do menu de contexto
	private static final int MENU_APAGAR= Menu.FIRST;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	// Definimos o Resource Layout a ser controlado pela Activity
		setContentView(R.layout.activity_lista_login);
	/**
	* Fazemos a ligação entre o objeto "listaObjetosEmprestados" e a View 
	* ListView do nosso Resource Layout. 
	*/
		listaMateriais= (ListView) findViewById(R.id.lista_login);
	// Registra o Context Menu para a ListView da nossa tela
		registerForContextMenu(listaMateriais);
	}
	
	/**
	* Retorna o objeto ListView já preenchido com a lista de 
	* objetos emprestados.
	*/
	@Override
	protected void onResume() 
	{
		super.onResume();
		// Chama o método que irá montar a ListView na tela.
		montaListView();
	}
	public ListView getListaMateriais() 
	{
		return listaMateriais;
	}
	
	/**
	* Cria o menu de contexto.
	*/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		// Adiciona a opção "Apagar" ao Context Menu
		menu.add(0, MENU_APAGAR, 0, "Apagar");
	}
	
	/**
	* Monta a ListView com os dados a serem apresentados na tela.
	*/
	private void montaListView()   
	{
		// Consulta os objetos cadastrados no banco de dados através do DAO
		LoginDAO dao = new LoginDAO(getApplicationContext());
		// Guarda os objetos consultados em uma List
		final List<Login> materiais = dao.listaTodos();
		// Instancia o Adapter que irá adaptar os dados na ListView
		ArrayAdapter<Login> adapter = new ListaLoginAdapter(this, android.R.layout.simple_list_item_1, materiais);
		/**
		* Define o Adapter que irá adaptar os dados consultados no banco de
		* dados à nossa ListView do Resource Layout.
		* 
		* @paramadapter
		*/
		listaMateriais.setAdapter(adapter);
		/**
		* Ativa o Listener que contém o evento a ser disparado ao clicar
		* sobre um item da nossa ListView
		*/
		listaMateriais.setOnItemClickListener(new ListaLoginListener(this));
	}
	/**
	* Remove o objeto selecionado.
	*/
	private void remove(Login objeto) 
	{
		// Cria uma nova instância do DAO
		LoginDAO dao = new LoginDAO(getApplicationContext());
		// Remove o objeto do banco de dados
		dao.remove(objeto);
		/**
		* Atualiaza a ListView para que o objeto removido não seja mais
		* apresentado na tela.
		*/
		montaListView();
	}
	
	/**
	* Dispara o evento da opção selecionada no menu de contexto.
	*/
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		/**
		* A classe "AdapterView.AdapterContextMenuInfo" irá nos ajudar 
		* a extrair algumas informações do nosso Context Menu, como por exemplo 
		* a posição da ListView em que a opção do menu foi clicada pelo usuário.
		* Precisaremos exatamente dessa informação para saber qual o item da ListView 
		* que o usuário deseja interagir.
		*/
		AdapterView.AdapterContextMenuInfo info;
		info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		// Trata a ação da opção "Apagar" do menu de contexto
		if(item.getItemId() == MENU_APAGAR) 
		{
			// Obtemos o objeto que o usuário deseja remover através da sua posição na ListView
			Login objeto = (Login) 
			getListaMateriais().getItemAtPosition(info.position);
			// Chama o método que irá remover o objeto do banco de dados
			remove(objeto);
			/**
			* Mostra uma mensagem na tela informando ao usuário que a 
			* operação foi realizada com sucesso
			*/
			Toast.makeText(getApplicationContext(), "Registro removido com sucesso!", Toast.LENGTH_LONG).show();
			/**
			* Após tratar com sucesso o evento de uma opção do 
			* menu de contexto, o retorno deve ser sempre "true" 
			*/
		return true;
		}
		return super.onContextItemSelected(item);
		}
	 @Override
	 public boolean onCreateOptionsMenu (Menu menu)
	 {
		 getMenuInflater().inflate(R.menu.menu, menu);
	 return true ;
	 }
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) 
	 {

	 return super.onOptionsItemSelected(item);
	 }
	 @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) 
		{

		    if (keyCode == KeyEvent.KEYCODE_BACK) 
		    {
		    	Intent intent = new Intent(this, LoginScreen.class);
				startActivity(intent);
				ListaLoginActivity.this.finish();
		    }
		    return super.onKeyDown(keyCode, event);
		}
}
