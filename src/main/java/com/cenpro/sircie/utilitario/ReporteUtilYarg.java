package com.cenpro.sircie.utilitario;

import com.haulmont.yarg.structure.ReportBand;
import com.haulmont.yarg.structure.impl.BandBuilder;

public class ReporteUtilYarg
{
    public static final String PARAM_NOMBRE_REPORTE = "nombreReporte";
    public static final String PARAM_TEMPLATE = "template";
    public static final String PARAM_REPORTE_PARAMETERS = "reporteParametros";
    
    public static ReportBand buildReportBand(String name, String queryName, String script,
            String loaderType)
    {
        BandBuilder bandBuilder = new BandBuilder();
        ReportBand reportBand = bandBuilder.name(name).query(queryName, script, loaderType).build();
        return reportBand;
    }

    public static ReportBand buildReportBand(String name)
    {
        BandBuilder bandBuilder = new BandBuilder();
        ReportBand reportBand = bandBuilder.name(name).build();
        return reportBand;
    }
}