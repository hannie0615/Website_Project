package camper.project.service;

import camper.project.domain.Camp;
import camper.project.domain.Member;
import camper.project.domain.Reserve;
import camper.project.repository.MemberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Camp> findCampsBySellerId(String id) { return repository.findCampsBySellerId(id); }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validkeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validkeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public List<Reserve> findByid(String id) {
        return repository.findByid(id);
    }


}
