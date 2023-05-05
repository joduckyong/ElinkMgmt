<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>KCB 휴대폰 본인확인 서비스</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script type="text/javascript">
	function request(){
		document.form1.action = "<c:out value='${POPUP_URL}'/>";
		document.form1.method = "post";
		document.form1.submit();
	}
</script>
</head>

<body>
	<form name="form1">
		<input type="hidden" name="tc" value="kcb.oknm.online.safehscert.popup.cmd.P931_CertChoiceCmd"/>	<!-- 변경불가-->
		<input type="hidden" name="cp_cd" value="<c:out value='${CP_CD}'/>">	<!-- 회원사코드 -->
		<input type="hidden" name="mdl_tkn" value="<c:out value='${MDL_TKN}'/>">	<!-- 토큰 --> 
		<input type="hidden" name="type" value="<c:out value='${TYPE}'/>">	<!-- 토큰 --> 
		<input type="hidden" name="target_id" value="">	
	</form>
</body>
<script>
<c:choose>
	<c:when test="${SUCC eq true}">
		request();
	</c:when>
	<c:otherwise>
		alert("<c:out value='${RSLT_CD}'/> : <c:out value='${RSLT_MSG}'/>"); 
		self.close(); 
	</c:otherwise>
</c:choose>
</script>
</html>
