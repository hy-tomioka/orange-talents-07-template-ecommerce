package br.com.zupacademy.yudi.mercadolivre.generics.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {
    @PersistenceContext
    private EntityManager em;
    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(ExistsId params) {
        clazz = params.domainClass();
        fieldName = params.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        Query query = em.createQuery("select 1 from " + clazz.getName() + " c where c." + fieldName + " = :pid");
        query.setParameter("pid", value);
        return !query.getResultList().isEmpty();
    }
}
