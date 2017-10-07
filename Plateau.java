package JeuDémineur;

import java.time.chrono.MinguoChronology;
import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Plateau {
    public static int hauteur, largeur, nbMines;
    
    public boolean[][] mines ;
        /*
         * true  --> avoir mine
         * false --> n'avoir pas mine
         */
    private int[][] etats;
        /* 
         * 1 --> cachée;
         * 2 --> révelée;
         * 3 --> avec drapeau;
         * 4 --> sans drapeau;
         * 5 --> à regarder;
         */
    private int[][] adja;
        /*indique le nombre de mines adjacentes 
          à chaque case*/
    int POSER_DRAPEAU = 2;
    
    boolean jeuPerdu = false;
    
    public Plateau(int hauteur,int largeur,int nbMines){
    	this.hauteur = hauteur;
    	this.largeur = largeur;
    	this.nbMines = nbMines;
  
    	mines = new boolean[this.hauteur][this.largeur];
    	etats = new int[this.hauteur][this.largeur];
    	adja = new int[this.hauteur][this.largeur];
    	
    	rempliAdja();
    	ajouteMinesAlea();
    }

    private void ajouteMinesAlea(){
    	Random rdm = new Random();
    	int rest = nbMines;
    	if(rest>0){
    		for(int i=0;i<rest;i++){
        		mines[rdm.nextInt(hauteur)][rdm.nextInt(largeur)] = true;
    		}
    		int exist=0;
        	for(int m=0;m<hauteur;m++){
        		for(int n=0;n<largeur;n++){
        			if(mines[m][n]==true){
        				exist = exist+1;
        			}
        		}
        	}
        	rest = rest - exist;
    	}
    }
    
    private int calculeAdjacence(int h,int w){
    	int n=0;
    	if(h==0 && w==0){
    		for(int i=h;i<=h+1;i++){
        		for(int j=w;j<=w+1;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(h==0 && w==largeur-1){
    		for(int i=h;i<=h+1;i++){
        		for(int j=w-1;j<=w;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(h==hauteur-1 && w==0){
    		for(int i=h-1;i<=h;i++){
        		for(int j=w;j<=w+1;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(h==hauteur-1 && w==largeur-1){
    		for(int i=h-1;i<=h;i++){
        		for(int j=w-1;j<=w;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(h==0){
    		for(int i=h;i<=h+1;i++){
        		for(int j=w-1;j<=w+1;j++){
        			//System.out.println(i+" "+j);
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(h==hauteur-1){
    		for(int i=h-1;i<=h;i++){
        		for(int j=w-1;j<=w+1;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(w==0){
    		for(int i=h-1;i<=h+1;i++){
        		for(int j=w;j<=w+1;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	if(w==largeur-1){
    		for(int i=h;i<=h+1;i++){
        		for(int j=w-1;j<=w;j++){
        			if(mines[i][j] == true){
        				if(i!=h || j!=w){
        					n++;
        				}
        			}
        		}
        	}
    		return n;
    	}
    	for(int i=h-1;i<=h+1;i++){
    		for(int j=w-1;j<=w+1;j++){
    			
    			if(mines[i][j] == true){
    				if(i!=h || j!=w){
    					n++;
    				}
    			}
    		}
    	}
    	return n;
    }
    
    private void rempliAdja(){
    	for(int i=0;i<hauteur;i++){
    		for(int j=0;j<largeur;j++){
    			adja[i][j] = calculeAdjacence(i,j);
    		}
    	}
    }
    
    public void rempliEtats(){
    	for(int i=0;i<hauteur;i++){
    		for(int j=0;j<largeur;j++){
    			etats[i][j] = 1;
    		}
    	}
    }
    
    public void choisirCase(int i,int j) {
    	if(mines[i][j]) {
    		jeuPerdu = true;
    	}else {
    		revelerCase(i, j);
    	}
	}
   
    
    public void revelerCase(int i,int j){
    	if((etats[i][j]==1 ||etats[i][j]==3)&& mines[i][j] == false) {
    		if(calculeAdjacence(i, j)==0) {
    			if(etats[i][j]!=2) {
    				etats[i][j] = 2;
    				if(i==0 && j ==0) {
            			for(int m=i;m<=i+1;m++){
                			for(int n=j;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(i==0 && j ==largeur-1) {
            			for(int m=i;m<=i+1;m++){
                			for(int n=j-1;n<=j;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(i==hauteur-1 && j ==0) {
            			for(int m=i-1;m<=i;m++){
                			for(int n=j;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(i==hauteur-1 && j ==largeur-1) {
            			for(int m=i-1;m<=i;m++){
                			for(int n=j-1;n<=j;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(i==0) {
            			for(int m=i;m<=i+1;m++){
                			for(int n=j-1;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(i==hauteur-1) {
            			for(int m=i-1;m<=i;m++){
                			for(int n=j-1;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(j==0) {
            			for(int m=i-1;m<=i+1;m++){
                			for(int n=j;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else if(j==largeur-1) {
            			for(int m=i-1;m<=i+1;m++){
                			for(int n=j-1;n<=j;n++){
                				revelerCase(m,n);
                			}
                		}
            		}else {
            			for(int m=i-1;m<=i+1;m++){
                			for(int n = j-1;n<=j+1;n++){
                				revelerCase(m,n);
                			}
                		}
            		}
    			}	
    		}else {
    			etats[i][j] = 2;
    		}
    	}
    }
    
    public void drapeauCase(int i,int j){
    	if(POSER_DRAPEAU>0) {
    		if(etats[i][j] == 1) {
    			etats[i][j] = 3;
        		POSER_DRAPEAU = POSER_DRAPEAU - 1;
    		}
    	}
    }
    
    public void drawCase(int i,int j){
    	if(etats[i][j]==1){
    		System.out.print(".\t");
    	}else if(etats[i][j]==2){
    		System.out.print(calculeAdjacence(i, j)+"\t");
    	}else if(etats[i][j]==3){
    		System.out.print("?\t");
    	}else{
    		etats[i][j] = 1;
    		System.out.print(".\t");
    	}
    }

    public void affichage(){
    	System.out.println("*************************************");
    	System.out.println("*********Mines/Drapeaux**************");
    	System.out.println("*********  "+nbMines+"  /    "+POSER_DRAPEAU+"     ************");
    	for(int j=0;j<largeur+1;j++){
    		System.out.print(j+"\t");
		}
    	System.out.println();
    	for(int i=0;i<hauteur;i++){
    		System.out.print((char)(i+65)+"\t");
    		for(int j=0;j<largeur;j++){
    			drawCase(i,j);
    		}
    		System.out.print("\n");
    	}
    }
    
    public boolean jeuGagne() {
    	int compt=0;
    	for(int i=0;i<hauteur;i++) {
    		for(int j=0;j<largeur;j++) {
    			if(mines[i][j]==false && etats[i][j]==2) {
    				compt++;
    			}
    		}
    	}
    	if(compt == (hauteur*largeur - nbMines)) {
    		return true;
    	}
		return false;
	}
    
    public void drapeauOuRelever(int i,int j) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Vous voulez drapeau un case (importez 1) ou relever un case (importez 2) ?");
    	int h = sc.nextInt();
    	while(h!=1 && h!=2) {
    		System.out.println("Erreur !! importez 1 ou 2 !!!");
    		h = sc.nextInt();
    	}
    	if(h==1) {
    		drapeauCase(i, j);
    	}else {
			choisirCase(i, j);
		}
	}
    
    public static void main(String[] args){
    	Plateau p =new Plateau(8,8,12);
    	p.affichage();
    	p.choisirCase(3, 2);
    	p.affichage();
 
    	
    }
    
}
