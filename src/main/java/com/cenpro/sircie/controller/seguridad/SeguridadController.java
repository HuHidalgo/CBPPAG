package com.cenpro.sircie.controller.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.controller.excepcion.anotacion.Vista;
import com.cenpro.sircie.service.IMultiTabDetService;
import com.cenpro.sircie.service.IPerfilService;
import com.cenpro.sircie.service.IPersonaService;
import com.cenpro.sircie.service.ITipoAuditoriaService;
import com.cenpro.sircie.service.IUsuarioService;
import com.cenpro.sircie.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/seguridad")
public @Controller class SeguridadController
{
    private @Autowired IPerfilService perfilService;
    private @Autowired IPersonaService personaService;
    private @Autowired IUsuarioService usuarioService;
    private @Autowired IMultiTabDetService multiTabDetService;
    private @Autowired ITipoAuditoriaService tipoAuditoriaService;

    @Audit(tipo = Tipo.Usu)
    @GetMapping(value = "/{mantenimiento:usuario}")
    public String irPaginaMantenimientoUsuario(@PathVariable String mantenimiento, Model model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("perfiles", perfilService.buscarTodos());
        model.addAttribute("personas", personaService.buscarTodos());
        return "seguras/seguridad/mantenimiento";
    }

    @GetMapping(value = "/auditoria")
    public String irPaginaConsultaAuditoria(Model model)
    {
        model.addAttribute("usuarios", usuarioService.buscarTodos());
        model.addAttribute("tiposAuditoria", tipoAuditoriaService.buscarTodos());
        model.addAttribute("accionesAuditoria",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ACCION_AUDITORIA));
        return "seguras/auditoria/auditoria";
    }
    
    @GetMapping(value = "/{mantenimiento:perfil}")
    public String irPaginaMantenimientoPefil(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        return "seguras/seguridad/mantenimiento";
    }
}