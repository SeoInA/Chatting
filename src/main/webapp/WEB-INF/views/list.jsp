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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
<div>
	<p><a href="<%=request.getContextPath()%>/logout">로그아웃</a></p>
</div>
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
		<td>${u.userid}</td> 
		<td id="userName">${u.username}</td>
		<td>
			<a href="#" onclick="return onChat(${u.userid},'${u.username}');">채팅하기 </a>
 		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div> 

</div>

</main>
<jsp:include page="/WEB-INF/views/talk.jsp" />
</body>
<script>

	//채팅 하기 (방 만들기 또는 불러오기)
		function onChat(id,name){
			if("${sessionScope.ID}" != id){
			$.ajax({
				url:"createChat.do",
				data:{
					userName:"${sessionScope.Name}",
					userId:"${sessionScope.ID}",
					receiverId:id
				},
				type:"post",
				success:function(data){
					// 채팅방이 닫혀있고, 채팅 리스트가 닫혀있다면
		            if($('.chatContainer').hasClass("display-none") && $('.chatListContainer').hasClass('display-none')){
		            	// 리스트를 연다
		                $('.chatListContainer').toggleClass('display-none');
		             	// 채팅 방 목록을 불러온다.
		                getRoomList();		
		                // 해당 채팅 방으로 들어간다.
		                $('.userNameId:contains('+name+')').parent().trigger("click");
		            }
					// 채팅 리스트가 열려 있다면
		            else if(!$('.chatListContainer').hasClass('display-none')){
		                // 해당 채팅 방으로 들어간다.
		                $('.userNameId:contains('+name+')').parent().trigger("click");
		            }
		            else{
		            	alert("이미 채팅방이 열려 있습니다.");
		            }
				}
			});
			
            return false;
			}
		}
	</script>
</html>