<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>KCB 휴대폰 본인확인 서비스</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script type="text/javascript">
	function request(){
		opener.document.form1.action = "http://localhost:9091/ev/join2?test=test";
		opener.document.form1.method = "get";
		opener.document.form1.submit();
	}
</script>
</head>

<body>
	<form name="form1">
		<input type="hidden" name="CP_CD" value="<c:out value='${CP_CD}'/>">	
		<input type="hidden" name="TX_SEQ_NO" value="<c:out value='${TX_SEQ_NO}'/>">	
		<input type="hidden" name="RSLT_CD" value="<c:out value='${RSLT_CD}'/>">	
		<input type="hidden" name="RSLT_MSG" value="<c:out value='${RSLT_MSG}'/>">	
		<input type="hidden" name="RSLT_NAME" value="<c:out value='${RSLT_NAME}'/>">	
		<input type="hidden" name="RSLT_BIRTHDAY" value="<c:out value='${RSLT_BIRTHDAY}'/>">	
		<input type="hidden" name="RSLT_SEX_CD" value="<c:out value='${RSLT_SEX_CD}'/>">	
		<input type="hidden" name="RSLT_NTV_FRNR_CD" value="<c:out value='${RSLT_NTV_FRNR_CD}'/>">	
		<input type="hidden" name="DI" value="<c:out value='${DI}'/>">	
		<input type="hidden" name="CI" value="<c:out value='${CI}'/>">	
		<input type="hidden" name="CI_UPDATE" value="<c:out value='${CI_UPDATE}'/>">	
		<input type="hidden" name="TEL_COM_CD" value="<c:out value='${TEL_COM_CD}'/>">	
		<input type="hidden" name="TEL_NO" value="<c:out value='${TEL_NO}'/>">	
		<input type="hidden" name="RETURN_MSG" value="<c:out value='${RETURN_MSG}'/>">	
	</form>
</body>

<script>
<c:choose>
	<c:when test="${'B000' eq RSLT_CD}">
		alert('본인인증성공');
//		request();
		self.close(); 
	</c:when>
	<c:otherwise>
		alert("본인인증실패 : <c:out value='${RSLT_CD}'/> : <c:out value='${RSLT_MSG}'/>"); 
//		request();
		self.close(); 
	</c:otherwise>
</c:choose>
</script>
</html>
