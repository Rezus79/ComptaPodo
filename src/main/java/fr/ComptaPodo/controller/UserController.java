package fr.ComptaPodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.ComptaPodo.bll.UtilisateurService;
import fr.ComptaPodo.bo.Utilisateur;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "nonConnecter");
        cookie.setPath("/"); 
        response.addCookie(cookie);

        // Efface le contexte de sécurité
        SecurityContextHolder.clearContext();
		return "redirect:/";
	}
	
	@GetMapping("/private/liste_manager")
	public String Manager(Model model) {
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		users.addAll(utilisateurService.consulterUtilisateurs());
		model.addAttribute("utilisateurs" , users);
		return "home/liste_manager";
	}
}
