package com.bptn.feedapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bptn.feedapp.jpa.Feed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.bptn.feedapp.jpa.User;

public interface FeedRepository extends JpaRepository<Feed, Integer>, PagingAndSortingRepository<Feed, Integer> {
	
	//Retrieving the feeds associated with the user
	//result of feeds are paginated and sorted based on pageable object
	Page<Feed> findByUser(User user, Pageable pageable);
	
	
	//Retrieving the feeds not associated with the user
	//result of feeds are paginated and sorted based on pageable object
	Page<Feed> findByUserNot(User user, Pageable pageable);

}
