package camper.project.repository;

import camper.project.domain.Camp;
import camper.project.domain.CampImage;
import camper.project.domain.Room;

import java.util.List;

public interface CampRepositoryInterface {
    void saveCamp(Camp c);
    Camp findCampByName(String name);
    List<Camp> findCampByLocation(String location);
    List<Camp> findCampBySellerId(String sellerId);
    void saveImage(CampImage campImage);

    Camp findCampByCampId(int campId);

    List<CampImage> findImg(String name);

    void saveRoom(Room r);

    List<Room> findRoomByCampId(int id);

    void deleteCamp(int campId);



}
