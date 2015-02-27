$(function() {

	$(".dropdownLowest").click(function(e) {
		e.preventDefault();
		$("#dph").val("10");
	});

	$(".dropdownLow").click(function(e) {
		e.preventDefault();
		$("#dph").val("15");
	});

	$(".dropdownNormal").click(function(e) {
		e.preventDefault();
		$("#dph").val("21");
	});

	$(".clear").click(function(e) {
		e.preventDefault();
		$("#dph").val("21");
		$("#cenaBezDph").val("");
		$("#cenaSDph").val("");
		$("#castkaDph").val("");
		$("#name").val("");
		$(".save").css("display", "none");
		$("#cenaBezDph").prop("disabled", false);
		$("#cenaSDph").prop("disabled", false);
	});
	
	$("#cenaBezDph").keyup(function() {
		if($(this).val()) {
			$("#cenaSDph").prop("disabled", true);
		} else {
			$("#cenaSDph").prop("disabled", false);
		}
	});

	$("#cenaSDph").keyup(function() {
		if($(this).val()) {
			$("#cenaBezDph").prop("disabled", true);
		} else {
			$("#cenaBezDph").prop("disabled", false);
		}
	});

	$(".run").click(function(e) {
		e.preventDefault();
		var inputTextDph = $("#dph");
		var inputTextCenaBezDph = $("#cenaBezDph");
		var inputTextCenaSDph = $("#cenaSDph");
		var inputTextCastkaDph = $("#castkaDph");
		var valDph = parseInt(inputTextDph.val());
		var valCenaBezDph = parseInt(inputTextCenaBezDph.val());
		var valCenaSDph = parseInt(inputTextCenaSDph.val());
		var valCastkaDph = parseInt(inputTextCastkaDph.val());
		if(!valCenaBezDph && !valCenaSDph) {
			return;
		}
		if($("#cenaSDph").prop("disabled")) {
			var castkaDph = roundToTwo(valCenaBezDph * valDph / 100);
			inputTextCastkaDph.val(castkaDph);
			inputTextCenaSDph.val(roundToTwo(valCenaBezDph + castkaDph));
		}
		else if($("#cenaBezDph").prop("disabled")) {
			// http://www.zakonyprolidi.cz/cs/2004-235#p37-2
			var koeficient = roundToFour(valDph / (valDph + 100));
			var castkaDph = roundToTwo(valCenaSDph * koeficient);
			var castkaSDph = roundToTwo(valCenaSDph - castkaDph);
			inputTextCastkaDph.val(castkaDph);
			inputTextCenaBezDph.val(castkaSDph);
		}
		
		$(".save").css("display", "inline");
	});
	
	function roundToFour(num) {    
		return +(Math.round(num + "e+4")  + "e-4");
	}

	function roundToTwo(num) {    
		return +(Math.round(num + "e+2")  + "e-2");
	}
	
	$(".save").click(function(e) {
		e.preventDefault();
		$('#saveDialog').modal();
	});
	
	/*
	 * autofocus in modal dialog
	 */
	$('#saveDialog').on('shown.bs.modal', function () {
		$("#name").focus();
	})

	
    var dataTable = $('#items').DataTable({
    	"language" : {"url": "//cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Czech.json"},
    	"ajax" : "/items",
    	"columns" : [ {"data" : "id"}, {"data": "name"}, {"data": "cenaBezDph", "width" : "60px"}, {"data": "cenaSDph", "width" : "60px"}, {"data": "", "width" : "90px"} ],
    	"columnDefs" : [ {
			"targets": -1,
			"data": null,
			"defaultContent": "<button class='btn btn-primary btnNacist'>Načíst</button> <button class='btn btn-danger btnSmazat'>X</button> "
    	}, {
			"targets": 0,
			"visible": false
    	} ]
	});
    
    $('#items tbody').on( 'click', '.btnNacist', function () {
        var data = dataTable.row( $(this).parents('tr') ).data();
        var itemId = data.id;
        $.get("/items/" + itemId, function (data) {
        	$("#dph").val(data.dph);
        	$("#cenaBezDph").val(data.cenaBezDph);
        	$("#cenaSDph").val(data.cenaSDph);
        	$("#castkaDph").val(data.castkaDph);
        	$("#name").val(data.name);
        	if(data.cenaBezDphDisabled) {
        		$("#cenaBezDph").prop("disabled", true);
        		$("#cenaSDph").prop("disabled", false);
        	}
        	if(data.cenaSDphDisabled) {
        		$("#cenaBezDph").prop("disabled", false);
        		$("#cenaSDph").prop("disabled", true);
        	}
        });
    } );

    $('#items tbody').on( 'click', '.btnSmazat', function () {
        var data = dataTable.row( $(this).parents('tr') ).data();
        var itemId = data.id;
        BootstrapDialog.show({
	            title: 'Opravdu smazat?',
	            message: 'Opravdu smazat "' + data.name + '"?',
                buttons: [{
                    label: 'Storno',
                    action: function(dialog) {
                    	dialog.close();
                    }
                }, {
                    label: 'Smazat',
                    cssClass: 'btn-primary',
                    action: function(dialog) {
						$.post("/items/delete/" + itemId, function (data) {
							dataTable.ajax.reload();
						});
                    	dialog.close();
                    }
                }]
        });
    } );


    $(".saveConfirm").click(function(e) {
		e.preventDefault();
		var valName = $("#name").val();
		var valCenaBezDph = $("#cenaBezDph").val();
		var valCenaSDph = $("#cenaSDph").val();
		var valDph = $("#dph").val();
		var valCastkaDph = $("#castkaDph").val();
		var valCenaBezDphDisabled = $("#cenaBezDph").prop("disabled");
		var valCenaSDphDisabled = $("#cenaSDph").prop("disabled");
		$.post("/save", {name : valName, cenaBezDph : valCenaBezDph, cenaSDph : valCenaSDph, 
						 dph : valDph, cenaBezDphDisabled : valCenaBezDphDisabled, 
						 cenaSDphDisabled : valCenaSDphDisabled, castkaDph : valCastkaDph}, function(data) {
			dataTable.ajax.reload();
		});
		$('#saveDialog').modal("hide");
	});

    $('#name').keypress(function (e) {
    	 var key = e.which;
    	 if(key == 13)  // the enter key code
    	  {
    	    $('.saveConfirm').click();
    	    return false;  
    	  }
	});   


});