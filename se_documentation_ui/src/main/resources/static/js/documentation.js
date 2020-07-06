$(document).ready(function(){
	Utils.log("document ready");
	Utils.log(__PROPS);

	
	function formSubmitComplete(response){
		Utils.log(response);
		
		if(response.successful){
			//set successful notification
			Utils.successNotification({message: __PROPS["msg.code.0003"] });
			var userInputs = [NEW_TOPIC_INPUT, SUB_TOPIC_TITLE, SUB_TOPIC_CONTENT ];
			
			//reset the inputs
			userInputs.forEach(function(input){
				$("#" + input).val("");
			});
			 
			$("#" + CURRENT_TOPIC_INPUT ).val("default");
			 
			disableInput(NEW_TOPIC_INPUT, false);
			disableInput(CURRENT_TOPIC_INPUT, false);
			
		}else{
			Utils.errorNotification({message: __PROPS["msg.code.0004"] });
		}
	}
	
	$(".subtopic-heading").click(function(){
		var elementObj = $(this);
		var subTopicData = elementObj.data();
		var params = {}
		params.subtopicId = subTopicData.subtopicId;
		
		Utils.ajax(URLBASE + GET_SUBTOPIC, params , function (response){
			var subTopic = response.subtopic;
			var subTopicTitle = subTopic.title;
			Utils.log(response);
			$("#" + TOPIC_DISPLAY_CONTAINER).html(Templates.subTopic(subTopic));
			
			Utils.attachDeleteClickEvent(function(response){
				var container = $("#" + TOPIC_DISPLAY_CONTAINER);
				container.empty();
				container.html(Templates.subTopicDeleted());
				
				var parent = elementObj.parent().parent();
				var parentId = "#"+ parent.attr("id");

				Utils.animate(parentId, "backOutLeft").then((message) => {
					parent.remove();
					var notificationMsg = "";
					
					try{
						notificationMsg = Utils.stringTemplateResolver(__PROPS["msg.code.0005"], {subTopicTitle: subTopicTitle});  
					}catch(e){
						Utils.log(e);
					}
					
					Utils.successNotification({
						message: notificationMsg,
						options: {}
					});
					
				});
			});
			
		});
		
	});
	
	$("#formSubmitBtn").click(function(){
		var params = {};
		var form = {};
		
		form["newTopic"] 		= $("#" + NEW_TOPIC_INPUT).val().trim();
		
		//if no new topic, then get the selected current topic
		if(form["newTopic"] == ""){
			var currentDocValue =  $("#" + CURRENT_TOPIC_INPUT).val();
			if(currentDocValue != SELECT_DROPDOWN_DEFAULT_VALUE){
				form["currentTopic"] 	= currentDocValue;				
			}else{
				Utils.errorNotification({message: __PROPS["msg.code.0001"] });
				return;
			}
			delete form["newTopic"] ;
			
		}
		
		
		var title = $("#" + SUB_TOPIC_TITLE).val().trim();
		var content = $("#" + SUB_TOPIC_CONTENT).val().trim();
		
		if(title != "" && content != "" ){
			form["title"] 			= title;
			form["content"]			= content;			
		}else{
		
			Utils.errorNotification({message: __PROPS["msg.code.0002"] });
			return;
		}

		params["form"] = form;
		
		Utils.ajax(URLBASE+POST_FORM, params, formSubmitComplete);
		
	});
	
	
	
	$("#" + NEW_TOPIC_INPUT).keyup(function(){
		var inputValue = $("#" + NEW_TOPIC_INPUT).val().trim();
		
		var setDisabled = false;
		if(inputValue.length > 0){
			setDisabled=true
		}
		
		disableInput(CURRENT_TOPIC_INPUT, setDisabled);
	});
	
	$("#" + CURRENT_TOPIC_INPUT).change(function(){
		var selectValue = $("#" + CURRENT_TOPIC_INPUT).val();
		var setDisabled = false;
		
		if(selectValue != SELECT_DROPDOWN_DEFAULT_VALUE){
			setDisabled = true
		}
		disableInput(NEW_TOPIC_INPUT, setDisabled);
	});
	
	function disableInput(input, setDisabled){
		$("#" + input).attr("disabled",!setDisabled == false);
	}
	
	$(".topic_delete_button").click((e) => {
		var topicObj = getDataSet(e);
		var ajaxObj = {
				url: URLBASE + GET_TOPIC,
				params: {"topicId": topicObj.topicid},
				func: (response) => {
					Utils.log(response);
					$("#" + TOPIC_DELETE_CONFIRM_WORK_AREA).empty();
					$("#" + TOPIC_DELETE_CONFIRM_WORK_AREA).html(Templates.DeleteTopicModalWorkAreaContent(response.topic));
					$("#" + TOPIC_DELETE_CONFIRM_MODAL).data("data-topicObj",response.topic);
				}
		};
		
		Utils.showModal(TOPIC_DELETE_CONFIRM_MODAL, topicObj, "data-topic", ajaxObj);
	});
	
	getDataSet = (obj) => {
		return obj.currentTarget.dataset;
	}
	
	
	$("#topic-delete-confirm-confirmbtn").click(()=>{
		var modalData = $("#" + TOPIC_DELETE_CONFIRM_MODAL).data();
		var topicId = modalData.dataTopic.topicid;
		
		Utils.ajax(URLBASE + DELETE_TOPIC,{"topicId" : topicId} ,(response)=>{
			var deletedTopic = $("#topicContainer" + topicId); 
			Utils.animate("#" + deletedTopic.attr("id"), "backOutLeft").then((message) => {
				deletedTopic.remove();
				var topic = $("#" + TOPIC_DELETE_CONFIRM_MODAL).data("data-topicObj");
				var notificationMsg = "";
				try{
					notificationMsg = Utils.stringTemplateResolver(__PROPS["msg.code.0006"],{topicTitle: topic.topicTitle});
				}catch(e){
					Utils.log(e);
				}
				
				Utils.successNotification({"message": notificationMsg ,"options": {}})
			});
		});
		Utils.toggelModal(TOPIC_DELETE_CONFIRM_MODAL);
	});
	
	(function init(){
		
		Utils.log("init called");
	})();
	
});