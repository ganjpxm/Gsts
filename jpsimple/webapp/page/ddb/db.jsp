<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test Database</title>
</head>
<body>
<%
    String username="young123_admin";
    String password="myweb456";
    String url="jdbc:mysql://50.31.138.79:3306/young123_ddb?useUnicode=true&amp;characterEncoding=utf-8";
    try {
    	Class.forName("com.mysql.jdbc.Driver");
    } catch(ClassNotFoundException msg) {
        out.println("Error loading driver:" + msg.getMessage());
    }
    try{
        Connection Conn = DriverManager.getConnection(url,username, password);
        Statement Stmt = Conn.createStatement();
        String query = "select now()";
        ResultSet res = Stmt.executeQuery(query);
        while(res.next()) {
        	out.println("Query result : "+res.getObject(1));
        }
    } catch(SQLException e) {
    	String err1Msg = e.getMessage();
%>
	<TD COLSPAN="2"><STRONG><EM>err1Msg = <%= err1Msg %></EM></STRONG></TD>
<%
	}
%>
</body>
</html>