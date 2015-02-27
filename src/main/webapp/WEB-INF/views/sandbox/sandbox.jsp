<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sandbox</title>
<script type="text/javascript" language="javascript" src="${ctx}/js/jquery.js"></script>
</head>

<body>
  <input type="button" id="btn1" value="textToText" />
  <input type="button" id="btn2" value="textToJson" />
  <input type="button" id="btn3" value="jsonToJson" />
  <input type="button" id="btn4" value="jsonToText" />
  <input type="button" id="btn5" value="jsonToList" />
  <input type="button" id="btn6" value="jsonToMap" />
  <br />
  <input type="button" id="btn11" value="cacheExpires0" />
  <input type="button" id="btn12" value="cacheExpires1min" />
  <input type="button" id="btn13" value="cacheControlNoCache" />
  <input type="button" id="btn14" value="cacheControlMaxAge60sec" />
  <input type="button" id="btn15" value="cacheLastModified" />
  <input type="button" id="btn16" value="cacheEtag" />
  <div id="div1"></div>

</body>
</html>

<script type="text/javascript" language="javascript">
    $(function() {
        $("#btn1").click(function() {
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case1",
                contentType : "text/plain",
                data : "klg",
                dataType : "text",
                success : function(data) {
                    alert(data);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn2").click(function() {
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case2",
                contentType : "text/plain",
                data : "gordon",
                dataType : "json",
                success : function(data) {
                    alert(data.name);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn3").click(function() {
            var json = {
                name : "klg"
            };
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case3",
                contentType : "application/json",
                data : JSON.stringify(json),
                dataType : "json",
                success : function(data) {
                    alert(data.name);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn4").click(function(event) {
            var json = {
                name : "klg",
                age : 33
            };
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case4",
                contentType : "application/json",
                data : JSON.stringify(json),
                dataType : "text",
                success : function(data) {
                    alert(data);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn5").click(function(event) {
            var json = {
                name : "klg",
                age : 33
            };
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case5",
                contentType : "application/json",
                data : JSON.stringify(json),
                dataType : "json",
                success : function(data) {
                    alert(data[1].name);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn6").click(function(event) {
            var json = {
                name : "klg",
                age : 33,
                gender : "MALE"
            };
            $.ajax({
                type : "POST",
                url : "${ctx}/api/v1/sandbox/case6",
                contentType : "application/json",
                data : JSON.stringify(json),
                dataType : "json",
                success : function(data) {
                    alert(data.userName);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        });

        $("#btn11").click(function() {
            $.get("${ctx}/api/v1/sandbox/case11", {
                name : "klg"
            }, function(data, textStatus) {
                $("#div1").html(data);
            });
        });

        $("#btn12").click(function() {
            $.get("${ctx}/api/v1/sandbox/case12", {
                name : "klg"
            }, function(data, textStatus) {
                $("#div1").html(data);
            });
        });

        $("#btn13").click(function() {
            $.get("${ctx}/api/v1/sandbox/case13", {
                name : "klg"
            }, function(data, textStatus) {
                $("#div1").html(data);
            });
        });

        $("#btn14").click(function() {
            $.get("${ctx}/api/v1/sandbox/case14", {
                name : "klg"
            }, function(data, textStatus) {
                $("#div1").html(data);
            });
        });

        $("#btn15").click(function() {
            $.ajax({
                type : "GET",
                url : "${ctx}/api/v1/sandbox/case15",
                data : {
                    name : "klg"
                },
                ifModified : true,
                success : function(data) {
                    $("#div1").html(data);
                }
            });
        });

        $("#btn16").click(function() {
            $.ajax({
                type : "GET",
                url : "${ctx}/api/v1/sandbox/case16",
                data : {
                    name : "klg"
                },
                ifModified : true,
                success : function(data) {
                    $("#div1").html(data);
                }
            });
        });

    });
</script>