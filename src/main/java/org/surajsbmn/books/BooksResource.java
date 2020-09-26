package org.surajsbmn.books;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.surajsbmn.books.model.Book;
import org.surajsbmn.books.repository.BookRepository;

@Path("/books")
public class BooksResource {

	@Inject
	BookRepository repository;
	
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBooks() {
    	 List<Book> books = repository.listBooks();
    	 GenericEntity<List<Book>> entities =  new GenericEntity<List<Book>>(books) {};
    	return Response.ok(entities).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
    	repository.addBook(book);
    	return Response.ok().build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") Long id) {
    	Book book = repository.getBookById(id);
    	if(book == null)
    		return Response.status(Status.NOT_FOUND).build();
    	else {
    		GenericEntity<Book> entity = new GenericEntity<Book>(book) {};
    		return Response.ok(entity).build();
    	}
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBookById(@PathParam("id") Long id) {
    	Integer recordsDeleted = repository.deleteBookById(id);
    	if(recordsDeleted == 0)
    		return Response.status(Status.NOT_FOUND).build();
    	else {
    		return Response.ok().build();
    	}
    }
    
    @PUT
    @Path("/{id}/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookPrice(@PathParam("id") Long id,@PathParam("price")Integer price) {
    	Integer recordsUpdated = repository.updateBookPrice(id, price);
    	if(recordsUpdated == 0)
    		return Response.status(Status.NOT_FOUND).build();
    	else {
    		return Response.ok().build();
    	}
    }
}