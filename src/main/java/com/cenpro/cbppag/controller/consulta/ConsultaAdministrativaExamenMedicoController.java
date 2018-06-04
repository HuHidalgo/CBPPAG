package com.cenpro.cbppag.controller.consulta;

import static com.cenpro.cbppag.utilitario.ConstantesGenerales.PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
import static com.cenpro.cbppag.utilitario.ConstantesGenerales.P_CAMPANIAS;
import static com.cenpro.cbppag.utilitario.ConstantesGenerales.P_CONSULTA;
import static com.cenpro.cbppag.utilitario.ConstantesGenerales.P_ESTADOS_EXAMEN_MEDICO;
import static com.cenpro.cbppag.utilitario.ConstantesGenerales.P_FACULTADES;
import static com.cenpro.cbppag.utilitario.ConstantesGenerales.P_TIPOS_ALUMNO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cenpro.cbppag.controller.excepcion.anotacion.Vista;
import com.cenpro.cbppag.service.ICampaniaService;
import com.cenpro.cbppag.service.IFacultadService;
import com.cenpro.cbppag.service.IMultiTabDetService;
import com.cenpro.cbppag.service.IParametroGeneralService;
import com.cenpro.cbppag.utilitario.DatesUtils;
import com.cenpro.cbppag.utilitario.MultiTablaUtil;

@Vista
@RequestMapping("/consulta/administrativa")
public @Controller class ConsultaAdministrativaExamenMedicoController
{
    private @Autowired IFacultadService facultadService;
    private @Autowired ICampaniaService campaniaService;
    private @Autowired IMultiTabDetService multiTabDetService;
    private @Autowired IParametroGeneralService parametroGeneralService;

    @GetMapping("/{consulta:laboratorio}")
    public String irPaginaConsultaAdministrativaExamenMedicoLaboratorio(
            @PathVariable String consulta, ModelMap model)
    {
        model.addAttribute(P_TIPOS_ALUMNO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
        model.addAttribute(P_ESTADOS_EXAMEN_MEDICO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO_EXAMEN_MEDICO));
        model.addAttribute(P_CAMPANIAS, campaniaService.buscarTodos());
        model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
        model.addAttribute("anios", DatesUtils.obtenerAnios(parametroGeneralService.buscarAnioInicio()));
        model.addAttribute(P_CONSULTA, consulta);
        model.addAttribute("indicadoresRPR", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RPR));
        model.addAttribute("indicadoresRPR2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RPR));
        model.addAttribute("indicadoresHemograma2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HEMOGRAMA));
        model.addAttribute("indicadoresHemograma", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HEMOGRAMA));
        model.addAttribute("diluciones", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_DILUCION));
        model.addAttribute("diluciones2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_DILUCION));
        model.addAttribute("gruposSanguineo", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_GRUPO_SANGUINEO));
        model.addAttribute("gruposSanguineo2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_GRUPO_SANGUINEO));
        model.addAttribute("factoresRh", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_FACTOR_RH));
        model.addAttribute("factoresRh2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_FACTOR_RH));
        model.addAttribute("tiposHemoglobina", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HEMOGLOBINA));
        return PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
    }

    @GetMapping("/{consulta:radiologico}")
    public String irPaginaConsultaAdministrativaExamenMedicoRadiologico(
            @PathVariable String consulta, ModelMap model)
    {
        model.addAttribute(P_TIPOS_ALUMNO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
        model.addAttribute(P_ESTADOS_EXAMEN_MEDICO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO_EXAMEN_MEDICO));
        model.addAttribute(P_CAMPANIAS, campaniaService.buscarTodos());
        model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
        model.addAttribute("anios", DatesUtils.obtenerAnios(parametroGeneralService.buscarAnioInicio()));
        model.addAttribute("resultadosRadiologico", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_RADIOLOGICO));
        model.addAttribute("resultadosRadiologico2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_RADIOLOGICO));
        model.addAttribute(P_CONSULTA, consulta);
        return PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
    }

    @GetMapping("/{consulta:psicologia}")
    public String irPaginaConsultaAdministrativaExamenMedicoPsicologico(
            @PathVariable String consulta, ModelMap model)
    {
        model.addAttribute(P_TIPOS_ALUMNO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
        model.addAttribute(P_ESTADOS_EXAMEN_MEDICO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO_EXAMEN_MEDICO));
        model.addAttribute(P_CAMPANIAS, campaniaService.buscarTodos());
        model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
        model.addAttribute("resultadosPsicologico", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO));
        model.addAttribute(P_CONSULTA, consulta);
        return PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
    }
    
    @GetMapping("/{consulta:odontologia}")
    public String irPaginaConsultaAdministrativaExamenMedicoOdontologico(
            @PathVariable String consulta, ModelMap model)
    {
        model.addAttribute(P_TIPOS_ALUMNO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
        model.addAttribute(P_ESTADOS_EXAMEN_MEDICO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO_EXAMEN_MEDICO));
        model.addAttribute(P_CAMPANIAS, campaniaService.buscarTodos());
        model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
        //model.addAttribute("resultadosPsicologico", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO));
        model.addAttribute(P_CONSULTA, consulta);
        return PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
    }
    
    @GetMapping("/{consulta:triaje}")
    public String irPaginaConsultaAdministrativaTriaje(@PathVariable String consulta, ModelMap model)
    {
        model.addAttribute(P_TIPOS_ALUMNO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
        model.addAttribute(P_ESTADOS_EXAMEN_MEDICO, multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ESTADO_EXAMEN_MEDICO));
        model.addAttribute(P_CAMPANIAS, campaniaService.buscarTodos());
        model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
        model.addAttribute("rangoPesos", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RANGO_PESO));
        model.addAttribute("rangoTallas", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RANGO_TALLA));
        model.addAttribute("rangoPulsos", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_PULSO));
        model.addAttribute("presionesSistolicas", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_PRESION_SISTOLICA));
        model.addAttribute("presionesDiastolicas", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_PRESION_DIASTOLICA));
        model.addAttribute(P_CONSULTA, consulta);
        return PAGINA_CONSULTA_ADMINISTRATIVA_EXAMEN_MEDICO;
    }
}