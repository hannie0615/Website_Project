package camper.project.repository;

import camper.project.domain.Camp;
import camper.project.domain.CampImage;

import java.util.List;

public interface CampRepositoryInterface {
    void saveCamp(Camp c);
    Camp findCampByName(String name);
    List<Camp> findCampByLocation(String location);
    List<Camp> findCampBySellerId(String sellerId);
    void saveImage(CampImage campImage);

    List<CampImage> findImg(String name);

}
