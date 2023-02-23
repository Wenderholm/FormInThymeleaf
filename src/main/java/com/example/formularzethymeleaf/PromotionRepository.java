package com.example.formularzethymeleaf;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PromotionRepository {
    private final List<Promotion> promotions = new ArrayList<>();

    void add(Promotion promotion){
        promotions.add(0,promotion);
    }

    List<Promotion> findAll(){
        return promotions;
    }

    List<Promotion> removeByName(String name){
        for (int i = 0; i < promotions.size(); i++) {
            if(name.equals(promotions.get(i).getProduct())){
                promotions.remove(promotions.get(i));
            }
        }
        return promotions;
    }

    Promotion findByName(String name){
        for (int i = 0; i < promotions.size(); i++) {
            if(name.equals(promotions.get(i).getProduct())){
                return promotions.get(i);
            }
        }
        return null;
    }
}
