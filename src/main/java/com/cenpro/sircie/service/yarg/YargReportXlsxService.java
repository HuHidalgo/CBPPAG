package com.cenpro.sircie.service.yarg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.haulmont.yarg.formatters.factory.DefaultFormatterFactory;
import com.haulmont.yarg.loaders.factory.DefaultLoaderFactory;
import com.haulmont.yarg.loaders.impl.JsonDataLoader;
import com.haulmont.yarg.reporting.Reporting;
import com.haulmont.yarg.reporting.RunParams;
import com.haulmont.yarg.structure.Report;
import com.haulmont.yarg.structure.ReportBand;
import com.haulmont.yarg.structure.ReportOutputType;
import com.haulmont.yarg.structure.impl.ReportBuilder;
import com.haulmont.yarg.structure.impl.ReportTemplateBuilder;

import com.cenpro.sircie.service.IYargReportXlsxService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;
import com.cenpro.sircie.utilitario.Conversor;
import com.cenpro.sircie.utilitario.ReporteUtilYarg;
import com.cenpro.sircie.utilitario.StringsUtils;

@Service
public class YargReportXlsxService implements IYargReportXlsxService
{
    private static final String NOMBRE_REPORTE = "Reporte";

    public byte[] renderMergedOutputModel(Map<String, Object> model)
            throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        model.putIfAbsent(ReporteUtilYarg.PARAM_NOMBRE_REPORTE, NOMBRE_REPORTE);
        Map<String, Object> parametros = new HashMap<String, Object>();
        ReportBuilder reportBuilder = createReportBuilder();
        buildExcelDocument(model, reportBuilder, parametros);
        Report report = reportBuilder.build();
        Reporting reporting = new Reporting();
        reporting.setFormatterFactory(new DefaultFormatterFactory());
        reporting.setLoaderFactory(
                new DefaultLoaderFactory().setJsonDataLoader(new JsonDataLoader()));
        RunParams runParams = new RunParams(report).params(parametros);
        String nombreReporte = (String) model.get(ReporteUtilYarg.PARAM_NOMBRE_REPORTE);
        renderReport(reporting, runParams, output);
        return output.toByteArray();
    }

    protected ReportBuilder createReportBuilder()
    {
        return new ReportBuilder();
    }

    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, ReportBuilder reportBuilder,
            Map<String, Object> parametros) throws IOException
    {
        String templateExcel = (String) model.get(ReporteUtilYarg.PARAM_TEMPLATE);
        Map<String, Object> reporteParametros = (Map<String, Object>) model
                .get(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS);
        Conversor.toMapJSON(reporteParametros, parametros);
        String rutaTemplateExcel = getClass().getClassLoader()
                .getResource(StringsUtils.concatenarCadena(ConstantesGenerales.RUTA_REPORTE,
                        templateExcel, ConstantesGenerales.EXTENSION))
                .getPath();
        ReportTemplateBuilder reportTemplateBuilder = new ReportTemplateBuilder()
                .documentPath(rutaTemplateExcel)
                .documentName(
                        StringsUtils.concatenarCadena(templateExcel, ConstantesGenerales.EXTENSION))
                .outputType(ReportOutputType.xlsx).readFileFromPath();
        reportBuilder.template(reportTemplateBuilder.build());
        model.entrySet().stream()
                .filter(map -> map.getKey().startsWith(ConstantesGenerales.PREFIX_REPORT_BAND)
                        && map.getValue() instanceof ReportBand)
                .forEach(map -> reportBuilder.band((ReportBand) map.getValue()));
    }

    protected void renderReport(Reporting reporting, RunParams runParams,
            ByteArrayOutputStream output) throws IOException
    {
        reporting.runReport(runParams, output);
    }
}