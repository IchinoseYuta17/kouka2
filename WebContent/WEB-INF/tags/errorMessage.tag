<%@ tag language="java" body-content="empty" %>
<%@ attribute name="fieldName" required="true" %>
<%@ attribute name="errorMessage" required="false" %>

<c:if test="${not empty param[fieldName] and empty param[fieldName]}">
    <span class="error">${errorMessage}</span>
</c:if>
<c:if test="${not empty requestScope[fieldName + 'Error']}">
    <span class="error">${requestScope[fieldName + 'Error']}</span>
</c:if>
