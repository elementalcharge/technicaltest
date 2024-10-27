package org.technicaltest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.technicaltest.dto.SpaceShipDTO;
import org.technicaltest.entity.SpaceShip;
import org.technicaltest.repository.SpaceShipRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpaceShipServiceTest {

    @Mock
    private SpaceShipRepository spaceShipRepository;

    @InjectMocks
    private SpaceShipService spaceShipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSpaceShips() {
        Pageable pageable = PageRequest.of(0, 10);
        List<SpaceShip> spaceShips = Arrays.asList(
                new SpaceShip("Enterprise", Arrays.asList("Star Trek")),
                new SpaceShip("Millennium Falcon", Arrays.asList("Star Wars"))
        );
        Page<SpaceShip> page = new PageImpl<>(spaceShips, pageable, spaceShips.size());

        when(spaceShipRepository.findAll(pageable)).thenReturn(page);

        Page<SpaceShip> result = spaceShipService.getAllSpaceShips(pageable);

        assertEquals(2, result.getContent().size());
        verify(spaceShipRepository).findAll(pageable);
    }

    @Test
    void getSpaceShipById() {
        Integer id = 1;
        SpaceShip spaceShip = new SpaceShip("Enterprise", Arrays.asList("Star Trek"));
        spaceShip.setId(id);

        when(spaceShipRepository.findById(id)).thenReturn(Optional.of(spaceShip));

        Optional<SpaceShipDTO> result = spaceShipService.getSpaceShipById(id);

        assertTrue(result.isPresent());
        assertEquals("Enterprise", result.get().getSpaceShipName());
        verify(spaceShipRepository).findById(id);
    }

    @Test
    void saveSpaceShip() {
        SpaceShipDTO dto = new SpaceShipDTO();
        dto.setSpaceShipName("Enterprise");
        dto.setMovies("Star Trek");

        SpaceShip entity = new SpaceShip("Enterprise", Arrays.asList("Star Trek"));
        entity.setId(1);

        when(spaceShipRepository.save(any(SpaceShip.class))).thenReturn(entity);

        SpaceShipDTO result = spaceShipService.saveSpaceShip(dto);

        assertNotNull(result);
        assertEquals("Enterprise", result.getSpaceShipName());
        assertEquals("Star Trek", result.getMovies());
        verify(spaceShipRepository).save(any(SpaceShip.class));
    }

    @Test
    void deleteSpaceShip() {
        Integer id = 1;

        spaceShipService.deleteSpaceShip(id);

        verify(spaceShipRepository).deleteById(id);
    }

    @Test
    void entityToDto() {
        SpaceShip entity = new SpaceShip("Enterprise", Arrays.asList("Star Trek", "Star Trek II"));
        entity.setId(1);

        SpaceShipDTO result = spaceShipService.entityToDto(entity);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Enterprise", result.getSpaceShipName());
        assertEquals("Star Trek;Star Trek II", result.getMovies());
    }

    @Test
    void dtoToEntity() {
        SpaceShipDTO dto = new SpaceShipDTO();
        dto.setId(1);
        dto.setSpaceShipName("Enterprise");
        dto.setMovies("Star Trek;Star Trek II");

        SpaceShip result = spaceShipService.dtoToEntity(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Enterprise", result.getName());
        assertEquals(2, result.getMovies().size());
        assertTrue(result.getMovies().contains("Star Trek"));
        assertTrue(result.getMovies().contains("Star Trek II"));
    }

    @Test
    void findSpaceShipsByNameContaining() {
        String name = "Enterprise";
        Pageable pageable = PageRequest.of(0, 10);
        List<SpaceShip> spaceShips = Arrays.asList(
                new SpaceShip("USS Enterprise", Arrays.asList("Star Trek")),
                new SpaceShip("Enterprise-D", Arrays.asList("Star Trek: TNG"))
        );
        Page<SpaceShip> page = new PageImpl<>(spaceShips, pageable, spaceShips.size());

        when(spaceShipRepository.findByNameContainingIgnoreCase(name, pageable)).thenReturn(page);

        Page<SpaceShipDTO> result = spaceShipService.findSpaceShipsByNameContaining(name, pageable);

        assertEquals(2, result.getContent().size());
        assertTrue(result.getContent().get(0).getSpaceShipName().contains("Enterprise"));
        assertTrue(result.getContent().get(1).getSpaceShipName().contains("Enterprise"));
        verify(spaceShipRepository).findByNameContainingIgnoreCase(name, pageable);
    }
}
