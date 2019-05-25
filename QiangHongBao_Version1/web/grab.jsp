<%--
  Created by IntelliJ IDEA.
  User: lzh
  Date: 2018/12/17
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>参数</title>
    <script type="text/javascript"
        src="https://code.jquery.com/jquery-3.2.0.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            var max=30000;
            for(var i=1;i<=max;i++){
                $.post({
                    url:
                    "./userRedPacket/grabRedPacket.do?redPacketId=1&userId="+i,
                    success:function (result) {

                    }
                })
            }
        })
    </script>
</head>
<body>

</body>
</html>
