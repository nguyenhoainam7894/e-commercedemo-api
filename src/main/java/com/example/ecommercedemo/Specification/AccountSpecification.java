//package com.example.ecommercedemo.Specification;
//
//import com.example.ecommercedemo.entity.Account;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//public class AccountSpecification implements Specification<Account> {
//
//    private SearchCriteria criteria;
//
//    public AccountSpecification(SearchCriteria criteria){
//        this.criteria = criteria;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//        if (criteria.getOperator().equalsIgnoreCase(">")) {
//            return builder.greaterThanOrEqualTo(
//                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        }
//        else if (criteria.getOperator().equalsIgnoreCase("<")) {
//            return builder.lessThanOrEqualTo(
//                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        }
//        else if (criteria.getOperator().equalsIgnoreCase(":")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return builder.like(
//                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        }
//
//        return null;
//    }
//}
