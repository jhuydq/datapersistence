package com.udacity.course3.repo;

import com.udacity.course3.entity.delivery.Delivery;
import com.udacity.course3.entity.inventory.Plant;
import com.udacity.course3.repo.projection.RecipientAndPrice;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;



@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;


    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    public List<Delivery> getAllDeliveries(String recipientName){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        query.setParameter("recipientName", recipientName);
        return query.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> criteriaQuery = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = criteriaQuery.from(Plant.class);

        criteriaQuery.select(cb.construct(
                                    RecipientAndPrice.class,
                                    root.get("delivery").get("recipientName"),
                                    cb.sum(root.get("price"))))
                        .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
