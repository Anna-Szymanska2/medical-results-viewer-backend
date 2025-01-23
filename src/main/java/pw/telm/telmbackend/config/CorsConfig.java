package pw.telm.telmbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing).
 * Enables Web MVC and configures a CORS filter to handle cross-origin requests.
 */
@Configuration
@EnableWebMvc
public class CorsConfig {
    /**
     * Maximum age for the CORS configuration to be cached by clients.
     */
    private static final Long MAX_AGE = 3600L;

    /**
     * Order in which the CORS filter is registered within the filter chain.
     */
    private static final int CORS_FILTER_ORDER = -102;

    /**
     * Configures and registers a CORS filter bean with specific CORS settings.
     *
     * @return a {@link CorsFilter} instance containing the CORS filter configuration.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(), 
                HttpMethod.OPTIONS.name()
        ));

        config.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}