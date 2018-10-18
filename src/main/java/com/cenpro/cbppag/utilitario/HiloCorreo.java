package com.cenpro.cbppag.utilitario;

import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.model.registro.Perfeccionamiento;
import com.cenpro.cbppag.service.IAlertaService;

public class HiloCorreo extends Thread{
	private final EnviarCorreoUtil enviar = new EnviarCorreoUtil();
	private Matricula matricula;
	private Perfeccionamiento pago;
	
	public HiloCorreo(IAlertaService alertaService, Matricula matricula, Perfeccionamiento pago) {
		enviar.setAlerta(alertaService);
		this.matricula = matricula;
		this.pago = pago;
	}
	
	public void run(){
		enviar.enviarCorreo(matricula, pago);
	}
}
