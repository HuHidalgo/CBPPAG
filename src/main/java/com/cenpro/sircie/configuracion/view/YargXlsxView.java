package com.cenpro.sircie.configuracion.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haulmont.yarg.structure.ReportBand;
import com.haulmont.yarg.structure.ReportOutputType;
import com.haulmont.yarg.structure.impl.ReportBuilder;
import com.haulmont.yarg.structure.impl.ReportTemplateBuilder;

import com.cenpro.sircie.utilitario.ConstantesGenerales;
import com.cenpro.sircie.utilitario.Conversor;
import com.cenpro.sircie.utilitario.ReporteUtilYarg;
import com.cenpro.sircie.utilitario.StringsUtils;

public class YargXlsxView extends AbstractYargXlsxView
{
    private final static String RUTA_REPORTE = ConstantesGenerales.RUTA_REPORTE;
    private final static String EXTENSION = ConstantesGenerales.EXTENSION;
    private final static String PREFIX_REPORT_BAND = ConstantesGenerales.PREFIX_REPORT_BAND;

    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, ReportBuilder reportBuilder,
            Map<String, Object> parametros, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String templateExcel = (String) model.get(ReporteUtilYarg.PARAM_TEMPLATE);
        Map<String, Object> reporteParametros = (Map<String, Object>) model
                .get(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS);
        Conversor.toMapJSON(reporteParametros, parametros);
        String rutaTemplateExcel = getClass().getClassLoader()
                .getResource(StringsUtils.concatenarCadena(RUTA_REPORTE, templateExcel, EXTENSION))
                .getPath();
        ReportTemplateBuilder reportTemplateBuilder = new ReportTemplateBuilder()
                .documentPath(rutaTemplateExcel)
                .documentName(StringsUtils.concatenarCadena(templateExcel, EXTENSION))
                .outputType(ReportOutputType.xlsx).readFileFromPath();
        reportBuilder.template(reportTemplateBuilder.build());
        model.entrySet().stream()
                .filter(map -> map.getKey().startsWith(PREFIX_REPORT_BAND)
                        && map.getValue() instanceof ReportBand)
                .forEach(map -> reportBuilder.band((ReportBand) map.getValue()));
    }
}