package master.webapp.controlador;

import master.webapp.entidades.Noticia;
import master.webapp.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portalWebFormula1")
public class NoticiaController {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @CrossOrigin
    @GetMapping("/noticias")
    public ResponseEntity<List<Noticia>> getAllNoticias() {
        return ResponseEntity.ok(noticiaRepositorio.findAll());
    }

    @CrossOrigin
    @GetMapping("/noticias/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
        return noticiaRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
