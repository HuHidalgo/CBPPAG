package com.cenpro.cbppag.controller.ingresos;


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

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/ingresos")
public @Controller class IngresosController
{
	private @Autowired IModalidadService modalidadService;
	private @Autowired IEspecializacionService especializacionService;
	private @Autowired IMultiTabDetService multiService;	
	
    @GetMapping(value = "/{mantenimiento:pago}")
    public String irPaginaMantenimientoPago(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        return "seguras/ingresos/mantenimiento";
    }
    
    @GetMapping(value = "/{mantenimiento:matricula}")
    public String irPaginaMantenimientoMatricula(@PathVariable String mantenimiento, ModelMap model)
    {
        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        model.addAttribute("tiposPago", multiService.buscarPorIdTabla(22));
        model.addAttribute("conceptosPago", multiService.buscarPorIdTabla(24));
        return "seguras/ingresos/mantenimiento";
    }
    
    @GetMapping("{reporteIngresos:reportePago}")
    public String irPaginaReportePagos(ModelMap model)
    {
    	model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        return "seguras/ingresos/reportePagos";
    }
    
    @GetMapping("{reporteIngresos:reporteDeuda}")
    public String irPaginaReporteDeudas(ModelMap model)
    {
    	model.addAttribute("modalidades", modalidadService.buscarTodos());
        model.addAttribute("especializaciones", especializacionService.buscarTodos());
        return "seguras/ingresos/reporteDeudas";
    }
}