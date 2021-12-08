package com.sojicute.cradle.domain;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "elements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "road_id")
    private Road road;

}
