package com.udesc.padroesdeprojeto.gamelog.visitor;

import com.udesc.padroesdeprojeto.gamelog.model.DetailedReview;
import com.udesc.padroesdeprojeto.gamelog.model.Review;

public interface ReviewVisitor {
    void visit(Review review);
    void visit(DetailedReview detailedReview);
}
