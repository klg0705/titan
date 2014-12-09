<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:formatDate value="${simple.birthday}" var="birthday" pattern="yyyy-MM-dd" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple Entity Management</title>
</head>

<body>
  <form id="inputForm" action="${ctx}/simple/${action}" method="post">
    <input type="hidden" name="id" value="${simple.id}" />
    <fieldset>
      <legend>
        <small>Simple Entity Management</small>
      </legend>
      <div>
        <label>Name:</label>
        <div>
          <input type="text" id="entity_name" name="name" value="${simple.name}" />
        </div>
      </div>
      <div>
        <label>Age:</label>
        <div>
          <input type="text" id="entity_age" name="age" value="${simple.age}" />
        </div>
      </div>
      <div>
        <label>Birthday:</label>
        <div>
          <input type="text" id="entity_birthday" name="birthday" value="${birthday}" />
        </div>
      </div>
      <div>
        <label>Gender:</label>
        <div>
          <input type="text" id="entity_gender" name="gender" value="${simple.gender}" />
        </div>
      </div>
      <div>
        <input id="submit_btn" type="submit" value="提交" />&nbsp; <input id="cancel_btn" type="button" value="返回" onclick="history.back();" />
      </div>
    </fieldset>
  </form>
</body>
</html>
