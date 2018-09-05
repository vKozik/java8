package com.grow.java8.calories.dao.impl;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.stream.Stream;

@Component
@Profile("h2")
public class FoodDAOJdbcImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOJdbcImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Food> getStream(){
        Query query = entityManager.createQuery("SELECT c FROM Food c order by c.id", Food.class);
        return query.getResultList().stream();
    }

    @Override
    public Food getFood(Long id) {
        return entityManager.getReference(Food.class, id);
    }

    @Override
    public Food setFood(Food food) {
        return entityManager.merge(food);
    }
    
}