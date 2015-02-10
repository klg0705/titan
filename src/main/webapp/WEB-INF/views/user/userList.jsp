<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setBundle basename="messages/resources" var="siteRes" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message bundle="${ siteRes }" key="user.management" /></title>
</head>

<body>
  <c:if test="${not empty message}">
    <div id="message">${message}</div>
  </c:if>
  <table id="contentTable">
    <thead>
      <tr>
        <th>姓名</th>
        <th>电子邮件</th>
        <th>手机号</th>
        <th>创建时间</th>
        <th>管理</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${pageResult.content}" var="user">
        <tr>
          <td><a href="${ctx}/user/update/${user.id}">${user.username}</a></td>
          <td>${user.email}</td>
          <td>${user.mobilePhoneNumber}</td>
          <td><spring:eval expression="user.createDate" /></td>
          <td><a href="${ctx}/user/delete/${user.id}">删除</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <tags:pagination page="${pageResult}"/>

  <div>
    <a class="btn" href="${ctx}/user/create">创建</a>
  </div>
</body>
</html>
