$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		idTipoDocumento : "",
		numeroDocumento : "",
		codigoPago : "",
		$verificarAlumno : $("#verificarDatos"),
		$codigoAlumno : $("#codigoAlumno"),
		$nombres : $("#nombreAlumno"),
		$apellidos : $("#apellidoAlumno"),
		$correo : $("#correoAlumno"),
		$modalidad : $("#modalidad"),
		$cuotaPendiente : $("#nroCuotasPendientes"),
		$tipoPago : $("#tipoPago"),
		$nroCiclo : $("#ciclo"),
		$costoCuota : $("#costoCuota"),
		$especializacion : $("#nombreEspecializacion"),
		$fechaPago : $("#fechaPago"),
		$voucher : $("#uploadfile"),
		$documento : "",
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearDatePickerSimple($local.$fechaPago, "DD/MM/YYYY");

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
			"url" : $variableUtil.root + "ingresos/pago?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay Pagos registrados"
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6 ],
			"className" : "all filtrable",
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonDescargar
		} ],
		"columns" : [ {
			"data" : 'codigoPago',
			"title" : "Pago"
		}, {
			"data" : 'codigoAlumno',
			"title" : "Código"
		},{
			"data" : function(row) {
				return row.apellidoAlumno+ ", " + row.nombreAlumno;
			},
			"title" : "Datos del Alumno"
		}, {
			"data" : function(row) {
				return row.nombreModalidad + " - " + row.nombreEspecializacion;
			},
			"title" : "Modalidad - Especialización"
		}, {
			"data" : 'tipoPago',
			"title" : "Tipo Pago"
		},{
			"data" : 'numeroCiclo',
			"title" : "Ciclo"
		}, {
			"data" : 'costoMatricula',
			"title" : "Monto(Soles)"
		},{
			"data" : null,
			"title" : 'Acción'
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
		title : "Registro de Pago",
		autoOpen : false,
		modal : false,
		height : 640,
		width : 636,
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formMantenimiento);
		$local.$actualizarMantenimiento.addClass("hidden");
		$local.$registrarMantenimiento.removeClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$modalMantenimiento.on("open.popupwindow", function() {
		$formMantenimiento.find("input:not([disabled]):first").focus();
	});

	$local.$modalMantenimiento.on("close.popupwindow", function() {
		$local.idTipoDocumento = "";
		$local.numeroDocumento = "";
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
	
	$local.$verificarAlumno.click(function(){		
		var codAlumno = $local.$codigoAlumno .val();
		if (codAlumno == null || codAlumno == undefined) {
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/pago/" + codAlumno,
			success : function(pagos) {
				$.each(pagos, function(i, pago) {				
					$local.$apellidos.val(this.apellidoAlumno);
					$local.$nombres.val(this.nombreAlumno);
					$local.$correo.val(this.correoAlumno);
					$local.$modalidad.val(this.nombreModalidad);
					$local.$tipoPago.val(this.tipoPago);
					$local.$nroCiclo.val(this.numeroCiclo);
					$local.$costoCuota.val(this.costoCuota);
					$local.$especializacion.val(this.nombreEspecializacion);
					$local.$cuotaPendiente.val(this.nroCuotasPendientes);
					$local.codigoMatricula = this.codigoMatricula;
					console.log($local.codigoMatricula);
				});
			}
		});
	});
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var pago = $formMantenimiento.serializeJSON();
		pago.codigoMatricula = $local.codigoMatricula;
		pago.fechaPago = $local.$fechaPago.data("daterangepicker").startDate.format("YYYY-MM-DD");	
		  
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "ingresos/pago",
			data : JSON.stringify(pago),
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
			success : function(pagos) {		
				var form = $("#formMantenimiento")[0];
				var data = new FormData(form);
				
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var pago = pagos[0];
				var row = $local.tablaMantenimiento.row.add(pago).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				
				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : $variableUtil.root + "ingresos/pago/uploadfile/"+"?accion=cargar",
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					beforeSend : function(xhr) {
						xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
						
					},
					success : function(response) {
			
					},
					complete : function(response) {
					}
				});
				
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
		var pago = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.codigoPago = pago.codigoPago;
		$funcionUtil.llenarFormulario(pago, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var pago = $formMantenimiento.serializeJSON();
		pago.codigoPago = $local.codigoPago;
		console.log(pago.nombreEspecializacion);
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "ingresos/pago",
			data : JSON.stringify(pago),
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
			success : function(pagos) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(pagos[0]).draw();
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

	$local.$tablaMantenimiento.children("tbody").on("click", ".descargar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var pago = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/pago/voucher/" + pago.codigoPago,
			beforeSend : function(xhr) {
				$local.$registrarMantenimiento.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			success : function(pago) {
				var contentType = "application/pdf";
				var file = b64toBlob (pago.bytesLeidos,contentType);
				download(file, "voucher");
			}
		});
	});
	
	function b64toBlob(b64Data, contentType, sliceSize) {
		  contentType = contentType || '';
		  sliceSize = sliceSize || 512;

		  var byteCharacters = atob(b64Data);
		  var byteArrays = [];

		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		    var slice = byteCharacters.slice(offset, offset + sliceSize);

		    var byteNumbers = new Array(slice.length);
		    for (var i = 0; i < slice.length; i++) {
		      byteNumbers[i] = slice.charCodeAt(i);
		    }

		    var byteArray = new Uint8Array(byteNumbers);

		    byteArrays.push(byteArray);
		  }

		  var blob = new Blob(byteArrays, {type: contentType});
		  return blob;
	};
	
	function download(text, filename){
		  var blob = new Blob([text], {type: "application/pdf"});
		  var url = window.URL.createObjectURL(blob);
		  var a = document.createElement("a");
		  a.href = url;
		  a.download = filename;
		  a.click();
	};
});