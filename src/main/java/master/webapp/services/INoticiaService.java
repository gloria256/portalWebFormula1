package master.webapp.services;


import java.util.List;
import java.util.Optional;

import master.webapp.entidades.Noticia;

public interface INoticiaService {
    List<Noticia> getAllNoticias();
    Optional<Noticia> findNoticiaById(Integer id);
    Optional<Noticia> deleteNoticiaById(Integer id);
    Noticia addNoticia(Noticia noticia);
    Noticia updateNoticia(Noticia noticia);
}
