package org.surajsbmn.books.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.surajsbmn.books.model.Book;

@ApplicationScoped
public class BookRepository {

	@Inject
	EntityManager em;
	
	public List<Book> listBooks(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Book> cq = cb.createQuery(Book.class);
	    Root<Book> rootEntry = cq.from(Book.class);
	    CriteriaQuery<Book> all = cq.select(rootEntry);
	    TypedQuery<Book> allQuery = em.createQuery(all);
	    return allQuery.getResultList();
	}
	
	@Transactional
	public void addBook(Book book) {
		em.persist(book);
	}
	
	public Book getBookById(Long id) {
		return em.find(Book.class, id);
	}
	
	@Transactional
	public Integer deleteBookById(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Book> cd = cb.createCriteriaDelete(Book.class); 
	    Root<Book> rootEntry = cd.from(Book.class);
	    cd.where(cb.equal(rootEntry.get("id"),id));
	    return em.createQuery(cd).executeUpdate();
	}
	
	@Transactional
	public Integer updateBookPrice(Long id,Integer price) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Book> cu = cb.createCriteriaUpdate(Book.class);
		Root<Book> root = cu.from(Book.class);
		cu.set("price", price).where(cb.equal(root.get("id"), id));
		return em.createQuery(cu).executeUpdate();
	}
}
