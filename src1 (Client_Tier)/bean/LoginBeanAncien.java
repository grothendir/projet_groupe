package bean;

import java.util.List;

import centreformationbis.Equipe;
import centreformationbis.Etudiant;
import centreformationbis.Formateur;
import centreformationbis.Projet;
import centreformationbis.Promotion;
import dao.Dao;

public class LoginBeanAncien {

	private Formateur formateur;
	private Etudiant etudiant;
	private Dao dao;
	private String nom;
	private String motdepasse;
	private String nomprojet;
	private Projet leProjet;

	private List<Projet> lesProjets;
	private List<Promotion> lesPromotions;
	private String nomPromotion;

	private List<Equipe> lesEquipes;
	private Equipe equipe;
	private String nomequipe;

	//////////////////// CONSTRUCTEUR//////////////////////
	public LoginBeanAncien() {
		dao = new Dao();
		setLesPromotions(dao.getLesPromotions());
		setLesEquipes(dao.getLesEquipes());
	}

	////////////////////// CREATION D UN PROJET//////////////

	public String creerProjet() {
		System.out.println("promotion trouvée" + nomPromotion + " " + leProjet.getSujet());

		for (Promotion p : lesPromotions) {
			if (p.getNomPromo().equals(nomPromotion))
				dao.creerProjet(leProjet, formateur, p);
		}
		return "inconnu";
	}

	//////////////////////// INSCRIPTION DANS UNE
	//////////////////////// EQUIPE///////////////////////////
	public String inscriptionEquipe() {
		System.out.println("Equipe trouvee : " + nomequipe + " " + etudiant.getNom());

		for (Equipe e : lesEquipes) {
			if (e.getNomEquipe().equals(nomequipe)) {
				System.out.println("Equipe trouvee : " + nomequipe + " " + etudiant.getNom());
				dao.inscriptionEquipe(e, etudiant);
			}
		}
		return "validationInscription";
	}

	/////////////////////// VALIDATION MEMBRE// ETUDIANT OU
	/////////////////////// FORMATEUR//////////////////

	public String validationMembre() {
		formateur = dao.verifierFormateur(nom, motdepasse);
		if (formateur != null) {
			lesProjets = dao.getProjetParFormateur(formateur);
			leProjet = new Projet();
			return "inviteFormateur";
		}

		else {
			etudiant = dao.verifierEtudiant(nom, motdepasse);
			if (etudiant != null)
				return "inviteEtudiant";
			else
				return "inconnu";
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public String getNomprojet() {
		return nomprojet;
	}

	public void setNomprojet(String nomprojet) {
		this.nomprojet = nomprojet;
	}

	public Projet getLeProjet() {
		return leProjet;
	}

	public void setLeProjet(Projet leProjet) {
		this.leProjet = leProjet;
	}

	public List<Projet> getLesProjets() {
		return lesProjets;
	}

	public void setLesProjets(List<Projet> lesProjets) {
		this.lesProjets = lesProjets;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public List<Equipe> getLesEquipes() {
		return lesEquipes;
	}

	public void setLesEquipes(List<Equipe> lesEquipes) {
		this.lesEquipes = lesEquipes;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNomequipe() {
		return nomequipe;
	}

	public void setNomequipe(String nomequipe) {
		this.nomequipe = nomequipe;
	}

	public List<Promotion> getLesPromotions() {
		return lesPromotions;
	}

	public void setLesPromotions(List<Promotion> lesPromotions) {
		this.lesPromotions = lesPromotions;
	}

	public String getNomPromotion() {
		return nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

}
