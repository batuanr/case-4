package com.example.case_module_4.repository;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
   Page<Book> findAllByNameContaining(Pageable pageable,String name);
   Page<Book> findAllByAuthor(Pageable pageable, Author author);
   Page<Book> findAllByCategoryList(Pageable pageable,Category category);

   @Query(value = "select * from book  order by id desc limit 4",nativeQuery = true)
   Iterable<Book> findTopByIdOrderByIdDesc();

   @Query (value = "update book set count_borrowed=count_borrowed+1,quantity=quantity-1 where id= :id ",nativeQuery = true)
   void borrowBook(@Param("id") Long id);
   @Query(value = "update book set quanttity=quantity+1 where id= :id",nativeQuery = true)
   void repayBook(@Param("id") Long id);

   @Query(value = "select * from book where count_borrowed=(select MAX(count_borrowed) from book) ",nativeQuery = true)
   Optional<Book> findTopBook();

}
