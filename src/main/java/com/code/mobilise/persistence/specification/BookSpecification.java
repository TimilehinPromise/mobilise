package com.code.mobilise.persistence.specification;

import com.code.mobilise.persistence.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class BookSpecification implements Specification<Book> {

    private final List<SearchCriteria> list;

    public BookSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        // add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL) && criteria.getValue() instanceof LocalDateTime) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()),
                        LocalDateTime.parse(criteria.getValue().toString())));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL) && criteria.getValue() instanceof LocalDateTime) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), LocalDateTime.parse(criteria.getValue().toString())));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
    public static Specification<Book> searchInBookWithKeyword( String keyword) {
        return (root, query, criteriaBuilder) -> {

            // Convert keyword to lowercase for case-insensitive search
            String lowerKeyword = keyword.toLowerCase();


            // Build predicates for case-insensitive search
            Predicate authorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + lowerKeyword + "%");
            Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + lowerKeyword + "%");


            // Combine predicates
            Predicate combinedPredicate = criteriaBuilder.or(
                 authorPredicate,titlePredicate
            );

            query.distinct(true); // Ensure distinct results
            return combinedPredicate;
        };
    }

}
