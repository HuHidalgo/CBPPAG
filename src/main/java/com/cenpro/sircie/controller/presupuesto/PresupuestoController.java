package com.cenpro.sircie.controller.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.controller.excepcion.anotacion.Vista;
import com.cenpro.sircie.service.IConceptoService;
import com.cenpro.sircie.service.IMetaService;
import com.cenpro.sircie.service.IMultiTabDetService;
import com.cenpro.sircie.service.IPartidaService;
import com.cenpro.sircie.service.IPersonaService;
import com.cenpro.sircie.service.ITareaService;
import com.cenpro.sircie.service.IUnidadService;
import com.cenpro.sircie.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/presupuesto")
public @Controller class PresupuestoController
{
    private @Autowired ITareaService tareaService;
    private @Autowired IUnidadService unidadService;
    private @Autowired IConceptoService conceptoService;
    private @Autowired IPartidaService partidaService;
    private @Autowired IMultiTabDetService multiTabDetService;

    @GetMapping("/{mantenimiento:ingreso}")
    public String irPaginaMantenimientoPIngreso(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("unidades", unidadService.buscarTodos());
        model.addAttribute("partidas", partidaService.buscarTodos());
        model.addAttribute("conceptos", conceptoService.buscarTodos());
        model.addAttribute("meses",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_MESES));
        return "seguras/presupuesto/mantenimiento";
    }


    @GetMapping("/{mantenimiento:egreso}")
    public String irPaginaMantenimientoPEgreso(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("unidades", unidadService.buscarTodos());
        model.addAttribute("partidas", partidaService.buscarTodos());
        model.addAttribute("tareas", tareaService.buscarTodos());
        model.addAttribute("meses",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_MESES));
        return "seguras/presupuesto/mantenimiento";
    }
    
    @GetMapping("/{mantenimiento:inicial}")
    public String irPaginaMantenimientoInicial(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        return "seguras/presupuesto/mantenimiento";
    }
    
}