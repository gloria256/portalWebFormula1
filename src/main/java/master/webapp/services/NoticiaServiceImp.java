package master.webapp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import master.webapp.entidades.Noticia;
import master.webapp.repositorios.NoticiaRepositorio;

@Service
public class NoticiaServiceImp implements INoticiaService {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Override
    public List<Noticia> getAllNoticias() {
        return noticiaRepositorio.findAll();
    }

    @Override
    public Optional<Noticia> findNoticiaById(Integer id) {
        return noticiaRepositorio.findById(id);
    }

    @Override
    public Optional<Noticia> deleteNoticiaById(Integer id) {
        Optional<Noticia> noticia = noticiaRepositorio.findById(id);
        noticia.ifPresent(noticiaRepositorio::delete);
        return noticia;
    }

    @Override
    public Noticia addNoticia(Noticia noticia) {
        noticia.setDate(new Date());
        return noticiaRepositorio.save(noticia);
    }

}
