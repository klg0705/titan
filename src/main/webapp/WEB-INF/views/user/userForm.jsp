<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setBundle basename="messages/resources" var="siteRes" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message bundle="${ siteRes }" key="user.management" /></title>
</head>
<body>
  <form:form id="inputForm" action="${ctx}/user/${action}" method="post" modelAttribute="user">
    <form:errors path="*" />
    <form:hidden path="id" />
    <fieldset>
      <legend>
        <small><fmt:message bundle="${ siteRes }" key="user.management" /></small>
      </legend>
      <div>
        <form:label path="username">姓名：</form:label>
        <div>
          <form:input path="username" />
          <form:errors path="username" />
        </div>
      </div>
      <div>
        <form:label path="email">电子邮件：</form:label>
        <div>
          <form:input path="email" />
          <form:errors path="email" />
        </div>
      </div>
      <div>
        <form:label path="mobilePhoneNumber">手机号：</form:label>
        <div>
          <form:input path="mobilePhoneNumber" />
          <form:errors path="mobilePhoneNumber" />
        </div>
      </div>
      <c:if test="${ action == 'create' }">
      <div>
        <form:label path="password">密码：</form:label>
        <div>
          <form:input path="password" />
          <form:errors path="password" />
        </div>
      </div>
      </c:if>
      <div>
        <input id="submit_btn" type="submit" value="提交" />&nbsp; <input id="cancel_btn" type="button" value="返回" onclick="history.back();" />
      </div>
    </fieldset>
  </form:form>
</body>
</html>
