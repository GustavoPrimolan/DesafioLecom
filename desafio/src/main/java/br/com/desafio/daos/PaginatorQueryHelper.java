package br.com.desafio.daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.desafio.models.PaginatedList;

public class PaginatorQueryHelper
{

   public PaginatorQueryHelper()
   {
   }

   public <T> PaginatedList list(TypedQuery<T> query,
         TypedQuery<Number> countQuery, int currentPage, int max)
   {
      List<T> currentList = query.setFirstResult(currentPage * max)
            .setMaxResults(max).getResultList();
      Number count = countQuery.getSingleResult();
      return new PaginatedList(currentList, count);
   }

   public <T> PaginatedList list(EntityManager manager, Class<T> klass,
         int currentPage, int max)
   {

      if (!klass.isAnnotationPresent(Entity.class))
      {
         throw new IllegalArgumentException("Entidade não possui anotação @Entity");
      }

      TypedQuery<T> listQuery = manager.createQuery(
            "select o from " + klass.getSimpleName() + " o", klass);

      TypedQuery<Number> countQuery = manager.createQuery(
            "select count(1) from " + klass.getSimpleName() + " o",
            Number.class);

      return list(listQuery, countQuery, currentPage, max);
   }
}
