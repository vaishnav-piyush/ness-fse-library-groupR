package com.ness.services.gatewayservice.filters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/*
 * This filter gets invoked on each request pass through
 * Zuul. The following checks are performed in the filter
 * 1. Confirm the Bearer token is available on each request. 
 * 2. If not then send out a standard error response. Except for Create profile request
 * 3. Set the CTFS Custom Header (X-CTFS-JWT-Username) on each request
 * 4. Set the X-Forwarded-For on each request
 * 
 */
public class GatewayServicePreFilter extends ZuulFilter
{

    private List<String> bypassAuthURIs = Arrays.asList("/fse/v1/<>/<>");

    private static final Logger LOG = LoggerFactory.getLogger(GatewayServicePreFilter.class);

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();

        //Get the source ip and add to the request Headers
        String xforwardedfor = ctx.getRequest().getRemoteAddr();
        ctx.addZuulRequestHeader("X-Forwarded-For", xforwardedfor);
        LOG.debug("GatewayServicePreFilter X-Forwarded-For =" + xforwardedfor);

        // Check if URI can progress without Bearer Token
        if (bypassAuthURIs.contains(ctx.getRequest().getRequestURI())) {
            LOG.debug("GatewayServicePreFilter bypassed for URI=" + ctx.getRequest().getRequestURI());
            return null;
        }

        /*// Check for Bearer Token presence
        String authHeader = ctx.getRequest().getHeader("Authorization");
        LOG.debug("GatewayServicePreFilter Auth Header =" + authHeader);
        if (authHeader == null) {
            sendUnauthorizedResponse(ctx);
            return null;
        }*/
        return null;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public int filterOrder()
    {
        return 0;
    }

    @Override
    public String filterType()
    {
        return "pre";
    }

    private boolean validateBearerToken(String token, String key)
    {
        try
        {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            if (!(claims.getSubject().equals("enrolment-claim") || claims.getSubject().equals("access-claim"))) return false;
            if (!claims.getIssuer().equals("Canadian Tire Financial Services")) return false;
        }
        catch(Exception e)
        {
            LOG.warn(" Unable to parse claims for token " + token + ". Error is " + e.getMessage());
            return false;
        }
        return true;
    }

    private void sendUnauthorizedResponse(RequestContext ctx)
    {
        ctx.setSendZuulResponse(false);
        ctx.getResponse().setContentType("'application/json'");
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        String response = "{\"statusCode\": \"40100\", \"description\": \"Indicates that authorization bearer token is not present\"}";
        ctx.setResponseBody(response);
    }    
 }
