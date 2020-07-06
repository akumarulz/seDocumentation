$(document).ready(function(){
	
	Utils.ajax(URLBASE+"/archivedDocumentsManagement/getArchivedEntries", {"limit": 4,"offset":2}, (response)=>{
			Utils.log(response);
	});
});