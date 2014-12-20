<%@ taglib uri="c" prefix="c" %>
<%@ taglib uri="c-rt" prefix="c-rt" %>
<%@ taglib uri="fmt" prefix="fmt" %>
<%@ taglib uri="fmt-rt" prefix="fmt-rt" %>
<%@ taglib uri="jp" prefix="jp" %>
<c:if test="${webConfig['language']=='zh'}"> 
  <fmt:setLocale value="zh"/>
</c:if>
<c:if test="${webConfig['language']=='en'}"> 
  <fmt:setLocale value="en"/>
</c:if> 
