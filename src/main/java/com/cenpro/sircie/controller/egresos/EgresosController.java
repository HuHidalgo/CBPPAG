package com.cenpro.sircie.controller.egresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.controller.excepcion.anotacion.Vista;
import com.cenpro.sircie.service.IConceptoService;
import com.cenpro.sircie.service.ICursoService;
import com.cenpro.sircie.service.IMetaService;
import com.cenpro.sircie.service.IMultiTabDetService;
import com.cenpro.sircie.service.IPartidaService;
import com.cenpro.sircie.service.ITareaService;
import com.cenpro.sircie.service.IUnidadService;
import com.cenpro.sircie.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/egresos")
public @Controller class EgresosController {
	private @Autowired IMultiTabDetService multiTabDetService;
    private @Autowired IUnidadService unidadService;
    private @Autowired IMetaService metaService;
    private @Autowired IPartidaService partidaService;
    private @Autowired ITareaService tareaService;
    private @Autowired ICursoService cursoService;
    
    
  //@Audit(tipo = Tipo.Ingresos)
    @GetMapping(value = "/planificacion/{mantenimiento:programacionGastos}")
    public String irPaginaMantenimientoActProgramacionGastos(@PathVariable String mantenimiento, ModelMap model)
    {
    	model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("unidades", unidadService.buscarTodos());
        model.addAttribute("metas", metaService.buscarTodos());
        model.addAttribute("tareas", tareaService.buscarTodos());
        model.addAttribute("partidas", partidaService.buscarTodos());
        return "seguras/egresos/planificacionMantenimiento";
    }
    
    //@Audit(tipo = Tipo.Ingresos)
    @GetMapping(value = "/usgom/{mantenimiento:programacionGastos}")
    public String irPaginaMantenimientoProgramacionGastos(@PathVariable String mantenimiento, ModelMap model)
    {
    	model.addAttribute("mantenimiento", mantenimiento);
    	model.addAttribute("tiposDocumentoIdentidad",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO_IDENTIDAD));
        model.addAttribute("tiposDocumento",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_DOCUMENTO));
        model.addAttribute("tiposAdquisicion",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ADQUISICION));
        model.addAttribute("tiposEstado",
                multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO));
        model.addAttribute("unidades", unidadService.buscarTodos());
        model.addAttribute("cursos", cursoService.buscarTodos());
        model.addAttribute("partidas", partidaService.buscarTodos());
        return "seguras/egresos/usgomMantenimiento";
    }
}
