var codeTime = 60;

function getCode(obj) {
    sendSms(obj);
}
//开启验证码倒计时，并且在时间内不能重复点击
function startTime(obj) {
    var ins = setInterval(function () {
        if(codeTime==0){
            $(obj).attr("disabled",false);
            $(obj).val("获取验证码");
            codeTime=60;
            clearInterval(ins);
        }else{
            $(obj).attr("disabled",true);
            $(obj).val("重新发送("+codeTime+")秒");
            codeTime--;
        }
    },1000);
}

//发送ajax请求获取验证码
function sendSms(obj) {
    var phone = document.getElementById("phone").value;
    var re = /^1\d{10}$/;
    if (re.test(phone)) {
        $.ajax({
            url:"servlet/SendCheckCodeController",
            type:"get",
            data:{
                phone:$("#phone").val()
            },
            success:function (res) {
                if(res==200){
                    alert("获取验证码成功");
                    startTime(obj);
                }else{
                    alert("获取失败:"+res);
                }
            }
        })
    } else {
        phoneError_label = document.getElementById("phoneError");
        phoneError_label.innerHTML = "请输入正确的手机号码!";
    }
    
}

function phoneError() {
    var phone = document.getElementById("phone").value;
    var re = /^1\d{10}$/;
    phoneError_label = document.getElementById("phoneError");
    if (re.test(phone)) {
        phoneError_label.innerHTML = "";
    }else{
        
        phoneError_label.innerHTML = "请输入正确的手机号码！";
    }
}

function password1Error() {
    var password1 = document.getElementById("password1").value;
    var re = /\w{8,20}$/;
    password1Error_label = document.getElementById("password1Error");
    if (re.test(password1)) {
        password1Error_label.innerHTML = "";
    } else {
        password1Error_label.innerHTML = "密码在8~20个字符之间！";
    }
    if (document.getElementById("password2").value != "") {
        password2Error();
    }
}

function password2Error() {
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    password2Error_label = document.getElementById("password2Error");

    if (password1 == password2) {
        password2Error_label.innerHTML = "";
    } else {
        password2Error_label.innerHTML = "两次输入密码不一致！";
    }
}

function mySubmit() {
    var phone = document.getElementById("phone").value;
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var checkCode = document.getElementById("checkCode").value;
    var phoneError_label = document.getElementById("phoneError").innerHTML;
    var password1Error_label = document.getElementById("password1Error").innerHTML;
    var password2Error_label = document.getElementById("password2Error").innerHTML;
    var checkBox = document.getElementById("checkBox").value;

    if (phone != "" && password1 != "" && password2 != "" && checkCode != "" && 
    password1Error_label == "" && password2Error_label == "" && phoneError_label == ""
    && checkBox != "uncheck")
    {
        document.getElementById("form").submit();
        console.log("yes");
    } else 
    {
        alert("信息填写有误！");
    }
}