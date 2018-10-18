package com.cenpro.cbppag.controller.mantenimiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.cenpro.cbppag.service.IPersonaService;
import com.cenpro.cbppag.service.IEspecializacionService;
import com.cenpro.cbppag.service.IModalidadService;
import com.cenpro.cbppag.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/mantenimiento")
public @Controller class MantenimientoController
{
    private @Autowired IPersonaService personaService;
    private @Autowired IMultiTabDetService multiTabDetService;
    private @Autowired IEspecializacionService tareaService;
    private @Autowired IModalidadService modalidadService;

    @Audit(tipo = Tipo.Persona)
    @GetMapping("/{mantenimiento:persona}")
    public String irPaginaMantenimientoPersona(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("tiposDocumento",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO_IDENTIDAD));
        return "seguras/mantenimiento/mantenimiento";
    }

    @Audit(tipo = Tipo.MulTabCab)
    @GetMapping("/{mantenimiento:multitabcab}")
    public String irPaginaMantenimientoTabladeTabla(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        return "seguras/mantenimiento/multiTabla";
    }
   
    @GetMapping("/{mantenimiento:modalidad}")
    public String irPaginaMantenimientoUnidad(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        return "seguras/mantenimiento/mantenimiento";
    }
        
    @GetMapping("/{mantenimiento:especializacion}")
    public String irPaginaMantenimientoEspecializacion(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("modalidades", modalidadService.buscarTodos());
        return "seguras/mantenimiento/especializacion";
    }

    @GetMapping("/{mantenimiento:alerta}")
    public String irPaginaMantenimientoAlerta(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("tiposAlerta", multiTabDetService.buscarPorIdTabla(23));
        return "seguras/mantenimiento/mantenimiento";
    }
    
}