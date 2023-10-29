package br.com.makersweb.user.infrastructure.utils;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author aaristides
 */
public class SpecificationUtils {

    private SpecificationUtils() {
    }

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, cb) -> cb.like(cb.upper(root.get(prop)), like(term.toUpperCase()));
    }

    private static String like(final String term) {
        return "%" + term + "%";
    }

}
