package fr.ComptaPodo.bll;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ComptaPodo.bo.FicheCompta;
import fr.ComptaPodo.dal.FicheComptaRepository;

@Service
public class FicheComptaServiceImpl implements FicheComptaService{

	@Autowired
	FicheComptaRepository ficheComptaRepository;
	
	public FicheComptaServiceImpl(FicheComptaRepository ficheComptaRepository) {
		this.ficheComptaRepository = ficheComptaRepository;
	}
	
	@Override
	public FicheCompta CreerFiche(FicheCompta ficheCompta) {
		// TODO Auto-generated method stub
		return ficheComptaRepository.save(ficheCompta);
	}

	@Override
	public List<FicheCompta> consulterFiches() {
		// TODO Auto-generated method stub
		return ficheComptaRepository.findAll();
	}

	@Override
	public FicheCompta getFicheById(Long id) {
		Optional<FicheCompta> ficheOptional = ficheComptaRepository.findById(id);
	    return ficheOptional.orElse(null); 
	}

	@Override
	public FicheCompta findByNomPatient(String nomPatient) {
		// TODO Auto-generated method stub
		return ficheComptaRepository.findByNomPatient(nomPatient);
	}

	@Override
	public FicheCompta modifierFiche(FicheCompta ficheCompta) {
		
		ficheComptaRepository.saveAndFlush(ficheCompta);
		return null;
	}

	@Override
	public void supprimerFiche(Long id) {
		ficheComptaRepository.deleteById(id);
		
	}
	
	

}
