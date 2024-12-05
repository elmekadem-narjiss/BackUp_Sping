package dcc.tp2.enseignantservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CHERCHEUR-SERVICE")
public interface ChercheurRestFeign {

    @CircuitBreaker(name = "chercheurCircuitBreaker", fallbackMethod = "fallbackNbChercheurEnseignant")
    @Retry(name = "chercheurRetry")
    @RateLimiter(name = "chercheurRateLimiter")
    @GetMapping("/Chercheurs/Enseignant/{id}")
    Long nb_chercheur_Enseignant(@PathVariable Long id);

    // Méthode de secours en cas d'échec
    default Long fallbackNbChercheurEnseignant(Long id, Throwable throwable) {
        // Retour d'une valeur par défaut ou gestion d'erreur
        return 0L; // Retourner une valeur par défaut
    }
}
