package br.com.ada.americanas.adamon.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_jogador")
@Data
public class Jogador {
    @Id
    @Column(name = "id_jogador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Column
    private String password;

    @Column
    private BigDecimal saldo;

    @ManyToMany
    @JoinTable(name = "Jogador_Adamons",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdAdamons")})
    private List<Adamon> adamons = new ArrayList<>();

    public Jogador() {
        saldo = new BigDecimal(100);
    }
}
