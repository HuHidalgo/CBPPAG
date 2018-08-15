$(document).ready(function() {

	var $local = {
		tablaReporteDeudaGeneral : "",
		$tablaReporteDeudaGeneral : $("#tablaReporteDeudas"),
		$limpiar : $("#limpiar"),
		$buscar : $("#buscar"),
		$exportar : $("#exportar"),
		$especializaciones : $("#especializaciones"),
		$modalidades : $("#modalidades")
	}

	$formReporteDeudas = $("#formReporteDeudas");
	
	$funcionUtil.crearSelect2($local.$modalidades, "--Selecciona Modalidad--");
	$funcionUtil.crearSelect2($local.$especializaciones, "--Selecciona Especialización--");

	$local.tablaReporteDeudaGeneral = $local.$tablaReporteDeudaGeneral.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteDeudaGeneral.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteDeudaGeneral);
		},
		"ordering" : false,
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}],
		"columns" : [ 
			{
			"data" : "codigoAlumno",
			"title" : "Código"
		}, {
			"data" : function(row) {
				return row.nombreAlumno + " "+ row.apellidoAlumno;
			},
			"title" : "Datos Alumno"
		}, {
			"data" : "nombreModalidad",
			"title" : "Modalidad"
		}, {
			"data" : "nombreEspecializacion",
			"title" : "Especialización"
		}, {
			"data" : "numeroCiclo",
			"title" : "Nro. Ciclo"
		}, {
			"data" : "numeroCuota",
			"title" : "Nro. Cuota"
		}, {
			"data" : "montoDeuda",
			"title" : "Monto Deuda"
		}]
	});
	
	$local.$tablaReporteDeudaGeneral.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteDeudaGeneral.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteDeudaGeneral.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteDeudaGeneral.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	
	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		var idModalidad = $(this).val();
		if (idModalidad == null || idModalidad == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad,
			beforeSend : function(xhr) {
				$local.$especializaciones.find("option:not(:eq(0))").remove();
				$local.$especializaciones.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Especializaciones</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$.each(especializaciones, function(i, especializacion) {
					$local.$especializaciones.append($("<option />").val(this.idEspecializacion).text(this.idEspecializacion + " - " + this.nombreEspecializacion));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					console.log(" 123 "+opcionSeleccionada);
					$local.$especializaciones.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});

	});
		
	$local.$buscar.on("click", function() {
		var reporte = $formReporteDeudas.serializeJSON();
		reporte.idModalidad = $local.$modalidades.val();
		reporte.idEspecializacion = $local.$especializaciones.val();
		console.log(reporte);
		/*if ($funcionUtil.camposVacios($formReporteDeudas)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteDeudas.valid()) {
			return;
		}*/
		var cadena;
		if(reporte.idModalidad == null){
			cadena = "nulo" +"-";
		}
		else{
			cadena = reporte.idModalidad + "-";
		}
		
		if(reporte.idEspecializacion == null){
			cadena = cadena  + "nulo" +"-";
		}
		else{
			cadena = cadena  + reporte.idEspecializacion + "-";
		}
		
		if(reporte.codigoAlumno == ""){
			cadena = cadena  + "nulo" +"-";
		}
		else{
			cadena = cadena  + reporte.codigoAlumno + "-";
		}
		
		if(reporte.numeroCiclo == ""){
			cadena = cadena  + "nulo";
		}
		else{
			cadena = cadena  + reporte.numeroCiclo;
		}
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte/deuda/"+cadena,
			beforeSend : function() {
				$local.tablaReporteDeudaGeneral.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(deudas) {
				console.log(deudas);
				if (deudas.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteDeudaGeneral.rows.add(deudas).draw();
								
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").removeClass("fa-spinner fa-pulse fa-fw").addClass("fa-search");
			}
		});

	});
	
	$local.$limpiar.on("click", function() {
		var criterioBusqueda = $formReporteDeudas.serializeJSON();

		criterioBusqueda.verbo = "DET_LIMPIAR_CONCEPTOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteIngresosDetalle.clear().draw();
				$local.$limpiar.attr("disabled", true).find("i").removeClass("fa-refresh").addClass("fa-spinner fa-pulse fa-fw");
			},
			
			complete : function() {
				$local.$limpiar.attr("disabled", false).find("i").removeClass("fa-spinner fa-pulse fa-fw").addClass("fa-refresh");
			}
		});

		criterioBusqueda.verbo = "GEN_LIMPIAR_CONCEPTOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar1",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteIngresosGeneral.clear().draw();
			},
			
			complete : function() {
				$local.$limpiar.attr("disabled", false).find("i").addClass("fa-refresh").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$exportar.on("click", function() {
		var criterioBusqueda = $formReporteDeudas.serializeJSON();
		if ($funcionUtil.camposVacios($formReporteIngresosConceptos)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteIngresosConceptos.valid()) {
			return;
		}
		var rangoFecha = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFecha.fechaInicio;
		criterioBusqueda.fechaFin = rangoFecha.fechaFin;
		var descripcionRangoFechas = $local.$rangoFechaBusqueda.val();
		criterioBusqueda.descripcionRangoFecha = descripcionRangoFechas == null || descripcionRangoFechas == undefined || descripcionRangoFechas == "" ? "TODOS" : descripcionRangoFechas;
		var paramCriterioBusqueda = $.param(criterioBusqueda);
		window.location.href = $variableUtil.root + "ingresos/reporte?accion=exportar&" + paramCriterioBusqueda;
	});

});