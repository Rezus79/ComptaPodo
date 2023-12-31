package fr.ComptaPodo.bll;

import java.util.List;
import java.util.Optional;

import fr.ComptaPodo.bo.FicheCompta;

public interface FicheComptaService {

	FicheCompta CreerFiche(FicheCompta ficheCompta);
	
	List<FicheCompta> consulterFiches();
	
	FicheCompta getFicheById(Long id);
	
	FicheCompta findByNomPatient(String nomPatient);
	
	FicheCompta modifierFiche(FicheCompta ficheCompta);
	
	void supprimerFiche(Long id);
	
}
