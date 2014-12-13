<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="study.gordon.titan.common.entity.QueryResult" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px;
}

.pagination li {
	display: inline;
}

.pagination li a,.pagination li span {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	color: #428bca;
	text-decoration: none;
	background-color: #fff;
	border: 1px solid #ddd;
}

.pagination li a:hover,.pagination li span:hover,.pagination li a:focus,.pagination li span:focus
	{
	color: #2a6496;
	background-color: #eee;
	border-color: #ddd;
}

.pagination .active a,.pagination .active span,.pagination .active a:hover,.pagination .active span:hover,.pagination .active a:focus,.pagination .active span:focus
	{
	z-index: 2;
	color: #fff;
	cursor: default;
	background-color: #428bca;
	border-color: #428bca;
}

.pagination .disabled span,.pagination .disabled span:hover,.pagination .disabled span:focus,.pagination .disabled a,.pagination .disabled a:hover,.pagination .disabled a:focus
	{
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
	border-color: #ddd;
}
</style>

<c:set var="displayRange" value="3" />
<c:set var="current" value="${page.page}" />
<c:set var="begin" value="${current - displayRange}" />
<c:if test="${begin < 1}">
  <c:set var="begin" value="${1}" />
</c:if>
<c:set var="end" value="${current + displayRange}" />
<c:if test="${end > page.totalPage}">
  <c:set var="end" value="${page.totalPage}" />
</c:if>

<div class="pagination">
  <ul>
    <c:choose>
      <c:when test="${page.firstPage}">
        <li class="disabled"><a href="#">&lt;&lt;</a></li>
        <li class="disabled"><a href="#">&lt;</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="?page=1">&lt;&lt;</a></li>
        <li><a href="?page=${current - 1}">&lt;</a></li>
      </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${begin}" end="${end}">
      <c:choose>
        <c:when test="${i == current}">
          <li class="active"><a href="?page=${i}">${i}</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="?page=${i}">${i}</a></li>
        </c:otherwise>
      </c:choose>
    </c:forEach>

    <c:choose>
      <c:when test="${page.lastPage}">
        <li class="disabled"><a href="#">&gt;</a></li>
        <li class="disabled"><a href="#">&gt;&gt;</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="?page=${current + 1}">&gt;</a></li>
        <li><a href="?page=${page.totalPage}">&gt;&gt;</a></li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>
