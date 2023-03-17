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
    <style>
        .form{
            padding:1.875em 1.125em 2em;
            background-color:#fff;
        }
        .flex{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .progress{
            padding:1em 0.3125em;
            border-bottom: 1px solid #f2f2f2;
            display: flex;
            align-items: center;
            font-weight: bold;
        }
        .img{
            width:1.875em;height:1.875em;margin-right: 1em;
        }
        .icon-box{
            width:1.25em;height:1.25em;margin-bottom: 0.25em;
        }
        .icon{
            width:0.625em;height:0.625em;border-radius: 50%;overflow: hidden;
        }
        .icon-selected{
            background-color: #32a735;box-shadow: 0 0 0 5px #d1f0d1;
        }
        .icon-selected-not{
            background-color: #ececec;
        }
        .btn {
            width: 100%;
            height: 3.143em;
            background-color: #32a735;
            border-radius:4px;
            border: none;
            color:#fff;
        }
        .thread{
            width:1px;background-color: #f1f1f1;
        }
        .title{
            font-weight: bold;margin-bottom: 0.625em;
        }
        .tips{
            font-size: 12px;color:#afafaf
        }
    </style>
</head>

<body style="background-color: #f0f0f2;margin:0;">
    <div class="form">
        <div class="progress">
            <image src="/dui.png" class="img"></image>
            <%--<image src="./dui.png" class="img"></image>--%>
            已提交开票申请
        </div>
        <div style="display: flex;padding:1.5em 0">
            <div style="display: flex;flex-direction: column;align-items: center;margin-right: 1em;">
                <div class="flex icon-box">
                    <div class="icon icon-selected"></div>
                </div>
                <div style="height:4.375em;margin-bottom: 0.25em;" class="thread"></div>
                <div class="flex icon-box">
                    <div class="icon icon-selected-not"></div>
                </div>
                <div style="height:1.5em" class="thread"></div>
            </div>
            <div>
                <div style="margin-bottom: 3.25em;">
                    <div class="title">提交申请</div>
                    <div class="tips">商户将在5分钟内为你开票</div>
                </div>
                <div>
                    <div style="color:#949494" class="title">开票完成</div>
                    <div class="tips">收到发票后，可以在填入的邮箱中进行查看！</div>
                </div>
            </div>
        </div>
        
        <div>
            <input class="btn" type="button" value="完成">
        </div>
    </div>
</body>
<script type="text/javascript">
    function closejsp(){
        window.close();
    }
</script>
</html>