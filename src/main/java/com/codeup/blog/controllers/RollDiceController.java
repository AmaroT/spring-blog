package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String showDiceForm(){
        return "rollDice";
    }

    //Dice Walkthrough
    @PostMapping("/roll-dice")
    public String showDiceResult(@RequestParam(name = "number") Integer number, Model model) {
        int numberRolled = (int) ((Math.random() * 6) + 1);

        String message = "You've selected " + number + " and the number rolled was " + numberRolled + ".";
        if (number.equals(numberRolled)) {
            message += "You won!";
        } else {
            message += "Oh well, try again!";
        }
        model.addAttribute("message", message);

        return "rollDice";
    }
}
