package com.cenpro.sircie.model.egresos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Egresos {
	
	private Integer idEgreso;
	private Integer idProgramacionGasto;
	private Integer idGasto;
	private Integer idTabla;
	
	private String codigoUnidad;
	private String nombreUnidad;
	private String nroConceptoUnidad;
	private String clasificador;
	
	private String nombreProveedor;
	private String tipoDocProveedor;
	private String nroDocProveedor;
	
	private String detalle;
	private String documento;
	private String docReferencia;
	private Double montoTotal;
	
	private String codigoCurso;
	private String nombreCurso;

}
