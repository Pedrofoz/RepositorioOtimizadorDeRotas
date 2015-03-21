package br.feg.unesp.ui;

import java.util.ArrayList;
import java.util.List;

// A classe DAO serve para acessar os dados contidos no objeto modelado pela classe Destinos
public class DAO {
	
	//A classe deve conter um atributo de Collections
	private List<Destinos> lista;
	
	// O m�todo contrutor deve assegurar a cria��o da ArrayList
	public DAO() {
		lista = new ArrayList<Destinos>();
	}
	
	/*
	 * Daqui para baixo existem m�todos para o objeto: getNumElementos, getElemento, buscaElemento
	 * apagaElemento, insereElemento, toString;
	 */

//O que faz? Devolve a quantidade de objetos contidos na lista
//o que devolve? O n�mero de elementos dentro da lista
public int getNumElementos(){
	return lista.size();
}

//O que faz? Verifica se o objeto identificado pelo id est� na lista e o pega
//O que devolve? Um objeto destinos caso ele esteja na lista
public Destinos getElemento(int id){
	Destinos result = null;
	if (id < lista.size()) {
		result = lista.get(id);
	}
	return result;
}

//O que faz? O m�todo recebe um id e verifica se o Destinos em quest�o est� na lista.
//O que devolve? "-1" se n�o estiver na lista; "0" se for o primeiro elemento; "i" se for o i-�simo elemento
public int buscaElemento(int id) {
	int result = -1;
	for (int i = 0; i < lista.size(); i++) {
		if (lista.get(i).getId()==id) {
			result = i;
			break;//sai do for
		}
	}
	return result;
}
//O que faz? O m�todo recebe um objeto do tipo Destinos e verifica se o objeto em quest�o est� na lista.
//O que devolve? "true" se estiver; "false" se n�o estiver;
public boolean buscaElemento(Destinos d) {
	return lista.contains(d);
}

//O que faz? O m�todo apaga o objeto identificado por id
//O que devolve? A posi��o do objeto, antes de ser apagado, caso ele esteja na lista. Sen�o devolve "-1"
public int apagaElemento(int id){
	int result = buscaElemento(id);
	if (result != -1) {
		lista.remove(result);			
	}
	return result;
}
//O que faz? O m�todo apaga o objeto passado.
//O que devolve? "true" se apagou e "false" se n�o apagou
public boolean apagaElemento(Destinos d){
	return	lista.remove(d);	
}

//O que faz? O m�todo insere o objeto de destinos na lista
//O que devolve? "true" se apagou e "false" se n�o apagou
public void insereElemento(Destinos d) throws Exception{
	if ( !buscaElemento( d ) ) { //se n�o estiver na lista
		lista.add(d);            //coloca na lista
	} else                       //se estiver
		throw new Exception("O Destinos "+d.getNomeDaRua()+" com c�digo: "+d.getId()+" ja est� na lista"); //escreva
}

//O que faz? Permite ler o informa��es dos objetos da lista no terminal
//O que devolve? Uma frase(String) contendo as informa��es
public String toString() {
	String saida = "Numero de elementos " + this.getNumElementos() + "\n";
	for (Destinos d : lista)
		saida += d + "\n";
	return saida;
}

}
