package fr.ComptaPodo.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ComptaPodo.bo.FicheCompta;

@Repository
public interface FicheComptaRepository extends JpaRepository<FicheCompta, Long>{

	
	FicheCompta findByNomPatient(String nomPatient);
}
