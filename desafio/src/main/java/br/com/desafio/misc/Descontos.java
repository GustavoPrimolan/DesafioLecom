package br.com.desafio.misc;


import br.com.desafio.enums.Plano;

public class Descontos {

	public static double calculoDesconto(Plano plano, int dias) {
		double porcentagem = 0.0;
		
		if (plano.equals(Plano.OURO)) {
			porcentagem = 0.10;
		
		} else if (plano.equals(Plano.PRATA)) {
			porcentagem=0.05;
		}
		
		if(dias > 10)
			porcentagem+=0.05;

		return porcentagem;
	}

}
