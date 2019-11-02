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
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(false);
        filter.setIncludeClientInfo(true);
        filter.setBeforeMessagePrefix("\n\n---------------------------------------------------------------------------REQUEST-------------------------------------------------------------------- \n\n");
        filter.setAfterMessageSuffix("\n\n--------------------------------------------------------------------------END OF REQUEST--------------------------------------------------------------- \n\n");
        return filter;
    }
}
