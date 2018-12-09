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
		idMatricula : "0",
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
		$numeroCuotas : $("#nroCuotaAPagar"),
		$voucher : $("#uploadfile"),
		$documento : "",
		$bandera : false,
		$banderaDeudas : false,
		$arregloEspecializacion : [],
		$arregloDiplomatura : [],
		$especializacionesDiplomaturas : [],
		$montoPagar : 0.0,
		pagos : [],
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearDatePickerSimple3($local.$fechaPago, "DD/MM/YYYY");
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

		if ((codAlumno == null && tipoDoc == null) || (codAlumno == "" && tipoDoc == "")) {
			$funcionUtil.notificarException($variableUtil.codigoAlumnoVacio, "fa-exclamation-circle", "Información", "danger");
			return;
		}
		
	    matriculaDiplomatura();
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/perfeccionamiento/" + tipoDoc + "/" + codAlumno,
			beforeSend : function(xhr) {
				$local.$verificarAlumno.attr("disabled", true).find("i").removeClass("fa-check-square-o").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(pagos) {
				$local.idMatricula = 0;
				$local.$banderaDeudas = false;
				var pago = pagos[0];
				mostrarDiplomaturas("M102", "1");
				
				if(pagos.length == 0){
					mostrarDiplomaturas2("M102", "1");
					$local.$conceptosPago.val("PD").trigger("change.select2");
					$local.$modalidades.val("M102").trigger("change.select2");
					$funcionUtil.notificarException($variableUtil.alumnoNoEncontrado, "fa-exclamation-circle", "Información", "danger");
					return;
				}
				else{
					if(pago.idConceptoPago == null){
						mostrarDiplomaturas2("M102", "1");
						$local.$banderaDeudas = false;
						$local.$apellidos.val(pago.apellidoAlumno);
						$local.$nombres.val(pago.nombreAlumno);
						$local.$correo.val(pago.correoAlumno);
						$local.$tiposDocumento.val(pago.tipoDocumento).trigger("change.select2");
						$local.$conceptosPago.val("PD").trigger("change.select2");
						$local.$modalidades.val("M102").trigger("change.select2");
					}
					else{
						console.log("Entro 1348613.586");
						
						$local.idMatricula = pago.idMatricula;
						//$local.$modalidades.find("option:not(:eq(0))").remove();
						//$local.$modalidades.append($("<option />").val("M102").text("DIPLOMATURA"));
						$local.$tiposDocumento.val(pago.tipoDocumento).trigger("change.select2");
						$local.$apellidos.val(pago.apellidoAlumno);
						$local.$nombres.val(pago.nombreAlumno);
						$local.$correo.val(pago.correoAlumno);
						
						$local.pagos = pagos;
						var modalidadesAuxiliar = [];
						var especializacionesAuxiliar = [];
						
						$.each(pagos, function(i, pago) {			
							modalidadesAuxiliar.push(pago.idModalidad + " - " +pago.nombreModalidad);
							especializacionesAuxiliar.push(pago.idEspecializacion + " - " +pago.nombreEspecializacion);
						});			
						
						modalidadesAuxiliar = eliminateDuplicates(modalidadesAuxiliar);
						
						var posicion1 = 0;
						$.each(modalidadesAuxiliar, function(i, modalidad) {		
							posicion1 = modalidad.indexOf("-");
							if(modalidad.substring(0, posicion1-1)!="M102"){
								$local.$modalidades.append($("<option />").val(modalidad.substring(0, posicion1-1)).text(modalidad.substring(posicion1+2, modalidad.length)));
							}
						});
						
						especializacionesAuxiliar = eliminateDuplicates(especializacionesAuxiliar);
						/*var posicion2 = 0;
						$.each(especializacionesAuxiliar, function(i, especializacion) {	
							posicion2 = especializacion.indexOf("-");
							if(especializacion.substring(0, posicion2-1) != 'ASTI' && especializacion.substring(0, posicion2-1) != 'GPGE' && especializacion.substring(0, posicion2-1) != 'GPTI'){
								$local.$especializaciones.append($("<option />").val(especializacion.substring(0, posicion2-1)).text(especializacion.substring(posicion2+2, especializacion.length)));
							}
						});*/
						
						if(pago.idModalidad == "M100" || pago.idModalidad == "M101"){
							$local.$conceptosPago.val("PDM").trigger("change.select2");
						}
						else{
							$local.$conceptosPago.val("PD").trigger("change.select2");
						}
						
						$local.$modalidades.val(pago.idModalidad).trigger("change.select2");
							
						$local.$tiposPago.val(pago.idTipoPago).trigger("change.select2");					
						$local.$montoAPagar.val(pago.montoAPagar);
						$local.$numCiclo.val(pago.numeroCiclo);
						$local.$cuotaPendiente.val(pago.nroCuotasPendientes);
						$local.$numeroCuotas.val(4-pago.nroCuotasPendientes+1);
						$local.$banderaDeudas = true;
						modalidades();
						$local.$especializaciones.val(pago.idEspecializacion).trigger("change.select2");
						//$local.$modalidades.trigger("change");
					}
					
				}
				
				
				if(!$local.$banderaDeudas){
					$funcionUtil.notificarException($variableUtil.deudaPagada, "fa-exclamation-circle", "Información", "info");
				}
				
			},
			complete : function(response) {
				$local.$verificarAlumno.attr("disabled", false).find("i").addClass("fa-check-square-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	
	});
	
	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		modalidades();
	});
	
	function modalidades(){
		var idModalidad = $local.$modalidades.val();
		var nroCiclo = $local.$numCiclo.val();	
		
		if (nroCiclo == "")
			nroCiclo = 0;
		console.log("Modalidaes entro");
		
		if (idModalidad == null || idModalidad == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		
		$local.$especializaciones.find("option:not(:eq(0))").remove();
		
		if(idModalidad == "M100" || idModalidad == "M101"){
			$local.$conceptosPago.val("PDM").trigger("change.select2");
		}
		else{
			$local.$conceptosPago.val("PD").trigger("change.select2");
		}
		
		if($local.$banderaDeudas){
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			
			if(idModalidad == "M102"){
				$.each($local.$arregloEspecializacion, function(i, especializacion) {
					console.log("entro M102");
					$local.$especializaciones.append($("<option />").val(especializacion.idEspecializacion).text(especializacion.nombreEspecializacion));
				});	
			}
			else{
				
				$.each($local.pagos, function(i, pago) {
					if(pago.nroCuotasPendientes != 0){
						if(idModalidad != "M102" && idModalidad == pago.idModalidad){
							console.log("entro M100");
							$local.$especializaciones.append($("<option />").val(pago.idEspecializacion).text(pago.nombreEspecializacion));
						}
					}
				});
			}	
		}
		else{
			console.log("Mostrar diplomaturas ddf515f3e");
			mostrarDiplomaturas("M102", "1");
		}
	}
	
	$local.$especializaciones.on("change", function(event, opcionSeleccionada)  {
		var idModalidad = $local.$modalidades.val();
		var idEspecializacion = $(this).val();
		if (idEspecializacion == null || idEspecializacion == undefined || idEspecializacion == -1) {
			$local.$nroCiclo.val("");
			return;
		}
		
		if($local.$banderaDeudas){
			var banderas = false;
			$.each($local.pagos, function(i, pago) {	
				console.log(pago);
				if(pago.nroCuotasPendientes != 0){
					if(idEspecializacion == pago.idEspecializacion){
						$local.$tiposPago.val(pago.idTipoPago).trigger("change.select2");
						$local.$nroCiclo.val(pago.numeroCiclo);
						$local.$cuotaPendiente.val(pago.nroCuotasPendientes);
						$local.$numeroCuotas.val(4-pago.nroCuotasPendientes+1);
						$local.idMatricula = pago.idMatricula;
						$local.$montoAPagar.val(pago.montoAPagar);
						banderas = true;
					}
				}
			});
			if(!banderas){
				$.each($local.pagos, function(i, pago) {	
					if(pago.nroCuotasPendientes != 0){
						$.each($local.$arregloEspecializacion, function(i, especialidad) {
							if(idEspecializacion == especialidad.idEspecializacion){
								$local.$montoPagar = especialidad.costoCiclo; 
								$local.$montoAPagar.val(especialidad.costoCiclo);
								$local.$nroCiclo.val("1");
								$local.$cuotaPendiente.val("");
								$local.$numeroCuotas.val("");
								//$local.$tiposPago.trigger("change");
							}
							
						});
					}
				});
				$local.idMatricula = 0;
			}
			if($local.$arregloDiplomatura.length != 0 && idModalidad == "M102"){
				var contador2 = 0;
				$.each($local.$arregloDiplomatura, function(i, diplomatura) {
					if(idEspecializacion == diplomatura.idEspecializacion){
						contador2++;
					}
				});
				contador2++;
				console.log(contador2);
				var esp = $local.$arregloEspecializacion.find(function(especializacion) {
					  return especializacion.idEspecializacion == idEspecializacion;
				});
				
				if(contador2<=esp.numCiclos){
					$local.$numCiclo.val(contador2);
					obtenerCostoCicloEspecializacion(idEspecializacion, contador2);
				}
				else{
					$local.$numCiclo.val("");
					$funcionUtil.notificarException($variableUtil.ciclosCompletos, "fa-exclamation-circle", "Información", "info");
				}
				
			}
		}
		else{
			console.log("Entro en especializacion P");
			$.ajax({
				type : "GET",
				url : $variableUtil.root + "mantenimiento/especializacion/costo/" + idEspecializacion + "/" + 0,
				beforeSend : function(xhr) {
					
				},
				statusCode : {
					400 : function(response) {
						$funcionUtil.limpiarMensajesDeError($formMantenimiento);
						$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
					}
				},
				success : function(especializaciones) {
					var contador = 1;
					var esp;
					$.each(especializaciones, function(i, especializacion) {
						esp = especializacion;
					});
					$local.$montoPagar = esp.costoCiclo; 
					//$local.$montoAPagar.val(esp.costoCiclo);
					$local.$numCiclo.val("1");
					
					if(idModalidad == "M102"){
						$.each($local.$arregloEspecializacion, function(i, especialidad) {
							if(idEspecializacion == especialidad.idEspecializacion){
								var contador2 = 0;
								$.each($local.$arregloDiplomatura, function(i, diplomatura) {
									if(idEspecializacion == diplomatura.idEspecializacion){
										contador2++;
									}
								});
								$local.$montoPagar = especialidad.costoCiclo; 
								//$local.$montoAPagar.val(especialidad.costoCiclo);
								
								contador2++;
								console.log(contador2);
								
								var esp = $local.$arregloEspecializacion.find(function(especializacion) {
									  return especializacion.idEspecializacion == idEspecializacion;
								});
								
								if(contador2<=esp.numCiclos){
									$local.$numCiclo.val(contador2);
									$local.$cuotaPendiente.val("");
									$local.$numeroCuotas.val("");
								}
								else{
									$local.$numCiclo.val("");
									$local.$cuotaPendiente.val("");
									$local.$numeroCuotas.val("");
									$funcionUtil.notificarException($variableUtil.ciclosCompletos, "fa-exclamation-circle", "Información", "info");
								}
							}
							
						});
					}	
				},
				complete : function() {
					
				}
			});
		}
		
		
	});
	
	$local.$tiposPago.on("change", function(event, opcionSeleccionada)  {
		var idTipoPago = $(this).val();
		if (idTipoPago == null || idTipoPago == undefined || idTipoPago == -1) {
			return;
		}
		if(idTipoPago == 'PC'){
			$local.$cuotaPendiente.val("4");
			$local.$numeroCuotas.val("4");
			$local.$montoAPagar.val($local.$montoPagar);
		}
		else{
			$local.$cuotaPendiente.val("4");
			$local.$numeroCuotas.val("1");
			$local.$montoAPagar.val($local.$montoPagar/4);
		}
		
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
		pago.conceptoPago = $("#conceptosPago option:selected").text();
		pago.nombreModalidad = $("#modalidades option:selected").text();
		pago.nombreEspecializacion = $("#especializaciones option:selected").text();
		pago.montoAPagar = $local.$montoAPagar.val();

		//pago.nroCuotasAPagar =$local.$numeroCuotas.val();  
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
		$local.$modalidades.val(pago.idModalidad).trigger("change.select2"); 
		$local.$modalidades.trigger("change", [ pago.idEspecializacion ]);
		$local.$conceptosPago.val(pago.idConceptoPago).trigger("change.select2");
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
				download(file, "voucher.pdf");
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
		  document.body.appendChild(a);
		  a.click();
		  a.remove();
	};
	
	function matriculaDiplomatura(){
		var codAlumno = $local.$codigoAlumno .val();		
		var tipoDoc = $local.$tiposDocumento.val();
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/matricula/diplomatura/" + tipoDoc + "/" + codAlumno,
			success : function(matriculas) {
				$local.$arregloDiplomatura =  matriculas;
			}
		});
	};
	
	/**
	 * Método encargado de mostrar solo las especializaciones de la modalidad
	 * Diplomatura
	 */
	function mostrarDiplomaturas(idModalidad, numeroCiclo){
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad + "/" + numeroCiclo,
			beforeSend : function(xhr) {
				$local.$especializaciones.find("option:not(:eq(0))").remove();
				$local.$modalidades.find("option:not(:eq(0))").remove();
				$local.$especializaciones.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Especializaciones</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$local.$arregloEspecializacion = especializaciones;
				
				$local.$modalidades.append($("<option />").val("M102").text("DIPLOMATURA"));
				/*$.each(especializaciones, function(i, especializacion) {
					console.log(especializacion);
					$local.$especializaciones.append($("<option />").val(especializacion.idEspecializacion).text(especializacion.nombreEspecializacion));
				});*/
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});
	}
	
	function mostrarDiplomaturas2(idModalidad, numeroCiclo){
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad + "/" + numeroCiclo,
			beforeSend : function(xhr) {
				$local.$especializaciones.find("option:not(:eq(0))").remove();
				$local.$modalidades.find("option:not(:eq(0))").remove();
				$local.$especializaciones.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Especializaciones</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$local.$arregloEspecializacion = especializaciones;
				
				$local.$modalidades.append($("<option />").val("M102").text("DIPLOMATURA"));
				$.each(especializaciones, function(i, especializacion) {
					console.log(especializacion);
					$local.$especializaciones.append($("<option />").val(especializacion.idEspecializacion).text(especializacion.nombreEspecializacion));
				});
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});
	}
	
	/**
	 * Esta función se encargará de traer el costo de la matrícula 
	 * dependiendo de la especialización y del ciclo
	 */
	function obtenerCostoCicloEspecializacion(idEspecializacion, numeroCiclo){
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/costo/" + idEspecializacion + "/" + numeroCiclo,
			beforeSend : function(xhr) {
				
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				var contador = 1;
				var esp = especializaciones[0];
				$local.$montoPagar = esp.costoCiclo; 
			},
			complete : function() {
				
			}
		});
	}
});