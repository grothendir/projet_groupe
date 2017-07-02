package application.copy2;

import java.util.List;

import dao.Dao;
import centreformationbis.Formateur;
import centreformationbis.Projet;
import centreformationbis.Promotion;;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		//dao.getEtudiant("ALIMI");
		//dao.getEquipe("equipe1");
		
		//dao.inscriptionEquipe( dao.getEquipe("equipe1"),dao.getEtudiant("ALIMI"));
		dao.inscriptionPromotion(dao.getEtudiant("ALIMI"),dao.getPromotion("Promo3"));
		System.out.println(dao.getPromotionEtudiant(dao.getPromotion("Promo3")));
		/**
		Formateur r;
		r = Dao.verifierFormateur("MartinTest", "admin");
		System.out.println(r);
		r = Dao.verifierFormateur("Martin", "admin");
		System.out.println(r);
		//r = Dao.verifierFormateur("moi meme", "ddd");
		//System.out.println(r);

		System.out.println("**********************************");

		List<Projet> liste = Dao.getProjetParFormateur(r);
		for (Projet p : liste)
			System.out.println(p.getSujet());
		
	
	System.out.println("**************************************");

         List<String> sujets = Dao.getSujetParFormateur(r);
         for(String s : sujets)
	       System.out.println(s);

         
    Promotion p = new Dao().getPromotion("Promo2");
    Projet projet = new Projet();
    projet.setTitre("Developpement java");
    projet.setSujet("Distributeur bières");
    projet.setDateCreation("2017-06-28");
    projet.setDateLimiteRendu("2017-07-28");
    Dao.creerProjet(projet, r, p);**/
    
    
}}
