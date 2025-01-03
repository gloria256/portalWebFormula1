package master.webapp.controlador;

import master.webapp.entidades.Noticia;
import master.webapp.services.NoticiaServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portalWebFormula1")
public class NoticiaController {

    @Autowired
    private NoticiaServiceImp noticiaServiceImp;

    @CrossOrigin
    @GetMapping("/noticias")
    public ResponseEntity<List<Noticia>> getAllNoticias() {
        return ResponseEntity.ok(noticiaServiceImp.getAllNoticias());
    }

    @CrossOrigin
    @GetMapping("/noticias/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
        return noticiaServiceImp.findNoticiaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @DeleteMapping("/noticias/{id}")
    public ResponseEntity<Noticia> deleteNoticiaById(@PathVariable Integer id) {
        return noticiaServiceImp.deleteNoticiaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping("/noticias")
    public ResponseEntity<Noticia> addNoticia(@RequestBody Noticia noticia) {
        return ResponseEntity.ok(noticiaServiceImp.addNoticia(noticia));
    }

    @CrossOrigin
    @PutMapping("/noticias/{id}")
    public ResponseEntity<Noticia> updateNoticia(@RequestBody Noticia noticia) {
        return ResponseEntity.ok(noticiaServiceImp.updateNoticia(noticia));
    }

}
