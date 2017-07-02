package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import centreformationbis.Projet;
import centreformationbis.Promotion;
import centreformationbis.Equipe;
import centreformationbis.Etudiant;
import centreformationbis.Formateur;
import centreformationbis.Membre;

public class Dao {
	private EntityManager em;;

	public Dao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("centreformationbis");
		em = factory.createEntityManager();
	}

	

	
	
	
	/////////////////////////////AJOUT DE METHODES POUR INSCRIPTION D UN MEMBRE DANS UNE EQUIPE////////////
	
	public Equipe getEquipe(String nom)
	{String requete =  "SELECT g FROM Equipe g where g.nomEquipe = ";
	requete = requete + "'" + nom + "'";
	TypedQuery<Equipe> query = em.createQuery(requete, Equipe.class);	
	
	if (query.getResultList().size() != 0) {
		Equipe e= query.getSingleResult();
		System.out.println("Equipe trouv�e: "+e.getNomEquipe());
		return e;
	}
	else return null;
	}
	
	
	public Etudiant getEtudiant(String nom)
	{String requete =  "SELECT g FROM Etudiant g where g.nom = ";
	requete = requete + "'" + nom + "'";
	TypedQuery<Etudiant> query = em.createQuery(requete, Etudiant.class);	
	
	if (query.getResultList().size() != 0) {
		Etudiant e= query.getSingleResult();
		System.out.println("Etudiant trouv�: "+e.getNom());
		return e;
	}
	else return null;
	}
	
	Object object;
	
	public boolean inscriptionEquipe(Equipe equi,Etudiant etud)
	{
		em.getTransaction().begin();
		List<Etudiant> liste;
		liste = equi.getEtudiants();
		liste.add(etud);
//		equi.setEtudiants(liste);
		em.merge(equi);
		em.getTransaction().commit();
		return true;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////INSCRIPTION FORMATION///////////////////
	public boolean inscriptionPromotion(Etudiant etud,Promotion promo)
	{
		em.getTransaction().begin();
		List<Promotion> liste;
		liste = etud.getPromotions();
		liste.add(promo);
		em.merge(etud);
		em.getTransaction().commit();
		return true;
	}
	
	
	
	public Promotion getPromotion(String nom)
	{String requete =  "SELECT g FROM Promotion g where g.nomPromo = ";
	requete = requete + "'" + nom + "'";
	TypedQuery<Promotion> query = em.createQuery(requete, Promotion.class);	
	
	if (query.getResultList().size() != 0) {
		Promotion p= query.getSingleResult();
		System.out.println("Promotion trouv�e: "+p.getNomPromo());
		return p;
	}
	else return null;
	}
	
	
	public Etudiant getPromotionEtudiant (Promotion p)
	{
		
	String requete =  "SELECT g FROM Etudiant g where g.idMembre IN (SELECT m.idMembre FROM membre_promotion m where m.idPromotion = ";
	requete = requete + p.getIdPromotion()+")";
		
		
	//String requete =  "SELECT g FROM Promotion g where g.idPromotion IN (SELECT m.idPromotion FROM membre_promotion m where m.idMembre = ";
	//requete = requete + e.getIdMembre()+")";
	//TypedQuery<Promotion> query = em.createQuery(requete, Promotion.class);	
	TypedQuery<Etudiant> query = em.createQuery(requete, Etudiant.class);	
	
	if (query.getResultList().size() != 0) {
		Etudiant e= query.getSingleResult();
		System.out.println("Promotion trouv�e: "+p.getNomPromo());
		return e;
	}
	else return null;
	}


	
	public boolean creerProjet(Projet p, Formateur f, Promotion pro)
	{
		System.out.println("creation projet"); // pour tester
		p.setFormateur(f);
		p.setPromotion(pro);
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		return true;
	}
	

	
	// requete parametr�e
	public List<Projet> getProjetParFormateur(Formateur formateur) {
		List<Projet> lesprojets = null;
		Query q = em.createQuery("SELECT s FROM Projet s Where s.formateur = :var");
		q.setParameter("var", formateur);
		lesprojets = q.getResultList();
		return lesprojets;
	}
	
	public List<String> getSujetParFormateur(Formateur formateur) {
		List<String> lessujets = null;
		Query q = em.createQuery("SELECT s.sujet FROM Projet s Where s.formateur = :var");
		q.setParameter("var", formateur);
		lessujets = q.getResultList();
		return lessujets;
	}
	

	
	public List<Promotion> getLesPromotions(){	
		String requete =  "SELECT g FROM Promotion g ";
		TypedQuery<Promotion> query = em.createQuery(requete, Promotion.class);
		List <Promotion> liste = query.getResultList();
		return liste;
	}
	
	public List<Equipe> getLesEquipes(){
		String requete = "select g FROM Equipe g";
		TypedQuery<Equipe> query = em.createQuery(requete, Equipe.class);
		List <Equipe> liste = query.getResultList();
		return liste;
	}
	
	/**
	 * Vérifie le formateur en fonction du nom et du mot de passe
	 * @param nom
	 * @param mdp
	 * @return formateur
	 */
	public Formateur verifierFormateur(String nom, String mdp) {
		String requete = "SELECT g FROM Formateur g where g.nom = ";
		requete = requete + "'" + nom + "'";
		Formateur i1 = null;
		
		TypedQuery<Formateur> query = em.createQuery(requete, Formateur.class);
		
		if (query.getResultList().size() != 0) {
			i1 = query.getSingleResult();
			System.out.println("Formateur trouv�:" + i1.getNom());
			requete = "SELECT g FROM Membre g where g.nom = ";
			requete = requete + "'" + nom + "'";
			requete = requete + "and g.motdepasse = " + "'" + mdp + "'";
			if (query.getResultList().size() != 0) {
				System.out.println("Mot de passe ok");
				Formateur f = query.getSingleResult();
				return f;

			} else {
				System.out.println("Mot de passe faux");
				return null;
			}
		} else {
			System.out.println("Pas trouv�");
			return null;
		}
	}
	
	
	public Etudiant verifierEtudiant(String nom, String mdp) {
		String requete = "SELECT g FROM Etudiant g where g.nom = ";
		requete = requete + "'" + nom + "'";
		Etudiant e1 = null;
		
		TypedQuery<Etudiant> query = em.createQuery(requete, Etudiant.class);
		
		if (query.getResultList().size() != 0) {
			e1 = query.getSingleResult();
			System.out.println("Etudiant trouv�:" + e1.getNom());
			requete = "SELECT g FROM Membre g where g.nom = ";
			requete = requete + "'" + nom + "'";
			requete = requete + "and g.motdepasse = " + "'" + mdp + "'";
			if (query.getResultList().size() != 0) {
				System.out.println("Mot de passe ok");
				Etudiant e = query.getSingleResult();
				return e;

			} else {
				System.out.println("Mot de passe faux");
				return null;
			}
		} else {
			System.out.println("Pas trouv�");
			return null;
		}
	}
	
	
}

