<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple Entity</title>
</head>

<body>
  <c:if test="${not empty message}">
    <div id="message">${message}</div>
  </c:if>
  <table id="contentTable">
    <thead>
      <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>管理</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${queryResult.resultList}" var="simple">
        <tr>
          <td><a href="${ctx}/simple/update/${simple.id}">${simple.name}</a></td>
          <td>${simple.age}</td>
          <td><spring:eval expression="simple.birthday" /></td>
          <td>${simple.gender}</td>
          <td><a href="${ctx}/simple/delete/${simple.id}">删除</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <tags:mypagination page="${queryResult}"/>

  <div>
    <a class="btn" href="${ctx}/simple/create">创建</a>
  </div>
</body>
</html>
