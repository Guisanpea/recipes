package com.recipes.ejb.support;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class JpaResultHelper {
    public static Object getSingleResultOrNull(Query query) {
        List results = query.getResultList();

        if (results.size() == 1) return results.get(0);
        else if (results.size() == 0) return null;

        throw new NonUniqueResultException();
    }
}
