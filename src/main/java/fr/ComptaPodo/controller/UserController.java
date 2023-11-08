package fr.ComptaPodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.ComptaPodo.bll.UtilisateurService;
import fr.ComptaPodo.bo.Utilisateur;

@Controller
public class UserController {

	@Autowired
	UtilisateurService utilisateurService;
	
	@GetMapping("/login")
	public String connexion(Model model) {
		
		return "home/login";
	}
	

	
	@GetMapping("/private/creer")
	public String creer(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "home/creer";
	}
	
	@PostMapping("/private/creer")
	public String creer(@ModelAttribute Utilisateur utilisateur) {
		utilisateurService.CreerUtilisateur(utilisateur);
		return "redirect:/user/menu";
	}
}
