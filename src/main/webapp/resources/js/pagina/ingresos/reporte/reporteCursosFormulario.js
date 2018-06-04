$(document).ready(function() {

	$formReporteIngresosCursos.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true
			},
			codigoCurso : {
				required : true
			}
		},
		messages : {
			codigoUnidad : {
				required : "Seleccione una Unidad."
			},
			codigoCurso : {
				required : "Seleccione un Curso."
			}
		}
	});
});