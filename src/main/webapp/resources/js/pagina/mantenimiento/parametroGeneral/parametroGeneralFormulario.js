$(document).ready(function() {

	$formMantenimiento.validate({
		rules : {
			fechaProceso : {
				required : true
			},
			plantillas : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 200 ]
			},
			binRuteoSwitch : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			},
			codigoInstitucion : {
				required : true,
				number : true,
				range : [ 1, 99 ]
			},
			idEmpresa : {
				required : true,
				notOnlySpace : true,
				selectlength : [ 1, 4 ]
			},
			repositorioPrepago : {
				notOnlySpaceOrEmpty : true,
				maxlength : 200
			},
			repositorioCompensacion : {
				required : true,
				notOnlySpaceOrEmpty : true,
				rangelength : [ 3, 200 ]
			},
			surchargeSoles : {
				notOnlySpaceOrEmpty : true,
				number : true,
				range : [ 0, 9999 ]
			},
			surchargeDolares : {
				notOnlySpaceOrEmpty : true,
				number : true,
				range : [ 0, 9999 ]
			},
			porcentajeIgv : {
				required : true,
				digits : true,
				range : [ 0, 100 ]
			},
			rutaContextoSimpBus : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 70 ]
			},
			tiempoReprogramacion : {
				required : true,
				digits : true,
				range : [ 0, 99 ]
			}
		},
		messages : {
			fechaProceso : {
				required : "Seleccione la Fecha de Proceso"
			},
			plantillas : {
				required : "Ingrese la Plantilla.",
				notOnlySpace : "La Plantilla no puede contener solo espacios en blanco.",
				rangelength : "La Plantilla debe tener entre 3 y 200 car&aacute;cteres."
			},
			binRuteoSwitch : {
				required : "Ingrese el BIN Ruteo Switch.",
				number : "El BIN de Ruteo Switch debe contener solo n&uacute;meros.",
				rangelength : "El BIN de Ruteo Switch debe contener 6 d&iacute;gitos."
			},
			codigoInstitucion : {
				required : "Seleccione una Instituci&oacute;n.",
				number : "El C&oacute;digo de Instituci&oacute;n debe ser un n&uacute;mero.",
				range : "El C&oacute;digo de Instituci&oacute;n debe estar entre 1 y 99."
			},
			idEmpresa : {
				required : "Seleccione una Empresa",
				notOnlySpace : "El Id Emprese no debe contener solo espacios en blanco.",
				selectlength : "El Id Emprese debe contener entre 1 y 4 car&aacute;cteres."
			},
			repositorioPrepago : {
				notOnlySpace : "El Repositorio de Prepago no debe contener solo espacios en blanco.",
				maxlength : "El Repositorio de Prepago no debe contener m&aacute;s de 200 c&aacute;cteres."
			},
			repositorioCompensacion : {
				required : "Ingrese el Repositorio Compensaci&oacute;n",
				notOnlySpace : "El Repositorio Compensaci&oacute;n no debe contener solo espacios en blanco.",
				rangelength : "El Repositorio Compensaci&oacute;n debe contener entre 3 y 200 car&aacute;cteres."
			},
			surchargeSoles : {
				number : "El Surcharge Soles debe ser un n&uacute;meros.",
				range : "El Surcharge Soles debe estar entre 0 y 9999"
			},
			surchargeDolares : {
				number : "El Surcharge D&oacute;lares debe ser un n&uacute;mero.",
				range : "El Surcharge D&oacute;lares debe estar entre 0 y 9999"
			},
			porcentajeIgv : {
				required : "Ingrese el Porcentaje I.G.V.",
				digits : "El Porcentaje I.G.V. debe contener d&iacute;gitos.",
				range : "El Porcentaje I.G.V. debe estar entre 0 y 100."
			},
			rutaContextoSimpBus : {
				required : "Ingrese la Ruta del SimpBus.",
				notOnlySpace : "La Ruta del SimpBus no debe contener solo espacios en blanco.",
				maxlength : "La Ruta del SimpBus debe contener entre 3 y 70 car&aacute;cteres."
			},
			tiempoReprogramacion : {
				required : "Ingrese el Tiempo de Reprogramación.",
				digits : "El Tiempo de Reprogramación debe contener solo dig&iacute;gitos.",
				range : "El Tiempo de Reprogramación debe estar entre 0 y 99."
			}
		}
	});

});