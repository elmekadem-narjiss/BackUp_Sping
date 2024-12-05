package dcc.tp2.chercheurservice.client;


import dcc.tp2.chercheurservice.module.Enseignant;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENSEIGNANT-SERVICE")
public interface EnseignantRestFeign {

    @CircuitBreaker(name = "projetCircuitBreaker", fallbackMethod = "fallbackEnseignant")
    @Retry(name = "projetRetry")
    @RateLimiter(name = "projetRateLimiter")
    @GetMapping("/Enseignants/{id}")
    Enseignant Enseignant_ByID(@PathVariable Long id);

    // Méthode de secours en cas d'échec de la requête Feign
    default Enseignant fallbackEnseignant(Long id, Throwable throwable) {
        // Vous pouvez retourner un objet Enseignant par défaut ou null
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("Nom de secours");
        enseignant.setPrenom("Prénom de secours");
        return enseignant;
    }
}
