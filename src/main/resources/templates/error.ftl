<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, minimum-scale=1, width=device-width">
    <title>Error 404 (Not Found)!!</title>
    <style>
        * {
            margin: 0;
            padding: 0
        }

        html, code {
            font: 15px/22px arial, sans-serif
        }

        html {
            background: #fff;
            color: #222;
            padding: 15px
        }

        body {
            margin: 7% auto 0;
            max-width: 390px;
            min-height: 180px;
            padding: 30px 0 15px
        }

        * > body {
            background: url(${ctx}/static/img/robot.png) 100% 5px no-repeat;
            padding-right: 205px
        }

        p {
            margin: 11px 0 22px;
            overflow: hidden
        }

        ins {
            color: #777;
            text-decoration: none
        }

        a img {
            border: 0
        }

        @media screen and (max-width: 772px) {
            body {
                background: none;
                margin-top: 0;
                max-width: none;
                padding-right: 0
            }
        }

        #logo {
            background: url(${ctx}/static/img/googlelogo_color_150x54dp2.png) no-repeat;
            margin-left: -5px
        }

        @media only screen and (min-resolution: 192dpi) {
            #logo {
                background: url(${ctx}/static/img/googlelogo_color_150x54dp.png) no-repeat 0% 0%/100% 100%;
                -moz-border-image: url(${ctx}/static/img/googlelogo_color_150x54dp.png) 0
            }
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 2) {
            #logo {
                background: url(${ctx}/static/img/googlelogo_color_150x54dp.png) no-repeat;
                -webkit-background-size: 100% 100%
            }
        }

        #logo {
            display: inline-block;
            height: 54px;
            width: 150px
        }
    </style>
</head>
<body><a href="${ctx}/"><span id="logo" aria-label="Google"></span></a>
<p><b>404.</b>
    <ins>That’s an error.</ins>
</p>
<p>The requested URL <code>/</code> was not found on this server.
    <ins>That’s all we know.</ins>
</p>
<script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        var url = window.location.href;
        $('code').html(url);
    })
</script>
</body>
</html>