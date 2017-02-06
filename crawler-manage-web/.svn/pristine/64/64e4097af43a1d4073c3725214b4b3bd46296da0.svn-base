//收集参数
var collectParam = function(selector) {
	var $form = $(selector);
	var param = new Object(); //存放参数
	
	var name;
	var value;
	
	//文本框
	var $input_texts = $form.find("input[type='text']");
	for (var i = 0; i < $input_texts.length; i++) {
		name = $input_texts[i].name;
		value = $input_texts[i].value;
		//if(!value) continue;
		param[name] = value;
	}
	
	//密码框
	var $input_passwords = $form.find("input[type='password']");
	for (var i = 0; i < $input_passwords.length; i++) {
		name = $input_passwords[i].name;
		value = $input_passwords[i].value;
		//if(!value) continue;
		param[name] = value;
	}
	
	//单选按钮
	var $input_radios = $form.find("input[type='radio']:checked");
	for (var i = 0; i < $input_radios.length; i++) {
		name = $input_radios[i].name;
		value = $input_radios[i].value;
		//if(!value) continue;
		param[name] = value;
	}
	
	//多选按钮
	var $input_checkboxs = $form.find("input[type='checkbox']:checked");
	for (var i = 0; i < $input_checkboxs.length; i++) {
		name = $input_checkboxs[i].name;
		value = $input_checkboxs[i].value;
		//if(!value) continue;
		if (!(name in param)) {
			param[name] = new Array();
		}
		param[name].push(value);
	}
	
	//单选下拉列表
	var $selects = $form.find("select");
	for (var i = 0; i < $selects.length; i++) {
		name = $selects[i].name;
		value =$($selects[i]).find("option:selected").val();
		//if(!value) continue;
		param[name] = value;
	}
	
	return param;
}