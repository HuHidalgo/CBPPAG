package com.cenpro.sircie.aspecto.enumeracion;

public enum Accion
{
    Registro("Reg"),
    Visita("Vis"),
    Actualizacion("Act"), 
    Eliminacion("Eli"),
    Consulta("Con"),
    Reporte("Rep"),
    Acceso("Acc"),
    Ninguna("");
    
    private final String nombre;       

    private Accion(String s) {
        nombre = s;
    }

    public String toString() {
       return this.nombre;
    }   
}