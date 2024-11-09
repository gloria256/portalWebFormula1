package master.webapp.repositorios;

import master.webapp.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {
    List<Noticia> findByTituloContainingIgnoreCase(String titulo);
}
