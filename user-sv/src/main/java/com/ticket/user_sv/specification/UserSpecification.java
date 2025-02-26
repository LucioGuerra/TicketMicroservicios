package com.ticket.user_sv.specification;

import com.ticket.user_sv.entity.OutsideUser;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<OutsideUser> byName(String name) {
        return ((root, query, cb) -> name == null ? cb.conjunction() : cb.like(root.get("name"), name));
    }

    public static Specification<OutsideUser> byEmail(String email) {
        return ((root, query, cb) -> email == null ? cb.conjunction() :  cb.like(root.get("email"), email));
    }
     public static Specification<OutsideUser> isDeleted(Boolean isDeleted) {
        return ((root, query, cb) -> isDeleted == null ? cb.conjunction() : cb.equal(root.get("isDeleted"), isDeleted));
     }

     public static Specification<OutsideUser> bySla(Boolean sla) {
        return ((root, query, cb) -> sla == null ? cb.conjunction() : cb.equal(root.get("sla"), sla));
     }

     public static Specification<OutsideUser> byCompany(String company) {
        return ((root, query, cb) -> company == null ? cb.conjunction() : cb.equal(root.get("company"), company));
     }

     public static Specification<OutsideUser> byUsername(String username) {
        return ((root, query, cb) -> username == null? cb.conjunction() : cb.equal(root.get("username"), username));
     }

     public static Specification<OutsideUser> byCuil(String cuil) {
        return ((root, query, cb) -> cuil == null? cb.conjunction(): cb.equal(root.get("cuil"), cuil));
     }
}
