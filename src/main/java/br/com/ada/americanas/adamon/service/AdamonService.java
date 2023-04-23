package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.repository.AdamonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdamonService {

    @Autowired
    private AdamonRepository adamonRepository;

    public List<Adamon> findAllAdamons(){
        return adamonRepository.findAll();
    }

    public Adamon finById(Long id){
        return adamonRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado cliente"));
    }
    public Adamon createNewAdamon(Adamon adamon){
        return adamonRepository.save(adamon);
    }
}
