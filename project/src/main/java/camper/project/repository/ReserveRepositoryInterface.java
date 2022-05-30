package camper.project.repository;



import camper.project.domain.Camp;
import camper.project.domain.Reserve;
import camper.project.domain.Room;

import java.util.List;

public interface ReserveRepositoryInterface {
    void saveReserve(Reserve r);
    List<Reserve> deletereserve(String reserveid);
    List<Reserve> findByid(String clientid);

    Camp findByCampId(int campId);
    Room findByRoomId(int roomId);

}
