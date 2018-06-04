package com.cenpro.sircie.model.presupuesto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoEgreso 
{
	private Integer idPEgreso;
	private String codigoUnidad;
	private String nombreUnidad;
	private Integer codigoTarea;
	private String nombreTarea;
	private String clasificador;
	private String descripcionPartida;
	
	private String mes;
	private String idMes;
	private String año;
	private Double importe;
	private Double inicial;
	
}
