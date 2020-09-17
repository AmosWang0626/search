package com.amos.search.dao;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * DESCRIPTION: PersonRepository
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/9/17
 */
@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

    Iterable<Person> findByNameLike(String name);

    @Query("{\"match_phrase\":{\"description\":\"?0\"}}")
    Iterable<Person> findByDesc(String description);

}
