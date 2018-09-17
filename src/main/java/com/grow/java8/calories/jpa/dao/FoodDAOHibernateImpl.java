package com.grow.java8.calories.jpa.dao;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.jpa.entity.FoodEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("hibernate")
public class FoodDAOHibernateImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOHibernateImpl.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Stream<? extends Food> getStream(){
        Query query = entityManager.createQuery("SELECT c FROM FoodEntity c order by c.id", FoodEntity.class);
        return query.getResultList().stream();
    }
    
    @Override
    public Food getFood(Long id) {
        if (id == null){
            return null;
        }

        return entityManager.getReference(FoodEntity.class, id);
    }
    
    @Override
    public Food setFood(Food food) {
        return entityManager.merge((FoodEntity)food);
    }

    @Override
    public void removeFood(Food food){
        entityManager.remove((FoodEntity)food);
    }

}
