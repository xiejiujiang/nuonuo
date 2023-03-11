<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <image src="./dui.png" class="img"></image>
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
                    <div class="tips">收到发票后，可到卡包-我的票券查看</div>
                </div>
            </div>
        </div>
        
        <div>
            <input class="btn" type="button" value="完成">
        </div>
    </div>
</body>

</html>