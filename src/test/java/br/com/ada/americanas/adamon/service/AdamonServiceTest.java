package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.repository.AdamonRepository;
import br.com.ada.americanas.adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdamonServiceTest {
    @InjectMocks
    AdamonService adamonService;

    @Mock
    AdamonRepository repository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void deveSucessoAoBuscarAdamons() {
        when(repository.findAll()).thenReturn(TesteUtils.obterAdamons());
        //execução
        List<Adamon> adamons = adamonService.findAllAdamons();
        //verificação
        Assertions.assertFalse(adamons.isEmpty());
    }

    @Test
    void deveSucessoAoBuscarAdamonsListaVazia() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        //execução
        List<Adamon> adamons = adamonService.findAllAdamons();
        //verificação
        assertTrue(adamons.isEmpty());
    }

    @Test
    void deveSucessoAoSalvarNovoAdamon() {
        //cenário
        Adamon adamonASerSalvo = new Adamon();
        adamonASerSalvo.setName("Alexamon");

        Adamon adamonSalvo = new Adamon();
        adamonSalvo.setId(1L);

        when(repository.save(Mockito.any(Adamon.class))).thenReturn(adamonSalvo);

        Adamon adamon = adamonService.createNewAdamon(adamonASerSalvo);

        Assertions.assertNotNull(adamon);
        Assertions.assertEquals(adamonSalvo.getId(), adamon.getId());
    }
}
