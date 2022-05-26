package camper.project.service;


import camper.project.domain.Reserve;
import camper.project.repository.ReserveRepositoryInterface;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Reserveservice {

    private ReserveRepositoryInterface repsository;

    @Autowired
    public Reserveservice(ReserveRepositoryInterface repository) {
        this.repsository= repository;
    }

    public void join(Reserve r) {
        repsository.saveReserve(r);

    }

    public List<Reserve> findByid(String clientid) {
        return repsository.findByid(clientid);
    }

    public List<Reserve> deletereserve(String reserveid) {
        return repsository.deletereserve(reserveid);
    };
}
