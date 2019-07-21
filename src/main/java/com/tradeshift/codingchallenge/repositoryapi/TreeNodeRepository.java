package com.tradeshift.codingchallenge.repositoryapi;

import com.tradeshift.codingchallenge.entity.TreeNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TreeNodeRepository extends CrudRepository<TreeNode, Long> {

    // custom query example and return a stream
    @Query("select c from Customer c where c.email = :email")
    Stream<TreeNode> findByEmailReturnStream(@Param("email") String email);
}
