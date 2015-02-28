$(function() {

    var dataTableAdmin = $('#adminTable').DataTable({
    	"language" : {"url": "//cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Czech.json"},
    	"ajax" : "/invisible-items",
    	"columns" : [ {"data" : "id"}, {"data": "name"}, {"data": "dph", "width": "30px"}, {"data": "cenaBezDph", "width" : "60px"}, {"data": "cenaSDph", "width" : "60px"}, {"data": "", "width" : "90px"} ],
    	"columnDefs" : [ {
			"targets": -1,
			"data": null,
			"defaultContent": "<button class='btn btn-danger btnSmazat'>X</button> <button class='btn btn-primary btnVisible'><i class='fa fa-eye'></i></button> "
    	}, {
			"targets": 0,
			"visible": false
    	} ]
	});
    
    $('#adminTable tbody').on( 'click', '.btnSmazat', function () {
        var data = dataTableAdmin.row( $(this).parents('tr') ).data();
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
							dataTableAdmin.ajax.reload();
						});
                    	dialog.close();
                    }
                }]
        });
    } );
    
    $('#adminTable tbody').on( 'click', '.btnVisible', function () {
        var data = dataTableAdmin.row( $(this).parents('tr') ).data();
        var itemId = data.id;
		$.post("/items/visible/" + itemId, function (data) {
			dataTableAdmin.ajax.reload();
		});
    } );


});