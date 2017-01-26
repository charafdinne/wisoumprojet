package afnTOafd;
import java.util.*;

public class Program {

public  static void  main(String [] args){
	Scanner lectureClavier=new Scanner(System.in);
	Afd exemple1= new Afd();
	AfnTOafd exemple2= new AfnTOafd();
	Afd resultatFinal= new Afd();
	
        boolean entree = true;
        
	int choix= 0;
        boolean test=true;
    exemple1.menuAlphabet();
    
    do{
        do{
            try{
                entree = true;
                System.out.print("enter votre choix:   ");
                choix=lectureClavier.nextInt();
                if(choix !=1 && choix !=2 && choix !=3 && choix !=4 & choix !=5) 
                {
                    entree = false;
                    throw new Exception("Entrez un choix valide");
                }
            }catch(Exception e)
            {
                entree = false;
                System.out.println(e.getMessage());
            }
        }while(entree == false);
        
    switch(choix){
    case 1: exemple1.ajouterSymbole();break;
    case 2: exemple1.supprimerSymbole();break;
    case 3: exemple1.affichageAlphabet();break;
    case 4: System.out.println(exemple1.tailleAlphabet());break;
    case 5: test=false;break;
    }
    }while(test== true);
    
    
    exemple1.menuEtat();
   test=true;
   do{
    do{
        try{
            entree = true;
            System.out.print("enter votre choix:   ");
            choix=lectureClavier.nextInt();
         
            if(choix !=1 && choix !=2 && choix !=3 && choix !=4 & choix !=5) 
         {
             entree = false;
             throw new Exception("Entrez un choix valide");
         }
        }catch(Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }while(entree == false);
    
    switch(choix){
    case 1: exemple1.ajouterEtats();break;
    case 2: exemple1.supprimeretat();break;
    case 3: exemple1.affichageEtats();break;
    case 4: System.out.println(exemple1.tailleEtats());break;
    case 5: test=false;break;
    }
    }while(test== true);
    
    exemple1.saisirEtatFinaux();
    exemple1.affichageEtatsInitial();
    exemple1.fonctionTrans();
    exemple1.AfficherFonctionTrans();
    //elimination des etats inaccessible pour l'afd quand on finira de le determiner
        // ici j'ai fait le test avec un afd directement
        
    Afd a = exemple1.eliminerEtatInaccessible();
    a.AfficherFonctionTrans();
    
    /*
    exemple2.testEpsilonTransition(exemple1);
    exemple2.transfert(exemple1);
    exemple2.afficherFonctionTransition();
    exemple2.afficherInfoAFd(resultatFinal, exemple1);
    resultatFinal.affichageAlphabet();
    resultatFinal.affichageEtats();
    resultatFinal.affichageEtatsFinaux();
    resultatFinal.affichageEtatsInitial();
    resultatFinal.AfficherFonctionTrans();
    
    resultatFinal.eliminerEtatInaccessible();
    resultatFinal.affichageEtats();*/
    
    
}

}
