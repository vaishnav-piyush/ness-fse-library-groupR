package com.ness.services.gatewayservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/*
 * This class implements a post filter that ensure that
 * http headers are set as required
 */
public class GatewayServicePostFilter extends ZuulFilter
{

    /*
     * Set the Cache-Control header to no-store
     * (non-Javadoc)
     * @see com.netflix.zuul.IZuulFilter#run()
     */
    @Override
    public Object run()
    {
        RequestContext.getCurrentContext().getResponse().addHeader("Cache-Control", "no-store");
        return null;
    }

    /*
     * true for all requests
     * (non-Javadoc)
     * @see com.netflix.zuul.IZuulFilter#shouldFilter()
     */
    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    /*
     * Filter priority
     * (non-Javadoc)
     * @see com.netflix.zuul.ZuulFilter#filterOrder()
     */
    @Override
    public int filterOrder()
    {
        return 0;
    }

    /*
     * Filter Type set to post
     * (non-Javadoc)
     * @see com.netflix.zuul.ZuulFilter#filterType()
     */
    @Override
    public String filterType()
    {
        return "post";
    }

}
