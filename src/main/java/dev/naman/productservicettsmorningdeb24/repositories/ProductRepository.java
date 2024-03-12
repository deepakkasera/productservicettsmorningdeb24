package dev.naman.productservicettsmorningdeb24.repositories;

import dev.naman.productservicettsmorningdeb24.models.Category;
import dev.naman.productservicettsmorningdeb24.models.Product;
import dev.naman.productservicettsmorningdeb24.repositories.projections.ProductProjection;
import dev.naman.productservicettsmorningdeb24.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p); // the attributes that are automatically
    //  generated by DB will not be in the param, but will be there in
    // returned object


    @Override
    List<Product> findAll();

    @Query("select p from Product p where p.category.title = :title and p.id = :productId")
    Product getTheProductsWithParticularName(@Param("title") String title,
                                             @Param("productId") Long productId);

    @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
    List<ProductProjection> getTitlesOfProductsOfGivenCategory(@Param("categoryId") String categoryId);

    Product findByIdIs(Long id);

    List<Product> findAllByTitle(String title);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByCategory_TitleLike(String categoryTitleLike);

    List<Product> findAllByCategory_IdEquals(Long categoryId);

    List<Product> findByTitleStartingWithAndIdGreaterThanAndPriceLessThan(String titleStart, Long idGreaterThan, int priceLessThan);

    @Query(value = "select * from products p where p.id = :id, p.title = :title", nativeQuery = true)
    List<ProductProjection> doSomething(@Param("id") String id, @Param("title") String title);
}
