package camper.project.service;

import camper.project.domain.Member;
import camper.project.repository.MemberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

    MemberRepositoryInterface repository;

    @Autowired
    public MemberService(MemberRepositoryInterface repository) {
        this.repository = repository;
    }

    public void join(Member m) {
        repository.saveMember(m);
    }

    public Member findMember(String id) {
        return repository.findMember(id);
    }

}
