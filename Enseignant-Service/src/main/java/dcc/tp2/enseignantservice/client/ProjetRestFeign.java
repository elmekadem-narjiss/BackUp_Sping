package dcc.tp2.enseignantservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJET-SERVICE")
public interface ProjetRestFeign {

    @CircuitBreaker(name = "projetCircuitBreaker", fallbackMethod = "fallbackNbProjetEnseignant")
    @Retry(name = "projetRetry")
    @RateLimiter(name = "projetRateLimiter")
    @GetMapping("/Projets/Enseignant/{id}")
    Long nb_Projet_Enseignant(@PathVariable Long id);

    // Méthode de secours en cas d'échec
    default Long fallbackNbProjetEnseignant(Long id, Throwable throwable) {
        // Retour d'une valeur par défaut ou gestion d'erreur
        return 0L; // Retourner une valeur par défaut
    }
}
