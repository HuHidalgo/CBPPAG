package com.cenpro.sircie.utilitario;

public class ConstantesGenerales
{
    public static final String REGISTRO_EXITOSO = "Se registró correctamente";
    public static final String ACTUALIZACION_EXITOSA = "Se actualizó correctamente";
    public static final String ELIMINACION_EXITOSA = "Se eliminó correctamente";

    /* ESTADO_EXAMEN_MEDICO */
    public static final String ESTADO_NUMERO_REGISTRO_GENERADO = "N";
    public static final String ESTADO_CONCLUIDO = "C";
    public static final String ESTADO_TOMA_RADIOLOGICA = "T";
    public static final String ESTADO_INCOMPLETO = "I";

    /* RPR */
    public static final String RPR_REACTIVO = "R";
    public static final String RPR_NO_REACTIVO = "N";

    /* TIPO REPORTE */
    public static final String REPORTE_GENERAL = "GENERAL";
    public static final String REPORTE_DETALLE = "DETALLE";

    /* DESTINO ENVIO */
    public static final String SUM = "SUM";
    public static final String FAC = "FAC";
    public static final String ASREG = "ASREG";

    /* REPORTE YARG */
    public final static String RUTA_REPORTE = "xlsx/";
    public final static String EXTENSION = ".xlsx";
    public final static String PREFIX_REPORT_BAND = "rb_";

    /* CONTENT TYPE */
    public final static String CONTENT_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final int ESTADO_EXITO = 1;
    public static final int ESTADO_ERROR = 0;
    public static final String UTF_8 = "UTF-8";
}