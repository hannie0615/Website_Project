package camper.project.service;

import  camper.project.domain.Camp;
import camper.project.domain.CampImage;
import camper.project.domain.Room;
import camper.project.repository.CampRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CampService {

    CampRepositoryInterface repository;

    @Autowired
    public CampService(CampRepositoryInterface repository) {
        this.repository = repository;
    }

    public void register(Camp c) {
        repository.saveCamp(c);
    }

    public Camp findByName(String name) {
        return repository.findCampByName(name);
    }

    public List<Camp> findByLocation(String location) {
        return repository.findCampByLocation(location);

    }

    public List<Room> findRoomByCampId(int campId) { return repository.findRoomByCampId(campId); }

    public Camp findCampByCampId(int campId) { return repository.findCampByCampId(campId); }

    public void registerRoom(Room r) {
        repository.saveRoom(r);
    }

    public List<Camp> findBySellerId(String sellerId) {
        return repository.findCampBySellerId(sellerId);
    }

    public void uploadImage(CampImage ci) {
        repository.saveImage(ci);
    }

    public void deleteCamp(int campId) { repository.deleteCamp(campId); }
}

