package camper.project.repository;

import camper.project.domain.Member;

import java.util.List;

public interface MemberRepositoryInterface {

    void saveMember(Member m);

    Member findMember(String id);


}
