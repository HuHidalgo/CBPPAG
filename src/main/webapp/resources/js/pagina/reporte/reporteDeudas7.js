$(document).ready(function() {

	var $local = {
		tablaReporteDeudaGeneral : "",
		$tablaReporteDeudaGeneral : $("#tablaReporteDeudas"),
		$limpiar : $("#limpiar"),
		$buscar : $("#buscar"),
		$exportar : $("#exportar"),
		$especializaciones : $("#especializaciones"),
		$modalidades : $("#modalidades"),
		$codigoAlumno : $("#codigoAlumno"),
		$numeroCiclo : $("#numeroCiclo")
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
		var nroCiclo = $local.$numeroCiclo.val();	
		if (nroCiclo == "")
			nroCiclo = 0;
		
		if (idModalidad == null || idModalidad == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad + "/" + nroCiclo,
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
					$local.$especializaciones.append($("<option />").val(this.idEspecializacion).text(this.nombreEspecializacion));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
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
		if(reporte.numeroCiclo == ""){
			reporte.numeroCiclo = "0";
		}
		/*if ($funcionUtil.camposVacios($formReporteDeudas)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteDeudas.valid()) {
			return;
		}
*/
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/reporte?accion=buscarDeuda",
			data : reporte,
			beforeSend : function() {
				$local.tablaReporteDeudaGeneral.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(deudas) {
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
		$local.$codigoAlumno.val("");
		$local.$numeroCiclo.val("");
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "/mantenimiento/modalidad?accion=buscarTodos",
			beforeSend : function(xhr) {
				$local.$modalidades.find("option:not(:eq(0))").remove();
				$local.$modalidades.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Especializaciones</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(modalidades) {
				$local.$modalidades.append($("<option />").val("").text("--Selecciona Modalidad--"));
				$.each(modalidades, function(i, modalidad) {
					$local.$modalidades.append($("<option />").val(this.idModalidad).text(this.idModalidad + " - " + this.nombreModalidad));
				});
			},
			complete : function() {
				$local.$modalidades.parent().find(".cargando").remove();
			}
		});
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "/mantenimiento/especializacion?accion=buscarTodos",
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
				$local.$especializaciones.append($("<option />").val("").text("--Selecciona Especialización--"));
				$.each(especializaciones, function(i, especializacion) {
					$local.$especializaciones.append($("<option />").val(this.idEspecializacion).text(this.idEspecializacion + " - " + this.nombreEspecializacion));
				});
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});
	});

	$local.$exportar.on("click", function() {
		var reporte = $formReporteDeudas.serializeJSON();
		reporte.idModalidad = $local.$modalidades.val();
		reporte.idEspecializacion = $local.$especializaciones.val();
		if(reporte.numeroCiclo == ""){
			reporte.numeroCiclo = "0";
		}
		
		console.log(reporte);
		/*if ($funcionUtil.camposVacios($formReporteDeudas)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteDeudas.valid()) {
			return;
		}*/
		
		var paramReporte = $.param(reporte);
		
		window.location.href = $variableUtil.root + "registro/reporte?accion=exportar&" + paramReporte;
	});

});