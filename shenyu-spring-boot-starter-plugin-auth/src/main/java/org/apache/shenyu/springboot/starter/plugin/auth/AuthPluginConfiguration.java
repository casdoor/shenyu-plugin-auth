package org.apache.shenyu.springboot.starter.plugin.auth;

import org.apache.shenyu.plugin.api.ShenyuPlugin;
import org.apache.shenyu.plugin.auth.AuthrPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"shenyu.plugins.auth.enabled"}, havingValue = "true", matchIfMissing = true)
public class AuthPluginConfiguration {

    @Bean
    public ShenyuPlugin contextPathPlugin() {
        return new AuthrPlugin();
    }
}
