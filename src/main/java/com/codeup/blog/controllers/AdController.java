package com.codeup.blog.controllers;


import com.codeup.blog.models.Ad;
import com.codeup.blog.models.AdCategory;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.blog.repositories.AdRepository;

import java.util.ArrayList;

@Controller
public class AdController {
    private final AdRepository adRepo;
    private final UserRepository userRepo;

    public AdController(AdRepository adRepo, UserRepository userRepo) {
        this.adRepo = adRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(path = "/ads", method = RequestMethod.GET)
    public String showAllAds(Model model) {
        model.addAttribute("ads", adRepo.findAll());
        return "ads/index";
    }

    @RequestMapping(path = "/ads/{id}", method = RequestMethod.GET)
    public String showSingleAd(@PathVariable long id, Model model) {
        model.addAttribute("ad", adRepo.getOne(id));
        return "ads/show";
    }

    @GetMapping("ads/hardcoded/create")
    public String createHardcodedAd() {
        Ad ad = new Ad();
        ad.setTitle("Title to Chevy Silverado, Title only.");
        ad.setDescription("Selling the title to Daddy's Silverado. His car was lost at sea.");
        ad.setCategories(new ArrayList<AdCategory>());
        ad.setOwner(userRepo.getOne(1L));
        adRepo.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/create")
    public String showCreateView() {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           Model model) {
        Ad ad = new Ad(title, description);
        adRepo.save(ad);
        return "redirect:/ads/" + ad.getId();
    }

    @GetMapping("/ads/delete/{id}")
    public String deleteAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        adRepo.delete(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        model.addAttribute("ad", ad);
        return "ads/edit";
    }

    @PostMapping("/ads/edit")
    public String updateAd(@RequestParam(name = "id") long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setTitle(title);
        ad.setDescription(description);
        adRepo.save(ad);
        return "redirect:/ads/" + ad.getId();
    }
}