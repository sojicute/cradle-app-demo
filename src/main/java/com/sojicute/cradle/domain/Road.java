package com.sojicute.cradle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "roads")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "road", cascade = CascadeType.ALL)
    private List<Element> elements = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Road(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
