package org.apache.shenyu.plugin.casdoor;

import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.plugin.api.ShenyuPlugin;
import org.apache.shenyu.plugin.api.ShenyuPluginChain;
import org.apache.shenyu.plugin.api.result.ShenyuResultEnum;
import org.apache.shenyu.plugin.api.result.ShenyuResultWrap;
import org.apache.shenyu.plugin.api.utils.WebFluxResultUtils;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.annotation.Resource;
import java.net.URI;

public class CasdoorPlugin implements ShenyuPlugin {

    @Resource
    private CasdoorAuthService casdoorAuthService;

    String withe[] = {"http://localhost:9195/http/hi","http://localhost:9195/favicon.ico"};

    @Override
    public reactor.core.publisher.Mono<Void> execute(org.springframework.web.server.ServerWebExchange exchange, ShenyuPluginChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token =  exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(token!=null){
            CasdoorUser casdoorUser = casdoorAuthService.parseJwtToken(token);
            if(casdoorUser!=null){
                return chain.execute(exchange);
            }
        }
        org.springframework.util.MultiValueMap<String, String> queryParams = request.getQueryParams();
        String code = queryParams.getFirst("code");
        String state = queryParams.getFirst("state");
        if(code!=null||state!=null){
            token = casdoorAuthService.getOAuthToken(code, state);
            System.out.println(token);
            if (token!=null){
                return chain.execute(exchange);
            }
        }
        Object error = ShenyuResultWrap.error(exchange, ShenyuResultEnum.ERROR_TOKEN);
        return WebFluxResultUtils.result(exchange, error);
    }

    @Override
    public int getOrder() {
        return PluginEnum.CASDOOR.getCode();
    }

    @Override
    public String named() {
        return PluginEnum.CASDOOR.getName();
    }

    @Override
    public boolean skip(org.springframework.web.server.ServerWebExchange exchange) {
        String uri = exchange.getRequest().getURI().toString();
        System.out.println(uri);
        for (int i=0;i<withe.length;i++){
            if(uri.equals(withe[i])){
                return true;
            }
        }
        return false;
    }
}