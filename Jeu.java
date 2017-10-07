package JeuDémineur;

import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Jeu {
    public Joueur leJoueur;
    public Plateau lePlateau;
    
    public Jeu() {
    	this.leJoueur = new Joueur();
    	this.lePlateau = new Plateau(8, 8, 10);
	}
    
    public void jeu() {
    	Jeu leJeu = new Jeu();
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Donnez votre nom : ");
    	leJeu.leJoueur.setNom();

    	while (!leJeu.lePlateau.jeuPerdu) {
    		if(leJeu.lePlateau.jeuGagne()) {
    			System.out.println("Vous avez gagné !! Bravo !!");
    		}else {
    			leJeu.lePlateau.affichage();
        		System.out.println("Choissisez la case !! Monsieur " + leJeu.leJoueur.getNom()+" ");
    			System.out.println(" Entre 1 ~~ " + leJeu.lePlateau.hauteur +" pour hortizontale !");
    			int i = sc.nextInt();
    			System.out.println(" Entre A ~~ " + (char)(leJeu.lePlateau.largeur+64) +" pour verticale !");
    			String j = sc.next();
    			char c = j.charAt(0);
    			leJeu.lePlateau.drapeauOuRelever((int)(c)-65,i-1);
    		}
		}
	}
}
