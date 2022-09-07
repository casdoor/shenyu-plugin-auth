package org.apache.shenyu.springboot.starter.plugin.casdoor;

import org.apache.shenyu.plugin.api.ShenyuPlugin;
import org.apache.shenyu.plugin.casdoor.CasdoorPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"shenyu.plugins.casdoor.enabled"}, havingValue = "true", matchIfMissing = true)
public class CasdoorPluginConfiguration {

    @Bean
    public ShenyuPlugin contextPathPlugin() {
        return new CasdoorPlugin();
    }
}
