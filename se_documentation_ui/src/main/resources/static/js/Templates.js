const Templates = (function(Templates){
	if(Templates == undefined){
		Templates = {};
	}
	
	Templates.subTopicDeleted = function(){
		return "<div> " +
				"<p>SubTopic Deleted</p>" +
				"</div>";
	}
	
	function subTopicManagementBar  (subtopic){
		var id = subtopic.id;
		return 	"<button value='"+id+"' type='button' class='btn btn-primary subtopic-management-buttons editSubTopicBtn'><i class='fas fa-file-alt'></i></button>" + 
				"<button value='"+id+"' type='button' class='btn btn-danger subtopic-management-buttons deleteSubtopicBtn'><i class='fas fa-times'></i></button>";
	} 
	
	Templates.subTopic = function(subtopic){
		var buttons = subTopicManagementBar(subtopic);
		return "<div class='template-subtopic'>" + 
					"<div id='subtopicManagementBar'>"+
						buttons +
						"</div>"+
					"<div class='subtopic-heading'>" +
						"<h2>" + subtopic.title + "</h2>" +
					"</div>" +
					"<div class='subtopic-content'>"+
						"<p>" + subtopic.content + "</p>" + 
					"</div>" +
				"</div>";
	}
	
	Templates.DeleteTopicModalWorkAreaContent = (topic) => {
		var topicAndSubTopicsTemplate = "<div>";
		topicAndSubTopicsTemplate += "<h4>" + topic.topicTitle + "</h4>";
		topicAndSubTopicsTemplate += "<ul>";
		
		$.each(topic.subTopics,(e,t)=>{
			topicAndSubTopicsTemplate += "<li>" + t.title + "</li>";
		});
		
		topicAndSubTopicsTemplate += "</ul></div>";
		return topicAndSubTopicsTemplate;
	}
	
	
	return Templates;
	
})();