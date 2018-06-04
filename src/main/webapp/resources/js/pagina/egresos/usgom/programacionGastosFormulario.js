$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			nroProgramacionGasto : {
				required : true,
				notOnlySpace : true
			}
		},
		messages : {
			nroProgramacionGasto : {
				required : "Ingrese número de programación de gasto.",
				notOnlySpace : "El número de programación de gasto no puede contener solo espacios en blanco."
			}
		}
	});
});