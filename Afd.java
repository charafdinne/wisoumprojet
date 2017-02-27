package afntoafd;

import java.util.*;
import java.util.Map.Entry;
import static java.util.Spliterators.iterator;

public class Afd{
	
public ArrayList <Character> alphabet;
public ArrayList <String> etats;
public ArrayList <String> etatsFinaux;
public String [][] fonctionTransition;
public String [][] newfcttrans;
public String etatInitial;
public ArrayList<String>  new_etats;
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
            //System.out.print(etats.indexOf(etatTmp) + etatTmp);
            
            if(!etatTmp.equals("vide"))  
            {    
               // System.out.println(" " +i + " ==> " + fonctionTransition[this.etats.indexOf(etatTmp)][i]);
                
                if(!afdRes.etats.contains(fonctionTransition[this.etats.indexOf(etatTmp)][i]) && fonctionTransition[this.etats.indexOf(etatTmp)][i] != "vide" && !open.contains(fonctionTransition[this.etats.indexOf(etatTmp)][i]))
                    open.add(fonctionTransition[this.etats.indexOf(etatTmp)][i]);
            
                afdRes.fonctionTransition[afdRes.etats.indexOf(etatTmp)][i] = fonctionTransition[this.etats.indexOf(etatTmp)][i];
                //System.out.println(afdRes.fonctionTransition[this.etats.indexOf(etatTmp)][i]);
            }
            afdRes.AfficherFonctionTrans();              
            i++;
        }
    }
    return afdRes;
}

public Afd minimaliser()
{
 //   Afd afdRes = new Afd();
    int l;
    HashMap<String,ArrayList> marque = new HashMap<String,ArrayList>();
    
    for(int i = 0; i< etats.size() ; i++)
    {
        if(!etatsFinaux.contains(etats.get(i)))
        {
            ArrayList a = new ArrayList();
            
            for( int j = 0; j < etatsFinaux.size(); j++)
            {
                a.add(etatsFinaux.get(j));
            }
            marque.put(etats.get(i), a);
        }
    }
    
    System.out.println("la marque :"+marque);
    
    boolean nMarque ;
    do{
        nMarque = false;
        for(int i = 0; i <etats.size() - 1 ; i++)
        {
            l = 1;
            while(l < etats.size())
            {
            if(!Marque(etats.get(i), etats.get(l),marque) && !etats.get(i).equals(etats.get(l)))
            {
                System.out.println(etats.get(i) + " et "+  etats.get(l));
                for(int k = 0; k < alphabet.size(); k++)
                {
                    System.out.println("leur trans "+fonctionTransition[i][k]+fonctionTransition[l][k]);
                    if(Marque(fonctionTransition[i][k],fonctionTransition[l][k],marque))
                    {
                        System.out.println("traitement "+etats.get(i) + " et "+  etats.get(l));
                        Marquer(etats.get(i), etats.get(l),marque) ;
                        System.out.println(etats.get(i) + " et "+  etats.get(l) + "sont marqués");
                        nMarque = true;
                        break;
                    }
                }
            }    
            ++l;
            }
        }
    }while(nMarque);
    
    /*System.out.println("la nouvelle marque :"+marque);
    AfficherFonctionTrans(); 
    */
    new_etats = new ArrayList(etats.size());
    
    for(int i = 0 ; i < etats.size() ; i++)
        new_etats.add(i, etats.get(i));
    
    newfcttrans = new String[etats.size()][alphabet.size()];
    for(int i = 0; i < etats.size() ; i++)
        for(int j= 0; j < alphabet.size() ; j++)
        {
                newfcttrans[i][j] = fonctionTransition[i][j];   
        }
    
    ArrayList a = new ArrayList();
    
    for(int i =0 ; i < etats.size() - 1 ;i++)
    {
        l = 1;
        while(l < etats.size() )
        {
            if(!Marque(etats.get(i),etats.get(l),marque) && !etats.get(i).equals(etats.get(l)))
                    {
                        if(!(a.contains(etats.get(i)) && a.contains(etats.get(l))))
                        {   a.add(etats.get(i));
                            a.add(etats.get(l));
                            //System.out.println("les etats nn marqués :"+ etats.get(i) +etats.get(l));
                            Modifier_fct_trans(etats.get(i),etats.get(l));
                            //for(int k= 0 ; k< alphabet.size() ; k++)
                            //newfcttrans[etats.indexOf(etats.get(l))][k] = "walo";
                            Modifier_etats(etats.get(i),etats.get(l));                           
                        }
                    }
            l++;            
        }
    }
    
    
    Afd wa_akhirane = new Afd();
    wa_akhirane.alphabet = this.alphabet;
    wa_akhirane.etatInitial = this.etatInitial;
    wa_akhirane.etats = this.new_etats;
    wa_akhirane.fonctionTransition = this.newfcttrans;
    return wa_akhirane;
    /*System.out.println("les trans par les new etats :");
    for(int i = 0; i < new_etats.size();i++)
            System.out.println(new_etats.get(i));
    
    for(int i = 0 ; i<new_etats.size();i++)
    {
        for(int j = 0;j<alphabet.size();j++)
        {
            System.out.print(newfcttrans[i][j]+"\t");
        }
	System.out.print("\n");
    }*/
    /*
    System.out.println("affichae de new fct trans");
    
    for(int i = 0; i < new_etats.size() ; i++)
        for(int j= 0; j < alphabet.size() ; j++)
        {
                System.out.println(fonctionTransition[i][j]);            
        }
    */
}

