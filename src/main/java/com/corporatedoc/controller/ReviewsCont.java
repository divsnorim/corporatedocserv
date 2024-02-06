package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.ReviewDetails;
import com.corporatedoc.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewsCont extends Attributes {
    @GetMapping
    public String Reviews(Model model) {
        AddAttributesReviews(model);
        return "reviews";
    }

    @GetMapping("/{id}")
    public String ReviewDetails(Model model, @PathVariable Long id) {
        AddAttributesReviewDetails(model, id);
        return "reviewDetails";
    }

    @GetMapping("/{id}/delete")
    public String ReviewDelete(@PathVariable Long id) {
        reviewsRepo.deleteById(id);
        return "redirect:/reviews";
    }
    @GetMapping("/{reviewId}/delete/{reviewDetailId}")
    public String ReviewDetailDelete(@PathVariable Long reviewDetailId, @PathVariable Long reviewId) {
        reviewDetailsRepo.deleteById(reviewDetailId);
        return "redirect:/reviews/{reviewId}";
    }


    @PostMapping("/add")
    public String ReviewsAdd(@RequestParam String review) {
        reviewsRepo.save(new Reviews(review, DateNow(), getUser()));
        return "redirect:/reviews";
    }

    @PostMapping("/{id}/add")
    public String ReviewsAdd(@RequestParam String review, @PathVariable Long id) {
        Reviews reviews = reviewsRepo.getReferenceById(id);
        reviews.addDetail(new ReviewDetails(review, DateNow(), getUser()));
        reviewsRepo.save(reviews);
        return "redirect:/reviews/{id}";
    }
}
