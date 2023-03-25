<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>订单确认</title>
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
    <style type="text/css">
        .form{
            background-color:#fff;
            font-size: 14px;
            border-radius: 0 0 0.5em 0.5em;
        }
        .title{
            border-bottom:1px solid #e3e4e6;
            padding:1em 0;display: flex;
            justify-content: center;
            align-items: center;
            color:#d3d4d6;
            background-color: #f7f8fa;
            font-size: 12px;
        }
        .line{
            display: flex;
            align-items: center;
            padding:0.875em 0.625em 1em;
            border: 1px dotted #f2f2f2;
        }
        .left-title{
            margin-right: 3.375em;
            color:#aaaaaa;
        }
        .flex{
            display: flex;flex-direction: column;justify-content: center;align-items: center;
        }
        .btn {
            width: 20.286em;
            height: 2.286em;
            background-color: #1aac1b;
            border-radius:20px;
            border: none;
            color:#fff;
        }
        .type{
            width:71px;
            height:21px;
            color:#32a735;
            border-radius: 3px;
            border: 1px solid #32a735;
        }
        .green{
            color:#32a735;
        }
        input{
            border: 1px solid #fff;
        }
        input:focus{    outline: none;     border: 1px solid #fff; }
        .help{
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fff;
            padding:0.857em;font-size: 10px;
        }
        body{
            background-color: #32a735;
            min-height:100vh;
            margin:0;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
    </style>
</head>
<body>
    <div style="padding:5.125em 2.5% 0;">
        <div class="form">
            <div class="title">
                <span class="green">订单确认></span><span>申请开票></span><span>发票保存</span>
            </div>
            <form action="/nuonuo/token/goCommit?code=${redetailMap.code}" method="post" enctype="multipart/form-data">
                <div class="line">
                    <div class="left-title">店铺名称 :</div>
                    <span>${redetailMap.storename}</span>
                </div>
                <div class="line">
                    <div class="left-title">交易序号 :</div>
                    <span>${redetailMap.code}</span>
                </div>
                <div class="line">
                    <div class="left-title"> 交易日期 :</div>
                    <span>${redetailMap.voucherdate}</span>
                </div>
                <div class="line">
                    <div class="left-title"> 合计金额 :</div>
                    <span>${redetailMap.totalAmount}</span><span>元</span>
                </div>
                <div style="height:2.25em"></div>
                <div style="border-top:1px solid #e4e4e4;padding:0.625em 0 2.625em" class="flex">
                    <input class="btn" type="submit" value="确认" onclick="cc();">
                </div>
            </form>
        </div>
    </div>
</body>

<script type="text/javascript">
    function cc(){
        //alert('走了1！');
        //location.href="/tste";
    }
</script>
</html>