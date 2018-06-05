$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true,
				notOnlySpace : true
			},
			nombreUnidad : {
				required : true,
				notOnlySpace : true
			},
			nroConcepto : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 1, 3 ]
			}
		},
		messages : {
			codigoUnidad : {
				required : "Ingrese el C&oacute;digo de Unidad.",
				notOnlySpace : "El C&oacute;digo de Unidad no debe contener solo espacios en blanco."
			},
			nombreUnidad : {
				required : "Ingrese el nombre de la Unidad.",
				notOnlySpace : "El nombre de la Unidad no puede contener solo espacios en blanco."
			},
			nroConcepto : {
				required : "Ingrese el número de concepto.",
				notOnlySpace : "El número de concepto no puede contener solo espacios en blanco.",
				rangelength : "El número de concepto debe contener entre 1 y 3 car&aacute;cteres."
			}
		}
	});
});