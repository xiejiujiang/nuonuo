<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>申请开票</title>
    <style>
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
        <form action="#" method="post" enctype="multipart/form-data">
            <div style="background-color: #fafafa;padding:1.5em 0 1.625em 1.125em;">
                <div class="flex" style="margin-bottom: 1.375em;">
                    <div class="left-title">订单号</div>
                    <span>020170716505663500908</span>
                </div>
                <div class="flex" style="margin-bottom: 1.375em;">
                    <div class="left-title">金额</div>
                    <span>6.20</span>
                    <span>元</span>
                </div>
                <div class="flex">
                    <div class="left-title">下单时间</div>
                    <span>2023.02.01 09:07:48</span>
                </div>
            </div>
            <div style="padding:0 0 0 1.125em;">
                <div style="display: flex;align-items: center;padding:1.375em 0 1.25em;border-bottom: 1px solid #f4f4f4;">
                    <div class="left-title">发票类型</div>
                    <div class="type flex type-noSelect" style="margin-right:0.75em">个人</div>
                    <div class="type flex type-select">单位</div>
                </div>
                <!-- 个人 -->
                <!-- <div class="line flex" style="padding:1.625em 0;">
                    <div class="left-title">抬头名称</div>
                    <div style="flex:1">
                        <input type="text" name="" placeholder="名称" style="width:100%;">
                    </div>
                    <div class="green" style="margin-right: 1.875em;width:4em">选择抬头</div>
                </div>
                <div class="line flex">
                    <div class="left-title">邮箱</div>
                    <input type="text" name="" placeholder="个人联系邮箱">
                </div>
                <div class="line flex">
                    <div class="left-title">手机号</div>
                    <input type="number" name="" placeholder="(选填)">
                </div> -->
                 <!-- 单位 -->
                <div class="line flex" style="padding:1.625em 0;">
                    <div class="left-title">抬头名称</div>
                    <div style="flex:1">
                        <input type="text" name="" placeholder="单位名称" style="width:100%;">
                    </div>
                    <div class="green" style="margin-right: 1.875em;width:4em">选择抬头</div>
                </div>
                <div class="line flex">
                    <div class="left-title">税号</div>
                    <input type="number" name="" placeholder="15-20位">
                </div>
                <div class="line flex">
                    <div class="left-title">单位地址</div>
                    <input type="text" name="" placeholder="单位地址信息 (选填)">
                </div>
                <div class="line flex">
                    <div class="left-title">电话号码</div>
                    <input type="number" name="" placeholder="电话号码(选填)">
                </div>
                <div class="line flex">
                    <div class="left-title">开户银行</div>
                    <input type="text" name="" placeholder="开户银行名称(选填)">
                </div>
                <div class="line flex">
                    <div class="left-title">银行账号</div>
                    <input type="number" name="" placeholder="银行账号号码 (选填)">
                </div>
                <div class="line flex">
                    <div class="left-title">邮箱</div>
                    <input type="text" name="" placeholder="(选填)">
                </div>
                <div class="line flex">
                    <div class="left-title">手机号</div><input type="number" name="" placeholder="(选填)">
                </div>
            </div>
            <div style="padding:1.875em 1.125em 0.25em 1.125em;">
                <input class="btn" type="submit" value="申请开票">
            </div>
            
        </form>
        
    </div>
</body>

</html>