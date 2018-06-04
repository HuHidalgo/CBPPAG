package com.cenpro.sircie.model.egresos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gastos {
	
	private Integer idGasto;
	private Integer idEgreso;
	private Integer nroMeta;
	private String codigoTarea;
	private String clasificador;

	private String tipoAdquisicion;
	private String detalle;
	private Integer cantidad;
	private Double precioUnitario;
	private Double montoTotal;
	
	private String codigoUnidad;
	private String nombreUnidad;
	
	private String nombreProveedor;
	private String tipoDocProveedor;
	private String nroDocProveedor;
	
	private String documento;
	private String docReferencia;
	
	private String codigoCurso;
	private String nombreCurso;
}
