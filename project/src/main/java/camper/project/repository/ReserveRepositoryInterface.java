package camper.project.repository;



import camper.project.domain.Reserve;

import java.util.List;

public interface ReserveRepositoryInterface {
    void saveReserve(Reserve r);
    List<Reserve> deletereserve(String reserveid);
    List<Reserve> findByid(String clientid);

}
