package ru.netology.springboottask1.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboottask1.profile.DevProfile;
import ru.netology.springboottask1.profile.ProductionProfile;
import ru.netology.springboottask1.profile.SystemProfile;

@Configuration
public class TaskConfiguration {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true",  matchIfMissing = false)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
