package fr.sogeti.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fduneau
 */
public class PreFilter extends ZuulFilter {

    private static final Logger LOG = Logger.getLogger(PreFilter.class.getName());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        LOG.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        
        return null;
    }

}
