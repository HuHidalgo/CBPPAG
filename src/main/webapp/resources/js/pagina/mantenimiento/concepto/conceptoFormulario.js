$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true,
				notOnlySpace : true
			},
			nroConceptoEsp : {
				required : true,
				notOnlySpace : true,
				digits : true
			},
			nomConceptoEsp : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 250 ]
			},
			descConceptoEsp : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 300 ]
			},
			importe : {
				required : true,
				notOnlySpace : true
			}
		},
		messages : {
			codigoUnidad : {
				required : "Ingrese el C&oacute;digo de Unidad.",
				notOnlySpace : "El C&oacute;digo de Unidad no debe contener solo espacios en blanco."
			},
			nroConceptoEsp : {
				required : "Ingrese el número de concepto.",
				notOnlySpace : "El nombre del concepto no puede contener solo espacios en blanco.",
				digits : "El C&oacute;digo de Facultad debe contener solo d&iacute;gitos."
			},
			nomConceptoEsp : {
				required : "Ingrese el nombre de concepto.",
				notOnlySpace : "El nombre de concepto no puede contener solo espacios en blanco.",
				rangelength : "El número de concepto debe contener entre 3 y 250 car&aacute;cteres."
			},
			descConceptoEsp : {
				required : "Ingrese la descripción de concepto.",
				notOnlySpace : "La descripción de concepto no puede contener solo espacios en blanco.",
				rangelength : "El número de concepto debe contener entre 3 y 300 car&aacute;cteres."
			},
			importe : {
				required : "Ingrese el importe de concepto.",
				notOnlySpace : "El importe de concepto no puede contener solo espacios en blanco."
			}
		}
	});
});