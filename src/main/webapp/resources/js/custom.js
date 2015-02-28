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
		var valDph = parseFloat(inputTextDph.val());
		var valCenaBezDph = parseFloat(parseFloat(inputTextCenaBezDph.val().replace(",", ".")).toFixed(2));
		var valCenaSDph = parseFloat(parseFloat(inputTextCenaSDph.val().replace(",", ".")).toFixed(2));
		var valCastkaDph = parseFloat(parseFloat(inputTextCastkaDph.val()).toFixed(2));
		if(!valCenaBezDph && !valCenaSDph) {
			return;
		}
		if($("#cenaSDph").prop("disabled")) {
			var castkaDph = parseFloat((valCenaBezDph * valDph / 100).toFixed(2));
			inputTextCastkaDph.val(castkaDph);
			inputTextCenaSDph.val((valCenaBezDph + castkaDph).toFixed(2));
		}
		else if($("#cenaBezDph").prop("disabled")) {
			// http://www.zakonyprolidi.cz/cs/2004-235#p37-2
			var koeficient = parseFloat((valDph / (valDph + 100)).toFixed(4));
			var castkaDph = parseFloat((valCenaSDph * koeficient).toFixed(2));
			var castkaSDph = parseFloat((valCenaSDph - castkaDph).toFixed(2));
			inputTextCastkaDph.val(castkaDph);
			inputTextCenaBezDph.val(castkaSDph);
		}
		
		$(".save").css("display", "inline");
	});
	
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
    	"columns" : [ {"data" : "id"}, {"data": "name"}, {"data": "dph", "width": "30px"}, {"data": "cenaBezDph", "width" : "60px"}, {"data": "cenaSDph", "width" : "60px"}, {"data": "", "width" : "90px"} ],
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

    $(".saveConfirm").click(function(e) {
		e.preventDefault();
		var form = $("#submitForm");
		if(!form.valid()) {
			return;
		}
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
    
    $.validator.messages.required = "Toto pole je povinné!";

    $("#submitForm").validate(
    		{
    			rules: {
    				name: {
    					required : true,
    					minlength : 1
    				}
    			},
    			highlight: function(element) {
    				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    			},
    			unhighlight: function(element) {
    				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
    			}
    		}
    );

});