package com.cenpro.cbppag.controller.registro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.cbppag.aspecto.anotacion.Audit;
import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.controller.excepcion.anotacion.Vista;
import com.cenpro.cbppag.service.IEspecializacionService;
import com.cenpro.cbppag.service.IModalidadService;
import com.cenpro.cbppag.service.IMultiTabDetService;
import com.cenpro.cbppag.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/registro")
public @Controller class RegistroController
{
	private @Autowired IModalidadService modalidadService;
	private @Autowired IEspecializacionService especializacionService;
	private @Autowired IMultiTabDetService multiService;	
	
    @GetMapping(value = "/{mantenimiento:perfeccionamiento}")
    public String irPaginaMantenimientoPerfeccionamiento(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
    	model.addAttribute("tiposDocumento", multiService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO_IDENTIDAD));
        model.addAttribute("conceptosPago", multiService.buscarPorIdTabla(24));
        model.addAttribute("tiposPago", multiService.buscarPorIdTabla(22));
        model.addAttribute("modalidades", modalidadService.buscarTodos());
        return "seguras/registro/mantenimiento";
    }
    
    @GetMapping(value = "/{mantenimiento:matricula}")
    public String irPaginaMantenimientoMatricula(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
    	model.addAttribute("tiposDocumento", multiService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO_IDENTIDAD));
        model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        model.addAttribute("tiposPago", multiService.buscarPorIdTabla(22));
        model.addAttribute("conceptosPago", multiService.buscarPorIdTabla(24));
        return "seguras/registro/mantenimiento";
    }
    
    @GetMapping("{reporteIngresos:reportePago}")
    public String irPaginaReportePagos(ModelMap model)
    {
    	model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        return "seguras/registro/reportePagos";
    }
    
    @GetMapping("{reporteIngresos:reporteDeuda}")
    public String irPaginaReporteDeudas(ModelMap model)
    {
    	model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        return "seguras/registro/reporteDeudas";
    }
}