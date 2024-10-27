/*package org.technicaltest.repository;

import org.technicaltest.dto.SpaceShipDTO;
import org.technicaltest.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceShipDAO implements ISpaceShipDAO{

    private List<SpaceShipDTO> spacheShips;
    private static Integer DB_ID;

    public SpaceShipDAO() {
        this.spacheShips = Utils.getListFromJson("spaceShips.json");
        SpaceShipDAO.DB_ID = spacheShips.size() + 1;
    }



    @Override
    public SpaceShipDTO save(SpaceShipDTO sps) {
        if (sps.getId() == null){
            sps.setId(SpaceShipDAO.DB_ID);
            SpaceShipDAO.DB_ID++;
        }else if (this.exists(sps.getId())){
            this.delete(sps.getId());
        }
        spacheShips.add(sps);

        this.saveData();

        return sps;
    }

    @Override
    public boolean delete(Integer id) {
        if (!exists(id)) return false;

        spacheShips = spacheShips.stream()
                .filter(spaceship -> !spaceship.getId().equals(id))
                .collect(Collectors.toList());

        this.saveData();

        return !exists(id);
    }

    public boolean exists(Integer id) {
        return spacheShips.stream()
                .anyMatch(spaceship -> spaceship.getId().equals(id));
    }

    @Override
    public Optional<SpaceShipDTO> findById(Integer id) {
        return spacheShips.stream()
                .filter(stu -> stu.getId().equals(id))
                .findFirst();
    }

    private void saveData() {
        Utils.saveListInJson("users.json", this.spacheShips);
    }
}
*/