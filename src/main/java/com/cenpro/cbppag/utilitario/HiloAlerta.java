package com.cenpro.cbppag.utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class HiloAlerta extends TimerTask{
	private EnviarCorreoUtil enviar;

	public void setEnviar(EnviarCorreoUtil enviar) {
		this.enviar = enviar;
	}

	@Override
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha1 = new Date();
		Date fecha2 = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha1);
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		
		fecha1 = calendar.getTime();
		
		String fechaAntes  = dateFormat.format(fecha1);
		enviar.mensajeAlertaAntes(fechaAntes);
		
		System.out.println(fechaAntes);
		calendar.setTime(fecha2);
		calendar.add(Calendar.DAY_OF_MONTH, -5);
		
		fecha2 = calendar.getTime();
		
		String fechaDespues = dateFormat.format(fecha2);
		System.out.println(fechaDespues);
		enviar.mensajeAlertaDespues(fechaDespues);
	}

}
