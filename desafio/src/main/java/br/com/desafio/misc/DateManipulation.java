package br.com.desafio.misc;

import java.util.Calendar;

public class DateManipulation {
	
	public static int diferencaEmDias(Calendar c1, Calendar c2)
	{
		long m1 = c1.getTimeInMillis();
	    long m2 = c2.getTimeInMillis();
	    
	    return (int) ((m2 - m1) / (24*60*60*1000));
	}
	
}
