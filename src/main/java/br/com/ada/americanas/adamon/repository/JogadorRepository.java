package br.com.ada.americanas.adamon.repository;

import br.com.ada.americanas.adamon.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {


}
