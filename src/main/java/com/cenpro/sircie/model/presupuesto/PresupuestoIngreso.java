package com.cenpro.sircie.model.presupuesto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoIngreso 
{
	private Integer idPIngreso;
	private String codigoUnidad;
	private String nombreUnidad;
	private Integer idConcepto;
	private String nroConcepto;
	private String nombreConcepto;
	private String clasificador;
	private String descripcionPartida;
	
	private String mes;
	private String idMes;
	private String año;
	private Double importe;
	private Double inicial;
}
