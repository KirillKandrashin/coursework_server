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
@Table(name = "publishers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "publisherList", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Book> bookList;
}
