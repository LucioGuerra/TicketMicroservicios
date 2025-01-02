package com.tickets.requirement_sv.specification;

import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.Requirement;
import com.tickets.requirement_sv.entity.State;
import org.springframework.data.jpa.domain.Specification;

public class RequirementSpecification {
    public static Specification<Requirement> hasSubject(String subject) {
        return (root, query, cb) -> subject == null? null: cb.like(root.get("subject"), "%" + subject + "%");
    }

    public static Specification<Requirement> hasType(Long typeId) {
        return (root, query, cb) -> typeId == null? null: cb.equal(root.get("typeId"), typeId);
    }

    public static Specification<Requirement> hasState(State state) {
        return (root, query, cb) -> state == null? null: cb.equal(root.get("state"), state);
    }

    public static Specification<Requirement> hasPriority(Priority priority) {
        return (root, query, cb) -> priority == null? null: cb.equal(root.get("priority"), priority);
    }

    public static Specification<Requirement> hasCreatorId(Long creatorId) {
        return (root, query, cb) -> creatorId == null? null: cb.equal(root.get("creatorId"), creatorId);
    }

    public static Specification<Requirement> hasAssigneeId(Long assigneeId) {
        return (root, query, cb) -> assigneeId == null? null: cb.equal(root.get("assigneeId"), assigneeId);
    }

    public static Specification<Requirement> hasDeleted(Boolean isDeleted) {
        return (root, query, cb) -> isDeleted == null? null: cb.equal(root.get("isDeleted"), isDeleted);
    }
}
