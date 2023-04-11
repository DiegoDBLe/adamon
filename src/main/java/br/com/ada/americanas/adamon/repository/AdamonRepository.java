package br.com.ada.americanas.adamon.repository;

import br.com.ada.americanas.adamon.model.Adamon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdamonRepository extends JpaRepository<Adamon, Long> {
}
