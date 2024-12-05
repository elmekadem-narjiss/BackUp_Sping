package dcc.tp2.projetservice.client;

import dcc.tp2.projetservice.module.Chercheur;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CHERCHEUR-SERVICE")
public interface ChercheurRestFeign {

    @CircuitBreaker(name = "chercheurCircuitBreaker", fallbackMethod = "fallbackGetChercheurByID")
    @Retry(name = "chercheurRetry")
    @RateLimiter(name = "chercheurRateLimiter")
    @Bulkhead(name = "chercheurBulkhead")  // Ajout du Bulkhead ici
    @GetMapping("/Chercheurs/{id}")
    Chercheur GetChercheurByID(@PathVariable Long id);

    // Méthode de secours en cas d'échec
    default Chercheur fallbackGetChercheurByID(Long id, Throwable throwable) {
        // Retour d'une valeur par défaut ou gestion d'erreur
        return new Chercheur(); // Retourner une instance vide de Chercheur ou une valeur appropriée
    }
}
