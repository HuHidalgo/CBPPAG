$(document).ready(function() {

	$formReporteIngresosConceptos.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true
			},
			idConcepto : {
				required : true
			}
		},
		messages : {
			codigoUnidad : {
				required : "Seleccione una Unidad."
			},
			idConcepto : {
				required : "Seleccione un Concepto."
			}
		}
	});
});