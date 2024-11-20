package com.example.gatewayservice.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import com.example.gatewayservice.conf.RsaConfig;
@Configuration
@EnableWebFluxSecurity
//@EnableWebSecurity
public class SecConfig {

  private RsaConfig rsaConfig ;

  public SecConfig(RsaConfig rsaConfig) {
    this.rsaConfig = rsaConfig;
  }


  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
    httpSecurity
      .csrf(csrf -> csrf.disable())
      .authorizeExchange(auth -> auth.pathMatchers("/ENSEIGNANT-SERVICE/Enseignants/email/{id}").permitAll())
      .authorizeExchange(auth -> auth.pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/email/{id}").permitAll())
      .authorizeExchange(auth -> auth.pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/**").hasAuthority("Chercheur"))
      .authorizeExchange(auth -> auth.pathMatchers("/CHERCHEURSERVICE/Enseignants/**").hasAuthority("Enseignant"))
      .authorizeExchange(auth -> auth.anyExchange().authenticated())
      .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    return httpSecurity.build();
  }
  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    // Ensure that rsaConfig.publicKey() returns a valid PublicKey
    return NimbusReactiveJwtDecoder.withPublicKey(rsaConfig.publicKey()).build();
  }
}
