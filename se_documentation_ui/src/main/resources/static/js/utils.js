//Urls
const URLBASE = "/documentation";
const GET_SUBTOPIC = "/getsubtopic";
const POST_FORM = "/addDocument/postDocument";
const DELETE_SUBTOPIC = "/addDocument/deleteSubtopic";
const GET_TOPIC = "/getTopic";
const DELETE_TOPIC = "/deleteTopic";

// Inputs
const NEW_TOPIC_INPUT = "documentNewTopic";
const CURRENT_TOPIC_INPUT = "documentCurrentTopics";
const SUB_TOPIC_TITLE = "documentTitle";
const SUB_TOPIC_CONTENT = "documentContent";

// Values
const SELECT_DROPDOWN_DEFAULT_VALUE = "default";

// containers
const TOPIC_DISPLAY_CONTAINER = "topicDisplayContainer";
const TOPIC_DELETE_CONFIRM_WORK_AREA = "topic-delete-confirm-work-area";
const TOPIC_DELETE_CONFIRM_MODAL = "topic-delete-confirm";
var Utils = (function(Utils){
	
	if(Utils == undefined){
		var Utils = {}
	}
	
	Utils.showModal = (name, data, dataName, ajaxObj) => {
		if(data != undefined){
			$("#" + name).data(dataName,data);
		}
		
		if(ajaxObj != undefined){
			Utils.ajax(ajaxObj.url, ajaxObj.params, ajaxObj.func);
		}
		
		Utils.toggelModal(name);
	}
	
	Utils.toggelModal = (modalName) => {
		$("#" + modalName).modal("toggle");
	}
	
	
	
	Utils.animate = (element, animation, prefix = 'animate__') =>
		  // We create a Promise and return it
		  new Promise((resolve, reject) => {
		    const animationName = `${prefix}${animation}`;
		    const node = document.querySelector(element);

		    node.classList.add(`${prefix}animated`, animationName);

		    // When the animation ends, we clean the classes and resolve the
			// Promise
		    function handleAnimationEnd() {
		      node.classList.remove(`${prefix}animated`, animationName);
		      node.removeEventListener('animationend', handleAnimationEnd);

		      resolve('Animation ended');
		    }

		    node.addEventListener('animationend', handleAnimationEnd);
		  });
	
	
	
		
	Utils.ajax = (url, payload, callback) =>{
		
		
		if(payload == undefined){
			payload = {};
		}
		
		
		$.ajax({
			method: "POST",
			url: url,
			data : JSON.stringify(payload),
			dataType : "json",
			contentType : "application/json"
				
		}).done(function(response){
			
			Utils.log("done");

			if(callback != undefined){
				callback(response);
			}
		   
		}).fail(function(msg){
			
			Utils.log("fail");
			Utils.log(msg);
	
		});
	}
	
	Utils.log = function(param){
		console.log(param);
	}
	
	Utils.selectDropDownDefaultValue = function(){
		return SELECT_DROPDOWN_DEFAULT_VALUE;
	}
	
	Utils.attachDeleteClickEvent = function(func){
		$(".deleteSubtopicBtn").click(function(){
			var subTopicValue = this.value;
			
			var params = {}
			params.deleteSubTopicRequest = {}
			params.deleteSubTopicRequest.subtopicValue = subTopicValue;
			Utils.ajax(URLBASE + DELETE_SUBTOPIC, params, func);
		});
	}
	
	Utils.stringTemplateResolver = (stringTemplate, obj) => {
		var stringReturned = stringTemplate;
		if(obj == undefined || typeof obj !== 'object' || stringTemplate.trim() == ''){
			throw __PROPS["msg.code.0007"];
		}
		for(var prop in obj){
			if(Object.prototype.hasOwnProperty.call(obj,prop)){
				stringReturned = stringReturned.replace("${"+prop+"}", obj[prop]);				
			}
		}
		return stringReturned;
	}
	
	
	function notification(message,type,options){
		if(options == undefined){
			options = {};
		}
		$.notify(message,type,options);
		
	}
	
	Utils.successNotification = function(params){
		notification(params.message,"success", params.options);
	}
	
	Utils.warningNotification = function(params){
		notification(params.message,"warn", params.options);
	}
	
	Utils.infoNotification = function(params){
		notification(params.message,"info", params.options);
	}
	
	Utils.errorNotification = function(params){
		notification(params.message,"error", params.options);
	}
	
	
	
	return Utils;
	
})();