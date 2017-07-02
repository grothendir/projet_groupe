package application.copy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centreformationbis.Etudiant;
import centreformationbis.Formateur;
import centreformationbis.Membre;
import centreformationbis.Projet;
import centreformationbis.Promotion;

public class TestEtudiantbis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory factory = Persistence.
				createEntityManagerFactory("centreformationbis");
				EntityManager em = factory.createEntityManager();
				
				/**
				// Creation d'un étudiant
				Membre m ;
				m = new Membre();
				
				em.getTransaction().begin();
				m.setNom("Alimi Franck");
				m.setPrenom("test");
				m.setMail("test@yahoo.fr");
				em.persist(m);		
				em.getTransaction().commit();
				
				Etudiant etu1 = new Etudiant();
				em.getTransaction().begin();
				
				etu1.setNom(m.getNom());
				etu1.setIdMembre(m.getIdMembre());
				//etu1.setMembre(m);
				em.persist(etu1);		
				em.getTransaction().commit();
				
				**/
				// Creation d'un formateur
				Membre m1 = new Membre();
				em.getTransaction().begin();
				
				m1.setNom("Martin Guy");
				m1.setPrenom("test");
				m1.setMail("test@yahoo.fr");
				em.persist(m1);		
				em.getTransaction().commit();
				
				Formateur form1 = new Formateur();
				em.getTransaction().begin();
				
				form1.setNom(m1.getNom());
				form1.setIdMembre(m1.getIdMembre());
				//etu1.setMembre(m);
				em.persist(form1);		
				em.getTransaction().commit();
				
		
				//Creation d'une promotion
				Promotion p = new Promotion();
				p.setNomPromo("Promo3");
				em.getTransaction().begin();
				em.persist(p);
				em.getTransaction().commit();
				
				//Creation d'un projet
				Projet proj = new Projet();
				proj.setPromotion(p);
				proj.setFormateur(form1);
				proj.setTitre("test projet promo3");
				em.getTransaction().begin();
				em.persist(proj);
				em.getTransaction().commit();
				
				
				
		
	}

}
