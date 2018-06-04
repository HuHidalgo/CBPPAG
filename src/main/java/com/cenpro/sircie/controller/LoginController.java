package com.cenpro.sircie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;

public @Controller class LoginController
{
    @GetMapping(value = { "/", "/login" })
    public String irLogin(@ModelAttribute("mensajeExcepcion") String mensajeExcepcion, Model model)
    {
        model.addAttribute("mensajeExcepcion", mensajeExcepcion);
        return "login";
    }

    @GetMapping(value = "/login", params = "error=true")
    public String irLoginConExcepcion(RedirectAttributes redirectAttribute,
            @RequestParam String mensajeExcepcion)
    {
        redirectAttribute.addFlashAttribute("mensajeExcepcion", mensajeExcepcion);
        return "redirect:/login";
    }

    @Audit(tipo = Tipo.Logout, comentario = Comentario.Logout, accion = Accion.Acceso)
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}