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

    @Override
    public void initialize(ExistsId params) {
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("select 1 from " + clazz.getName() + " c where c.id = :pid");
        query.setParameter("pid", value);
        return !query.getResultList().isEmpty();
    }
}
