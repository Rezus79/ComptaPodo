package fr.ComptaPodo.bo;



import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class FicheCompta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nomPatient;
	
	@Column
	private float kilometrage;
	
	@Column(nullable = false)
	private String localite;
	
	@Column(nullable = false)
	private Double facture;
	
	@Column(nullable = false)
	private String moyenPayement;
	
	@Column(nullable = false)
	private LocalDateTime dateDuSoin;
	
	
	public FicheCompta() {}


	public FicheCompta(Long id, String nomPatient, String localite, Double facture, String moyenPayement,
			LocalDateTime dateDuSoin) {
		this.id = id;
		this.nomPatient = nomPatient;
		this.localite = localite;
		this.facture = facture;
		this.moyenPayement = moyenPayement;
		this.dateDuSoin = dateDuSoin;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomPatient() {
		return nomPatient;
	}


	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}


	public String getLocalite() {
		return localite;
	}


	public void setLocalite(String localite) {
		this.localite = localite;
	}


	public Double getFacture() {
		return facture;
	}


	public void setFacture(Double facture) {
		this.facture = facture;
	}


	public String getMoyenPayement() {
		return moyenPayement;
	}


	public void setMoyenPayement(String moyenPayement) {
		this.moyenPayement = moyenPayement;
	}


	public LocalDateTime getDateDuSoin() {
		return dateDuSoin;
	}


	public void setDateDuSoin(LocalDateTime dateDuSoin) {
		this.dateDuSoin = dateDuSoin;
	}


	public float getKilometrage() {
		return kilometrage;
	}


	public void setKilometrage(float kilometrage) {
		this.kilometrage = kilometrage;
	}
	
	
}
