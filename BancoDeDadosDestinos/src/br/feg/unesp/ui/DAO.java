package br.feg.unesp.ui;

import java.util.ArrayList;
import java.util.List;

// A classe DAO serve para acessar os dados contidos no objeto modelado pela classe Destinos
public class DAO {
	
	//A classe deve conter um atributo de Collections
	private List<Destinos> lista;
	
	// O método contrutor deve assegurar a criação da ArrayList
	public DAO() {
		lista = new ArrayList<Destinos>();
	}
	
	/*
	 * Daqui para baixo existem métodos para o objeto: getNumElementos, getElemento, buscaElemento
	 * apagaElemento, insereElemento, toString;
	 */

//O que faz? Devolve a quantidade de objetos contidos na lista
//o que devolve? O número de elementos dentro da lista
public int getNumElementos(){
	return lista.size();
}

//O que faz? Verifica se o objeto identificado pelo id está na lista e o pega
//O que devolve? Um objeto destinos caso ele esteja na lista
public Destinos getElemento(int id){
	Destinos result = null;
	if (id < lista.size()) {
		result = lista.get(id);
	}
	return result;
}

//O que faz? O método recebe um id e verifica se o Destinos em questão está na lista.
//O que devolve? "-1" se não estiver na lista; "0" se for o primeiro elemento; "i" se for o i-ésimo elemento
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
//O que faz? O método recebe um objeto do tipo Destinos e verifica se o objeto em questão está na lista.
//O que devolve? "true" se estiver; "false" se não estiver;
public boolean buscaElemento(Destinos d) {
	return lista.contains(d);
}

//O que faz? O método apaga o objeto identificado por id
//O que devolve? A posição do objeto, antes de ser apagado, caso ele esteja na lista. Senão devolve "-1"
public int apagaElemento(int id){
	int result = buscaElemento(id);
	if (result != -1) {
		lista.remove(result);			
	}
	return result;
}
//O que faz? O método apaga o objeto passado.
//O que devolve? "true" se apagou e "false" se não apagou
public boolean apagaElemento(Destinos d){
	return	lista.remove(d);	
}

//O que faz? O método insere o objeto de destinos na lista
//O que devolve? "true" se apagou e "false" se não apagou
public void insereElemento(Destinos d) throws Exception{
	if ( !buscaElemento( d ) ) { //se não estiver na lista
		lista.add(d);            //coloca na lista
	} else                       //se estiver
		throw new Exception("O Destinos "+d.getNomeDaRua()+" com código: "+d.getId()+" ja está na lista"); //escreva
}

//O que faz? Permite ler o informações dos objetos da lista no terminal
//O que devolve? Uma frase(String) contendo as informações
public String toString() {
	String saida = "Numero de elementos " + this.getNumElementos() + "\n";
	for (Destinos d : lista)
		saida += d + "\n";
	return saida;
}

}
