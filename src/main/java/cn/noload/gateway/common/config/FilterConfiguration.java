package cn.noload.gateway.common.config;

import cn.noload.gateway.common.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caohao
 * @version 2018/5/20
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
