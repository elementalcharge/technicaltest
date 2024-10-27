package org.technicaltest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SpaceShipDTO {

    public @NotBlank(message = "El nombre de la nave no puede estar vacío.") @Pattern(regexp = "([A-Z]|[0-9])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la nave espacial debe comenzar con mayúscula.") @Size(max = 50, message = "La longitud del nombre de la nave no puede superar los 50 caracteres.") String getSpaceShipName() {
                return spaceShipName;
        }

        public void setSpaceShipName(@NotBlank(message = "El nombre de la nave no puede estar vacío.") @Pattern(regexp = "([A-Z]|[0-9])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la nave espacial debe comenzar con mayúscula.") @Size(max = 50, message = "La longitud del nombre de la nave no puede superar los 50 caracteres.") String spaceShipName) {
                this.spaceShipName = spaceShipName;
        }

        Integer id;

        @NotBlank(message = "El nombre de la nave no puede estar vacío.")
        @Pattern(regexp="([A-Z]|[0-9])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la nave espacial debe comenzar con mayúscula.")
        @Size(max = 50, message = "La longitud del nombre de la nave no puede superar los 50 caracteres.")
        String spaceShipName;

        private String movies;

        public String getMovies() {
            return movies;
        }

        public void setMovies(String movies) {
            this.movies = movies;
        }

    }
