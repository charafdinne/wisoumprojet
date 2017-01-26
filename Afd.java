package afnTOafd;

import java.util.*;
import static java.util.Spliterators.iterator;

public class Afd{
	
public ArrayList <Character> alphabet;
public ArrayList <String> etats;
public ArrayList <String> etatsFinaux;
public String [][] fonctionTransition;
public String etatInitial;

Scanner lectureClavier=new Scanner(System.in);
//////////////////////constructeur ///////////////////////////////////////////////////////////////////
public Afd(){

	alphabet= new ArrayList <Character> ();
	etats= new ArrayList <String> () ;
	etatsFinaux= new ArrayList <String>() ;
}
//////////////////////alphabet /////////////////////////////////////////////////////////////////////
public void ajouterSymbole(){
	char a;int z;
	System.out.print("donner un symbole de type charactère:   ");
	a=lectureClavier.next().charAt(0);
	if(tailleAlphabet()!=0){
		z=alphabet.indexOf(a);
		if(z !=-1){
		 System.out.println("ce sympole existe deja, le  charactère est refusé");}
		
		else{alphabet.add(new Character(a));
		System.out.println("votre symbole ajouté est :   "+a);}
			}
		else{
	alphabet.add(new Character(a));
	System.out.println("votre symbole ajouté est :   "+a);}
	
}

public void supprimerSymbole(){
    int num;char choix;
    System.out.println("Saisir le symbole que vous voulez supprimer :");
    choix=lectureClavier.next().charAt(0);
    Character ch=new Character(choix);
	num=alphabet.indexOf(ch);
	if(num >= 0){
	alphabet.remove(num);
	System.out.println("la suppression est faite avec succés ");
	}
	else
	System.out.println("ce charactère n'existe pas ");
}

public int tailleAlphabet(){
	return alphabet.size();
}

public void affichageAlphabet(){
	 int taille=tailleAlphabet();
	 for(int i=0;i<taille;i++){
		 System.out.print(alphabet.get(i)+"\t");
	 }
}

public void menuAlphabet(){
	System.out.println("choix 1: ajouter symbole");
	System.out.println("choix 2: supprimer symbole");
	System.out.println("choix 3: afficher alphabet");
	System.out.println("choix 4: afficher taille alphabet");
	System.out.println("choix 5: sortir de ce menu");
}
//////////////////////////Etats
public void menuEtat(){
	System.out.println("choix 1: ajouterun état:    ");
	System.out.println("choix 2: supprimer etat:    ");
	System.out.println("choix 2: afficher etat:     ");
	System.out.println("choix 3: afficher taille etats");
	System.out.println("choix 4: sortir de ce menu ");
}

public void ajouterEtats(){
	int  a;String e;
   
	System.out.print("donnez le nombre des états que vous souhaitez utilisés \n dans ce afd, par la suite on vous généra les noms de ce états comme suit en respectant l'ordre : q0,q1,q2,q3,....tel que q0 est \n l'état initial;cette démarche simplifie l'étude par la suite:    ");
	a=lectureClavier.nextInt();

	for(int i=0;i<a;i++){
	e="q"+i;	
	etats.add(e);
	}
	
}

public void supprimeretat(){
    int num;
    String choix;
    System.out.print("saisir l'état : ");
    choix=lectureClavier.nextLine();
    num=alphabet.indexOf(choix);
    if(num != -1)
	alphabet.remove(num);
    else 
    	System.out.print("cette état n'existe pas ");	
	
}

public int tailleEtats(){
	return etats.size();
}

