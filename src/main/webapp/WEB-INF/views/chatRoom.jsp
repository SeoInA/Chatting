<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/sockjs.js?ver=5"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/stomp.js?ver=1"></script>
<style>
</style>
</head>

<body>

<c:set var="login" value='<%=session.getAttribute("login")%>' />



 	<div class="col-12 row justify-content-center align-items-center my-5 ">
		<a href=""><img src="../../../resources/image/AlmomLogo.png"
			alt="Almom Logo" width="180px" class="img-fluid" /></a>
	</div>
	<div class="col-12">
		<div class="col-2" style="float: left">
			<span> 목록 </span>
		</div>
		<div class="col-8" style="float: left; text-align: center;">
		상대방 님과 대화</div>
		<div class="col-2" style="float: right">
			<span> 닫기 </span>
		</div>



	</div>
	<div class="col-12" style="margin-top: 40px; clear: both;">
		<div class="col-10"
			style="margin: 20px auto; text-align: center; color: white; background-color: #01D1FE; border: 1px solid #01D1FE; padding: 10px 10px; border-radius: 8px;">
			수업 일정과 강의 내용에 대해 문의해보세요. <br>(연락처 문의 또는 직접 알려주는 것은 불가)
		</div>

	</div>
	<!-- 채팅 내용 -->
	<div class="col-12">
		<div class="col-11"
			style="margin: 0 auto; border: 1px solid #01D1FE; height: 400px; border-radius: 10px; overflow:scroll" id = "chatArea">

			<div id="chatMessageArea" style = "margin-top : 10px; margin-left:10px;"></div>

			<c:forEach var="chatRoom" items="${chatHistory}">
			<p>
            	<span id="chatRoomSenderName">${chatRoom.senderName}</span><br>
            	<span id="chatRoomContent">${chatRoom.content}</span><br>
            	<span id="chatRoomSendTime">${chatRoom.sendTime}</span><br>
        	</p>    
            </c:forEach>

		</div>
	</div>

	<!-- 채팅 입력창 -->
	<div class="col-12" style="margin-top: 20px; margin-bottom: 15px;">
		<div class="col-12" style="float: left">
			<textarea class="form-control"
				style="border: 1px solid #01D1FE; height: 65px; float: left; width: 80%"
				placeholder="Enter ..." id = "message">


				</textarea>
			<c:set var="chatRoom" value="${chatRoom}"/>
			<span
				style="float: right; width: 18%; height: 65px; text-align: center; background-color: #01D1FE; border-radius: 5px;">
				<a style="margin-top: 30px; text-align: center; color: white; font-weight: bold;" id = "sendBtn"><br>전송</a>
				<input type="hidden" value="${login.getName()}" id="senderName" name="senderName"/>
                <input type="hidden" value="${login.getId()}" id="sender_id" name="sender_id"/>
               	<input type="hidden" value="${chatRoom.receiver_id}" id="receiver_id" name="receiver_id"/>
              	<input type="hidden" value="${chatRoom.receiverName}" id="receiverName" name="receiverName"/>                        
               	<input type="hidden" value="${chatRoom.id}" id="id" namd="room_id"/>    
			</span>
		</div>

	</div>


<img id="profileImg" class="img-fluid"
					src="/displayFile?fileName=${userImage}&directory=profile" style = "display:none">
<input type="text" id="nickname" value = "${chatRoom.receiverName}" style = "display:none">
 <input type="button" id="enterBtn" value="입장" style = "display:none">
 <input type="button" id="exitBtn" value="나가기" style = "display:none">
 
<script type="text/javascript">
    	 
    	
        var stompClient = null;
        var senderName = $('#senderName').val();
        var sender_id = $('#sender_id').val();
        var receiverName = $('#receiverName').val();
        var receiver_id = $('#receiver_id').val(); 
        var id = $('#id').val(); /* $('#id').val(); */
        
        <%-- invoke when DOM(Documents Object Model; HTML(<head>, <body>...etc) is ready --%>
        $(document).ready(connect());
        
        function connect() {
            <%-- map URL using SockJS--%>
            var socket = new SockJS('<%=request.getContextPath()%>/chat');
            var url = '/user/' + id + '/queue/message';
            <%-- webSocket 대신 SockJS을 사용하므로 Stomp.client()가 아닌 Stomp.over()를 사용함--%>
            stompClient = Stomp.over(socket);
            
            <%-- connect(header, connectCallback(==연결에 성공하면 실행되는 메서드))--%>
            stompClient.connect({}, function() {
                <%-- url: 채팅방 참여자들에게 공유되는 경로--%>
                <%-- callback(function()): 클라이언트가 서버(Controller broker)로부터 메시지를 수신했을 때(== STOMP send()가 실행되었을 때) 실행 --%>
                stompClient.subscribe(url, function(output) {
                    console.log("message받아올게!");
                    <%-- JSP <body>에 append할 메시지 contents--%>
                    showBroadcastMessage(createTextNode(JSON.parse(output.body)));
                });
                }, 
                    <%-- connect() 에러 발생 시 실행--%>
                        function (err) {
                            alert('error' + err);
            });
 
        };
        
        <%-- WebSocket broker 경로로 JSON 타입 메시지데이터를 전송함 --%>
        function sendBroadcast(json) {
            /* stompClient.send("/app/chat", {}, JSON.stringify(json)); */
        	stompClient.send("/app/chat", {}, JSON.stringify(json)); 
        }
        
        <%-- 보내기 버튼 클릭시 실행되는 메서드--%>
        function send() {
            var content = $('#message').val();
            sendBroadcast({
                'id': id,
                'sender_id': sender_id, 'receiver_id': receiver_id,
                'senderName': senderName, 
                'receiverName': receiverName,
                'content': content
                });
            $('#message').val("");
        }
        
        <%-- 메시지 입력 창에서 Enter키가 보내기와 연동되도록 설정 --%>
        var inputMessage = document.getElementById('message'); 
        inputMessage.addEventListener('keyup', function enterSend(event) {
            
            if (event.keyCode === null) {
                event.preventDefault();
            }
            
            if (event.keyCode === 13) {
                send();
            }
        });
        
        <%-- 입력한 메시지를 HTML 형태로 가공 --%>
        function createTextNode(messageObj) {
            console.log("createTextNode");
            console.log("messageObj: " + messageObj.content);
            return '<p><div class="row alert alert-info"><div class="col_8">' +
            messageObj.senderName +
            '</div><div class="col_4 text-right">' +
            messageObj.content+
            '</div><div>[' +
            messageObj.sendTime +
            ']</div></p>';
        }
        
        <%-- HTML 형태의 메시지를 화면에 출력해줌 --%>
        <%-- 해당되는 id 태그의 모든 하위 내용들을 message가 추가된 내용으로 갱신해줌 --%>
        function showBroadcastMessage(message) {
            $("#content").html($("#content").html() + message);
        }
    </script>

</body>
</html>