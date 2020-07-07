const DEFAULT_LIMIT= 10;
const DEFAULT_OFFSET = 0;
	
$(document).ready(function(){
	getArchiveEntries();	
});

getArchiveEntries = (limit, offset) => {
	if(limit == undefined || offset == undefined){
		limit = DEFAULT_LIMIT;
		offset == DEFAULT_OFFSET;
	}
	
	Utils.ajax(URLBASE+"/archivedDocumentsManagement/getArchivedEntries", {"limit": limit,"offset":offset}, (response)=>{
		Utils.log(response);
});
}