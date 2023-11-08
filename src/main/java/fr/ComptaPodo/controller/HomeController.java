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
import jakarta.websocket.server.PathParam;

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
		return "redirect:/user/menu";
	}
	
	@GetMapping("/user/liste")
	String listeFiche(Model model) {
		List<FicheCompta> fiches = new ArrayList<FicheCompta>();
		fiches.addAll(ficheComptaService.consulterFiches());
		
		model.addAttribute("fiches", fiches);
		return "home/liste";
	}
	
	@GetMapping("/user/impayes")
	String listeImpayes(Model model) {
		List<FicheCompta> fiches = new ArrayList<FicheCompta>();
		List<FicheCompta> impayes = new ArrayList<FicheCompta>();
		fiches.addAll(ficheComptaService.consulterFiches());
		
		for (FicheCompta fiche : fiches) {
			if (fiche.getFacture() == 0) {
				impayes.add(fiche);
			}
		}
		
		model.addAttribute("fiches", impayes);
		return "home/impayes";
	}
	
	@GetMapping("/user/fiche/detail")
	String ficheDetail(@PathParam("id") long id, Model model) {
		model.addAttribute("fiche", ficheComptaService.getFicheById(id));
		
		return "home/detail";
	}
	
	@GetMapping("/user/fiche/modifier")
	String ficheModifier(@PathParam("id") long id, Model model) {
		model.addAttribute("fiche", ficheComptaService.getFicheById(id));
		
		return "home/modifier";
	}
	
	@PostMapping("/user/fiche/modifier")
	String ficheModifier(@ModelAttribute FicheCompta fiche) {
		ficheComptaService.modifierFiche(fiche);
		return "redirect:/user/impayes";
	}
}
