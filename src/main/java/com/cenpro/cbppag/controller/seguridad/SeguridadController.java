package com.cenpro.cbppag.controller.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.cbppag.aspecto.anotacion.Audit;
import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.aspecto.enumeracion.Tipo;
import com.cenpro.cbppag.controller.excepcion.anotacion.Vista;
import com.cenpro.cbppag.service.IMultiTabDetService;
import com.cenpro.cbppag.service.IPerfilService;
import com.cenpro.cbppag.service.IPersonaService;
import com.cenpro.cbppag.service.ITipoAuditoriaService;
import com.cenpro.cbppag.service.IUsuarioService;
import com.cenpro.cbppag.utilitario.MultiTablaUtil;

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