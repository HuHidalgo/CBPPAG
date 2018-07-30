package com.cenpro.cbppag.utilitario;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.cenpro.cbppag.model.mantenimiento.Alerta;
import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.service.IAlertaService;
import com.ibm.icu.text.SimpleDateFormat;

public class EnviarCorreoUtil {
	private @Autowired IAlertaService alertaService;
	private final String correo = "";
    private final String clave = "";
	private String estructuraMensaje;
	private final SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
	static Properties properties = new Properties();
	
	static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }	
	
	public void setAlerta(IAlertaService alertaService) {
		this.alertaService = alertaService;
	}
	
	public int mensajeRegistroMatricula(Matricula matricula) {
		System.out.println("Objeto : "+alertaService);
 
		List<Alerta> alertas = alertaService.buscarMensaje("REGISTRO MATRICULA");
		System.out.println("Numero : "+alertas.size());
		estructuraMensaje = "Codigo: " + matricula.getCodigoAlumno() +
                			"\nAlumno: " + matricula.getNombreAlumno() + " " + matricula.getApellidoAlumno() +
                			"\nFecha de Pago: " + date.format(matricula.getFechaMatricula()) +
                			"\nConcepto de Pago: " + matricula.getConceptoPago() +
                			"\nModalidad: " + matricula.getNombreModalidad() +
                			"\nEspecializacion: " + matricula.getNombreEspecializacion() +
                			"\nCiclo: " + matricula.getNumeroCiclo() +
                			"\nForma de Pago de ciclo: " + matricula.getTipoPago() +
                			"\n" + alertas.get(0).getDescAlerta();
		try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                public PasswordAuthentication
                        getPasswordAuthentication() {
                    return new PasswordAuthentication(correo, clave);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(matricula.getCorreoAlumno()));
            message.setSubject("Posgrado - Pago de Matricula");
            message.setText(estructuraMensaje);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("error: "+e);
            return 1;
        }
        System.out.println("Mensaje de registro de Pago Enviado");
        return 0;
	}
}