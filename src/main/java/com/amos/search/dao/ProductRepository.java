package com.amos.search.dao;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * DESCRIPTION: ProductRepository
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/9/18
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    Iterable<Product> findByNameLike(String name);

    @Query("{\"match_phrase\":{\"description\":\"?0\"}}")
    Iterable<Product> findByName(String name);

}
