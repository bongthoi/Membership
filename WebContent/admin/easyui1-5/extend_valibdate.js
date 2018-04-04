$.extend($.fn.validatebox.defaults.rules, {
	    equals: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: 'Field do not match.'
	    },
	    minLength: {
	        validator: function(value, param){
	            return value.length >= param[0];
	        },
	        message: '密码长度必须 {0} 个字符以上.'
	    },
	    confirmPass: {
	        validator: function(value, param){
	            var pass = $(param[0]).passwordbox('getValue');
	            return value == pass;
	        },
	        message: '重输密码不正确.'
	    }
});