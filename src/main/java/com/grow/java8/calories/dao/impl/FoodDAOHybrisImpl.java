package com.grow.java8.calories.dao.impl;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("h2")
public class FoodDAOHybrisImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOHybrisImpl.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Stream<Food> getStream(){
        final Query query = entityManager.createQuery("SELECT c FROM Food c order by c.id", Food.class);
        return query.getResultList().stream();
    }
    
    @Override
    public Food getFood(Long id) {
        if (id == null){
            return null;
        }

        return entityManager.getReference(Food.class, id);
    }
    
    @Override
    public Food setFood(Food food) {
        return entityManager.merge(food);
    }

    @Override
    public void removeFood(Food food){
        entityManager.remove(food);
    }

}
