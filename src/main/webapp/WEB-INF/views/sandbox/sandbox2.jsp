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
<script type="text/javascript" language="javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/jquery.validate.messages_zh.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/jquery.serializejson.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/jquery-ui.js"></script>
<link rel="stylesheet" href="${ctx}/css/jquery-ui.css">
</head>

<body>
  <input type="button" id="btn1" value="get all" />
  <input type="button" id="btn2" value="clear all" />
  <div id="div1"></div>

  <div id="div2">
    <form id="form1">
      <fieldset>
        <legend>
          <small>Simple Entity Management</small>
        </legend>
        <div>
          <label for="name">Name:</label><em>*</em> <input type="text" id="name" name="name" />
        </div>
        <div>
          <label for="age">Age:</label><em>*</em> <input type="text" id="age" name="age" />
        </div>
        <div>
          <label for="birthday">Birthday:</label> <input type="text" id="birthday" name="birthday" />
        </div>
        <div>
          <label for="gender">Gender:</label> <select id="gender" name="gender">
            <option value="MALE" selected="selected">男</option>
            <option value="FEMALE">女</option>
          </select>
        </div>
        <div>
          <input id="submit_btn" type="submit" value="Create" />
        </div>
      </fieldset>
    </form>
  </div>
</body>
</html>

<script type="text/javascript" language="javascript">
    $(function() {
        $("#btn1").click(listSimple);
        $("#btn2").click(function() {
            $("#div1").empty();
        });
        $("#birthday").datepicker({
            showOn: "button",
            buttonImage: "image/calendar.gif",
            buttonImageOnly: true,
            buttonText: "Select date",
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+1"
        });
        $("#form1").validate({
            rules : {
                name : {
                    required : true,
                    minlength : 2
                },
                age : {
                    required : true,
                    range : [0, 150]
                },
                birthday : {
                    dateISO : true
                }
            },
            submitHandler : function(form) {
                var json = $("#form1").serializeJSON();
                $.ajax({
                    type : "POST",
                    url : "${ctx}/api/v1/simple",
                    contentType : "application/json",
                    data : JSON.stringify(json),
                    dataType : "text",
                    success : function(data) {
                        listSimple();
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.responseText);
                    }
                });
            }
        });
    });
    $(function() {
        listSimple();
    });

    function listSimple() {
        $.get("${ctx}/api/v1/simple", function(data, textStatus) {
            $("#div1").empty();
            var html = "";
            $.each(data, function(index, simple) {
                html += "<div><p>" + simple['id'] + ", " + simple.name + '<input type="button" value="delete" onclick="deleteSimple(' + simple.id
                        + ')" /></p></div>';
            });
            $("#div1").html(html);
        });
    }

    function deleteSimple(id) {
        if (window.confirm("确定删除该条记录吗？")) {
            $.ajax({
                type : "DELETE",
                url : "${ctx}/api/v1/simple/" + id,
                success : function(data) {
                    listSimple();
                }
            });
        }
    };
</script>