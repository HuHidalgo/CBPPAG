package com.cenpro.sircie.utilitario;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConstantesExcepciones
{
    /* Menzaje de Error de Carga de Archivo */
    public static final String ERROR_LECTURA_ARCHIVO = "Ocurrió un error en la lectura del archivo cargado. Reintente nuevamente, ingresando un archivo válido.";

    /* Mensajes de Error de Mantenimiento */
    public static final String ERROR_REGISTRO = "No se logró completar el registro. Reintente nuevamente.";

    /* Mensajes de Error de Parametro General */
    public static final String PARAMETRO_GENERAL_NO_ENCONTRADO = "Los Parámetros Generales no existen.";
    public static final String ANIO_INICIAL_NO_ENCONTRADO = "El Año Inicial no fue encontrado en Parámetros Generales.";
    public static final String ANIO_INICIAL_MAL_FORMATO = "El Año Inicial de Parámetros Generales debe ser un número.";
    public static final String ANIO_INICIAL_MAYOR_ANIO_ACTUAL = "El Año Inicial de Parámetros Generales no debe superar al Año Actual.";
    public static final String CORREO_SUM_NO_ENCONTRADO = "El Correo del SUM no fue encontrado en Párametros Generales.";
    public static final String CORREO_CLINICA_NO_ENCONTRADO = "El Correo de la Clínica Universitaria no fue encontrado en Párametros Generales.";

    /* Mensajes de Error en Conexion */
    public static final String ERROR_CONEXION_BASE_DATOS = "No se logró establecer la conexión a la Base de Datos. Revise los parámetros de conexión.";

    /* Codigos de Autenticacion */
    public static final String USUARIO_NO_ENCONTRADO = "El usuario %s no fue encontrado.";
    public static final String USUARIO_NO_ACTIVO = "El usuario %s no está activo.";
    public static final String CONTRASENIA_INCORRECTA = "La contraseña ingresada es incorrecta.";

    /* Mensajes de Error REST */
    public static final String ERROR_TYPE_MISMATCH_EXCEPTION_REST = "La petición enviada por el cliente contiene un formato inválido. Reintente la operación, por favor.";
    public static final String ERROR_MISSING_PARAMETER_EXCEPTION_REST = "La petición enviada por el cliente contiene datos incompletos. Reintente la operación, por favor.";

    /* Mensajes de Error Proceso Manual */
    public static final String DESTINO_ENVIO_DESCONOCIDO = "El Destino de Envío, asociado al Proceso Automático seleccionado, no fue identificado. Por favor, revise el Destino de Envío en <b>Mantenimiento de Proceso Automático</b>";

    /* Otros Mensajes de Error */
    public static final String VIOLACION_CLAVE_FORANEA = "VIOLACION_CLAVE_FORANEA";
    public static final String CAMPO_NO_DEFINIDO = "campoNoDefinido";
    public static final String ERROR_BASE_DATOS = "ERROR_BASE_DATOS";
    public static final String ERROR_DESCONOCIDO = "Ocurrió un error no identificado.";

    public static Map<Integer, String> EXCEPCION_SQL_SERVER = Collections
            .unmodifiableMap(new LinkedHashMap<Integer, String>()
            {
                private static final long serialVersionUID = 1L;
                {
                    put(547, VIOLACION_CLAVE_FORANEA);
                }
            });
}