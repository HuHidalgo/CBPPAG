$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$tablaDetalleMantenimiento : $("#tablaDetalleMantenimiento"),
		tablaDetalleMantenimiento : "",
		$tablaVerDetalleGastos : $("#tablaVerDetalleGastos"),
		tablaVerDetalleGastos : "",
		
		$modalMantenimiento : $("#modalMantenimiento"),
		$modalMantenimientoEgresos : $("#modalMantenimientoEgresos"),
		$modalDetalleMantenimiento : $("#modalDetalleMantenimiento"),
		$modalVerDetalleGastos : $("#modalVerDetalleGastos"),
		
		$aniadirDetalleMantenimiento : $("#aniadirDetalleMantenimiento"),
		$registrarDetalleMantenimiento : $("#registrarDetalleMantenimiento"),
		$actualizarDetalleMantenimiento : $("#actualizarDetalleMantenimiento"),
		
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$registrarMantenimiento1 : $("#registrarMantenimiento1"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		$fechaEnvio : $("#fechaEnvio"),
		$tiposDocumento : $("#tiposDocumento"),
		$tiposDocumentoIdentidad : $("#tiposDocumentoIdentidad"),
		$tiposAdquisicion : $("#tiposAdquisicion"),
		$tiposEstado : $("#tiposEstado"),
		$unidades : $("#unidades"),
		$cursos : $("#cursos"),
		$partidas : $("#partidas"),
		$gastos : $("#gastos"),
		$filaDetalleSeleccionada : "",
		codigoEgresoSeleccionado : 0,
		id_ProgramacionGasto : 0,
		codigoGastoSeleccionado : 0,
		arregloSiNo : [ "1", "0" ],
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");
	$formMantenimiento1 = $("#formMantenimiento1");
	$formDetalleMantenimiento = $("#formDetalleMantenimiento");
	$formVerDetalleGastos = $("#formVerDetalleGastos");
	
	$funcionUtil.crearSelect2($local.$unidades, "Seleccione una Unidad");
	$funcionUtil.crearSelect2($local.$cursos, "Seleccione un Curso");
	$funcionUtil.crearSelect2($local.$tiposDocumento, "Seleccione Documento");
	$funcionUtil.crearSelect2($local.$tiposDocumentoIdentidad, "Seleccione tipo de Documento");
	$funcionUtil.crearSelect2($local.$tiposAdquisicion, "Seleccione Tipo de Adquisicion");
	$funcionUtil.crearSelect2($local.$tiposEstado, "Seleccione Estado");

	$funcionUtil.crearDatePickerSimple($local.$fechaEnvio, "DD/MM/YYYY");
	
	$.fn.dataTable.ext.errMode = 'none';

	$local.$tablaMantenimiento.on('xhr.dt', function(e, settings, json, xhr) {
		switch (xhr.status) {
		case 500:
			$local.tablaMantenimiento.clear().draw();
			break;
		}
	});

	$local.tablaMantenimiento = $local.$tablaMantenimiento.DataTable({
		"ajax" : {
			"url" : $variableUtil.root + "egresos/usgom/programacionGastos?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay programaciones de gastos registrados."
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$local.filtrosSeleccionables["6"] = $local.arregloSiNo;
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento, $local.filtrosSeleccionables);
		},
		"ordering" : false,
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2 ],
			"className" : "all filtrable",
		}, {
			"targets" : 3,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonVerGastos
		}, {
			"targets" : 4,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonAniadirEgresos
		} ],
		"columns" : [ {
			"data" : function(row) {
				return $funcionUtil.convertirDeFormatoAFormato(row.fechaEnvio, "YYYY-MM-DD", "DD/MM/YYYY");
			},
			"title" : "Fecha"
		}, {
			"data" : "nroProgramacionGasto",
			"title" : "Programación de Gasto"
		}, {
			"className" : "all seleccionable data-no-definida dt-center",
			"data" :	function(row) {
				return $funcionUtil.insertarEtiquetaSiNo(row.estado);
			},
			"title" : "Atendido",
			"width" : "10%"
		}, {
			"data" : null,
			"title" : "Detalle de Gastos",
			"width" : "10%"
		}, {
			"data" : null,
			"title" : 'Acción',
			"width" : "10%"
		} ]
	});

	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});
	
	$local.$tablaMantenimiento.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.$modalMantenimiento.PopupWindow({
		title : "Mantenimiento de Programación de Gastos",
		autoOpen : false,
		modal : false,
		height : 300,
		width : 550
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formMantenimiento);
		$local.$actualizarMantenimiento.addClass("hidden");
		$local.$registrarMantenimiento.removeClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$modalMantenimiento.on("open.popupwindow", function() {
		$formMantenimiento.find("input:not([disabled]):first").focus();
		//$local.$modalMantenimiento.PopupWindow("maximize");
	});

	$local.$modalMantenimiento.on("close.popupwindow", function() {
		$local.codigoEgresoSeleccionado = 0;
	});

	$formMantenimiento.find("input").keypress(function(event) {
		if (event.which == 13) {
			if (!$local.$registrarMantenimiento.hasClass("hidden")) {
				$local.$registrarMantenimiento.trigger("click");
				return false;
			} else {
				if (!$local.$actualizarMantenimiento.hasClass("hidden")) {
					$local.$actualizarMantenimiento.trigger("click");
				}
				return false;
			}
		}
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
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
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
		
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var progGastos = $formMantenimiento.serializeJSON();
		progGastos.fechaEnvio = $local.$fechaEnvio.data("daterangepicker").startDate.format('YYYY-MM-DD');
		progGastos.estado = false;
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "egresos/usgom/programacionGastos",
			data : JSON.stringify(progGastos),
			beforeSend : function(xhr) {
				$local.$registrarMantenimiento.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(progGastos) {
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var egreso = progGastos[0];
				var row = $local.tablaMantenimiento.row.add(egreso).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalMantenimiento.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$registrarMantenimiento.attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".actualizar", function() {
		$funcionUtil.prepararFormularioActualizacion($formMantenimiento);
		$local.$filaSeleccionada = $(this).parents("tr");
		var progGastos = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.codigoEgresoSeleccionado = progGastos.idProgramacionGasto;
		$funcionUtil.llenarFormulario(progGastos, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var progGastos = $formMantenimiento.serializeJSON();
		progGastos.idProgramacionGasto = $local.codigoEgresoSeleccionado;
		progGastos.fechaEnvio = $local.$fechaEnvio.data("daterangepicker").startDate.format('YYYY-MM-DD');
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "egresos/usgom/programacionGastos",
			data : JSON.stringify(progGastos),
			beforeSend : function(xhr) {
				$local.$actualizarMantenimiento.attr("disabled", true).find("i").removeClass("fa-pencil-square").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(progGastos) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(progGastos[0]).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalMantenimiento.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$actualizarMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});
	
	//**************************************************************************************
	//						MODAL VER DETALLE DE GASTOS (EXPORTACIÓN) 
	//***************************************************************************************
	
	$local.$tablaVerDetalleGastos.on('xhr.dt', function(e, settings, json, xhr) {
		switch (xhr.status) {
		case 500:
			$local.tablaVerDetalleGastos.clear().draw();
			break;
		}
	});
	
	$local.tablaVerDetalleGastos = $local.$tablaVerDetalleGastos.DataTable({
		"language" : {
			"emptyTable" : "No hay gastos registrados."
		},
		"initComplete" : function() {
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaVerDetalleGastos);
		},
		"columnDefs" : [ {
			"targets" : 0,
			"className" : "all filtrable"
		}, {
			"targets" : 1,
			"className" : "all  filtrable"
		}, {
			"targets" : 2,
			"className" : "all  filtrable"
		},  {
			"targets" : 3,
			"className" : "all  filtrable"
		},  {
			"targets" : 4,
			"className" : "all  filtrable"
		} ],
		"columns" : [ {
			"data" : function(row) {
				return row.documento + " " + row.docReferencia;
			},
			"title" : "Documento Referencia", 
			"width": "18%"
		}, {
			"data" : 'detalle',
			"title" : "Detalle",
			"width"	: "43%"
		}, {
			"data" : 'nombreUnidad',
			"title" : "Solicitante",
			"width" : "25%"
		}, {
			"data" : 'clasificador',
			"title" : "Partida",
			"width": "7%"
		}, {
			"data" : 'montoTotal',
			"title" : "Monto",
			"width": "7%"
		} ]
	});
	
	$local.$tablaVerDetalleGastos.wrap("<div class='table-responsive'></div>");

	$local.$tablaVerDetalleGastos.find("thead").on('keyup', 'input', function() {
		$local.tablaVerDetalleGastos.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaVerDetalleGastos.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaVerDetalleGastos.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.$modalVerDetalleGastos.PopupWindow({
		title : "Detalle de Gastos",
		autoOpen : false,
		modal : false,
		height : 600,
		width : 1100,
	});
		
	$local.$modalVerDetalleGastos.on("open.popupwindow", function() {
		$formVerDetalleGastos.find("input:first").focus();
	});
	
	$local.$tablaMantenimiento.children("tbody").on("click", ".ver-gastos", function() {
		$local.$filaDetalleSeleccionada = $(this).parents("tr");
		var progGasto = $local.tablaMantenimiento.row($local.$filaDetalleSeleccionada).data();
		$local.id_ProgramacionGasto = progGasto.idProgramacionGasto;
		
		$.ajax({
	    	type : "GET",
	    	url: $variableUtil.root + "egresos/egresos/detalle/" +$local.id_ProgramacionGasto,
	    	beforeSend: function(){
	    		$local.tablaVerDetalleGastos.clear().draw();
	    	},
	    	success : function(egresos){
	    		if(egresos.length == 0){
	    			return;
	    		}
	    		console.log(egresos[0]);
	    		$local.tablaVerDetalleGastos.rows.add(egresos).draw();
	    	}
	    });		
		
		$local.$modalVerDetalleGastos.PopupWindow("open");
	});
	
	//***********************************************************************
	//						MODAL DE EGRESOS 
	//***********************************************************************

	$local.$registrarMantenimiento1.attr("disabled", true);
	
	$local.$modalMantenimientoEgresos.PopupWindow({
		title : "Mantenimiento de Egresos",
		autoOpen : false,
		modal : false,
		height : 550,
		width : 950,
	});
	
	$local.$modalMantenimientoEgresos.on("open.popupwindow", function() {
		$formMantenimiento1.find("input:not([disabled]):first").focus();
		//$local.$modalMantenimiento.PopupWindow("maximize");
	});
	
	$local.$modalMantenimientoEgresos.on("close.popupwindow", function() {
		$local.codigoEgresoSeleccionado = 0;
	});
	
	$local.$tablaMantenimiento.children("tbody").on("click", ".aniadir-egresos", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var progGastos = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.id_ProgramacionGasto = progGastos.idProgramacionGasto;
		$funcionUtil.prepararFormularioRegistro($formMantenimiento1);
		//$local.$actualizarMantenimiento.addClass("hidden");
		$local.$registrarMantenimiento1.removeClass("hidden");
		$local.$modalMantenimientoEgresos.PopupWindow("open");
	});
	
	$local.$gastos.on("click", function() {
		
		if (!$formMantenimiento1.valid()) {
			return;
		}
		var egresos = $formMantenimiento1.serializeJSON();
		egresos.idProgramacionGasto = $local.id_ProgramacionGasto;
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "egresos/egresos",
			data : JSON.stringify(egresos),
			beforeSend : function(xhr) {
				$local.$gastos.attr("disabled", true).find("i").removeClass("fa-plus").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento1);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento1);
				}
			},
			success : function(egresos) {
				//$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var egreso = egresos[0];
				$local.codigoEgresoSeleccionado = egreso.idEgreso;
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$gastos.attr("disabled", false).find("i").addClass("fa-plus").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
		
		$funcionUtil.prepararFormularioRegistro($formDetalleMantenimiento);
		$local.$actualizarDetalleMantenimiento.addClass("hidden");
		$local.$registrarDetalleMantenimiento.removeClass("hidden");
		$local.$modalMantenimientoEgresos.PopupWindow("hidden");
		$local.$modalDetalleMantenimiento.PopupWindow("open");
		$local.$registrarMantenimiento1.attr("disabled", false);
	});
	
	$local.$registrarMantenimiento1.on("click", function() {
		$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
		$local.$modalMantenimientoEgresos.PopupWindow("close");
	});
	
	//**************************************************************************************
	//						MODAL DETALLE DE GASTOS
	//***************************************************************************************
	
	$funcionUtil.crearSelect2($local.$partidas, "Seleccione una Partida");
	
	$local.$tablaDetalleMantenimiento.on('xhr.dt', function(e, settings, json, xhr) {
		switch (xhr.status) {
		case 500:
			$local.tablaDetalleMantenimiento.clear().draw();
			break;
		}
	});
	
	$local.tablaDetalleMantenimiento = $local.$tablaDetalleMantenimiento.DataTable({
		"language" : {
			"emptyTable" : "No hay gastos registrados."
		},
		"initComplete" : function() {
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaDetalleMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : 0,
			"className" : "all filtrable"
		}, {
			"targets" : 1,
			"className" : "all  filtrable"
		}, {
			"targets" : 2,
			"className" : "all  filtrable"
		},  {
			"targets" : 3,
			"className" : "all  filtrable"
		},  {
			"targets" : 4,
			"className" : "all  filtrable"
		},	{
			"targets" : 5,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonEliminar
		} ],
		"columns" : [ {
			"data" : 'clasificador',
			"title" : "Partida",
			"width"	: "15%"
		}, {
			"data" : 'detalle',
			"title" : "Detalle",
			"width"	: "50%"
		}, {
			"data" : 'cantidad',
			"title" : "Cantidad",
			"width" : "5%"
		}, {
			"data" : 'precioUnitario',
			"title" : "Precio Unitario",
			"width": "10%"
		}, {
			"data" : 'montoTotal',
			"title" : "Monto",
			"width": "10%"
		}, {
			"data" : null,
			"title" : 'Acción',
			"width": "10%"
		} ]
	});
	
	$local.$tablaDetalleMantenimiento.wrap("<div class='table-responsive'></div>");

	$local.$tablaDetalleMantenimiento.find("thead").on('keyup', 'input', function() {
		$local.tablaDetalleMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaDetalleMantenimiento.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaDetalleMantenimiento.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.$modalDetalleMantenimiento.PopupWindow({
		title : "Mantenimiento de Gastos",
		autoOpen : false,
		modal : false,
		height : 600,
		width : 1000,
	});
	
	$local.$aniadirDetalleMantenimiento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formDetalleMantenimiento);
		$local.$actualizarDetalleMantenimiento.addClass("hidden");
		$local.$registrarDetalleMantenimiento.removeClass("hidden");
		$local.$modalDetalleMantenimiento.PopupWindow("open");
	});
	
	$local.$modalDetalleMantenimiento.on("open.popupwindow", function() {
		$formDetalleMantenimiento.find("input:first").focus();
	});

	$local.$modalDetalleMantenimiento.on("close.popupwindow", function() {
		$local.codigoGastoSeleccionado = 0;
		$local.tablaDetalleMantenimiento.clear().draw();
	});
	
	$formDetalleMantenimiento.find("input").keypress(function(event) {
		if (event.which == 13) {
			if (!$local.$registrarDetalleMantenimiento.hasClass("hidden")) {
				$local.$registrarDetalleMantenimiento.trigger("click");
				return false;
			} else {
				if (!$local.$actualizarDetalleMantenimiento.hasClass("hidden")) {
					$local.$actualizarDetalleMantenimiento.trigger("click");
				}
				return false;
			}
		}
	});
	
	$local.$registrarDetalleMantenimiento.on("click", function() {
		if (!$formDetalleMantenimiento.valid()) {
			return;
		}
		var gastos = $formDetalleMantenimiento.serializeJSON();
		gastos.idEgreso = $local.codigoEgresoSeleccionado;
		gastos.montoTotal = (gastos.cantidad*gastos.precioUnitario).toFixed(2);
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "egresos/gastos",
			data : JSON.stringify(gastos),
			beforeSend : function(xhr) {
				$local.$registrarDetalleMantenimiento.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formDetalleMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formDetalleMantenimiento);
				}
			},
			success : function(gastos) {
				var gasto = gastos[0];
				$funcionUtil.notificarException($variableUtil.registroExitosoGasto, "fa-check", "Aviso", "success");
				var row = $local.tablaDetalleMantenimiento.row.add({
					"idGasto": gasto.idGasto,
					"clasificador" : gasto.clasificador,
					"tipoAdquisicion" : gasto.tipoAdquisicion,
					"detalle" : gasto.detalle,
					"cantidad" : gasto.cantidad,
					"precioUnitario" : gasto.precioUnitario,
					"montoTotal" : (gasto.cantidad*gasto.precioUnitario).toFixed(2)
				}).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$funcionUtil.prepararFormularioRegistro($formDetalleMantenimiento);
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$registrarDetalleMantenimiento.attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});
	
	$local.$tablaDetalleMantenimiento.children("tbody").on("click", ".actualizar", function() {
		$funcionUtil.prepararFormularioActualizacion($formDetalleMantenimiento);
		$local.$filaDetalleSeleccionada = $(this).parents("tr");
		var gastos = $local.tablaDetalleMantenimiento.row($local.$filaDetalleSeleccionada).data();
		$local.codigoGastoSeleccionado = gastos.idGasto;
		$funcionUtil.llenarFormulario(gastos, $formDetalleMantenimiento);
		$local.$actualizarDetalleMantenimiento.removeClass("hidden");
		$local.$registrarDetalleMantenimiento.addClass("hidden");
		$local.$modalDetalleMantenimiento.PopupWindow("open");
	});
	
	$local.$actualizarDetalleMantenimiento.on("click", function() {
		if (!$formDetalleMantenimiento.valid()) {
			return;
		}
		var gastos = $formDetalleMantenimiento.serializeJSON();
		gastos.idEgreso = $local.codigoEgresoSeleccionado;
		gastos.idGasto = $local.codigoGastoSeleccionado;
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "egresos/gastos",
			data : JSON.stringify(gastos),
			beforeSend : function(xhr) {
				$local.$actualizarDetalleMantenimiento.attr("disabled", true).find("i").removeClass("fa-pencil-square").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formDetalleMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formDetalleMantenimiento);
				}
			},
			success : function(gastos) {
				var gasto = gastos[0];
				$funcionUtil.notificarException($variableUtil.registroExitosoGasto, "fa-check", "Aviso", "success");
				$local.tablaDetalleMantenimiento.row($local.$filaDetalleSeleccionada).remove().draw(false);
				var row = $local.tablaDetalleMantenimiento.row.add({
					"idEgreso": gasto.idEgreso,
					"idGasto": gasto.idGasto,
					"clasificador" : gasto.clasificador,
					"tipoAdquisicion" : gasto.tipoAdquisicion,
					"detalle" : gasto.detalle,
					"cantidad" : gasto.cantidad,
					"precioUnitario" : gasto.precioUnitario,
					"montoTotal" : (gasto.cantidad*gasto.precioUnitario).toFixed(2)
				}).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
			},
			error : function(response) {
				console.log(response);
			},
			complete : function(response) {
				$local.$actualizarDetalleMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});
	
});