package cn.noload.gateway.common.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul 鉴权 Filter
 *
 * @author caohao
 * @version 2018/5/20
 */
public class TokenFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * 过滤器类型, 决定过滤器在请求的哪个周期中执行
     *
     * Zuul 中默认定义了4种不同生命周期的过滤器类型:
     * pre: 可以在请求被路由之前调用<br />
     * routing: 在路由请求时被调用<br />
     * post: 在 routing 和 error 过滤器之后被调用<br />
     * error: 在处理请求时发生错误被调用
     * */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否需要被执行
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getHeader("access_token");
        if(StringUtils.isBlank(token)) {
            logger.warn("access token is null");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
        } else {
            logger.info("access token is ok");
        }
        return null;
    }
}
