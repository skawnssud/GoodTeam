<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${viewAll }" var="list">
	<p>id: ${list.id}</p>
	<p>role: ${list.role}</p>
	<p>nick: ${list.nick}</p>
	<p>account: ${list.account}</p>
	<hr>
</c:forEach>