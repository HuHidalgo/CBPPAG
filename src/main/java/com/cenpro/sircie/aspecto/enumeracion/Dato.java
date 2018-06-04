package com.cenpro.sircie.aspecto.enumeracion;

public enum Dato
{
    /*Mantenimientos*/
    Persona("Tipo_de_Documento = #persona.idTipoDocumento, Número_de_Documento = #persona.numeroDocumento, "
    		+ "Apellido_Paterno = #persona.apellidoPaterno"), 
    
    Ingresos("Nombre_de_Cliente = #ingresos.nombresCliente, Apellidos_de_Cliente = #ingresos.apellidosCliente, "
    		+ "Numero_de_Documento = #ingresos.nroDocCliente"),
    MultiTabCab("Id_de_Tabla = #multiTabCab.idTabla, Descripción = #multiTabCab.descripcion"),
    MultiTabDet("Id_Detalle = #multiTabDet.idItem,Id_Tabla = #multiTabDet.idTabla, "
    		+ "Descripción = #multiTabDet.descripcionItem"),
    ParametroGeneral("Fecha_Proceso = #parametroGeneral.fechaProceso, Plantillas = #parametroGeneral.plantillas, "
    		+ "Config = #parametroGeneral.config, Bin_de_Ruteo = #parametroGeneral.binRuteoSwitch, "
    		+ "Institución = #parametroGeneral.codigoInstitucion, Repo_Prepago = #parametroGeneral.repositorioPrepago, "
    		+ "Repo_Compensación = #parametroGeneral.repositorioCompensacion, "
    		+ "Surcharge_Soles = #parametroGeneral.surchargeSoles, "
    		+ "Surcharge_Dolares = #parametroGeneral.surchargeDolares,Empresa = #parametroGeneral.idEmpresa, "
    		+ "Ruta_SimpBus = #parametroGeneral.rutaContextoSimpBus, "
    		+ "Enmascarar_Tarjeta = #parametroGeneral.enmascararTarjeta, IGV = #parametroGeneral.porcentajeIgv"),
    
    /*Seguridad*/
    Usuario("Codigo_de_Usuario = #usuario.idUsuario"),
    
    Ninguno("");
    private final String nombre;

    private Dato(String nombre)
    {
        this.nombre = nombre;
    }

    public String toString()
    {
        return this.nombre;
    }
}
