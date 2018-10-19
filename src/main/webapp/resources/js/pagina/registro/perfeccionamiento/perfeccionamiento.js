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
		idPerfeccionamiento : "",
		idMatricula : "",
		$verificarAlumno : $("#verificarDatos"),
		$codigoAlumno : $("#codigoAlumno"),
		$nombres : $("#nombreAlumno"),
		$apellidos : $("#apellidoAlumno"),
		$correo : $("#correoAlumno"),
		$modalidades : $("#modalidades"),
		$cuotaPendiente : $("#nroCuotasPendientes"),
		$tiposPago : $("#tiposPago"),
		$tiposDocumento : $("#tiposDocumento"),
		$conceptosPago : $("#conceptosPago"),
		$nroCiclo : $("#ciclo"),
		$montoAPagar : $("#montoAPagar"),
		$especializaciones : $("#especializaciones"),
		$fechaPago : $("#fechaPago"),
		$numCiclo : $("#numCiclo"),
		$numeroCuotas : $("#numeroCuotas"),
		$voucher : $("#uploadfile"),
		$documento : "",
		$bandera : false,
		pagos : [],
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearDatePickerSimple3($local.$fechaPago, "DD/MM/YYYY");
	$funcionUtil.crearSelect2($local.$numeroCuotas, "Seleccione un número de cuotas");
	$funcionUtil.crearSelect2($local.$modalidades, "Seleccione una Modalidad");
	$funcionUtil.crearSelect2($local.$especializaciones, "Seleccione una Especialización");
	$funcionUtil.crearSelect2($local.$tiposDocumento, "Seleccione un Tipo de Documento");
	$funcionUtil.crearSelect2($local.$conceptosPago, "Seleccione un concepto de pago");
	$funcionUtil.crearSelect2($local.$tiposPago, "Seleccione un tipo de pago");

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
			"url" : $variableUtil.root + "registro/perfeccionamiento?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay Pagos de perfeccionamiento registrados"
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6 ],
			"className" : "all filtrable",
		}, {
			"targets" : 4,
			"className" : "all dt-center",
			"render" : function(data, type, row, meta) {
				if (row.idTipoPago == "PC")
					return "<label class='label label-info label-size-12'>PAGO AL CONTADO</label>";
				else
					return "<label class='label label-info label-size-12'>PAGO EN CUOTAS</label>";
			}
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonDescargar
		} ],
		"columns" : [ {
			"data" : 'fechaPago',
			"title" : "Fecha de Pago"
		}, {
			"data" : function(row) {
				return row.tipoDocumento + " - " + row.numeroDocumento;
			},
			"title" : "Documento"
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
			"data" : null,
			"title" : "Tipo Pago"
		},{
			"data" : 'numeroCiclo',
			"title" : "Ciclo"
		}, {
			"data" : 'montoAPagar',
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
		title : "Registro de Perfeccionamiento",
		autoOpen : false,
		modal : false,
		height : 700,
		width : 1020,
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro2($formMantenimiento);
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
	
	function eliminateDuplicates(arr) {
		 var i,
		     len=arr.length,
		     out=[],
		     obj={};

		 for (i=0;i<len;i++) {
		    obj[arr[i]]=0;
		 }
		 for (i in obj) {
		    out.push(i);
		 }
		 return out;
	}
	
	$local.$verificarAlumno.click(function(){		
		var codAlumno = $local.$codigoAlumno .val();		
		var tipoDoc = $local.$tiposDocumento.val();
		if (codAlumno == null || codAlumno == undefined || codAlumno == "") {
			$funcionUtil.notificarException($variableUtil.codigoAlumnoVacio, "fa-exclamation-circle", "Información", "danger");
			return;
		}
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/perfeccionamiento/" + tipoDoc + "/" + codAlumno,
			beforeSend : function(xhr) {
				$local.$verificarAlumno.attr("disabled", true).find("i").removeClass("fa-check-square-o").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(pagos) {
				var pago = pagos[0];
				console.log(pagos);
				if(pagos.length == 0){
					$funcionUtil.notificarException($variableUtil.alumnoNoEncontrado, "fa-exclamation-circle", "Información", "danger");
					return;
				}
				
				if (pago.idTipoPago == "PC"){
					$local.$numeroCuotas.append($("<option />").val("4").text("TODAS (4 cuotas)"));
				}
				$local.idMatricula = pago.idMatricula;
				$funcionUtil.llenarFormulario(pago, $formMantenimiento); 
				$local.$modalidades.trigger("change", [ pago.idEspecializacion ]);
				/*
				$local.pagos = pagos;
				var modalidadesAuxiliar = [];
				var banderaDeuda = true;
				$.each(pagos, function(i, pago) {			
					if(pago.nroCuotasPendientes != 0){
						banderaDeuda = false;
						if(pago.tipoPago == "PAGO AL CONTADO"){
							$local.$bandera = true;
							$local.$numeroCuotas.find("option:not(:eq(0))").remove();
							$local.$numeroCuotas.append($("<option />").val("").text("Seleccione un número de cuotas"));
							$local.$numeroCuotas.append($("<option />").val("1").text("1 cuota"));
							$local.$montoAPagar.val(pago.montoAPagar*4);
						}
						else{
							$local.$bandera = false;
							if(!$local.$bandera){
								var j = 2;
								$local.$numeroCuotas.find("option:not(:eq(0))").remove();
								$local.$numeroCuotas.append($("<option />").val("").text("Seleccione un número de cuotas"));
								$local.$numeroCuotas.append($("<option />").val("1").text("1 cuota"));
								while(j<=pago.nroCuotasPendientes){
									$local.$numeroCuotas.append($("<option />").val(j).text(j + " cuotas"));
									j++;
								}
							}
							$local.$montoAPagar.val(this.montoAPagar);
						}
						modalidadesAuxiliar.push(pago.nombreModalidad);
						//$local.$modalidades.append($("<option />").val(pago.idModalidad).text(pago.idModalidad + " - "+pago.nombreModalidad));
						$local.$especializaciones.append($("<option />").val(pago.idEspecializacion).text(pago.nombreEspecializacion));
						$local.$apellidos.val(this.apellidoAlumno);
						$local.$nombres.val(this.nombreAlumno);
						$local.$correo.val(this.correoAlumno);
						//$local.$tiposPago.trigger("change");
						$local.$tiposPago.val(this.idTipoPago);
						//$local.$nroCiclo.val(this.numeroCiclo);
						//$local.$cuotaPendiente.val(this.nroCuotasPendientes);
						//$local.codigoMatricula = this.codigoMatricula;
					}
					else{
						banderaDeuda = true;
					}
				});		*/		
			},
			complete : function(response) {
				$local.$verificarAlumno.attr("disabled", false).find("i").addClass("fa-check-square-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	
	});
	
	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		var idModalidad = $(this).val();
		var nroCiclo = $local.$numCiclo.val();	
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
	
	
	$local.$especializaciones.on("change", function(event, opcionSeleccionada)  {
		var idEspecializacion = $(this).val();
		if (idEspecializacion == null || idEspecializacion == undefined || idEspecializacion == -1) {
			$local.$nroCiclo.val("");
			return;
		}
		
		$.each($local.pagos, function(i, pago) {	
			if(pago.nroCuotasPendientes != 0){
				if(idEspecializacion == pago.idEspecializacion){
					if(pago.tipoPago == "PC"){
						$local.$bandera = true;
						$local.$numeroCuotas.find("option:not(:eq(0))").remove();
						$local.$numeroCuotas.append($("<option />").val("").text("Seleccione un número de cuotas"));
						$local.$numeroCuotas.append($("<option />").val("1").text("1 cuota"));
						$local.$montoAPagar.val(pago.montoAPagar*4);
					}
					else{
						$local.$bandera = false;
						if(!$local.$bandera){
							console.log("Especialización : "+ $local.$bandera);
							var j = 2;
							$local.$numeroCuotas.find("option:not(:eq(0))").remove();
							$local.$numeroCuotas.append($("<option />").val("").text("Seleccione un número de cuotas"));
							$local.$numeroCuotas.append($("<option />").val("1").text("1 cuota"));
							while(j<=pago.nroCuotasPendientes){
								$local.$numeroCuotas.append($("<option />").val(j).text(j + " cuotas"));
								j++;
							}
							
						}
						$local.$montoAPagar.val(this.montoAPagar);
					}
					$local.$apellidos.val(this.apellidoAlumno);
					$local.$nombres.val(this.nombreAlumno);
					$local.$correo.val(this.correoAlumno);
					$local.$tipoPago.val(this.tipoPago);
					$local.$nroCiclo.val(this.numeroCiclo);
					$local.$cuotaPendiente.val(this.nroCuotasPendientes);
					$local.idMatricula = this.idMatricula;
				}
			}
		});
		
	});
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var pago = $formMantenimiento.serializeJSON();
		//pago.idMatricula = $local.idMatricula;
		//pago.idModalidad = $local.$modalidades.val();
		//pago.idEspecializacion = $local.$especializaciones.val();
		//pago.nombreEspecializacion = $("#especializaciones option:selected").text().substring(7);
		//pago.conceptoPago = "210-011";
		pago.fechaPago = $local.$fechaPago.data("daterangepicker").startDate.format("YYYY-MM-DD");	
		pago.idMatricula = $local.idMatricula;
		//pago.nroCuotasAPagar =$local.$numeroCuotas.val();  
		console.log(pago);
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "registro/perfeccionamiento",
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
					url : $variableUtil.root + "registro/perfeccionamiento/uploadfile/"+"?accion=cargar",
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
				$local.$modalidades.find("option:not(:eq(0))").remove();
				$local.$especializaciones.find("option:not(:eq(0))").remove();
				$local.$registrarMantenimiento.attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".actualizar", function() {
		$funcionUtil.prepararFormularioActualizacion($formMantenimiento);
		$local.$filaSeleccionada = $(this).parents("tr");
		var pago = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.idPerfeccionamiento = pago.idPerfeccionamiento;
		$local.$numeroCuotas.val(pago.nroCuotasAPagar).trigger("change.select2"); 
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
		pago.idPerfeccionamiento = $local.idPerfeccionamiento;
		pago.nroCuotasAPagar =$local.$numeroCuotas.val();  
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "registro/perfeccionamiento",
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
				
				var form = $("#formMantenimiento")[0];
				var data = new FormData(form);
				
				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : $variableUtil.root + "registro/perfeccionamiento/uploadfile/"+ $local.idPerfeccionamiento +"?accion=actualizar",
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
				$local.$actualizarMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".descargar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var pago = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/perfeccionamiento/voucher/" + pago.idPerfeccionamiento,
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