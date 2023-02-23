package com.example.formularzethymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PromotionController {
    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping("/")
    String promotionList(Model model) {
        List<Promotion> allPromotion = promotionRepository.findAll();
        model.addAttribute("promotions", allPromotion);
        return "list";
    }

    @GetMapping("/add")
    String addForm(Model model) {
//        użycie binding object celem ułatwienia zmiany nazw pól w obiektach(łatwiejszy refactoring kodu)
        model.addAttribute("promotion", new Promotion());
        return "add-form";
    }

    @PostMapping("/save")
    String savePromotion(Promotion promotion) {
        promotionRepository.add(promotion);
        return "redirect:/";
    }

//    uwuwanie elementów po nazwie jaka przekazujemy w
    @GetMapping("/delete/{name}")
    String deletePromotion(@PathVariable("name")String name){
        promotionRepository.removeByName(name);
        return "redirect:/";
    }
    @GetMapping("/edit/{name}")
    String editPromotion(@PathVariable("name")String name, Model model){
        Promotion objekt = promotionRepository.findByName(name);
        model.addAttribute("promotionToEdit",objekt);
        return "update-form";
    }

    @PostMapping("/update/{name}")
    String updatePromotion(@PathVariable("name")String name, Promotion promotion){
        promotionRepository.removeByName(name);
        promotionRepository.add(promotion);
        return "redirect:/";
    }
}
