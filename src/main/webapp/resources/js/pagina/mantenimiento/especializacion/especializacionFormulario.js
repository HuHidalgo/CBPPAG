$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true,
				notOnlySpace : true
			},
			nroMeta : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 1, 99 ]
			},
			codigoTarea : {
				required : true,
				notOnlySpace : true
			},
			nomTarea : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 200 ]
			}
		},
		messages : {
			codigoUnidad : {
				required : "Elija una Unidad.",
				notOnlySpace : "El C&oacute;digo de Unidad no debe contener solo espacios en blanco."
			},
			nroMeta : {
				required : "Elija una Meta.",
				notOnlySpace : "El N&uacute;mero de Meta no debe contener solo espacios en blanco.",
				range : "El N&uacute;mero de Meta debe estar entre 1 y 99."
			},
			codigoTarea : {
				required : "Ingrese el C&oacute;digo de Tarea.",
				notOnlySpace : "El C&oacute;digo de Tarea no debe contener solo espacios en blanco."
			},
			nomTarea : {
				required : "Ingrese el Nombre de Tarea.",
				notOnlySpace : "El Nombre de Tarea no puede contener solo espacios en blanco.",
				rangelength : "El Nombre de Tarea debe contener entre 3 y 200 car&aacute;cteres."
			}
		}
	});
});