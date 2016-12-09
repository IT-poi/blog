package com.cuit.boke.service;


import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuit.boke.entity.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:ssh-spring.xml" })
public class ReviewServiceTest {

	@Autowired
	private ReviewService reviewService;
	
	@Test
	@Rollback(false)
	public void testDeleteReview() {
		reviewService.deleteReview(2);
	}
	
	@Test
	public void testShowReview(){
		List<Review> reviews = reviewService.showReview(1);
		if(!reviews.isEmpty()){
			for(Review review:reviews){
				System.out.println(review);
			}
		}
	}

}
