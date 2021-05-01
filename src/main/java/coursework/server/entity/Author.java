package coursework.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "authors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //фио или компания(Marvel и тп)

    @ManyToMany(mappedBy = "authorList", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Book> bookList;
}
