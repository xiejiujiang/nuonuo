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
    <title>JSP收款单核销明细导入</title>
    <style type="text/css">
        #dd:hover{
            background-color: #D9D9D9;
        }
    </style>
</head>
    <body>
        <div style="width: 100%;height: 300px;box-shadow: 2px 2px 10px #666;border-left: 2px solid #5B9BD5;border-right: 2px solid #5B9BD5;border-top: 2px solid #5B9BD5">
            <div style="width: 100%;height: 25px; background-color: #BDD7EE;text-align: center;border-bottom: 2px solid #5B9BD5;font-weight: bold;line-height: 25px;">收款单核销明细导入</div>
            <div style="display: none; position: absolute;margin-top: 10px;margin-left: 20px;width: 150px;height: 100px;border: 1px solid #CCC">
                <div style="position: absolute;text-align: center;height: 30px;width: 150px;line-height: 30px;">数据路径</div>
                <div style="position: absolute;text-align: center;height: 30px;width: 150px;line-height: 30px;margin-top: 33px"></div>
                <div style="position: absolute;text-align: center;height: 30px;width: 150px;line-height: 30px;margin-top: 66px">导入转换</div>
            </div>
            <div id="ptttext" style="position: absolute;margin-top: 10px;margin-left: 20px;background-color: #D9D9D9;width: 300px;height: 30px;text-align: center;line-height: 30px;color: #5B9BD5"></div>
            <div style="position: absolute;margin-top: 44px;margin-left: 20px;width: 300px;height: 30px;text-align: center;line-height: 30px">
                导入表格前请仔细检查表格内容是否填写完整
            </div>
            <div style="position: absolute;margin-top: 79px;margin-left: 20px;background-color: #D9D9D9;width: 300px;height: 30px"></div>
            <form method="POST" enctype="multipart/form-data" action="http://39.99.224.54:9997/nuonuo/token/createSK">
                <input type="file" name="file" style="display: none" id="upload"></input>
                <input type="button" value="选择文件" style="cursor: pointer;height: 30px;width: 80px;position: absolute;margin-top: 10px;margin-left: 350px" onclick="test1();">
                <input type="button" value="取消" style="cursor: pointer;height: 30px;width: 80px;position: absolute;margin-top: 10px;margin-left: 470px" onclick="clear1();">
                <div  style="height: 30px;width: 198px;position: absolute;margin-top: 44px;margin-left: 250px;line-height: 30px;text-align: center;color:red"></div>
                <input id="cwriteid" type="submit" value="开始导入" style="cursor: pointer;height: 30px;width: 200px;position: absolute;margin-top: 79px;margin-left: 350px" onclick="cwrite();">
            </form>
        </div>
        <div style="height: 20px;width: 100%;border: 1px solid #CCC;margin-top: 20px;background-color: #D9D9D9"></div>
    </body>
    <script>
        $(function (){
            //订单excel选择框
            $("#upload").on("change",function () {
                var file = $("#upload")[0].files[0];
                var filename = file.name;
                if(filename != '' && filename.length > 20){
                    filename = filename.substring(0,12)+'....';
                    $("#ptttext").text(filename);
                }else{
                    $("#ptttext").text(filename);
                }
            });

            //匹配表选择框
            $("#updatePTT").on("change",function () {
                var file = $("#updatePTT")[0].files[0];
                var filename = file.name;
                if(filename != '' && filename.length > 20){
                    filename = filename.substring(0,12)+'....';
                    $("#pptext").text(filename);
                }else{
                    $("#pptext").text(filename);
                }
            });
        });

        function test1(){
            $("#upload").click();
        }

        function clear1(){
            var test1 = document.getElementById("upload");
            test1.value = '';
            $("#ptttext").text("");
        }

        function test2(){
            $("#updatePTT").click();
        }

        function clear2(){
            var test2 = document.getElementById("updatePTT");
            test2.value = '';
            $("#pptext").text("");
        }

        function sumbitform2(){
            //ajax 提交 form2 表单的内容
            var test2 = document.getElementById("updatePTT");
            if(test2.value == ''){
                alert('必选要先选择更新的excel匹配表');
            }else{
                //提交form表单
                var formData = new FormData($( "#form2")[0]);
                $.ajax({
                    url: 'http://39.99.224.54:9997/nuonuo/token/createSK' ,
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (returndata) {
                        var restr = returndata;
                        if(restr == '0000'){
                            alert('更新成功！');
                        }else{
                            alert('更新失败！');
                        }
                    },
                    error: function (returndata) {
                        alert('提交失败，请重试！');
                    }
                });
            }
        }

        function cwrite(){
            $("#cwriteid").val("正在核销中，请等待！。。。。。");
        }
    </script>
</html>