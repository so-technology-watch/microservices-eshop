package fr.sogeti.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 *
 * @author fduneau
 */
@Component
public class HeadersFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
        if(servletResponse instanceof HttpServletResponse){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            addHeader(response, "Access-Control-Allow-Origin", "*");
            addHeader(response, "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            addHeader(response, "Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
            if(servletRequest instanceof HttpServletRequest){
                String method = ((HttpServletRequest) servletRequest).getMethod();
                if(method.equals("OPTIONS")){
                    response.getOutputStream().println("OK");
                    return;
                }
            }
        }
        fc.doFilter(servletRequest, servletResponse);
    }
    
    /**
     * add a given header to the given response, if the header already exists, replace it's value
     * @param response the response on with we want to perform the push
     * @param header the header we want to add
     * @param value the value of the header
     */
    private void addHeader(HttpServletResponse response, String header, String value){
        if(!response.containsHeader(header)){
            response.addHeader(header, value);
        }else{
            response.setHeader(header, value);
        }
    }

    @Override
    public void destroy() {
    }
    
}
