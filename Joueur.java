package JeuDémineur;


import java.util.*;

public class Joueur {
    private String nom;
    
    public Joueur(){
        //Par défaut, le nom du joueur est "Anonyme". Le constructeur initialise un scan qui pourra vous être utile.
        Scanner sc = new Scanner(System.in);
        this.nom = "Anonyme";
    }
    
    public void setNom() {
    	Scanner sc = new Scanner(System.in);
    	this.nom = sc.next();
	}
    
    public String getNom() {
		return this.nom;
	}
}