 public void affichageEtats(){
	 int taille=tailleEtats();
	 for(int i=0;i<taille;i++){
		 System.out.print(etats.get(i)+"\t");
	 }
	 System.out.println();
 }
///////////////////////////////////////////fonction transition/:::::::::::::::::::::::::::::
public void fonctionTrans(){
	int i,j;String etatTest;char c;String f=null;boolean test=true;
	fonctionTransition = new String[tailleEtats()][tailleAlphabet()];
	for(i=0;i<tailleEtats();i++){
		etatTest=etats.get(i);
		for (j=0;j<tailleAlphabet();j++){
			c=alphabet.get(j);
			System.out.println("f("+etatTest+","+c+")=\n");
			do{ //gestion d'exception pour ne pas stopper tout le programme par une fuate de frappe
                            try{
                                test = true;
			f=lectureClavier.nextLine();
			if(!verifierEtat(f) && !f.equals("vide")) 
                        {
                            test = false;
                            throw new Exception ("Entrez un etat valide ou la chaine 'vide' ");
                        }
			}catch(Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                            
                        }while(!test);
                        
			fonctionTransition[i][j]=f;
		}
	}	
}
public void AfficherFonctionTrans(){
	System.out.print("\t");
	affichageAlphabet();
	System.out.print("\n");
	
	for(int i=0;i<tailleEtats();i++){
		System.out.print(etats.get(i)+"\t");
		for(int j=0;j<tailleAlphabet();j++){
			System.out.print(fonctionTransition[i][j]+"\t");
		}
		System.out.print("\n");
	}
}

private boolean verifierEtat(String f) { 
	int position=etats.indexOf(f);
	if(position==-1)return false;
	else 
	return true;
}

private boolean verifierEtatFinal(String f) { //fct pour verifier si on ajoutera l'etat au etat finaux aussi
    if(verifierEtat(f) && etatsFinaux.indexOf(f) != -1) return true;
    return false;
}
/////////////////////////////////////////Etats finaux
public void saisirEtatFinaux(){
	String f;boolean test;int nombreEtatFinaux;
	do{
	System.out.println("Veuillez saisir le nombre d'états finaux: ");
	nombreEtatFinaux=lectureClavier.nextInt();
	}while(nombreEtatFinaux<=0);
	
	for(int i=0;i<nombreEtatFinaux;i++){
		System.out.print("Veuillez saisir le"+(1+i)+" état final: ");
	do{
		f=lectureClavier.nextLine();
		test=verifierEtat(f);
		}while(test==false);
	etatsFinaux.add(f);
}
	System.out.print("\n");
}

public void affichageEtatsFinaux(){
	 int taille=tailleEtatsFinaux();
	 for(int i=0;i<taille;i++){
		 System.out.print(etatsFinaux.get(i)+"\t");
	 }
	 System.out.print("\n");
}

public int tailleEtatsFinaux(){
	return etatsFinaux.size();
}
///////////////////////////////////// Etat initial                 
/*
public void saisirEtatInitial(){
	String f;boolean test;
	System.out.print("Veuillez saisir l'état initial:   ");
	do{
		f=lectureClavier.nextLine();
		test=verifierEtat(f);
		}while(test==false);
	etatInitial=f;	
}
*/
public String getEtatInitial(){
	etatInitial="q0";
	return etatInitial;
}

public void affichageEtatsInitial(){
  System.out.println("l'état initial de votre AFD est : "+getEtatInitial());
}

public Afd eliminerEtatInaccessible()
{
    LinkedList<String> open = new LinkedList<String>() ;
    
    Afd afdRes = new Afd();
    afdRes.etatInitial = getEtatInitial();
    afdRes.fonctionTransition = new String[etats.size()][alphabet.size()];
    
    for(int i = 0 ; i < alphabet.size(); i++)
        afdRes.alphabet.add(i, alphabet.get(i));

    open.add(this.getEtatInitial());
    
    while(!open.isEmpty())
    {
        String etatTmp = open.poll();
        if(!etatTmp.equals("vide"))
            afdRes.etats.add(etatTmp);
        
        if(verifierEtatFinal(etatTmp)) afdRes.etatsFinaux.add(etatTmp);
        
       // Iterator<Character> it = alphabet.iterator();
        int i = 0;
        while(i<alphabet.size())
        {
            
            if(!etatTmp.equals("vide"))  
            {
                
                if(!afdRes.etats.contains(fonctionTransition[this.etats.indexOf(etatTmp)][i]) && fonctionTransition[this.etats.indexOf(etatTmp)][i] != "vide")
                    open.add(fonctionTransition[this.etats.indexOf(etatTmp)][i]);
            
                afdRes.fonctionTransition[afdRes.etats.indexOf(etatTmp)][i] = fonctionTransition[this.etats.indexOf(etatTmp)][i];
            }
            afdRes.AfficherFonctionTrans();              
            i++;
        }
    }
    return afdRes;
}

}
