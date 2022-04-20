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
public class SamolotyController {

    private SamolotyRepository samolotyRepository;

    public SamolotyController(SamolotyRepository samolotyRepository) {
        this.samolotyRepository = samolotyRepository;
    }

    @GetMapping(path = "/test2")
    String getAll(Model model, @RequestParam(value = "model",required = false) String modelToFind, String massage) {
        List<Samolot> samoloty = new ArrayList<>();
        if (modelToFind != null) {
            samoloty = samolotyRepository.findAllByModel(modelToFind);
        } else {
            samoloty = samolotyRepository.findAll();
        }
        model.addAttribute("samoloty", samoloty);
        model.addAttribute("samolot", new Samolot());
        model.addAttribute("massage",massage);
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
        return "redirect:/save";
    }

    @RequestMapping("/rezerwuj")
    public String rezerwuj(@ModelAttribute("samolot") Samolot samolot) {
        String redirectToMain = "redirect:/test2";
        if(samolot.getId()==null){
            return redirectToMain;
        }
        Optional<Samolot> znalezionySamolot = samolotyRepository.findById(samolot.getId());

        if (znalezionySamolot.isEmpty()) {
            return redirectToMain;
        }
        Samolot znaleziony = znalezionySamolot.get();

        if(znaleziony.getIloscMiejscNaPokladzie()==0){
            return redirectToMain+"?massage=Brak wolnych miejsc.";
        }
        if ( znaleziony.getIloscMiejscNaPokladzie()>0) {
            znaleziony.setIloscMiejscNaPokladzie(znaleziony.getIloscMiejscNaPokladzie() - 1);
            samolotyRepository.save(znaleziony);
            return redirectToMain;
        }
        return redirectToMain;
    }

    @RequestMapping("/znajdz")
    public String znajdz(@ModelAttribute("samolot") Samolot samolot, Model model) {
        List <Samolot>samoloty = samolotyRepository.findAllByLotnisko(samolot.getLotnisko());
        model.addAttribute("samoloty", samoloty);
        model.addAttribute("samolot", new Samolot());
        return "findByLotnisko";



       // return "redirect:/test2"+"?lotnisko="+samolot.getLotnisko();
    }




}
