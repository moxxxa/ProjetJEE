package com.mh.forum.transactions.dao;

import com.mh.forum.post.model.Post;
import com.mh.forum.transactions.model.Debtor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DebtorRepository  extends MongoRepository<Debtor,String> {
}
