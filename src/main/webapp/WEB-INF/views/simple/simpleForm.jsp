<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:formatDate value="${simple.birthday}" var="birthday" pattern="yyyy-MM-dd" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple Entity Management</title>
</head>
<body>
  <form:form id="inputForm" action="${ctx}/simple/${action}" method="post" modelAttribute="simple">
    <form:errors path="*" />
    <form:hidden path="id" />
    <fieldset>
      <legend>
        <small>Simple Entity Management</small>
      </legend>
      <div>
        <form:label path="name">Name:</form:label>
        <div>
          <form:input path="name" />
          <form:errors path="name" />
        </div>
      </div>
      <div>
        <form:label path="age">Age:</form:label>
        <div>
          <form:input path="age" />
          <form:errors path="age" />
        </div>
      </div>
      <div>
        <form:label path="birthday">Birthday:</form:label>
        <div>
          <form:input path="birthday" />
        </div>
      </div>
      <div>
        <form:label path="gender">Gender:</form:label>
        <div>
          <form:input path="gender" />
        </div>
      </div>
      <div>
        <input id="submit_btn" type="submit" value="提交" />&nbsp; <input id="cancel_btn" type="button" value="返回" onclick="history.back();" />
      </div>
    </fieldset>
  </form:form>
</body>
</html>
