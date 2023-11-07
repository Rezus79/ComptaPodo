package fr.ComptaPodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ComptaPodo.bll.FicheComptaService;
import fr.ComptaPodo.bo.FicheCompta;

@Controller
@RequestMapping("/")
public class HomeController {

	
	@Autowired
	FicheComptaService ficheComptaService;
	
	@GetMapping
	String index() {
		return "home/index";
	}
	
	@GetMapping("/user/menu")
	String menu() {
		return "home/menu";
	}
	
	@GetMapping("/user/nouvelle_fiche")
	String ficheCompta(Model model) {
		model.addAttribute("fiche", new FicheCompta());
		return "home/fiche";
	}
	
	@PostMapping("/user/nouvelle_fiche")
	String ficheCompta(@ModelAttribute FicheCompta fiche) {
		ficheComptaService.CreerFiche(fiche);
		return "redirect:/private/menu";
	}
	
	@GetMapping("/user/liste")
	String listeFiche(Model model) {
		List<FicheCompta> fiches = new ArrayList<FicheCompta>();
		fiches.addAll(ficheComptaService.consulterFiches());
		
		model.addAttribute("fiches", fiches);
		return "home/liste";
	}
}
