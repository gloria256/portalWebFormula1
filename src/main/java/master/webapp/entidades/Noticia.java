package master.webapp.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "permalink", nullable = false, length = 100)
    private String permalink;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "imagen")
    private byte[] imagen;

    @Lob
    @Column(name = "texto", nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(name = "date", nullable = false)
    private Date date;

}