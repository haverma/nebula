<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Trends</title>
</head>
<body>
<h1>Twitter trends</h1>
<h2> Details as submitted successfully </h2>
<h4> Location : ${Location} </h4>
<h4> Time :${Time} </h4>
<c:forEach begin="0" end="${fn:length(Trends) }" var="index">
   <tr>
      <td><c:out value="${Trends[index]}"/></br>
      </td>
   </tr>
</c:forEach>
</body>
</html>