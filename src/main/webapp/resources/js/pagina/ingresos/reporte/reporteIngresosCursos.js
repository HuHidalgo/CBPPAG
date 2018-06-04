$(document).ready(function() {

	var $local = {
		tablaReporteIngresosDetalle : "",
		$tablaReporteIngresosDetalle : $("#tablaReporteIngresosDetalle1"),
		tablaReporteIngresosGeneral : "",
		$tablaReporteIngresosGeneral : $("#tablaReporteIngresosGeneral1"),
		$limpiar : $("#limpiar1"),
		$buscar : $("#buscar1"),
		$exportar : $("#exportar1"),
		$unidades: $("#unidades1"),
		$cursos : $("#cursos"),
		$rangoFechaBusqueda : $("#rangoFechaBusqueda1")
	}

	$formReporteIngresosCursos = $("#formReporteIngresosCursos");
	
	$funcionUtil.crearSelect2($local.$unidades, "Seleccione una Unidad");
	$funcionUtil.crearSelect2($local.$cursos, "Seleccione un Curso");
	$funcionUtil.crearDateRangePickerSimple($local.$rangoFechaBusqueda);

	$local.tablaReporteIngresosDetalle = $local.$tablaReporteIngresosDetalle.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteIngresosDetalle.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteIngresosDetalle);
		},
		"ordering" : false,
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}],
		"columns" : [ 
			{
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.nroConceptoUnidad, row.nombreUnidad);
			},
			"title" : "Unidades"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.codigoCurso, row.nombreCurso);
			},
			"title" : "Cursos"
		}, {
			"data" : "ingresoTotal",
			"title" : "Ingreso Total"
		} ]
	});
	
	$local.$tablaReporteIngresosDetalle.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteIngresosDetalle.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteIngresosDetalle.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteIngresosDetalle.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.tablaReporteIngresosGeneral = $local.$tablaReporteIngresosGeneral.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteIngresosGeneral.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteIngresosGeneral);
		},
		"ordering" : false,
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		} ],
		"columns" : [ {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.nroConceptoUnidad, row.nombreUnidad);
			},
			"title" : "Unidad"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.codigoCurso, row.nombreCurso);
			},
			"title" : "Curso"
		}, {
			"data" : "importeEnero",
			"title" : "Enero"
		}, {
			"data" : "importeFebrero",
			"title" : "Febrero"
		} , {
			"data" : "importeMarzo",
			"title" : "Marzo"
		} , {
			"data" : "importeAbril",
			"title" : "Abril"
		} , {
			"data" : "importeMayo",
			"title" : "Mayo"
		} , {
			"data" : "importeJunio",
			"title" : "Junio"
		} , {
			"data" : "importeJulio",
			"title" : "Julio"
		} , {
			"data" : "importeAgosto",
			"title" : "Agosto"
		} , {
			"data" : "importeSetiembre",
			"title" : "Setiembre"
		} , {
			"data" : "importeOctubre",
			"title" : "Octubre"
		} , {
			"data" : "importeNoviembre",
			"title" : "Noviembre"
		} , {
			"data" : "importeDiciembre",
			"title" : "Diciembre"
		}  ]
	});
	
	$local.$tablaReporteIngresosGeneral.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteIngresosGeneral.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteIngresosGeneral.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteIngresosGeneral.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
		
	$local.$unidades.on("change", function(event, opcionSeleccionada) {
		var codigoUnidad = $(this).val();
		if (codigoUnidad == null || codigoUnidad == undefined) {
			$local.$cursos.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/curso/unidad/" + codigoUnidad,
			beforeSend : function(xhr) {
				$local.$cursos.find("option:not(:eq(0))").remove();
				$local.$cursos.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Cursos</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formReporteIngresosCursos);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formReporteIngresosCursos);
				}
			},
			success : function(cursos) {
				$.each(cursos, function(i, curso) {
					$local.$cursos.append($("<option />").val(this.codigoCurso).text(this.codigoCurso + " - " + this.nombreCurso));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					$local.$cursos.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$cursos.parent().find(".cargando").remove();
			}
		});
	});
	
	$local.$buscar.on("click", function() {
		var criterioBusqueda = $formReporteIngresosCursos.serializeJSON();
		if ($funcionUtil.camposVacios($formReporteIngresosCursos)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteIngresosCursos.valid()) {
			return;
		}
		var rangoFecha = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFecha.fechaInicio;
		criterioBusqueda.fechaFin = rangoFecha.fechaFin;
		criterioBusqueda.verbo = "DET_CURSOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar2",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteIngresosDetalle.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(ingresos) {
				if (ingresos.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteIngresosDetalle.rows.add(ingresos).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").removeClass("fa-spinner fa-pulse fa-fw").addClass("fa-search");
			}
		});

		criterioBusqueda.verbo = "GEN_CURSOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar3",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteIngresosGeneral.clear().draw();
			},
			success : function(ingresos) {
				if (ingresos.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteIngresosGeneral.rows.add(ingresos).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});
	
	$local.$limpiar.on("click", function() {
		var criterioBusqueda = $formReporteIngresosCursos.serializeJSON();
		if ($funcionUtil.camposVacios($formReporteIngresosCursos)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteIngresosCursos.valid()) {
			return;
		}

		criterioBusqueda.verbo = "DET_LIMPIAR_CURSOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar2",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteIngresosDetalle.clear().draw();
				$local.$limpiar.attr("disabled", true).find("i").removeClass("fa-refresh").addClass("fa-spinner fa-pulse fa-fw");
			},
			
			complete : function() {
				$local.$limpiar.attr("disabled", false).find("i").removeClass("fa-spinner fa-pulse fa-fw").addClass("fa-refresh");
			}
		});

		criterioBusqueda.verbo = "GEN_LIMPIAR_CURSOS";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/reporte?accion=buscar3",
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
		var criterioBusqueda = $formReporteIngresosCursos.serializeJSON();
		if ($funcionUtil.camposVacios($formReporteIngresosCursos)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		if (!$formReporteIngresosCursos.valid()) {
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