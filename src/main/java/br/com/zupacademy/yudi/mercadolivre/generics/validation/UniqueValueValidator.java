package br.com.zupacademy.yudi.mercadolivre.generics.validation;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String validatedField;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(UniqueValue params) {
        validatedField = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("select 1 from " + clazz.getName() + " where "
                + validatedField + " = :pvalue");
        query.setParameter("pvalue", value);
        List<?> resultList = query.getResultList();
        return resultList.isEmpty();
    }
}
