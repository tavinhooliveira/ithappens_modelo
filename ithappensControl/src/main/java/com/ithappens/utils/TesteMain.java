package com.ithappens.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;


public class TesteMain {
	
	public static void main(String[] args) throws ParseException{	
		
		
		
		String dateStart = "15/04/2017";
		String dataStop = "30/04/2017";
	
			
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data1 = new Date(format.parse(dateStart).getTime());
		Date data2 = new Date(format.parse(dataStop).getTime());
		
		DateTime dt1 = new DateTime(data1);
		DateTime dt2 = new DateTime(data2);
		
		try {
		
		String dias = (Days.daysBetween(dt1, dt2).getDays() + " Dias, ");
		String meses = (Months.monthsBetween(dt1, dt2).getMonths() + " Meses, ");
				
		System.out.println(dias+meses);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

					
	} 
	
	
}
