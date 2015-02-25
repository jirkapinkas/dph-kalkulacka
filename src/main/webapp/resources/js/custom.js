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

});