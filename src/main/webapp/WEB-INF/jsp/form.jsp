<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script src="https://cdn.bootcss.com/socket.io/1.7.3/socket.io.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <title>申请开票</title>
    <style type="text/css">
        .form{
            width:100%;
            box-sizing: border-box;
            background-color:#fff;
            font-size: 14px;
            border-radius: 0.25em;
        }
        .left-title{
            width:4em;
            margin-right: 1.25em;
        }
        .flex{
            display: flex;
			align-items: center;
        }
        .btn {
            width: 100%;
            height: 40px;
            background-color: #1aac1b;
            border-radius: 0.25em;
            border: none;
            color:#fff;
        }
        .type{
            width:8.166em;
            font-size: 12px;
            height:2.167em;
            justify-content: center;
            border-radius: 0.25em;
        }
        .line{
			border-bottom: 1px solid #f4f4f4;
            padding:0.875rem 0 1em;
        }
        .type-select{
            color:#32a735;
            border: 1px solid #32a735;
        }
        .type-noSelect{
            color:#dedede;
            border: 1px solid #dedede;
        }
        .green{
            color:#32a735;
        }
        input{
            border: 1px solid #fff;
        }
        input:focus{    outline: none;     border: 1px solid #fff; }
    </style>
</head>
<body style="background-color: #f0f0f2;margin:0;padding:1.625em 0.625em">
    <div class="form">
        <form action="/nuonuo/token/commitNuonuo" method="post" enctype="multipart/form-data">
            <input type="hidden" name="code" value="${code}">
            <div style="padding:0 0 0 1.125em;">
                <div style="display: flex;align-items: center;padding:1.375em 0 1.25em;border-bottom: 1px solid #f4f4f4;">
                    <div class="left-title">发票类型</div>
                    <div id="oneone" class="type flex type-noSelect" style="margin-right:0.75em" onclick="oneshow();">个人</div>
                    <div id="comcom" class="type flex type-select" onclick="companyshow();">单位</div>
                </div>

                <!-- 个人 -->
                <div id="one1" class="line flex" style="padding:1.625em 0;display: none">
                    <div class="left-title">抬头名称</div>
                    <div style="flex:1">
                        <input type="text" name="personname" placeholder="姓名(必填)" style="width:100%;">
                    </div>
                </div>
                <div id="one2" class="line flex" style="display: none">
                    <div class="left-title">邮箱</div>
                    <input type="text" name="personmail" placeholder="个人联系邮箱(必填)">
                </div>
                <div  id="one3" class="line flex" style="display: none">
                    <div class="left-title">手机号</div>
                    <input type="number" name="personmobile" placeholder="(必填)">
                </div>

                 <!-- 单位 -->
                <div id="company1" class="line flex" style="padding:1.625em 0;">
                    <div class="left-title">抬头名称</div>
                    <div style="flex:1">
                        <input type="text" name="companyname" placeholder="单位名称(必填)" style="width:100%;">
                    </div>
                    <%--<div class="green" style="margin-right: 1.875em;width:4em">选择抬头</div>--%>
                </div>
                <div id="company2" class="line flex">
                    <div class="left-title">税号</div>
                    <input type="number" name="companytaxnum" placeholder="15-20位(必填)">
                </div>
                <div id="company3" class="line flex">
                    <div class="left-title">单位地址</div>
                    <input type="text" name="companyaddress" placeholder="单位地址信息 (必填)">
                </div>
                <div id="company4" class="line flex">
                    <div class="left-title">电话号码</div>
                    <input type="number" name="companyphone" placeholder="电话号码(必填)">
                </div>
                <div id="company5" class="line flex">
                    <div class="left-title">开户银行</div>
                    <input type="text" name="companybankname" placeholder="开户银行名称(必填)">
                </div>
                <div id="company6" class="line flex">
                    <div class="left-title">银行账号</div>
                    <input type="number" name="companybanknum" placeholder="银行账号号码 (必填)">
                </div>
                <div id="company7" class="line flex">
                    <div class="left-title">邮箱</div>
                    <input type="text" name="companymail" placeholder="(必填)">
                </div>
                <div id="company8" class="line flex">
                    <div class="left-title">手机号</div>
                    <input type="number" name="companymobile" placeholder="(必填)">
                </div>
            </div>
            <div style="padding:1.875em 1.125em 0.25em 1.125em;">
                <input class="btn" type="submit" value="申请开票">
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    function oneshow(){
        $("#oneone").css('color','#32a735');
        $("#oneone").css('border','1px solid #32a735');
        $("#comcom").css('color','#dedede');
        $("#comcom").css('border','1px solid #dedede');
        $("#one1").show();
        $("#one2").show();
        $("#one3").show();
        $("#company1").hide();
        $("#company2").hide();
        $("#company3").hide();
        $("#company4").hide();
        $("#company5").hide();
        $("#company6").hide();
        $("#company7").hide();
        $("#company8").hide();
        $("input[name='companyname']").attr('value','');
        $("input[name='companytaxnum']").attr('value','');
        $("input[name='companyaddress']").attr('value','');
        $("input[name='companyphone']").attr('value','');
        $("input[name='companybankname']").attr('value','');
        $("input[name='companybanknum']").attr('value','');
        $("input[name='companymail']").attr('value','');
        $("input[name='companymobile']").attr('value','');
    }

    function companyshow(){
        $("#comcom").css('color','#32a735');
        $("#comcom").css('border','1px solid #32a735');
        $("#oneone").css('color','#dedede');
        $("#oneone").css('border','1px solid #dedede');
        $("#one1").hide();
        $("#one2").hide();
        $("#one3").hide();
        $("#company1").show();
        $("#company2").show();
        $("#company3").show();
        $("#company4").show();
        $("#company5").show();
        $("#company6").show();
        $("#company7").show();
        $("#company8").show();
        $("input[name='personname']").attr('value','');
        $("input[name='personmail']").attr('value','');
        $("input[name='personmobile']").attr('value','');
    }
</script>
</html>