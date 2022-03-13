package com.utcn.lab2.repository;

import com.utcn.lab2.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVoteRepository extends CrudRepository<Vote, Integer> {

    List<Vote> findByUserId(Integer id);

    List<Vote> findByEntryId(Integer id);

    List<Vote> findByEntryIdAndUserId(Integer entryId, Integer userId);
}
