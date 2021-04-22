<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- jsp directives 사용됨. -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- jsp directives, tag library 사용됨. -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List </title>

<style>
	.table tbodies {width:60%;}
	.table thead {background-color: #d69fa9;}
	.alink {color: #d69fa9;}
	.titlelink {color: black;}
	#stateB {
		background-color: #d69fa9;
		border: #d69fa9;
		float:right;
	}
     @import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);

 	 * {font-family: 'NanumSquare', sans-serif !important;}   
</style>
</head>
<body>
<main>
<div id="headTitle"><h2>전산전자공학부 공지 </h2></div>

<div class="board_section">
<div class="table-responsive" data-pattern="priority-columns">
<table cellspacing="0" id="tech-companies-1" class="table table-small-font table-bordered table-striped">
<thead>
<tr>
	<th>NO</th>
	<th>이름 </th>
	<th>쪽지보내기 </th>
</tr>

</thead>

<tbody class="tbodies">
<c:forEach items="${list}" var="u">
	<tr>
		<td>${u.id}</td> 
		<td>${u.name}</td>
		<td>
			<button type='button' class='btn mb-md-0 mb-2 btn-outline iconButton' onClick = "location.href='<%=request.getContextPath()%>/chatMessage/${u.id}'"><span class='tooltiptext'>쪽지</span></button>
 		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div> 

</div>

</main>

</body>
</html>