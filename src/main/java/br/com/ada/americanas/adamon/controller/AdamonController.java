package br.com.ada.americanas.adamon.controller;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.service.AdamonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adamon")
public class AdamonController {

    @Autowired
    private AdamonService adamonService;
    @GetMapping
    public List<Adamon> findByAllAdmon(){
        return adamonService.findAllAdamons();
    }

    @PostMapping
    public Adamon createNewAdamon(Adamon adamon){
        return adamonService.createNewAdamon(adamon);
    }

}
