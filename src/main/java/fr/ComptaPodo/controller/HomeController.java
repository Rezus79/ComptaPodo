package fr.ComptaPodo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@GetMapping("/user/mois")
	String totalMois(Model model) {
		List<FicheCompta> fiches = ficheComptaService.consulterFiches();
		Map<String, Double> totalMois = new HashMap<>();
		
		
		for (FicheCompta fiche : fiches) {
			Calendar calendar = Calendar.getInstance();
			LocalDateTime localDateTime = fiche.getDateDuSoin();
			Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
			calendar.setTime(date);
			
			String mois = new SimpleDateFormat("MMMM yyyy").format(calendar.getTime());
			
			double total = totalMois.getOrDefault(mois, 0.0);
			total += fiche.getFacture();
			totalMois.put(mois, total);
		}
		
		model.addAttribute("totalMois", totalMois);
		return "home/mois";
	}
	
	@GetMapping("/user/annee")
	String totalAnnee(Model model) {
		List<FicheCompta> fiches = new ArrayList<FicheCompta>();
		fiches.addAll(ficheComptaService.consulterFiches());
		double total = 0 ;
		int totalFiches = 0;
		for (FicheCompta fiche : fiches) {
			total += fiche.getFacture();
			totalFiches += 1;
		}
		
		
		
		model.addAttribute("total", total);
		model.addAttribute("totalFiches", totalFiches);
		return "home/annee";
	}
	
}
