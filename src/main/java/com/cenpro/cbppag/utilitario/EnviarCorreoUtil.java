package com.cenpro.cbppag.utilitario;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.cenpro.cbppag.model.alerta.AlertaPago;
import com.cenpro.cbppag.model.mantenimiento.Alerta;
import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.model.registro.Pago;
import com.cenpro.cbppag.service.IAlertaPagoService;
import com.cenpro.cbppag.service.IAlertaService;
import com.ibm.icu.text.SimpleDateFormat;

public class EnviarCorreoUtil {
	private IAlertaService alertaService;
	private IAlertaPagoService alertaPagoService;
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
	
	public void setAlertaPagoService(IAlertaPagoService alertaPagoService) {
		this.alertaPagoService = alertaPagoService;
	}
	
	public void enviarCorreo(Matricula matricula, Pago pago) {
		if(matricula != null) {
			mensajeRegistroMatricula(matricula);
		}
		if(pago != null) {
			 mensajeRegistroPago(pago);
		}
	}
	
	public int diasVerificacion(String tipoAlerta) {
		List<Alerta> alertas = alertaService.buscarMensaje(tipoAlerta);
		if(!alertas.isEmpty()) {
			return alertas.get(0).getDiasVerificacion();
		}
		return 0;
	}
	
	public int mensajeRegistroMatricula(Matricula matricula) {
		List<Alerta> alertas = alertaService.buscarMensaje("REGISTRO MATRICULA");

		if(!alertas.isEmpty()) {
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
		}
		
        System.out.println("Mensaje de registro de Pago Enviado");
        return 0;
	}
	
	public int mensajeRegistroPago(Pago pago){
		List<Alerta> alertas = alertaService.buscarMensaje("REGISTRO PERFECCIONAMIENTO");
		
		if(!alertas.isEmpty()) {
			estructuraMensaje = "Codigo: " + pago.getCodigoAlumno()+
        			"\nAlumno: " + pago.getNombreAlumno()+" "+pago.getApellidoAlumno()+
        			"\nFecha de Pago: " + date.format(pago.getFechaPago())+
        			"\nConcepto de Pago: " + pago.getConceptoPago()+
        			"\nModalidad: " + pago.getNombreModalidad()+
        			"\nEspecializacion: " + pago.getNombreEspecializacion()+
        			"\nNro. Cuotas que está Pagando: " + pago.getNroCuotasAPagar()+
        			"\nMonto Pagado: " + pago.getMontoPagado()+
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
			    message.setFrom(new InternetAddress(pago.getCorreoAlumno()));
			    message.setRecipients(Message.RecipientType.TO,
			            InternetAddress.parse(pago.getCorreoAlumno()));
			    message.setSubject("Posgrado - Pago de Perfeccionamiento");
			    message.setText(estructuraMensaje);
			    Transport.send(message);
			} catch (Exception e) {
			    e.getStackTrace();
			    return 1;
			}
		}
        System.out.println("Mensaje de registro de Pago Enviado");
        return 0;
    }
	
	public int mensajeAlertaAntes(String fecha) {	
		List<AlertaPago> pagosVencidos = alertaPagoService.buscarPorFecha(fecha);
		
		if(!pagosVencidos.isEmpty()) {
			List<Alerta> alertas = alertaService.buscarMensaje("ANTES DE VENCER");
			ArrayList<String> mensajes = new ArrayList<>();
			for(AlertaPago auxiliar : pagosVencidos) {
				String mensaje = "Alumno: " + auxiliar.getNombreAlumno() + " " + auxiliar.getApellidoAlumno() +
            					 "\nFecha de Vencimiento: " + date.format(auxiliar.getFechaVencimiento()) +
            					 "\nConcepto de Pago: " + auxiliar.getConceptoPago() +
            					 "\nModalidad: " + auxiliar.getModalidad() +
            					 "\nEspecializacion: " + auxiliar.getEspecializacion() +
            					 "\n" + alertas.get(0).getDescAlerta();
				mensajes.add(mensaje);
			}
			
			int i = 0;
			for(String auxiliar : mensajes) {
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
		                    InternetAddress.parse(pagosVencidos.get(i).getCorreoAlumno()));
		            message.setSubject("Posgrado - Aviso de vencimiento de Pago");
		            message.setText(auxiliar);
		            Transport.send(message);
		            
		            i++;
		        } catch (Exception e) {
		            System.out.println("error: "+e);
		        }
		        System.out.println("Mensaje de registro de Pago Enviado1");
			}
			return 1;
		}
		return 0;
	}
	
	public int mensajeAlertaDespues(String fecha) {	
		List<AlertaPago> pagosVencidos = alertaPagoService.buscarPorFecha(fecha);
		
		if(!pagosVencidos.isEmpty()) {
			List<Alerta> alertas = alertaService.buscarMensaje("DESPUES DE VENCER");
			ArrayList<String> mensajes = new ArrayList<>();
			for(AlertaPago auxiliar : pagosVencidos) {
				String mensaje = "Alumno: " + auxiliar.getNombreAlumno() + " " + auxiliar.getApellidoAlumno() +
            					 "\nFecha de Vencimiento: " + date.format(auxiliar.getFechaVencimiento()) +
            					 "\nConcepto de Pago: " + auxiliar.getConceptoPago() +
            					 "\nModalidad: " + auxiliar.getModalidad() +
            					 "\nEspecializacion: " + auxiliar.getEspecializacion() +
            					 "\n" + alertas.get(0).getDescAlerta();
				mensajes.add(mensaje);
			}
			
			int i = 0;
			for(String auxiliar : mensajes) {
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
		                    InternetAddress.parse(pagosVencidos.get(i).getCorreoAlumno()));
		            message.setSubject("Posgrado - Aviso de vencimiento de Pago");
		            message.setText(auxiliar);
		            Transport.send(message);
		            
		            i++;
		        } catch (Exception e) {
		            System.out.println("error: "+e);
		        }
		        System.out.println("Mensaje de registro de Pago Enviado2");
			}
			return 1;
		}
		return 0;
	}
}
