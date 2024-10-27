package org.technicaltest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.technicaltest.Annotations.LogNegativeId;
import org.technicaltest.dto.SpaceShipDTO;
import org.technicaltest.entity.SpaceShip;
import org.technicaltest.repository.SpaceShipRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceShipService {

    private final SpaceShipRepository spaceShipRepository;

    @Autowired
    public SpaceShipService(SpaceShipRepository spaceShipRepository) {
        this.spaceShipRepository = spaceShipRepository;
    }

    public Page<SpaceShip> getAllSpaceShips(Pageable pageable) {
        return spaceShipRepository.findAll(pageable);
    }

    @LogNegativeId
    public Optional<SpaceShipDTO> getSpaceShipById(Integer id) {
        return spaceShipRepository.findById(id)
                .map(this::entityToDto);
    }

    @LogNegativeId
    public SpaceShipDTO saveSpaceShip(SpaceShipDTO spaceShipDTO) {
        SpaceShip entity = dtoToEntity(spaceShipDTO);
        SpaceShip savedEntity = spaceShipRepository.save(entity);
        return entityToDto(savedEntity);
    }

    @LogNegativeId
    public void deleteSpaceShip(Integer id) {
        spaceShipRepository.deleteById(id);
    }
    

    public SpaceShipDTO entityToDto(SpaceShip spaceShip) {
        if (spaceShip == null) {
            return null;
        }

        SpaceShipDTO dto = new SpaceShipDTO();
        dto.setId(spaceShip.getId());
        dto.setSpaceShipName(spaceShip.getName());

        if (spaceShip.getMovies() != null && !spaceShip.getMovies().isEmpty()) {
            String moviesString = String.join(";", spaceShip.getMovies());
            dto.setMovies(moviesString);
        }

        return dto;
    }

    public SpaceShip dtoToEntity(SpaceShipDTO dto) {
        if (dto == null) {
            return null;
        }

        List<String> moviesList = dto.getMovies() != null && !dto.getMovies().isEmpty() 
            ? Arrays.asList(dto.getMovies().split(";")) 
            : new ArrayList<>();

        SpaceShip spaceShip = new SpaceShip(dto.getSpaceShipName(), moviesList);
        spaceShip.setId(dto.getId());

        return spaceShip;
    }
    public Page<SpaceShipDTO> findSpaceShipsByNameContaining(String name, Pageable pageable) {
        Page<SpaceShip> spaceShips = spaceShipRepository.findByNameContainingIgnoreCase(name, pageable);
        return spaceShips.map(this::entityToDto);
    }


}
