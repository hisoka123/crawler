<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %><%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %><% response.setStatus(200);Logger logger = LoggerFactory.getLogger("500.jsp");logger.error(exception.getMessage(), exception);%>{
  "error": {
    "errorCode": "500",
    "errorName": "HTTP Status 500",
    "errorMsg": "Request processing failed; nested exception is <%=exception.toString()%>"
  }
}