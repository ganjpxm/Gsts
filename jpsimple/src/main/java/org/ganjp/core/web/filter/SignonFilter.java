package org.ganjp.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignonFilter implements Filter {
	String LOGIN_PAGE="login_signon.jsp";
    protected FilterConfig filterConfig;

	//过滤处理的方法
    public void doFilter(final ServletRequest req,final ServletResponse res,FilterChain chain)
   		throws IOException,ServletException {
   		HttpServletRequest hreq = (HttpServletRequest)req;
   		HttpServletResponse hres = (HttpServletResponse)res;
   		HttpSession session = hreq.getSession();
   		String isLogin="";
   		try {
        	isLogin=(String)session.getAttribute("isLogin");        
            if (isLogin.equals("true")) {
               System.out.println("在SignonFilter中验证通过");
               chain.doFilter(req,res);//验证成功，继续处理
            } else {
               //验证不成功，让用户登录。
               hres.sendRedirect(LOGIN_PAGE);                        
               System.out.println("被SignonFilter拦截一个未认证的请求");
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
    public void setFilterConfig(final FilterConfig filterConfig) {
    	this.filterConfig=filterConfig;
	}
    //销毁过滤器
    public void destroy() {
    	this.filterConfig=null;
    }
    //初始化过滤器,和一般的Servlet一样，它也可以获得初始参数。
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;       
    }
}