void Modifier_etats(String e,String f)
{
    new_etats.set(etats.indexOf(e), "q'" + etats.indexOf(e) + etats.indexOf(f));
    new_etats.remove(f);      
}

void Modifier_fct_trans(String e,String f)
{
    for(int i = 0; i < etats.size() ; i++)
        for(int j= 0; j < alphabet.size() ; j++)
        {            
            if (fonctionTransition[i][j].equals(e) || fonctionTransition[i][j].equals(f))
            {
                newfcttrans[i][j] = "q'"+etats.indexOf(e) +etats.indexOf(f);
            }
        } 
}
public boolean Marque(String e ,String f, HashMap marque)
{
    if((!e.equals("vide") && f.equals("vide")) || (e.equals("vide") && !f.equals("vide")))
        return true;
    ArrayList a;
    if(marque.get(e) != null)
        {
            a = (ArrayList) marque.get(e);
            if(a.contains(f))
                return true;
        }
    if(marque.get(f) != null)
        {
            a = (ArrayList) marque.get(f);
            if(a.contains(e))
                return true;
        }
    
    return false;
}   
    
    
    
    /*Iterator it = marque.entrySet().iterator();
    boolean lala = false;
        while(it.hasNext())
        {
            Entry entree = (Entry) it.next();
            String key = (String)entree.getKey();
            ArrayList value = (ArrayList)entree.getValue();
            
            System.out.println( key + " : " +e+ " "+ value + " " + f);
            
            System.out.println( key + " : " +f+ " "+ value + " " + e);
            
            if(f == key && value.contains(e))
            {
                System.out.println("waaa l merd "+ f);
                lala =  true;
                break;
            }
            
            if(e == key  && value.contains(f))
            {
                System.out.println("waa l merrd" + e);
                lala = true;
                break;
            }
            
            System.out.println("salina hadi ... zid\n ");
            //if((e == key && value.contains(f)) || (value.contains(e) && f == key))
                //return true;
        }
        return lala;
}*/


public void Marquer(String e ,String f, HashMap marque)
{
    ArrayList b ;
    if(marque.get(e) != null)
    {   
        b = (ArrayList) marque.get(e);
        b.add(f);
        marque.put(e,b);
    }
    else
    {
        b = new ArrayList();
        b.add(f);
        marque.put(e, f);
    }
}

}
