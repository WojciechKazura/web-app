package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller//
//@RequestMapping(path="/test")
public class SamolotyController {

    private SamolotyRepository samolotyRepository;

    public SamolotyController(SamolotyRepository samolotyRepository) {
        this.samolotyRepository = samolotyRepository;
    }

    @GetMapping(path = "/test2")
    String getAll(Model model, @RequestParam(value = "model",required = false) String modelToFind) {
        List<Samolot> samoloty = new ArrayList<>();
        if (modelToFind != null) {
            samoloty = samolotyRepository.findAllByModel(modelToFind);
        } else {
            samoloty = samolotyRepository.findAll();
        }
        model.addAttribute("samoloty", samoloty);
        model.addAttribute("samolot", new Samolot());
        return "index";
    }

    @RequestMapping("/save")
    public ModelAndView movetoSave() {
        ModelAndView modelAndView = new ModelAndView("save");
        Samolot attributeValue = new Samolot();
        attributeValue.setId(1l);
        modelAndView.addObject("samolot", attributeValue);
        return modelAndView;
    }

    @RequestMapping("/zapiszSamolot")
    public String save(@ModelAttribute("samolot") Samolot samolot) {
        samolotyRepository.save(samolot);
        return "redirect:/";
    }

    @RequestMapping("/rezerwuj")
    public String rezerwuj(@ModelAttribute("samolot") Samolot samolot) {
        Optional<Samolot> znalezionySamolot = samolotyRepository.findById(samolot.getId());
        if (znalezionySamolot.isPresent()) {
            znalezionySamolot.get().setIloscMiejscNaPokladzie(znalezionySamolot.get().getIloscMiejscNaPokladzie() - 1);
            samolotyRepository.save(znalezionySamolot.get());
        }
        return "redirect:/test2";
    }

    @RequestMapping("/znajdz")
    public String znajdz(@ModelAttribute("samolot") Samolot samolot) {

        return "redirect:/test2"+"?model="+samolot.getModel();
    }

}
