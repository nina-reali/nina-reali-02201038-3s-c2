package br.com.bandtec.provasprint2.controle;


import br.com.bandtec.provasprint2.dominio.Lutador;
import br.com.bandtec.provasprint2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid Lutador novoLutador) {
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }


    @GetMapping
    public ResponseEntity getLutador() {
        List<Lutador> lutadores = repository.findAll();
        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(repository.findByLutadorOrderByforcaGolpeAsc());


    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivos() {
        return ResponseEntity.status(200).body(repository.findByVivoTrue());
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.status(200).body(repository.findByVivoFalse());
    }
}
