$(document).ready(function() {

	var $local = {
		tablaReportePagoGeneral : "",
		$tablaReportePagoGeneral : $("#tablaReportePagos"),
		$limpiar : $("#limpiar"),
		$buscar : $("#buscar"),
		$exportar : $("#exportar"),
		$especializaciones : $("#especializaciones"),
		$modalidades : $("#modalidades"),
		$codigoAlumno : $("#codigoAlumno"),
		$numeroCiclo : $("#numeroCiclo")
	}

	$formReportePago = $("#formReportePagos");
	
	$funcionUtil.crearSelect2($local.$modalidades, "--Selecciona Modalidad--");
	$funcionUtil.crearSelect2($local.$especializaciones, "--Selecciona Especialización--");

	$local.tablaReportePagoGeneral = $local.$tablaReportePagoGeneral.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReportePagoGeneral.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReportePagoGeneral);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"render" : function(data, type, row, meta) {
				if (row.conceptoPago == "PDM" || row.conceptoPago == "PD")
					return "<label class='label label-info label-size-12'>PERFECCIONAMIENTO</label>";
				else
					return "<label class='label label-info label-size-12'>MATRICULA</label>";
			}
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
			"data" : "cuotasPagadas",
			"title" : "Cuotas Pagadas"
		}, {
			"data" : "montoPagado",
			"title" : "Monto Pagado"
		}, {
			"data" : null,
			"title" : "Tipo Pago"
		}]
	});
	
	$local.$tablaReportePagoGeneral.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReportePagoGeneral.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReportePagoGeneral.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReportePagoGeneral.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	
	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		var idModalidad = $(this).val();
		console.log("modalidad");
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad + "/" + 0,
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
		var reporte = $formReportePago.serializeJSON();
		reporte.idModalidad = $local.$modalidades.val();
		reporte.idEspecializacion = $local.$especializaciones.val();
		if(reporte.numeroCiclo == ""){
			reporte.numeroCiclo = "0";
		}
		/*if ($funcionUtil.camposVacios($formReportePago)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReportePago.valid()) {
			return;
		}*/

		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/reporte?accion=buscarPago",
			data : reporte,
			beforeSend : function() {
				$local.tablaReportePagoGeneral.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(pagos) {
				if (pagos.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReportePagoGeneral.rows.add(pagos).draw();
								
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
		var reporte = $formReportePago.serializeJSON();
		reporte.idModalidad = $local.$modalidades.val();
		reporte.idEspecializacion = $local.$especializaciones.val();
		if(reporte.numeroCiclo == ""){
			reporte.numeroCiclo = "0";
		}
		/*if ($funcionUtil.camposVacios($formReportePago)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReportePago.valid()) {
			return;
		}*/
		var paramCriterioBusqueda = $.param(reporte);
		window.location.href = $variableUtil.root + "registro/reporte?accion=exportar2&" + paramCriterioBusqueda;
	});

});