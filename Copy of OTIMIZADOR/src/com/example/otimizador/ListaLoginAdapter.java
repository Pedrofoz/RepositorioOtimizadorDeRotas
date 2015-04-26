package com.example.otimizador;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ListaLoginAdapter extends ArrayAdapter<Login> 
{
	private final List<Login> materiais;
	private final Activity activity;

	public ListaLoginAdapter(Activity activity, int textViewResourceId, List<Login> materiais) 
	{
		super(activity, textViewResourceId, materiais);
		this.activity= activity;
		/**
		 * List de objetos do tipo ObjetoEmprestado recebida como par�metro
		 * pelo Construtor.
		 */
		this.materiais= materiais;
}

		/**
		 * Aqui que � onde a m�gica � feita: as Views TextView do 
		 * Resource Layout "item_lista_objetos_emprestados.xml" s�o preenchidas com os dados
		 * consultados no banco de dados e este Resource Layout ser� retornado como um objeto 
		 * do tipo View para ser adicionado na ListView do Resource 
		 * Layout "activity_lista_objetos_emprestados.xml". 
		 * 
		 * Este m�todo ser� chamado para cada
		 * registro consultado no banco de dados, fazendo com que a ListView mostre todos 
		 * os registros econtrados.
		 */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		/**
		 * Obtemos cada objeto do tipo ObjetoEmprestado dentro da List recebida 
		 * pelo Construtor, de acordo com a sua posi��o, para ser mostrado 
		 * na mesma posi��o da ListView, ou seja, o primeiro objeto da List vai 
		 * para a primeira posi��o da ListView e assim sucessivamente.
		 */
		Login material = materiais.get(position);
		/**
		 * Referenciamos o Resource Layout "item_lista_objetos_emprestados" em um objeto 
		 * do tipo View, que ser� o objeto de retorno deste m�todo. Esta � a View que ser� 
		 * adaptada e retornada para ser apresentada na ListView.	
		 */
		View view = activity.getLayoutInflater().inflate(R.layout.item_lista_login, null);
		/**
		* Injeta o valor do campo referente ao nome do objeto emprestado, do 
		* registro consultado no banco de dados, na TextView de ID "item_objeto_emprestado_nome".
		*/
		TextView nome = (TextView) view.findViewById(R.id.itemLoginNome);
		nome.setText(material.getNome().toString());
		/**
		* Injeta o valor do campo referente ao nome da pessoa para quem o 
		* objeto foi emprestado, do registro consultado no banco de dados, na TextView 
		* de ID "item_objeto_emprestado_pessoa".
		*/
		TextView password = (TextView) view.findViewById(R.id.itemLoginPassword);
		password.setText("Password: "+material.getPassword().toString());
		
		TextView login = (TextView) view.findViewById(R.id.itemLoginLogin);
		login.setText("LOGIN: "+material.getLogin().toString());
		
		TextView ultimoLogon = (TextView) view.findViewById(R.id.itemLoginUltimoLogon);
		ultimoLogon.setText("�ltimo Logon: "+material.getUltimoLogon().toString());
		
		TextView funcao = (TextView) view.findViewById(R.id.itemLoginFuncao);
		funcao.setText("Fun��o: "+material.getFuncao().toString());
		
		// Retorna a View j� adaptada para ser apresentada na ListView
		return view;
	}
	/**
	* Retorna o ID de um determinado item da ListView, de acordo 
	* com a sua posi��o.
	*/
	@Override
	public long getItemId(int position) 
	{
		return materiais.get(position).getId();
	}
	/**
	* Retorna o n�mero de itens que ser�o mostrados na ListView.
	*/
	@Override
	public int getCount() 
	{
		return super.getCount();
	}
	/**
	* Retorna um determinado item da ListView, de acordo
	* com a sua posi��o.
	*/
	@Override
	public Login getItem(int position) 
	{
		return materiais.get(position);
	}
}