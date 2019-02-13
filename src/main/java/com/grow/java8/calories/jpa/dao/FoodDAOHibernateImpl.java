package com.grow.java8.calories.jpa.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.jpa.entity.FoodEntity;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("hibernate")
public class FoodDAOHibernateImpl implements FoodDAO<FoodEntity> {
    private static final String ID_NULL_EXCEPTION = "id must not be null!";

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Stream<? extends FoodEntity> getStream(){
        Query query = entityManager.createQuery("SELECT c FROM FoodEntity c order by c.id", FoodEntity.class);
        return query.getResultList().stream();
    }
    
    @Override
    public Optional<FoodEntity> getFood(Long id) {
        Long foodId = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException(ID_NULL_EXCEPTION));
        FoodEntity food = (FoodEntity) Hibernate.unproxy(entityManager.getReference(FoodEntity.class, foodId));
        return Optional.ofNullable(food);
    }
    
    @Override
    public FoodEntity setFood(FoodEntity food) {
        return entityManager.merge(food);
    }

    @Override
    public void removeFood(FoodEntity food){
        entityManager.remove(food);
    }

}
