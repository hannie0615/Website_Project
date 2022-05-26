package camper.project.repository;

import camper.project.domain.Camp;
import camper.project.domain.Member;

import java.util.List;

public interface MemberRepositoryInterface {

    void saveMember(Member m);

    Member findMember(String id);

    List<Camp> findCampsBySellerId(String id);



}
