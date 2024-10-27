package org.technicaltest.entity;

import java.util.List;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "spaceships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpaceShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(name = "spaceship_movies", joinColumns = @JoinColumn(name = "spaceship_id"))
    @Column(name = "movie")
    private List<String> movies;

    public SpaceShip(String name, List<String> movies) {
        this.name = name;
        this.movies = movies;
    }
}
