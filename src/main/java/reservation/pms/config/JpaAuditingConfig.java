package reservation.pms.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

	private final String ANONYMOUS_USER = "anonymousUser";

    @Bean
    public AuditorAware<String> auditorAware(){
            return () -> {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if(authentication == null || authentication.getPrincipal().equals(ANONYMOUS_USER)){
//                    return Optional.empty();
//                }
//                User user = (User) authentication.getPrincipal();
//                return Optional.of(user.getUsername());
            	
            	return Optional.of("test");
            };
    }
}