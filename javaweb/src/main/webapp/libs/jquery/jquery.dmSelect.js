$.dmSelect = function(name, value, data,readOnly,lab) {
	if(readOnly){
		$("#" + name).attr("readonly", "readonly"); //���ÿؼ�ֻ��
	}
	
	if(!lab){
		var top = $("#" + name).offset().top; //��ȡ�ؼ�top��leftλ�ú�width��height
		var left = $("#" + name).offset().left;
		var height = $("#" + name).height();
		var width = $("#" + name).width() + 3;
	} else {
		var top = $("#" + lab).offset().top; //��ȡ�ؼ�top��leftλ�ú�width��height
		var left = $("#" + lab).offset().left;
		var height = $("#" + lab).height();
		var width = $("#" + lab).width() + 3;
	}
	
	var option_open = false; //����Ƿ������option
	function __dropheight(l) {
		var h;
		if (l > 10 || l < 1) {
			h = 10 * 20;
		} else {
			h = l * 20;
			h += 2;
		}
		return h;
	} //��������option��ʾ�߶�
	var div_html = "<div id='_li_" + name + "' style='position:absolute;background-color:#FFFFFF;top:"
			+ (top + height + 5) + "px;left:" + left + "px;width:" + ((width < 30) ? 30 : width) + "px;height:"
			+ __dropheight(data.length)
			+ "px;border:1px #666666 solid;overflow-x:hidden;overflow-y:auto;display:none;z-index:99999;'>";
	//forѭ�����option
	for (var i = 0; i < data.length; i++) {
		div_html += "<div style='text-align:left;padding-left:5px;' value='" + data[i][1] + "'>" + data[i][0]
				+ "</div>";
	}
	div_html += "</div>";
	if($("#_li_" + name)){
		$("#_li_" + name).remove();
	}
	$("#" + name).after(div_html); //��ӵ�input�ؼ�����
	function __open_option() {
		$("div[id^='_li_']").hide();
		$("#_li_" + name).show();
		option_open = true;
	} //��ʾ����option
	function __hide_option() {
		$("#_li_" + name).hide();
		option_open = false;
		$(document).unbind("click", __hide_option);
	} //��������option
	$("#" + name).click(function(event) {
		if (option_open) {
			__hide_option();
		} else {
			__open_option();
			$(document).bind("click", __hide_option)
		}
		event.stopPropagation();
	});
	$("#_li_" + name + " > div").hover(function() {
		$(this).css({
			"background-color" : "#CCCCCC"
		});
	}, function() {
		$(this).css({
			"background-color" : "#FFFFFF"
		});
	}).click(function() {
		$("#" + name).val($(this).html());
		try{
			$("#" + value).val($(this).attr("value"));
			$("#" + value).attr(name,$(this).html());
			
		}catch(e){
		}
		try{
			$("#" + name).focus();
		}catch(e){
		}
	});
};

/**
 //һ�������� <input type="text" id="time_hour" class="dropdown-select" /><input type="hidden" id="hf_time_hour" />

 var Options1 = [["00��", "00"], ["01��", "01"], ["02��", "02"], ["03��", "03"], ["04��", "04"], ["05��", "05"],
		["06��", "06"], ["07��", "07"], ["08��", "08"], ["09��", "09"], ["10��", "10"], ["11��", "11"], ["12��", "12"],
		["13��", "13"], ["14��", "14"], ["15��", "15"], ["16��", "16"], ["17��", "17"], ["18��", "18"], ["19��", "19"],
		["20��", "20"], ["21��", "21"], ["22��", "22"], ["23��", "23"]];

$(document).ready(function() {
	$.dmSelect("time_hour", "hf_time_hour", Options1);
	$("#time_hour").keyup(function(e){
		var filterOption = new Array();
		var flags = "i";
		var pattern = $("#time_hour").val();
		var regexp = new RegExp(pattern,flags);
		$.each(Options1,function(i,opt){
			if(regexp.test(opt[0])){
				filterOption[filterOption.length] = opt;
			}
		});
		//console.debug("filterOption="+filterOption.length);
	$.dmSelect("time_hour", "hf_time_hour", filterOption);
		$("#time_hour").click();
	});
	
	
});
**/