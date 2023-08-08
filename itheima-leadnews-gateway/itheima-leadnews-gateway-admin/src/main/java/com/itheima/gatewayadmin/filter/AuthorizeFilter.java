package com.itheima.gatewayadmin.filter;

import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.AppJwtUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求与响应对象
        ServerHttpRequest request =  exchange.getRequest();
        ServerHttpResponse response =  exchange.getResponse();

        // 获取请求路径
        String path = request.getURI().getPath();
        log.debug("请求路径:{}", path);
        // 判断请求路径是否为白名单（登录请求）
        if (path.startsWith("/admin/login")) {
            // 如果是登录请求则放行
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");
        if (!StringUtils.isEmpty(token)) {
            if (SystemConstants.JWT_OK == AppJwtUtil.verifyToken(token)) {
                return chain.filter(exchange);
            }
        }
        // 无效返回401
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
