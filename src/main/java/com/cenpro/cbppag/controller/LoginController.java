package com.cenpro.cbppag.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cenpro.cbppag.aspecto.anotacion.Audit;
import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.aspecto.enumeracion.Tipo;
import com.cenpro.cbppag.service.IAlertaPagoService;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.utilitario.EnviarCorreoUtil;
import com.cenpro.cbppag.utilitario.ProcesoAlerta;

public @Controller class LoginController
{
	private @Autowired IAlertaService alertaService;
	private @Autowired IAlertaPagoService alertaPagoService;
	
    @GetMapping(value = { "/", "/login" })
    public String irLogin(@ModelAttribute("mensajeExcepcion") String mensajeExcepcion, Model model)
    {
        model.addAttribute("mensajeExcepcion", mensajeExcepcion);
        EnviarCorreoUtil correo = new EnviarCorreoUtil();
        correo.setAlerta(alertaService);
        correo.setAlertaPagoService(alertaPagoService);
        ProcesoAlerta.proceso(correo);
        return "login";
    }

    @GetMapping(value = "/login", params = "error=true")
    public String irLoginConExcepcion(RedirectAttributes redirectAttribute,
            @RequestParam String mensajeExcepcion)
    {
        redirectAttribute.addFlashAttribute("mensajeExcepcion", mensajeExcepcion);
        System.out.println("2");
        return "redirect:/login";
    }

    @Audit(tipo = Tipo.Logout, comentario = Comentario.Logout, accion = Accion.Acceso)
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
    	System.out.println("3");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}