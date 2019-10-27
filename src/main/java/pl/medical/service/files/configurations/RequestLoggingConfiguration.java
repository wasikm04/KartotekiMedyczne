package pl.medical.service.files.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfiguration {
    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setMaxPayloadLength(10000);
        filter.setIncludePayload(true);
        filter.setIncludeQueryString(true);
        //filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setBeforeMessagePrefix("REQUEST  : ");
        filter.setAfterMessagePrefix("END OF REQUEST  : ");
        return filter;
    }
}
