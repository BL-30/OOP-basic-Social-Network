package packnp.tests.tools;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import packnp.cinema.Salle;



public class TestsMPO2020S09 {

	@SuppressWarnings({ "rawtypes" })
	public static int test01SpectateurDeclaration() {

		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		int noteAgeMax=30;
		int noteNomMax=30;
		int noteDeclaration=10;
		int noteAge=noteAgeMax;
		int noteNom=noteNomMax;
		int notePasDAutres=30;
		System.out.println(" Test verifiant la declaration des variables d'instance de la classe Spectateur");
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldNom=null, fieldAge=null;
		Field[] tf = cSpectateur.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldAge==null) {
						fieldAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldNom==null) {
						fieldNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Spectateur ne doit comporter qu'une variable d'instance de type String" ); 
					System.out.println("   et une variable d'instance de type int");
					notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Spectateur");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				notePasDAutres= 0;
			}
		}
		if (fieldNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			noteNom=0;
		} else if (!fieldNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteNom=noteNom/2;
		}
		if (fieldNom!=null && !Modifier.isPrivate(fieldNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldNom.getName()+" de Spectateur devrait etre declaree private");
			noteNom=noteNom/2;
		}
		if (fieldAge==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type int correspondant a l'age du spectateur.");
			noteAge=0;
		} else if (!fieldAge.getName().equals("age")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"age\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteAge=noteAge/2;
		}
		if (fieldAge!=null && !Modifier.isPrivate(fieldAge.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldAge.getName()+" de Spectateur devrait etre declaree private");
			noteAge=noteAge/2;
		}
		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Spectateur au lieu de 2");
			notePasDAutres=0;
		}
		if (noteNom<noteNomMax || noteAge<noteAgeMax) {
			notePasDAutres=0;
		}
		if (noteNom+noteAge+notePasDAutres+noteDeclaration==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteNom+noteAge+notePasDAutres+noteDeclaration;
		}
	}








	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test02SpectateurConstructeur() {
		int noteDeclarationMax=10;
		int noteNomMax=45;
		int noteAgeMax=45;
		int noteDeclaration=noteDeclarationMax;
		int noteAge=noteAgeMax;
		int noteNom=noteNomMax;
		System.out.println(" Test verifiant le constructeur de la classe Spectateur");
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldNom=null, fieldAge=null;
		Field[] tf = cSpectateur.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldAge==null) {
						fieldAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldNom==null) {
						fieldNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
					}
				} else {
					System.out.println("   Aie... la classe Spectateur ne doit comporter qu'une variable d'instance de type String" ); 
					System.out.println("   et une variable d'instance de type int");
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Spectateur");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
			}
		}
		if (fieldNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test du constructeur --> arret du test");
			return 0;
		} else if (!fieldNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldNom!=null && !Modifier.isPrivate(fieldNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldNom.getName()+" de Spectateur devrait etre declaree private");
		}
		if (fieldAge==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type int correspondant a l'age du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test du constructeur --> arret du test");
			return 0;
		} else if (!fieldAge.getName().equals("age")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"age\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldAge!=null && !Modifier.isPrivate(fieldAge.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldAge.getName()+" de Spectateur devrait etre declaree private");
		}
		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Spectateur au lieu de 2");
		}
		fieldNom.setAccessible(true);
		fieldAge.setAccessible(true);
		Class[] arg2 = { String.class, int.class };
		Class[] arg3 = { int.class, String.class };
		Constructor c2=null;
		Constructor c3=null;
		try {
			c2 = cSpectateur.getDeclaredConstructor(arg2);
		} catch (NoSuchMethodException e) {
			c2=null;
		} catch (SecurityException e) {
		}
		try {
			c3 = cSpectateur.getDeclaredConstructor(arg3);
		} catch (NoSuchMethodException e) {
			c3=null;
		} catch (SecurityException e) {
		}
		if (c2==null) {
			if (c3!=null) {
				System.out.println("   Aie... je ne trouve pas le constructeur Spectateur(String, int) mais vous avez declare un constructeur Spectateur(int, String)");
				System.out.println("   Respectez a la lecttre le diagramme de classes fourni, y compris l'ordre des parametres");
				return 0;
			} else {
				System.out.println("   Aie... je ne trouve pas le constructeur Spectateur(String, int)");
				return 0;
			}
		}
		if (!Modifier.isPublic(c2.getModifiers())) {
			System.out.println("   Aie... votre constructeur Spectateur(String, int) doit etre public");
			noteDeclaration=noteDeclaration/2;
		}
		c2.setAccessible(true);
		Object[][] params = { {"lucie", Integer.valueOf(5)},
				{"marcelle", Integer.valueOf(84)},
				{"leon", Integer.valueOf(33)},
				{"pauline", Integer.valueOf(14)},
				{"robert", Integer.valueOf(78)},
				{"ignace", Integer.valueOf(103)},
				{"lucas", Integer.valueOf(13)},
				{"zoe", Integer.valueOf(13)},
		};

		for (int i=0; i<params.length && (noteNom>0 || noteAge>0); i++) {
			Object pt1=null;
			String resNom="";
			int resAge=0;
			try {
				pt1 = (c2.newInstance(params[i]));
				resAge = (int)(fieldAge.get(pt1));
				resNom = (String)(fieldNom.get(pt1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resAge!=(int)(params[i][1])) {
				System.out.println("   Aie... apres s = new Spectateur( \""+params[i][0]+"\", "+params[i][1]+")) s a pour age "+resAge+".\n");
				noteAge=0;
			}
			if (!resNom.equals((String)(params[i][0]))) {
				System.out.println("   Aie... apres s = new Spectateur( \""+params[i][0]+"\", "+params[i][1]+")) s a pour nom "+resNom+".\n");
				noteNom=0;
			}
		}
		if (noteDeclaration+noteNom+noteAge==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteNom+noteAge;
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test03SpectateurEstMajeur() {
		int noteDeclarationMax=20;
		int noteComportementMax=80;
		int noteDeclaration=noteDeclarationMax;
		int noteComportement=noteComportementMax;
		System.out.println(" Test verifiant la methode estMajeur de la classe Spectateur");
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldNom=null, fieldAge=null;
		Field[] tf = cSpectateur.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldAge==null) {
						fieldAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldNom==null) {
						fieldNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
					}
				} else {
					System.out.println("   Aie... la classe Spectateur ne doit comporter qu'une variable d'instance de type String" ); 
					System.out.println("   et une variable d'instance de type int");
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Spectateur");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
			}
		}
		if (fieldNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			//			System.out.println("   L'existence de cette variable est indispensable au test du constructeur --> arret du test");
			//			return 0;
		} else if (!fieldNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldNom!=null && !Modifier.isPrivate(fieldNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldNom.getName()+" de Spectateur devrait etre declaree private");
		}
		if (fieldAge==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type int correspondant a l'age du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test de estMajeur --> arret du test");
			return 0;
		} else if (!fieldAge.getName().equals("age")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"age\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldAge!=null && !Modifier.isPrivate(fieldAge.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldAge.getName()+" de Spectateur devrait etre declaree private");
		}
		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Spectateur au lieu de 2");
		}
		if (fieldNom!=null) fieldNom.setAccessible(true);
		if (fieldAge!=null) fieldAge.setAccessible(true);

		Method mEstMajeur=null;
		try {
			Method[] methods = cSpectateur.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().contains("jeu") && mEstMajeur==null) {
					mEstMajeur=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mEstMajeur==null) {
			System.out.println("   Aie... Je ne trouve pas la methode estMajeur de Spectateur");
			return 0;
		}
		if (!Modifier.isPublic(mEstMajeur.getModifiers())) {
			System.out.println("   Aie... la methode estMajeur devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstMajeur.getName().equals("estMajeur")) {
			System.out.println("   Aie... votre methode "+mEstMajeur.getName()+" semble correspondre a la methode");
			System.out.println("   estMajeur que doit comporter Spectateur mais ne porte pas le nom estMajeur.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstMajeur.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mEstMajeur.getName()+" retourne une valeur de type "+mEstMajeur.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mEstMajeur.getParameters().length!=0) {
			System.out.println("   Aie... votre methode "+mEstMajeur.getName()+" prend "+mEstMajeur.getParameters().length+" parametres au lieu de 0");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mEstMajeur.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};
			boolean[] oracle = {false, true, true, false, true, false, false, false, true, true};

			Object[] argssn = {};
			for (int i=0; i<argsOk.length && noteComportement>0; i++) {
				Object pt1=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiator= objenesis.getInstantiatorOf(cSpectateur);
				pt1 = instantiator.newInstance();
				Object reso=null;
				try {
					if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					if (fieldAge!=null) fieldAge.set(pt1,argsOk[i][1]);
					if (mEstMajeur!=null) {
						reso=mEstMajeur.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEstMajeur.getName()+" n'est pas correcte. Elle retourne null sur une instance ayant "+argsOk[i][1]+" pour age");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res!=oracle[i]) {
							System.out.println("   Aie... "+mEstMajeur.getName()+" n'est pas correcte. Elle retourne "+res+" sur une instance ayant "+argsOk[i][1]+" pour age");
							noteComportement=0;
						}
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test04SpectateurEquals() {
		int noteDeclarationMax=20;
		int noteComportementMax=80;
		int noteDeclaration=noteDeclarationMax;
		int noteComportement=noteComportementMax;
		System.out.println(" Test verifiant la methode equals de la classe Spectateur");
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldNom=null, fieldAge=null;
		Field[] tf = cSpectateur.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldAge==null) {
						fieldAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldNom==null) {
						fieldNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
					}
				} else {
					System.out.println("   Aie... la classe Spectateur ne doit comporter qu'une variable d'instance de type String" ); 
					System.out.println("   et une variable d'instance de type int");
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Spectateur");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
			}
		}
		if (fieldNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test de equals --> arret du test");
			return 0;
		} else if (!fieldNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldNom!=null && !Modifier.isPrivate(fieldNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldNom.getName()+" de Spectateur devrait etre declaree private");
		}
		if (fieldAge==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type int correspondant a l'age du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test de equals --> arret du test");
			return 0;
		} else if (!fieldAge.getName().equals("age")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"age\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldAge!=null && !Modifier.isPrivate(fieldAge.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldAge.getName()+" de Spectateur devrait etre declaree private");
		}
		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Spectateur au lieu de 2");
		}
		if (fieldNom!=null) fieldNom.setAccessible(true);
		if (fieldAge!=null) fieldAge.setAccessible(true);

		Method mEquals=null;
		try {
			Method[] methods = cSpectateur.getDeclaredMethods();
			for (Method m : methods) {
				if (m.getReturnType().equals(boolean.class) && m.getName().equals("equals") && mEquals==null) {
					mEquals=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mEquals==null) {
			System.out.println("   Aie... Je ne trouve pas la redefinition de la methode equals dans Spectateur");
			return 0;
		}
		if (!Modifier.isPublic(mEquals.getModifiers())) {
			System.out.println("   Aie... la methode equals devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		//		if (!mEquals.getName().equals("estMajeur")) {
		//			System.out.println("   Aie... votre methode "+mEquals.getName()+" semble correspondre a la methode");
		//			System.out.println("   estMajeur que doit comporter Spectateur mais ne porte pas le nom estMajeur.");
		//			noteDeclaration=noteDeclaration/2;
		//		}
		if (!mEquals.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mEquals.getName()+" retourne une valeur de type "+mEquals.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mEquals.getParameters().length!=1 || !mEquals.getParameters()[0].getType().equals(Object.class)) {
			System.out.println("   Aie... la methode equals doit avoir un unique parametre de type Object");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mEquals.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};
			Object[][] argsOk2 = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(80)},
					{(" le"+"on").substring(1), Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"ronald", Integer.valueOf(78)},
					{"ignacio", Integer.valueOf(38)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"luc", Integer.valueOf(18)},
					{"luc", Integer.valueOf(20)},
			};
			boolean[] oracle = {true, false, true, true, false, false, true, true, false, false};

			Object[] argssn = new Object[1];
			for (int i=0; i<argsOk.length && noteComportement>0; i++) {
				Object pt1=null;
				Object pt2=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiator= objenesis.getInstantiatorOf(cSpectateur);
				pt1 = instantiator.newInstance();
				pt2 = instantiator.newInstance();
				Object reso=null;
				try {
					if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					if (fieldAge!=null) fieldAge.set(pt1,argsOk[i][1]);
					if (fieldNom!=null) fieldNom.set(pt2,argsOk2[i][0]);
					if (fieldAge!=null) fieldAge.set(pt2,argsOk2[i][1]);
					if (mEquals!=null) {
						argssn[0]=pt2;
						reso=mEquals.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEquals.getName()+" n'est pas correcte. p1.equals(p2) retourne null avec p1==(\""+argsOk[i][0]+"\", "+argsOk[i][1]+") et p2==(\""+argsOk2[i][0]+"\", "+argsOk2[i][1]+")");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res!=oracle[i]) {
							System.out.println("   Aie... "+mEquals.getName()+" n'est pas correcte.  p1.equals(p2) retourne "+res+" avec p1==(\""+argsOk[i][0]+"\", "+argsOk[i][1]+") et p2==(\""+argsOk2[i][0]+"\", "+argsOk2[i][1]+")");
							if (i==2) {
								System.out.println("   N'oubliez pas que String est un type objet, pas un type primitif. N'auriez vous pas teste l'egalite de deux String via == ?");
								noteComportement=noteComportement/2;
							} else {
								noteComportement=0;
							}
						}
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
			if (noteComportement>0) {
				Object pt1=null;
			//	Object pt2=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiator= objenesis.getInstantiatorOf(cSpectateur);
				pt1 = instantiator.newInstance();
				//pt2 = instantiator.newInstance();
				Object reso=null;
				try {
					if (fieldNom!=null) fieldNom.set(pt1,argsOk[0][0]);
					if (fieldAge!=null) fieldAge.set(pt1,argsOk[0][1]);
					//if (fieldNom!=null) fieldNom.set(pt2,argsOk2[i][0]);
					//if (fieldAge!=null) fieldAge.set(pt2,argsOk2[i][1]);
					if (mEquals!=null) {
						argssn[0]="dupont";
						reso=mEquals.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEquals.getName()+" n'est pas correcte. p1.equals(\"dupont\") retourne null avec p1==(\""+argsOk[0][0]+"\", "+argsOk[0][1]+")");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res!=false) {
							System.out.println("   Aie... "+mEquals.getName()+" n'est pas correcte.  p1.equals(\"dupont\") retourne true avec p1==(\""+argsOk[0][0]+"\", "+argsOk[0][1]+")");
							noteComportement=0;
						}
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test05SpectateurToString() {
		int noteDeclarationMax=20;
		int noteComportementMax=80;
		int noteDeclaration=noteDeclarationMax;
		int noteComportement=noteComportementMax;
		System.out.println(" Test verifiant la methode toString de la classe Spectateur");
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldNom=null, fieldAge=null;
		Field[] tf = cSpectateur.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldAge==null) {
						fieldAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldNom==null) {
						fieldNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
					}
				} else {
					System.out.println("   Aie... la classe Spectateur ne doit comporter qu'une variable d'instance de type String" ); 
					System.out.println("   et une variable d'instance de type int");
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Spectateur");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
			}
		}
		if (fieldNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test de toString --> arret du test");
			return 0;
		} else if (!fieldNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldNom!=null && !Modifier.isPrivate(fieldNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldNom.getName()+" de Spectateur devrait etre declaree private");
		}
		if (fieldAge==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type int correspondant a l'age du spectateur.");
			System.out.println("   L'existence de cette variable est indispensable au test de toString --> arret du test");
			return 0;
		} else if (!fieldAge.getName().equals("age")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldNom.getName()+"\" au lieu de \"age\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
		}
		if (fieldAge!=null && !Modifier.isPrivate(fieldAge.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldAge.getName()+" de Spectateur devrait etre declaree private");
		}
		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Spectateur au lieu de 2");
		}
		if (fieldNom!=null) fieldNom.setAccessible(true);
		if (fieldAge!=null) fieldAge.setAccessible(true);

		Method mToString=null;
		try {
			Method[] methods = cSpectateur.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) && 
						m.getName().toLowerCase().equals("tostring") && mToString==null) {
					mToString=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mToString==null) {
			System.out.println("   Aie... Je ne trouve pas la redefinition de la methode toString dans Spectateur");
			return 0;
		}
		if (!Modifier.isPublic(mToString.getModifiers())) {
			System.out.println("   Aie... la methode toString devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		//		if (!mEquals.getName().equals("estMajeur")) {
		//			System.out.println("   Aie... votre methode "+mEquals.getName()+" semble correspondre a la methode");
		//			System.out.println("   estMajeur que doit comporter Spectateur mais ne porte pas le nom estMajeur.");
		//			noteDeclaration=noteDeclaration/2;
		//		}
		if (!mToString.getReturnType().equals(String.class)) {
			System.out.println("   Aie... votre methode "+mToString.getName()+" retourne une valeur de type "+mToString.getReturnType().getName()+" au lieu de retourner une String");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mToString.getParameters().length!=0 ) {
			System.out.println("   Aie... la methode toString ne doit pas comporter de parametre");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mToString.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};

			Object[] argssn = {};//new Object[1];
			for (int i=0; i<argsOk.length && noteComportement>0; i++) {
				Object pt1=null;
				String res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiator= objenesis.getInstantiatorOf(cSpectateur);
				pt1 = instantiator.newInstance();
				Object reso=null;
				try {
					if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					if (fieldAge!=null) fieldAge.set(pt1,argsOk[i][1]);
					if (mToString!=null) {
						//	argssn[0]=pt2;
						reso=mToString.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. p1.toString() retourne null avec p1==(\""+argsOk[i][0]+"\", "+argsOk[i][1]+") ");
						noteComportement= 0;
					} else {
						res = (String)reso;
						if (!res.contains((String)(argsOk[i][0]))) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.  p1.toString() retourne \""+res+"\" avec p1==(\""+argsOk[i][0]+"\", "+argsOk[i][1]+")");
							System.out.println("   alors que la chaine retournee devrait mentionner le nom \""+argsOk[i][0]+"\"");
							noteComportement=0;
						}
						if (!res.contains(""+(Integer)(argsOk[i][1]))) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.  p1.toString() retourne \""+res+"\" avec p1==(\""+argsOk[i][0]+"\", "+argsOk[i][1]+")");
							System.out.println("   alors que la chaine retournee devrait mentionner l'age "+argsOk[i][01]);
							noteComportement=0;
						}
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}


	@SuppressWarnings({ "rawtypes" })
	public static int test06SalleDeclaration() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		int noteCapaciteMax=30;
		int noteNomMax=30;
		int noteListeMax=30;
		int noteDeclaration=0;//10;
		int noteCapacitege=noteCapaciteMax;
		int noteNom=noteNomMax;
		int noteListe=noteListeMax;
		int notePasDAutres=10;
		System.out.println(" Test verifiant la declaration des variables d'instance de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			noteCapacitege=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null &&!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			notePasDAutres=0;
		}
		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
			notePasDAutres=0;
		}
		if (noteNom+noteCapacitege+notePasDAutres+noteDeclaration+noteListe==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteNom+noteCapacitege+notePasDAutres+noteDeclaration+noteListe;
		}
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test07SalleConstructeur() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		int noteCapaciteMax=30;
		int noteNomMax=30;
		int noteListeMax=30;
		int noteDeclaration=10;
		int noteCapacite=noteCapaciteMax;
		int noteNom=noteNomMax;
		int noteListe=noteListeMax;
		//	int notePasDAutres=15;
		System.out.println(" Test verifiant le constructeur de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			//	notePasDAutres=0;
		}

		//		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
		//		//	notePasDAutres=0;
		//		}



		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);
		Class[] arg2 = { String.class, int.class };
		Class[] arg3 = { int.class, String.class };
		Constructor c2=null;
		Constructor c3=null;
		try {
			c2 = cSalle.getDeclaredConstructor(arg2);
		} catch (NoSuchMethodException e) {
			c2=null;
		} catch (SecurityException e) {
		}
		try {
			c3 = cSalle.getDeclaredConstructor(arg3);
		} catch (NoSuchMethodException e) {
			c3=null;
		} catch (SecurityException e) {
		}
		if (c2==null) {
			if (c3!=null) {
				System.out.println("   Aie... je ne trouve pas le constructeur Salle(String, int) mais vous avez declare un constructeur Salle(int, String)");
				System.out.println("   Respectez a la lecttre le diagramme de classes fourni, y compris l'ordre des parametres");
				return 0;
			} else {
				System.out.println("   Aie... je ne trouve pas le constructeur Salle(String, int)");
				return 0;
			}
		}
		if (!Modifier.isPublic(c2.getModifiers())) {
			System.out.println("   Aie... votre constructeur Salle(String, int) doit etre public");
			noteDeclaration=noteDeclaration/2;
		}
		c2.setAccessible(true);
		Object[][] params = { {"carnot", Integer.valueOf(120)},
				{"kastler", Integer.valueOf(200)},
				{"charpak", Integer.valueOf(200)},
				{"besse", Integer.valueOf(80)},
				{"A", Integer.valueOf(30)},
				{"B", Integer.valueOf(30)},
		};

		for (int i=0; i<params.length && (noteNom>0 || noteCapacite>0 || noteListe>0); i++) {
			Object pt1=null;
			String resNom="";
			int resCapacite=0;
			List resListe=null;
			try {
				pt1 = (c2.newInstance(params[i]));
				if (noteCapacite>0) {
					resCapacite = (int)(fieldSalleCapacite.get(pt1));
				}
				if (noteNom>0) {
					resNom = (String)(fieldSalleNom.get(pt1));
				}
				if (noteListe>0) {
					resListe = (List)(fieldSalleSpectateurs.get(pt1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (noteCapacite>0 && resCapacite!=(int)(params[i][1])) {
				System.out.println("   Aie... apres s = new Salle( \""+params[i][0]+"\", "+params[i][1]+")) s a pour capacite "+resCapacite+".\n");
				noteCapacite=0;
			}
			if (noteNom>0 && (resNom==null ||!resNom.equals((String)(params[i][0])))) {
				System.out.println("   Aie... apres s = new Salle( \""+params[i][0]+"\", "+params[i][1]+")) s a pour nom "+resNom+".\n");
				noteNom=0;
			}
			if (noteListe>0) {
				if (resListe==null) {
					System.out.println("   Aie... apres s = new Salle( \""+params[i][0]+"\", "+params[i][1]+")) s sa variable "+fieldSalleSpectateurs.getName()+" qui vaut null au lieu de referencer une liste vide");
					noteListe=0;
				} else if (resListe.size()!=0){
					System.out.println("   Aie... apres s = new Salle( \""+params[i][0]+"\", "+params[i][1]+")) s sa variable "+fieldSalleSpectateurs.getName()+" qui reference une liste de taille "+resListe.size()+" au lieu d'une liste vide");
					noteListe=0;
				}
			}
		}

		if (noteNom+noteCapacite+noteDeclaration+noteListe==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteNom+noteCapacite+noteDeclaration+noteListe;
		}
	}





	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test08SalleEstPleine() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int noteCapaciteMax=30;
		//		int noteNomMax=30;
		//		int noteListeMax=30;
		int noteDeclaration=10;
		int noteComportement=90;
		//		int noteCapacite=noteCapaciteMax;
		//		int noteNom=noteNomMax;
		//		int noteListe=noteListeMax;
		//	int notePasDAutres=15;
		System.out.println(" Test verifiant la methode estPleine de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de estPleine --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null;
		Field[] tfs = cSpectateur.getDeclaredFields();
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						return 0;
					}
				} 
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester estPleine --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			//	notePasDAutres=0;
		}

		//		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
		//		//	notePasDAutres=0;
		//		}



		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mEstPleine=null;
		try {
			Method[] methods = cSalle.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().contains("ein") && mEstPleine==null) {
					mEstPleine=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mEstPleine==null) {
			System.out.println("   Aie... Je ne trouve pas la methode estPleine de Salle");
			return 0;
		}
		if (!Modifier.isPublic(mEstPleine.getModifiers())) {
			System.out.println("   Aie... la methode estPleine devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPleine.getName().equals("estPleine")) {
			System.out.println("   Aie... votre methode "+mEstPleine.getName()+" semble correspondre a la methode");
			System.out.println("   estPleine que doit comporter Salle mais ne porte pas le nom estPleine.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPleine.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mEstPleine.getName()+" retourne une valeur de type "+mEstPleine.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mEstPleine.getParameters().length!=0) {
			System.out.println("   Aie... votre methode "+mEstPleine.getName()+" prend "+mEstPleine.getParameters().length+" parametres au lieu de 0");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mEstPleine.setAccessible(true);

		if (noteComportement>0) {
			Object[][] params = { {"carnot", Integer.valueOf(120)},
					{"kastler", Integer.valueOf(200)},
					{"charpak", Integer.valueOf(200)},
					{"besse", Integer.valueOf(80)},
					{"A", Integer.valueOf(30)},
					{"B", Integer.valueOf(30)},
			};
			Integer[] tailleTest = {120, 120, 200, 79, 30, 29};

			boolean[] oracle = {true, false, true, false, true, false};

			Object[] argssn = {};
			for (int i=0; i<params.length && noteComportement>0; i++) {
				Object pt1=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				Object reso=null;
				try {
					//if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					for (int j=0; j<tailleTest[i]; j++) {
						Object spec = instantiatorSpec.newInstance();
						fieldSpectateurNom.set(spec, "S"+j);
						la.add(spec);
						ll.add(spec);
					}
					if (fieldSalleSpectateurs.getType().getName().contains("ked")) {
						fieldSalleSpectateurs.set(pt1, ll);
					} else if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
					} else {
						fieldSalleSpectateurs.set(pt1, ll);
						//						System.out.println("   Veuillez utiliser soit une ArrayList, soit une LinkedList pour la liste des spectateurs de la salle");
						//						System.out.println("   type = "+fieldSalleSpectateurs.getType().getName());
						//						return 0;
					}
					if (mEstPleine!=null) {
						reso=mEstPleine.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEstPleine.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res!=oracle[i]) {
							System.out.println("   Aie... "+mEstPleine.getName()+" n'est pas correcte. Elle retourne "+res+" sur une salle de capacite "+params[i][1]+" contenant "+tailleTest[i]+" spectateurs");
							noteComportement=0;
						}
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}





	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test09SallePlacer() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int noteCapaciteMax=30;
		//		int noteNomMax=30;
		//		int noteListeMax=30;
		int noteDeclaration=10;
		int noteComportement=90;
		//		int noteCapacite=noteCapaciteMax;
		//		int noteNom=noteNomMax;
		//		int noteListe=noteListeMax;
		//	int notePasDAutres=15;
		System.out.println(" Test verifiant la methode placer de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de placer --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfs = cSpectateur.getDeclaredFields();
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester placer --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test dans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			//	notePasDAutres=0;
		}

		//		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
		//		//	notePasDAutres=0;
		//		}



		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mPlacer=null;
		try {
			Method[] methods = cSalle.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().contains("lacer") && mPlacer==null) {
					mPlacer=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mPlacer==null) {
			System.out.println("   Aie... Je ne trouve pas la methode placer de Salle");
			return 0;
		}
		if (!Modifier.isPublic(mPlacer.getModifiers())) {
			System.out.println("   Aie... la methode placer devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mPlacer.getName().equals("placer")) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" semble correspondre a la methode");
			System.out.println("   placer que doit comporter Salle mais ne porte pas le nom placer.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mPlacer.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" retourne une valeur de type "+mPlacer.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mPlacer.getParameters().length!=1 || !mPlacer.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mPlacer.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};
			Object[][] params = { {"carnot", Integer.valueOf(3)},
					{"kastler", Integer.valueOf(2)},
					{"charpak", Integer.valueOf(8)},
					{"besse", Integer.valueOf(7)},
					{"A", Integer.valueOf(9)},
					{"B", Integer.valueOf(10)},
			};
			//Integer[] tailleTest = {120, 120, 200, 79, 30, 29};

			boolean[][] oracle = {
					{true, true, true, false, false, false, false, false, false, false},
					{true, true, false, false, false, false, false, false, false, false},
					{true, true, true, true, true, true, true, true, false, false},
					{true, true, true, true, true, true, true, false, false, false},
					{true, true, true, true, true, true, true, true, true, false},
					{true, true, true, true, true, true, true, true, true, true},
			};

			Object[] argssn = new Object[1];
			for (int i=0; i<params.length && noteComportement>0; i++) {
				Object pt1=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				Object reso=null;
				try {
					//if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("ked")) {
						fieldSalleSpectateurs.set(pt1, ll);
					} else if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
					} else {
						fieldSalleSpectateurs.set(pt1, ll);
						//						System.out.println("   Veuillez utiliser soit une ArrayList, soit une LinkedList pour la liste des spectateurs de la salle");
						//						System.out.println("   type = "+fieldSalleSpectateurs.getType().getName());
						//						return 0;
					}
					for (int j=0; j<argsOk.length && noteComportement>0; j++) {
						Object spec = instantiatorSpec.newInstance();
						fieldSpectateurNom.set(spec, argsOk[j][0]);
						if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);
						if (mPlacer!=null) {
							argssn[0]=spec;
							reso=mPlacer.invoke(pt1,  argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (boolean)reso;
							if (res!=oracle[i][j]) {
								System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
								System.out.println("   Sur une salle de capacite "+params[i][1]+" et apres "+(j)+" placements dans cette salle");
								System.out.println("   la methode "+mPlacer.getName()+" retourne "+res);
								noteComportement=0;
							} else {
								List ls = (List)fieldSalleSpectateurs.get(pt1);
								if (res==true && !ls.contains(spec)) {
									System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
									System.out.println("   Sur une salle de capacite "+params[i][1]+" et apres "+(j)+" placements dans cette salle");
									System.out.println("   la methode "+mPlacer.getName()+" retourne bel et bien true");
									System.out.println("   mais sans avoir ajoute le parametre dans la liste des spectateurs de la salle");
									noteComportement=0;
								} else if (res==false && ls.contains(spec)) {
									System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
									System.out.println("   Sur une salle de capacite "+params[i][1]+" et apres "+(j)+" placements dans cette salle");
									System.out.println("   la methode "+mPlacer.getName()+" retourne bel et bien false");
									System.out.println("   mais a ajoute le parametre dans la liste des spectateurs de la salle alors que la salle est deja pleine");
									noteComportement=0;
								}
							}
						}
						//	la.add(spec);
						//	ll.add(spec);
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test10SalleEstPresent() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int noteCapaciteMax=30;
		//		int noteNomMax=30;
		//		int noteListeMax=30;
		int noteDeclaration=10;
		int noteComportement=90;
		//		int noteCapacite=noteCapaciteMax;
		//		int noteNom=noteNomMax;
		//		int noteListe=noteListeMax;
		//	int notePasDAutres=15;
		System.out.println(" Test verifiant la methode estPresent de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de estPresent --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfs = cSpectateur.getDeclaredFields();
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester estPresent --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			//	notePasDAutres=0;
		}

		//		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
		//		//	notePasDAutres=0;
		//		}



		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mEstPresent=null;
		try {
			Method[] methods = cSalle.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().contains("resen") && mEstPresent==null) {
					mEstPresent=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mEstPresent==null) {
			System.out.println("   Aie... Je ne trouve pas la methode estPresent de Salle");
			return 0;
		}
		if (!Modifier.isPublic(mEstPresent.getModifiers())) {
			System.out.println("   Aie... la methode estPresent devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPresent.getName().equals("estPresent")) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" semble correspondre a la methode");
			System.out.println("   estPresent que doit comporter Salle mais ne porte pas le nom estPresent.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPresent.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" retourne une valeur de type "+mEstPresent.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mEstPresent.getParameters().length!=1 || !mEstPresent.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mEstPresent.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};
//			Object[][] params = { {"carnot", Integer.valueOf(3)},
//					{"kastler", Integer.valueOf(2)},
//					{"charpak", Integer.valueOf(8)},
//					{"besse", Integer.valueOf(7)},
//					{"A", Integer.valueOf(9)},
//					{"B", Integer.valueOf(10)},
//			};
//			Integer[] tailleTest = {120, 120, 200, 79, 30, 29};
//
//			boolean[][] oracle = {
//					{true, true, true, false, false, false, false, false, false, false},
//					{true, true, false, false, false, false, false, false, false, false},
//					{true, true, true, true, true, true, true, true, false, false},
//					{true, true, true, true, true, true, true, false, false, false},
//					{true, true, true, true, true, true, true, true, true, false},
//					{true, true, true, true, true, true, true, true, true, true},
//			};

			Object[] argssn = new Object[1];
			List tous = new ArrayList();
			for (int i=0; i<argsOk.length && noteComportement>0; i++) {
				Object s=null;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				s=instantiatorSpec.newInstance();
				try {
					fieldSpectateurNom.set(s, argsOk[i][0]);
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(s,  argsOk[i][1]);
					tous.add(s);
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}

			}
			for (int i=0; i<argsOk.length && noteComportement>0; i++) {
				Object pt1=null;
				boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				//ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				Object reso=null;
				try {
					if (fieldSalleNom!=null) fieldSalleNom.set(pt1,"Kastler");
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,1000);
					//if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					//if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("ked")) {
						fieldSalleSpectateurs.set(pt1, ll);
					} else if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
					} else {
						fieldSalleSpectateurs.set(pt1, ll);
						//						System.out.println("   Veuillez utiliser soit une ArrayList, soit une LinkedList pour la liste des spectateurs de la salle");
						//						System.out.println("   type = "+fieldSalleSpectateurs.getType().getName());
						//						return 0;
					}
					for (int j=0; j<=i && noteComportement>0; j++) {
						ll.add(tous.get(j));
						la.add(tous.get(j));
					}
					for (int j=0; j<argsOk.length && noteComportement>0; j++) {
						//	Object spec = instantiatorSpec.newInstance();
						//	fieldSpectateurNom.set(spec, argsOk[j][0]);
						//	if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);

						if (mEstPresent!=null) {
							argssn[0]=tous.get(j);
							reso=mEstPresent.invoke(pt1,  argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (boolean)reso;
							if (res!=(j<=i)) {//oracle[i][j]) {
								System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte.");
								System.out.println("   Sur une salle ayant pour liste de spectateurs : "+la);
								System.out.println("   la methode "+mEstPresent.getName()+"("+tous.get(j)+") retourne "+res);
								noteComportement=0;
							} 
						}
						//	la.add(spec);
						//	ll.add(spec);
					}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test11SalleToString() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int noteCapaciteMax=30;
		//		int noteNomMax=30;
		//		int noteListeMax=30;
		int noteDeclaration=10;
		int noteComportement=90;
		//		int noteCapacite=noteCapaciteMax;
		//		int noteNom=noteNomMax;
		//		int noteListe=noteListeMax;
		//	int notePasDAutres=15;
		System.out.println(" Test verifiant la methode toString de la classe Salle");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de toString --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfs = cSpectateur.getDeclaredFields();
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						//	return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						//	return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			//	System.out.println("   Sans cette variable d'instance il est impossible de tester toString --> Arret du test");
			//	return 0;
		}	
		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tf = cSalle.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			System.out.println("   Impossible de poursuivre le test");
			return 0;
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=3) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Salle au lieu de 3");
			//	notePasDAutres=0;
		}

		//		if (noteNom<noteNomMax || noteCapacitege<noteCapaciteMax || noteListe<noteListeMax) {
		//		//	notePasDAutres=0;
		//		}



		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mToString=null;
		try {
			Method[] methods = cSalle.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().equals("toString") && mToString==null) {
					mToString=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mToString==null) {
			System.out.println("   Aie... Je ne trouve pas la redefinition de la methode toString de Salle");
			return 0;
		}
		if (!Modifier.isPublic(mToString.getModifiers())) {
			System.out.println("   Aie... la methode toString devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		//		if (!mToString.getName().equals("estPresent")) {
		//			System.out.println("   Aie... votre methode "+mToString.getName()+" semble correspondre a la methode");
		//			System.out.println("   estPresent que doit comporter Salle mais ne porte pas le nom estPresent.");
		//			noteDeclaration=noteDeclaration/2;
		//		}
		if (!mToString.getReturnType().equals(String.class)) {
			System.out.println("   Aie... votre methode "+mToString.getName()+" retourne une valeur de type "+mToString.getReturnType().getName()+" au lieu de retourner une String");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mToString.getParameters().length!=0) {//1 || !mToString.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mToString.getName()+" ne doit pas prendre de parametre ");//doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mToString.setAccessible(true);

		if (noteComportement>0) {
			//			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
			//					{"marcelle", Integer.valueOf(84)},
			//					{"leon", Integer.valueOf(33)},
			//					{"pauline", Integer.valueOf(4)},
			//					{"robert", Integer.valueOf(78)},
			//					{"ignace", Integer.valueOf(3)},
			//					{"lucas", Integer.valueOf(17)},
			//					{"zoe", Integer.valueOf(13)},
			//					{"lucien", Integer.valueOf(18)},
			//					{"luc", Integer.valueOf(19)},
			//			};
			Object[][] params = { {"carnot", Integer.valueOf(1000)},
					{"kastler", Integer.valueOf(1000)},
					{"charpak", Integer.valueOf(1000)},
					{"besse", Integer.valueOf(1000)},
					{"A", Integer.valueOf(1000)},
					{"B", Integer.valueOf(1000)},
			};
			//			Integer[] tailleTest = {120, 120, 200, 79, 30, 29};
			//
			//			boolean[][] oracle = {
			//					{true, true, true, false, false, false, false, false, false, false},
			//					{true, true, false, false, false, false, false, false, false, false},
			//					{true, true, true, true, true, true, true, true, false, false},
			//					{true, true, true, true, true, true, true, false, false, false},
			//					{true, true, true, true, true, true, true, true, true, false},
			//					{true, true, true, true, true, true, true, true, true, true},
			//			};

			Object[] argssn =  {};// new Object[1];
			List tous = new ArrayList();
			for (int i=0; i<1000 && noteComportement>0; i++) {
				Object s=null;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				s=instantiatorSpec.newInstance();
				try {
					fieldSpectateurNom.set(s, "s"+i);
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(s,  (int)(Math.random()*100));
					tous.add(s);
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}

			}
			for (int i=0; i<params.length && noteComportement>0; i++) {
				Object pt1=null;
				String res;

				Objenesis objenesis = new ObjenesisStd(); 
				//ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				Object reso=null;
				try {
					if (fieldSalleNom!=null) fieldSalleNom.set(pt1,params[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					//if (fieldNom!=null) fieldNom.set(pt1,argsOk[i][0]);
					//if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("ked")) {
						fieldSalleSpectateurs.set(pt1, ll);
					} else if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
					} else {
						fieldSalleSpectateurs.set(pt1, ll);
						//						System.out.println("   Veuillez utiliser soit une ArrayList, soit une LinkedList pour la liste des spectateurs de la salle");
						//						System.out.println("   type = "+fieldSalleSpectateurs.getType().getName());
						//						return 0;
					}
					int sizeR = (int)(Math.random()*900);
					for (int j=0; j<sizeR && noteComportement>0; j++) {
						ll.add(tous.get(j));
						la.add(tous.get(j));
					}
					//for (int j=0; j<argsOk.length && noteComportement>0; j++) {
					//	Object spec = instantiatorSpec.newInstance();
					//	fieldSpectateurNom.set(spec, argsOk[j][0]);
					//	if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);

					if (mToString!=null) {
						//	argssn[0]=tous.get(j);
						reso=mToString.invoke(pt1,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (String)reso;
						if (!res.contains((String)(params[i][0])) ||!res.contains(""+sizeR) ) {//oracle[i][j]) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
							System.out.println("   Sur une salle ayant pour nom :\""+params[i][0]+"\"");
							System.out.println("   et ayant une liste de "+sizeR+" spectateurs ");
							System.out.println("   la methode "+mToString.getName()+"() retourne \""+res+"\"");
							noteComportement=0;
							if (!res.contains((String)(params[i][0]))) {
								System.out.println("   la chaine retournee devrait comporter le nom de la salle");
							} else {
								System.out.println("   la chaine retournee devrait comporter le nombre de spectateurs presents");
							}
						} 
					}
					//	la.add(spec);
					//	ll.add(spec);
					//	}
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
		}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}
	}


	@SuppressWarnings({ "rawtypes" })
	public static int test12CinemaDeclaration() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		int notePasDAutres=10;
		int noteNomMax=40;
		int noteListeMax=40;
		int noteDeclaration=10;
		int noteNom=noteNomMax;
		int noteListe=noteListeMax;
		System.out.println(" Test verifiant la declaration des variables d'instance de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			notePasDAutres=0;
		}
		if (noteNom<noteNomMax || noteListe<noteListeMax) {
			notePasDAutres=0;
		}
		if (noteNom+notePasDAutres+noteDeclaration+noteListe==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteNom+notePasDAutres+noteDeclaration+noteListe;
		}
	}





	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test13CinemaConstructeur() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int notePasDAutres=10;
		int noteNomMax=45;
		int noteListeMax=45;
		int noteDeclaration=10;
		int noteNom=noteNomMax;
		int noteListe=noteListeMax;
		System.out.println(" Test verifiant le constructeur de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}
		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tfs = cSalle.getDeclaredFields();
		//int nbv=0;
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				//	nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}

		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						//		notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					//		notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			//	notePasDAutres=0;
		}
		//		if (noteNom<noteNomMax || noteListe<noteListeMax) {
		//			notePasDAutres=0;
		//		}



		if (fieldCinemaNom!=null) fieldCinemaNom.setAccessible(true);
		if (fieldCinemaSalles!=null) fieldCinemaSalles.setAccessible(true);
		//		Class[] arg2 = { String.class, int.class };
		//		Class[] arg3 = { int.class, String.class };
		Constructor c2=null;
		//		Constructor c3=null;
		Constructor[] constructeurs = null;
		try {
			constructeurs = cCinema.getDeclaredConstructors();
			//le.getDeclaredConstructor(arg2);
		} catch (Exception e) {
			c2=null;
		} 
		if (constructeurs==null || constructeurs.length!=1) {
			System.out.println("   Aie... la classe Cinema doit comporter un unique constructeur");
			return 0;
		} else {
			c2 = constructeurs[0];  
		}
		Parameter[] parameters = null;
		if (c2.getParameters()!=null) {
			parameters=c2.getParameters();
		}
		if (parameters==null || parameters.length!=2 
				|| !parameters[0].getType().equals(String.class)
				|| !parameters[1].getType().getName().contains("[Lpacknp.cinema.Salle")) {
			System.out.println("   Aie... le constructeur de la classe Cinema doit avoir une String pour premier parametre");
			System.out.println("   et un tableau de salles pour second parametre");
			if (parameters.length>0) System.out.println(parameters[1].getType().getName());
			return 0;
		}
		//		if (c2.getParameters()==null) {
		//			if (c3!=null) {
		//				System.out.println("   Aie... je ne trouve pas le constructeur Salle(String, int) mais vous avez declare un constructeur Salle(int, String)");
		//				System.out.println("   Respectez a la lecttre le diagramme de classes fourni, y compris l'ordre des parametres");
		//				return 0;
		//			} else {
		//				System.out.println("   Aie... je ne trouve pas le constructeur Salle(String, int)");
		//				return 0;
		//			}
		//		}
		if (!Modifier.isPublic(c2.getModifiers())) {
			System.out.println("   Aie... votre constructeur Cinema(String, Salle[]) doit etre public");
			noteDeclaration=noteDeclaration/2;
		}
		c2.setAccessible(true);
		Object[][] params = { {"carnot", Integer.valueOf(120)},
				{"kastler", Integer.valueOf(200)},
				{"charpak", Integer.valueOf(200)},
				{"besse", Integer.valueOf(80)},
				{"A", Integer.valueOf(30)},
				{"B", Integer.valueOf(30)},
		};
		List sallesa = new ArrayList();
		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);
		for (int i=0; i<params.length ; i++) {
			Object s=null;
			Objenesis objenesis = new ObjenesisStd(); 
			ObjectInstantiator instantiatorSall= objenesis.getInstantiatorOf(cSalle);
			s=instantiatorSall.newInstance();
			try {
				if (fieldSalleNom!=null) fieldSalleNom.set(s, params[i][0]);
				if (fieldSalleCapacite!=null) fieldSalleCapacite.set(s,  params[i][1]);
				if (fieldSalleSpectateurs!=null) {//fieldSalleSpectateurs.set(s,  new ArrayList());
					if (fieldSalleSpectateurs.getType().getName().contains("ked")) {
						fieldSalleSpectateurs.set(s, new LinkedList());
					} else if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(s, new ArrayList());
					} else {
						fieldSalleSpectateurs.set(s, new LinkedList());
					}
				}
				sallesa.add(s);
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}


		}

		Object[] par = new Object[2];

		for (int i=0; i<20 && (noteNom>0 || noteListe>0); i++) {
			Object pt1=null;
			String resNom="";
		//	int resCapacite=0;
			List resListe=null;
			try {
				List sal = null;
				if (fieldCinemaSalles!=null) {//fieldSalleSpectateurs.set(s,  new ArrayList());
					if (fieldCinemaSalles.getType().getName().contains("rray")) {
						sal = new ArrayList();
					} else {
						sal= new LinkedList();
					}
				}
				int first = (int)(Math.random()*3);
				int last = first+ (int)(Math.random()*3);
				Salle[] par1 = new Salle[1+last-first];
				for (int isalle=first; isalle<=last; isalle++) {
					sal.add(sallesa.get(isalle));
					par1[isalle-first]=(Salle)(sallesa.get(isalle));
				}
				par[0]="Cine"+i;
				par[1]=par1;

				pt1 = (c2.newInstance(par));

				if (noteNom>0) {
					resNom = (String)(fieldCinemaNom.get(pt1));
					if ((resNom==null ||!resNom.equals((String)(par[0])))) {
						System.out.println("   Aie... apres c = new Cinema( \""+par[0]+"\", "+Arrays.toString(par1)+")) c a pour nom "+resNom+".\n");
						noteNom=0;
					}

				}
				if (noteListe>0) {
					resListe = (List)(fieldCinemaSalles.get(pt1));
					if (resListe==null) {
						System.out.println("   Aie... apres c = new Cinema( \""+par[0]+"\", "+Arrays.toString(par1)+")) c a sa variable "+fieldCinemaSalles.getName()+" qui vaut null au lieu de referencer une liste vide");
						noteListe=0;
					} else if (!resListe.equals(sal)){
						System.out.println("   Aie... apres s = new Salle( \""+par[0]+"\", "+Arrays.toString(par1)+")) c a sa variable "+fieldCinemaSalles.getName()+" qui reference la liste "+resListe+" au lieu de "+sal);
						noteListe=0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}




		if (noteNom+noteDeclaration+noteListe==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteNom+noteDeclaration+noteListe;
		}
	}





	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test14CinemaPlacer() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int notePasDAutres=10;

		int noteDeclaration=10;
		int noteComportement=90;

		System.out.println(" Test verifiant la methode placer de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}

		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de placer --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfss = cSpectateur.getDeclaredFields();
		for (Field f : tfss) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						//				return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						//				return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester placer --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}
		//	Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		//	Field[] tf = cSalle.getDeclaredFields();
		//	int nbv=0;
		//	for (Field f : tf) {
		//		if (!Modifier.isStatic(f.getModifiers())) {
		//			nbv++;
		//			if (f.getType().equals(int.class)) {
		//				if (fieldSalleCapacite==null) {
		//					fieldSalleCapacite = f;
		//				} else {
		//					System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
		//					//	notePasDAutres=0;
		//				}
		//			} else if (f.getType().equals(String.class)) {
		//				if (fieldSalleNom==null) {
		//					fieldSalleNom = f;
		//				} else {
		//					System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
		//					//	notePasDAutres=0;
		//				}
		//			} else if (f.getType().getName().toUpperCase().contains("LIST")) {
		//				if (fieldSalleSpectateurs==null) {
		//					fieldSalleSpectateurs = f;
		//				} else {
		//					System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
		//					//	notePasDAutres=0;
		//				}
		//			} else {
		//				System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
		//				System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
		//				//	notePasDAutres=0;
		//			}
		//		} else {
		//			System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
		//			System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
		//			//	notePasDAutres= 0;
		//		}
		//	}

		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tfs = cSalle.getDeclaredFields();
		//int nbv=0;
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				//	nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}

		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						//		notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					//		notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			//	noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			//	noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			//	notePasDAutres=0;
		}
		//		if (noteNom<noteNomMax || noteListe<noteListeMax) {
		//			notePasDAutres=0;
		//		}


		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) fieldSpectateurAge.setAccessible(true);
		if (fieldCinemaNom!=null) fieldCinemaNom.setAccessible(true);
		if (fieldCinemaSalles!=null) fieldCinemaSalles.setAccessible(true);
		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mPlacer=null;
		try {
			Method[] methods = cCinema.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().contains("lacer") && mPlacer==null) {
					mPlacer=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mPlacer==null) {
			System.out.println("   Aie... Je ne trouve pas la methode placer de Cinema");
			return 0;
		}
		if (!Modifier.isPublic(mPlacer.getModifiers())) {
			System.out.println("   Aie... la methode placer devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mPlacer.getName().equals("placer")) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" semble correspondre a la methode");
			System.out.println("   placer que doit comporter Cinema mais ne porte pas le nom placer.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mPlacer.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" retourne une valeur de type "+mPlacer.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mPlacer.getParameters().length!=1 || !mPlacer.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mPlacer.getName()+" doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mPlacer.setAccessible(true);

		if (noteComportement>0) {
			Object[][] params = { {"carnot", Integer.valueOf(3)},
					{"kastler", Integer.valueOf(2)},
					{"charpak", Integer.valueOf(8)},
					{"besse", Integer.valueOf(7)},
					{"A", Integer.valueOf(9)},
					{"B", Integer.valueOf(10)},
			};

			Object[] argssn = new Object[1];
			ArrayList lsa = new ArrayList();
			LinkedList lsl = new LinkedList();
			List toutesListesSpectateurA = new ArrayList();

			for (int i=0; i<params.length ; i++) {
				Object pt1=null;
				//boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				//ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				//Object reso=null;
				try {
					if (fieldSalleNom!=null) fieldSalleNom.set(pt1,params[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
						toutesListesSpectateurA.add(la);
					} else  {
						fieldSalleSpectateurs.set(pt1, ll);
						toutesListesSpectateurA.add(ll);
					}
					lsa.add(pt1);
					lsl.add(pt1);
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
			Object pt1c=null;
			Objenesis objenesisc = new ObjenesisStd(); 
			ObjectInstantiator instantiatorCinema= objenesisc.getInstantiatorOf(cCinema);
			pt1c = instantiatorCinema.newInstance();
			try {
				if (fieldCinemaNom!=null) fieldCinemaNom.set(pt1c,"Pathe");
				if (fieldCinemaSalles.getType().getName().contains("rray")) {
					fieldCinemaSalles.set(pt1c, lsa);
				} else  {
					fieldCinemaSalles.set(pt1c, lsl);
				}
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
			// A ce stade, pt1c est le cinema Path comportant les salles dont les listes de spectateurs sont toutesListesSpectateurs
			// Capacite totale 39
			//			for (int i=0; i<50 && noteComportement>0; i++) {
			//				Object pt1=null;
			boolean res;
			Objenesis objenesis = new ObjenesisStd(); 
			ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
			//				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
			//				pt1 = instantiatorSalle.newInstance();
			Object reso=null;
			try {

				for (int j=0; j<50 && noteComportement>0; j++) {
					Object spec = instantiatorSpec.newInstance();

					if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, "Sp"+j);
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  1+(int)(Math.random()*80));
					if (mPlacer!=null) {
						argssn[0]=spec;
						reso=mPlacer.invoke(pt1c,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res!=(j<39)) {
							System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
							System.out.println("   Sur un Cinema d'une capacite total de 39 places et apres "+(j)+" placements dans ce cinema");
							System.out.println("   la methode "+mPlacer.getName()+" retourne "+res);
							noteComportement=0;
						} else {
							int nbPresents=0;
							boolean presen=false;
							for (int k=0;k<toutesListesSpectateurA.size(); k++) {
								List lk=(List)(toutesListesSpectateurA.get(k));
								nbPresents+=lk.size();
								presen=presen||lk.contains(spec);
							}
							//								List ls = (List)fieldSalleSpectateurs.get(pt1);
							if (j<39 && !presen) {
								System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema d'une capacite total de 39 places et apres "+(j)+" placements dans ce cinema");
								System.out.println("   apres l'appel  placer(spec) aucune salle ne contient spec");
								noteComportement=0;
							}
							if (noteComportement>0 && ((j>=39 && nbPresents!=39)||(j<39 && nbPresents!=j+1))) {
								System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema d'une capacite total de 39 places et apres "+(j)+" placements dans ce cinema");
								System.out.println("   apres un appel supplementaire a placer");	
								System.out.println("   le cinema contient "+nbPresents+" spectateurs");
								noteComportement=0;
							}
							//								if (res==true && !ls.contains(spec)) {
							//									System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
							//									System.out.println("   Sur une salle de capacite "+params[i][1]+" et apres "+(j)+" placements dans cette salle");
							//									System.out.println("   la methode "+mPlacer.getName()+" retourne bel et bien true");
							//									System.out.println("   mais sans avoir ajoute le parametre dans la liste des spectateurs de la salle");
							//									noteComportement=0;
							//								} else if (res==false && ls.contains(spec)) {
							//									System.out.println("   Aie... "+mPlacer.getName()+" n'est pas correcte.");
							//									System.out.println("   Sur une salle de capacite "+params[i][1]+" et apres "+(j)+" placements dans cette salle");
							//									System.out.println("   la methode "+mPlacer.getName()+" retourne bel et bien false");
							//									System.out.println("   mais a ajoute le parametre dans la liste des spectateurs de la salle alors que la salle est deja pleine");
							//									noteComportement=0;
							//								}
						}
					}
					//	la.add(spec);
					//	ll.add(spec);
				}
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
		}
		//	}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}

	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int test15CinemaEstPresent() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int notePasDAutres=10;

		int noteDeclaration=10;
		int noteComportement=90;

		System.out.println(" Test verifiant la methode estPresent de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}

		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de estPresent --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfss = cSpectateur.getDeclaredFields();
		for (Field f : tfss) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						//				return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						//				return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester estPresent --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}

		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tfs = cSalle.getDeclaredFields();
		//int nbv=0;
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				//	nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}

		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						//		notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					//		notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			//	noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			//	noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			//	notePasDAutres=0;
		}
		//		if (noteNom<noteNomMax || noteListe<noteListeMax) {
		//			notePasDAutres=0;
		//		}


		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) fieldSpectateurAge.setAccessible(true);
		if (fieldCinemaNom!=null) fieldCinemaNom.setAccessible(true);
		if (fieldCinemaSalles!=null) fieldCinemaSalles.setAccessible(true);
		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mEstPresent=null;
		try {
			Method[] methods = cCinema.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().toLowerCase().contains("estpresent") && mEstPresent==null) {
					mEstPresent=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mEstPresent==null) {
			System.out.println("   Aie... Je ne trouve pas la methode estPresent de Cinema");
			return 0;
		}
		if (!Modifier.isPublic(mEstPresent.getModifiers())) {
			System.out.println("   Aie... la methode estPresent devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPresent.getName().equals("estPresent")) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" semble correspondre a la methode");
			System.out.println("   estPresent que doit comporter Cinema mais ne porte pas le nom estPresent.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!mEstPresent.getReturnType().equals(boolean.class)) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" retourne une valeur de type "+mEstPresent.getReturnType().getName()+" au lieu de retourner un boolean");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mEstPresent.getParameters().length!=1 || !mEstPresent.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mEstPresent.getName()+" doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mEstPresent.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};

			Object[][] params = { {"carnot", Integer.valueOf(3)},
					{"kastler", Integer.valueOf(2)},
					{"charpak", Integer.valueOf(8)},
					{"besse", Integer.valueOf(7)},
					{"A", Integer.valueOf(9)},
					{"B", Integer.valueOf(10)},
			};

			Object[] argssn = new Object[1];
			ArrayList lsa = new ArrayList();
			LinkedList lsl = new LinkedList();
			List toutesListesSpectateurA = new ArrayList();


			for (int i=0; i<params.length ; i++) {
				Object pt1=null;
			//	boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
			//	ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
			//	Object reso=null;
				try {
					if (fieldSalleNom!=null) fieldSalleNom.set(pt1,params[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
						toutesListesSpectateurA.add(la);
					} else  {
						fieldSalleSpectateurs.set(pt1, ll);
						toutesListesSpectateurA.add(ll);
					}
					lsa.add(pt1);
					lsl.add(pt1);
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
			Object pt1c=null;
			Objenesis objenesisc = new ObjenesisStd(); 
			ObjectInstantiator instantiatorCinema= objenesisc.getInstantiatorOf(cCinema);
			pt1c = instantiatorCinema.newInstance();
			try {
				if (fieldCinemaNom!=null) fieldCinemaNom.set(pt1c,"Pathe");
				if (fieldCinemaSalles.getType().getName().contains("rray")) {
					fieldCinemaSalles.set(pt1c, lsa);
				} else  {
					fieldCinemaSalles.set(pt1c, lsl);
				}
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
			// A ce stade, pt1c est le cinema Pathe comportant les salles dont les listes de spectateurs sont toutesListesSpectateurs
			// Capacite totale 39
			//			for (int i=0; i<50 && noteComportement>0; i++) {
			//				Object pt1=null;
			boolean res;
			Objenesis objenesis = new ObjenesisStd(); 
			ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
			//				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
			//				pt1 = instantiatorSalle.newInstance();
			Object reso=null;
			try {
				List specta = new ArrayList();
				for (int j=0; j<argsOk.length && noteComportement>0; j++) {
					Object spec = instantiatorSpec.newInstance();

					if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, argsOk[j][0]);
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);
					specta.add(spec);
					int lis = (int)(Math.random()*toutesListesSpectateurA.size());
					//		System.out.println("ajout de "+spec+" dans la salle "+lis);
					((List)(toutesListesSpectateurA.get(lis))).add(spec);
				}
				for (int j=0; j<argsOk.length && noteComportement>0; j++) {
					//		Object spec = instantiatorSpec.newInstance();
					//
					//		if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, argsOk[j][0]);
					//		if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);
					//		System.out.println("verif du present "+specta.get(j));
					if (mEstPresent!=null) {
						argssn[0]=specta.get(j);//spec;
						reso=mEstPresent.invoke(pt1c,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (!res) {
							System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte.");
							System.out.println("   Sur un Cinema qui contient dans l'une de ses salles le spectateur s, estPresent(s) retourne false");
							noteComportement=0;
						} 
					}
					//	la.add(spec);
					//	ll.add(spec);
				}
				for (int j=0; j<12 && noteComportement>0; j++) {
					Object spec = instantiatorSpec.newInstance();
					//
					if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, "S"+((int)(Math.random()*80)));
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  (int)(Math.random()*80));
					if (mEstPresent!=null) {
						argssn[0]=spec;
						reso=mEstPresent.invoke(pt1c,  argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (boolean)reso;
						if (res) {
							System.out.println("   Aie... "+mEstPresent.getName()+" n'est pas correcte.");
							System.out.println("   Sur un Cinema qui ne contient le spectateur s dans aucune de ses salles, estPresent(s) retourne true");
							noteComportement=0;
						} 
					}
					//	la.add(spec);
					//	ll.add(spec);
				}
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
		}
		//	}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}

	}





	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static int test16CinemaToString() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int notePasDAutres=10;

		int noteDeclaration=10;
		int noteComportement=90;

		System.out.println(" Test verifiant la methode toString de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}

		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			System.out.println("   Cette classe est indispensable au test de toString --> arret du test");
			return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		Field[] tfss = cSpectateur.getDeclaredFields();
		for (Field f : tfss) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						//				return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						//				return 0;
					}
				}
			} 
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			System.out.println("   Sans cette variable d'instance il est impossible de tester toString --> Arret du test");
			return 0;
		}	
		fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}

		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tfs = cSalle.getDeclaredFields();
		//int nbv=0;
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				//	nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (!fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}

		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						//		notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					//		notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			//	noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			//	noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			//	notePasDAutres=0;
		}
		//		if (noteNom<noteNomMax || noteListe<noteListeMax) {
		//			notePasDAutres=0;
		//		}


		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) fieldSpectateurAge.setAccessible(true);
		if (fieldCinemaNom!=null) fieldCinemaNom.setAccessible(true);
		if (fieldCinemaSalles!=null) fieldCinemaSalles.setAccessible(true);
		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mToString=null;
		try {
			Method[] methods = cCinema.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().equals("toString") && mToString==null) {
					mToString=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mToString==null) {
			System.out.println("   Aie... Je ne trouve pas la methode toString de Cinema");
			return 0;
		}
		if (!Modifier.isPublic(mToString.getModifiers())) {
			System.out.println("   Aie... la methode toString devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		//		if (!mToString.getName().equals("toString")) {
		//			System.out.println("   Aie... votre methode "+mToString.getName()+" semble correspondre a la methode");
		//			System.out.println("   estPresent que doit comporter Cinema mais ne porte pas le nom estPresent.");
		//			noteDeclaration=noteDeclaration/2;
		//		}
		if (!mToString.getReturnType().equals(String.class)) {
			System.out.println("   Aie... votre methode "+mToString.getName()+" retourne une valeur de type "+mToString.getReturnType().getName()+" au lieu de retourner une String");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		if (mToString.getParameters().length!=0) {//1 || !mToString.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mToString.getName()+" ne doit prendre aucun parametre");//doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			noteComportement=0;
		}
		mToString.setAccessible(true);

		if (noteComportement>0) {
			Object[][] argsOk = { {"lucie", Integer.valueOf(5)},
					{"marcelle", Integer.valueOf(84)},
					{"leon", Integer.valueOf(33)},
					{"pauline", Integer.valueOf(4)},
					{"robert", Integer.valueOf(78)},
					{"ignace", Integer.valueOf(3)},
					{"lucas", Integer.valueOf(17)},
					{"zoe", Integer.valueOf(13)},
					{"lucien", Integer.valueOf(18)},
					{"luc", Integer.valueOf(19)},
			};

			Object[][] params = { {"carnot", Integer.valueOf(3)},
					{"kastler", Integer.valueOf(2)},
					{"charpak", Integer.valueOf(8)},
					{"besse", Integer.valueOf(7)},
					{"A", Integer.valueOf(9)},
					{"B", Integer.valueOf(10)},
			};

			Object[] argssn = new Object[1];
			ArrayList lsa = new ArrayList();
			LinkedList lsl = new LinkedList();
			List toutesListesSpectateurA = new ArrayList();


			for (int i=0; i<params.length ; i++) {
				Object pt1=null;
				//boolean res;
				Objenesis objenesis = new ObjenesisStd(); 
				ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
				pt1 = instantiatorSalle.newInstance();
				Object reso=null;
				try {
					if (fieldSalleNom!=null) fieldSalleNom.set(pt1,params[i][0]);
					if (fieldSalleCapacite!=null) fieldSalleCapacite.set(pt1,params[i][1]);
					ArrayList la = new ArrayList();
					LinkedList ll = new LinkedList();
					if (fieldSalleSpectateurs.getType().getName().contains("rray")) {
						fieldSalleSpectateurs.set(pt1, la);
						toutesListesSpectateurA.add(la);
					} else  {
						fieldSalleSpectateurs.set(pt1, ll);
						toutesListesSpectateurA.add(ll);
					}
					lsa.add(pt1);
					lsl.add(pt1);
				} catch (Exception e) {
					if (e instanceof InvocationTargetException) {
						e.getCause().printStackTrace();
					} else {
						e.printStackTrace();
					}
					return 0;
				}
			}
			Object pt1c=null;
			Objenesis objenesisc = new ObjenesisStd(); 
			ObjectInstantiator instantiatorCinema= objenesisc.getInstantiatorOf(cCinema);
			pt1c = instantiatorCinema.newInstance();
			try {
				if (fieldCinemaNom!=null) fieldCinemaNom.set(pt1c,"Pathe");
				if (fieldCinemaSalles.getType().getName().contains("rray")) {
					fieldCinemaSalles.set(pt1c, lsa);
				} else  {
					fieldCinemaSalles.set(pt1c, lsl);
				}
			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
			// A ce stade, pt1c est le cinema Pathe comportant les salles dont les listes de spectateurs sont toutesListesSpectateurs
			// Capacite totale 39
			//			for (int i=0; i<50 && noteComportement>0; i++) {
			//				Object pt1=null;
			String res;
			Objenesis objenesis = new ObjenesisStd(); 
			ObjectInstantiator instantiatorSpec= objenesis.getInstantiatorOf(cSpectateur);
			//				ObjectInstantiator instantiatorSalle= objenesis.getInstantiatorOf(cSalle);
			//				pt1 = instantiatorSalle.newInstance();
			Object reso=null;
			Object[] argvide= {};
			try {
				List specta = new ArrayList();
				for (int j=0; j<argsOk.length && noteComportement>0; j++) {
					Object spec = instantiatorSpec.newInstance();

					if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, argsOk[j][0]);
					if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);
					specta.add(spec);
					//		int lis = (int)(Math.random()*toutesListesSpectateurA.size());
					//		System.out.println("ajout de "+spec+" dans la salle "+lis);
					//		((List)(toutesListesSpectateurA.get(lis))).add(spec);
				}
				//	for (int j=0; j<argsOk.length && noteComportement>0; j++) {
				//		Object spec = instantiatorSpec.newInstance();
				//
				//		if (fieldSpectateurNom!=null) fieldSpectateurNom.set(spec, argsOk[j][0]);
				//		if (fieldSpectateurAge!=null) fieldSpectateurAge.set(spec,  argsOk[j][1]);
				//		System.out.println("verif du present "+specta.get(j));
				if (mToString!=null) {
					((List)	(toutesListesSpectateurA.get(0))).add(specta.get(0));
					reso=mToString.invoke(pt1c, argvide); //argssn);
				}
				if (reso==null ) {
					System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
					noteComportement= 0;
				} else {
					res = (String)reso;
					if (!res.contains("Pathe")) {
						System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
						System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
						noteComportement=0;
					} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
							|| !res.contains("1") || !res.contains("0") || res.contains("2")
							) {
						System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
						System.out.println("   \"carnot\"  avec 1 spectateur");
						System.out.println("   \"kastler\" avec 0 spectateur");
						System.out.println("   \"charpak\" avec 0 spectateur");
						System.out.println("   \"besse\"   avec 0 spectateur");
						System.out.println("   \"A\"       avec 0 spectateur");
						System.out.println("   \"B\"       avec 0 spectateur");
						System.out.println("   la methode toString() retourne : "+res);
						noteComportement=0;
					}
				}
				if (noteComportement>0) {
					if (mToString!=null) {
						((List)	(toutesListesSpectateurA.get(1))).add(specta.get(1));
						reso=mToString.invoke(pt1c, argvide); //argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (String)reso;
						if (!res.contains("Pathe")) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
							System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
							noteComportement=0;
						} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
								|| !res.contains("1") || !res.contains("0") || res.contains("2")
								) {
							System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
							System.out.println("   \"carnot\"  avec 1 spectateur");
							System.out.println("   \"kastler\" avec 1 spectateur");
							System.out.println("   \"charpak\" avec 0 spectateur");
							System.out.println("   \"besse\"   avec 0 spectateur");
							System.out.println("   \"A\"       avec 0 spectateur");
							System.out.println("   \"B\"       avec 0 spectateur");
							System.out.println("   la methode toString() retourne : "+res);
							noteComportement=0;
						}
					}
				}
				if (noteComportement>0) {
					if (mToString!=null) {
						((List)	(toutesListesSpectateurA.get(0))).add(specta.get(2));
						reso=mToString.invoke(pt1c, argvide); //argssn);
					}
					if (reso==null ) {
						System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
						noteComportement= 0;
					} else {
						res = (String)reso;
						if (!res.contains("Pathe")) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
							System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
							noteComportement=0;
						} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
								|| !res.contains("1") || !res.contains("0") || !res.contains("2")
								) {
							System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
							System.out.println("   \"carnot\"  avec 2 spectateur");
							System.out.println("   \"kastler\" avec 1 spectateur");
							System.out.println("   \"charpak\" avec 0 spectateur");
							System.out.println("   \"besse\"   avec 0 spectateur");
							System.out.println("   \"A\"       avec 0 spectateur");
							System.out.println("   \"B\"       avec 0 spectateur");
							System.out.println("   la methode toString() retourne : "+res);
							noteComportement=0;
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(0))).add(specta.get(3));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || !res.contains("0") || !res.contains("3") ||res.contains("2")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 1 spectateur");
								System.out.println("   \"charpak\" avec 0 spectateur");
								System.out.println("   \"besse\"   avec 0 spectateur");
								System.out.println("   \"A\"       avec 0 spectateur");
								System.out.println("   \"B\"       avec 0 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(1))).add(specta.get(4));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| res.contains("1") || !res.contains("0") || !res.contains("3") ||!res.contains("2")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 0 spectateur");
								System.out.println("   \"besse\"   avec 0 spectateur");
								System.out.println("   \"A\"       avec 0 spectateur");
								System.out.println("   \"B\"       avec 0 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(2))).add(specta.get(5));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || !res.contains("0") || !res.contains("3") ||!res.contains("2")||res.contains("4")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 1 spectateur");
								System.out.println("   \"besse\"   avec 0 spectateur");
								System.out.println("   \"A\"       avec 0 spectateur");
								System.out.println("   \"B\"       avec 0 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(3))).add(specta.get(6));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || !res.contains("0") || !res.contains("3") ||!res.contains("2")||res.contains("4")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 1 spectateur");
								System.out.println("   \"besse\"   avec 1 spectateur");
								System.out.println("   \"A\"       avec 0 spectateur");
								System.out.println("   \"B\"       avec 0 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(4))).add(specta.get(7));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || !res.contains("0") || !res.contains("3") ||!res.contains("2")||res.contains("4")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 1 spectateur");
								System.out.println("   \"besse\"   avec 1 spectateur");
								System.out.println("   \"A\"       avec 1 spectateur");
								System.out.println("   \"B\"       avec 0 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(5))).add(specta.get(8));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || res.contains("0") || !res.contains("3") ||!res.contains("2")||res.contains("4")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 1 spectateur");
								System.out.println("   \"besse\"   avec 1 spectateur");
								System.out.println("   \"A\"       avec 1 spectateur");
								System.out.println("   \"B\"       avec 1 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}					
					if (noteComportement>0) {
						if (mToString!=null) {
							((List)	(toutesListesSpectateurA.get(5))).add(specta.get(9));
							reso=mToString.invoke(pt1c, argvide); //argssn);
						}
						if (reso==null ) {
							System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte. Elle retourne null");
							noteComportement= 0;
						} else {
							res = (String)reso;
							if (!res.contains("Pathe")) {
								System.out.println("   Aie... "+mToString.getName()+" n'est pas correcte.");
								System.out.println("   Sur un Cinema portant le nom \"Pathe\" elle retourne une chaine ne comportant pas ce nom : "+res);
								noteComportement=0;
							} else if (!res.contains("carnot") ||!res.contains("kastler") ||!res.contains("charpak") ||!res.contains("besse") ||!res.contains("A") ||!res.contains("B")
									|| !res.contains("1") || res.contains("0") || !res.contains("3") ||!res.contains("2")||res.contains("4")
									) {
								System.out.println("   Aie... sur un cinema de nom \"Pathe\" comportant les salles : ");
								System.out.println("   \"carnot\"  avec 3 spectateur");
								System.out.println("   \"kastler\" avec 2 spectateur");
								System.out.println("   \"charpak\" avec 1 spectateur");
								System.out.println("   \"besse\"   avec 1 spectateur");
								System.out.println("   \"A\"       avec 1 spectateur");
								System.out.println("   \"B\"       avec 2 spectateur");
								System.out.println("   la methode toString() retourne : "+res);
								noteComportement=0;
							}
						}
					}					

				}
				//	la.add(spec);
				//	ll.add(spec);

			} catch (Exception e) {
				if (e instanceof InvocationTargetException) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
				return 0;
			}
		}
		//	}


		if (noteDeclaration+noteComportement==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteComportement;
		}

	}



	@SuppressWarnings({ "rawtypes" })
	public static int test17CinemaCreerParadiso() {
		//		Class cIForme = Reflexion.getClass("packnp.game.IForme");
		//		int penalite=0;
		//		if (cIForme==null) {
		//			System.out.println("   Aie... je ne trouve pas l'interface IForme dans le package game");
		//			System.out.println("          vous avez vraisemblablement deplace/supprime le fichier de cette interface");
		//			return 0;
		//		}
		//		int notePasDAutres=10;

		int noteNom=15;
		int noteTornatore=15;
		int noteNoiret=15;
		int noteSalvatore=15;
		int noteAlfredo=15;
		int noteElena=15;
		int noteDeclaration=10;

		System.out.println(" Test verifiant la methode creerParadiso de la classe Cinema");
		//		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		//		if (cSpectateur==null) {
		//			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
		//			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
		//			return 0;
		//		}

		Class cSpectateur= Reflexion.getClass("packnp.cinema.Spectateur");
		if (cSpectateur==null) {
			System.out.println("   Aie... je ne trouve pas la classe Spectateur dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			//	System.out.println("   Cette classe est indispensable --> arret du test");
			//	return 0;
		}
		Field fieldSpectateurNom=null,fieldSpectateurAge=null;
		if (cSpectateur!=null) {
		Field[] tfss = cSpectateur.getDeclaredFields();
		for (Field f : tfss) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().equals(String.class)) {
					if (fieldSpectateurNom==null) {
						fieldSpectateurNom = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type String");
						//				return 0;
					}
				} else if (f.getType().equals(int.class)) {
					if (fieldSpectateurAge==null) {
						fieldSpectateurAge = f;
					} else {
						System.out.println("   Aie... la classe Spectateur ne doit pas comporter plusieurs variables d'instance de type int");
						//				return 0;
					}
				}
			} 
		}
		}
		if (fieldSpectateurNom==null) {
			System.out.println("   Aie... La classe Spectateur doit comporter une variable d'instance de type String correspondant au nom du spectateur.");
			//			System.out.println("   Sans cette variable d'instance il est impossible de tester toString --> Arret du test");
			//			return 0;
		}	
		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) {fieldSpectateurAge.setAccessible(true);}

		Class cSalle= Reflexion.getClass("packnp.cinema.Salle");
		if (cSalle==null) {
			System.out.println("   Aie... je ne trouve pas la classe Salle dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldSalleNom=null, fieldSalleCapacite=null, fieldSalleSpectateurs=null;
		Field[] tfs = cSalle.getDeclaredFields();
		//int nbv=0;
		for (Field f : tfs) {
			if (!Modifier.isStatic(f.getModifiers())) {
				//	nbv++;
				if (f.getType().equals(int.class)) {
					if (fieldSalleCapacite==null) {
						fieldSalleCapacite = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type int");
						//	notePasDAutres=0;
					}
				} else if (f.getType().equals(String.class)) {
					if (fieldSalleNom==null) {
						fieldSalleNom = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldSalleSpectateurs==null) {
						fieldSalleSpectateurs = f;
					} else {
						System.out.println("   Aie... la classe Salle ne doit pas comporter plusieurs variables d'instance de type liste");
						//	notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Salle ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   une variable d'instance de type int et une variable d'instance de type liste");
					//	notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Salle");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldSalleNom==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type String correspondant au nom de la salle.");
			//	noteNom=0;
		} else if (!fieldSalleNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleNom!=null && !Modifier.isPrivate(fieldSalleNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleNom.getName()+" de Salle devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldSalleCapacite==null) {
			System.out.println("   Aie... La classe Salle doit comporter une variable d'instance de type int correspondant a la capacite de la salle.");
			//	System.out.println("   Impossible de tester la methode estPleine sans la presence de cette variable --> arret du test");
			//	return 0;
			//	noteCapacite=0;
		} else if (!fieldSalleCapacite.getName().equals("capacite")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleCapacite.getName()+"\" au lieu de \"capacite\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleCapacite!=null && !Modifier.isPrivate(fieldSalleCapacite.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleCapacite.getName()+" de Salle devrait etre declaree private");
			//	noteCapacitege=noteCapacitege/2;
		}
		if (fieldSalleSpectateurs==null) {
			System.out.println("   Aie... La classe Salle doit comporter une liste correspondant a la liste des spectateurs qu'elle contient");
			//	System.out.println("   Impossible de poursuivre le test sans cette liste --> Arret du test");
			//	return 0;
			//	noteListe=0;
		} else if (!fieldSalleSpectateurs.getName().equals("spectateurs")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldSalleSpectateurs.getName()+"\" au lieu de \"spectateurs\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !Modifier.isPrivate(fieldSalleSpectateurs.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldSalleSpectateurs.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldSalleSpectateurs!=null && !fieldSalleSpectateurs.getGenericType().toString().endsWith("Spectateur>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Spectateur pour type d'elements");
			//	noteListe=noteListe/2;
		}

		Class cCinema= Reflexion.getClass("packnp.cinema.Cinema");
		if (cCinema==null) {
			System.out.println("   Aie... je ne trouve pas la classe Cinema dans le package packnp.cinema");
			System.out.println("   Verifiez le nom que vous avez donne a la classe et le package dans lequel vous l'avez creee");
			return 0;
		}
		Field fieldCinemaNom=null, fieldCinemaSalles=null;
		Field[] tf = cCinema.getDeclaredFields();
		int nbv=0;
		for (Field f : tf) {
			if (!Modifier.isStatic(f.getModifiers())) {
				nbv++;
				if (f.getType().equals(String.class)) {
					if (fieldCinemaNom==null) {
						fieldCinemaNom = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type String");
						//	notePasDAutres=0;
					}
				} else if (f.getType().getName().toUpperCase().contains("LIST")) {
					if (fieldCinemaSalles==null) {
						fieldCinemaSalles = f;
					} else {
						System.out.println("   Aie... la classe Cinema ne doit pas comporter plusieurs variables d'instance de type liste");
						//		notePasDAutres=0;
					}
				} else {
					System.out.println("   Aie... la classe Cinema ne doit comporter qu'une variable d'instance de type String," ); 
					System.out.println("   et une variable d'instance de type liste");
					//		notePasDAutres=0;
				}
			} else {
				System.out.println("   Aie... le diagramme de classe ne mentionne aucune variable de classe pour la classe Cinema");
				System.out.println("   --> vous ne devriez pas declarer la variable static "+f.getName());
				//	notePasDAutres= 0;
			}
		}
		if (fieldCinemaNom==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une variable d'instance de type String correspondant au nom du cinema.");
			//	noteNom=0;
		} else if (!fieldCinemaNom.getName().equals("nom")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaNom.getName()+"\" au lieu de \"nom\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaNom!=null && !Modifier.isPrivate(fieldCinemaNom.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaNom.getName()+" de Cinema devrait etre declaree private");
			//	noteNom=noteNom/2;
		}
		if (fieldCinemaSalles==null) {
			System.out.println("   Aie... La classe Cinema doit comporter une liste correspondant a la liste des salles");
			//	noteListe=0;
		} else if (!fieldCinemaSalles.getName().equals("salles")) {
			System.out.println("   Aie...vous avez nomme votre variable \""+fieldCinemaSalles.getName()+"\" au lieu de \"salles\" : sachez respecter a la lettre les informations precisees sur le diagrame de classes");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !Modifier.isPrivate(fieldCinemaSalles.getModifiers())) {
			System.out.println("   Aie... pour respecter le principe d'encapsulation la variable d'instance ");
			System.out.println("   "+fieldCinemaSalles.getName()+" de Salle devrait etre declaree private");
			//	noteListe=noteListe/2;
		}
		if (fieldCinemaSalles!=null && !fieldCinemaSalles.getGenericType().toString().endsWith("Salle>")) {
			System.out.println("   Aie... Vous n'avez pas parametre votre liste avec Salle pour type d'elements");
			//	noteListe=noteListe/2;
		}


		if (nbv!=2) {
			System.out.println("   Aie... Vous avez defini "+nbv+" variables d'instances dans Cinema au lieu de 2");
			//	notePasDAutres=0;
		}
		//		if (noteNom<noteNomMax || noteListe<noteListeMax) {
		//			notePasDAutres=0;
		//		}


		if (fieldSpectateurNom!=null) fieldSpectateurNom.setAccessible(true);
		if (fieldSpectateurAge!=null) fieldSpectateurAge.setAccessible(true);
		if (fieldCinemaNom!=null) fieldCinemaNom.setAccessible(true);
		if (fieldCinemaSalles!=null) fieldCinemaSalles.setAccessible(true);
		if (fieldSalleNom!=null) fieldSalleNom.setAccessible(true);
		if (fieldSalleCapacite!=null) fieldSalleCapacite.setAccessible(true);
		if (fieldSalleSpectateurs!=null) fieldSalleSpectateurs.setAccessible(true);

		Method mCreerParadiso=null;
		try {
			Method[] methods = cCinema.getDeclaredMethods();
			for (Method m : methods) {
				if (//m.getReturnType().equals(boolean.class) &&
						m.getName().toLowerCase().contains("paradiso") && mCreerParadiso==null) {
					mCreerParadiso=m;
				} 			}
		} catch (Exception e1) {
		} 
		if (mCreerParadiso==null) {
			System.out.println("   Aie... Je ne trouve pas la methode creerParadiso de Cinema");
			return 0;
		}
		if (!Modifier.isPublic(mCreerParadiso.getModifiers())) {
			System.out.println("   Aie... la methode creerParadiso devrait etre public.");
			noteDeclaration=noteDeclaration/2;
		}
		if (!Modifier.isStatic(mCreerParadiso.getModifiers())) {
			System.out.println("   Aie... la methode creerParadiso devrait etre static.");
			noteDeclaration=noteDeclaration/2;
		}

		//		if (!mToString.getName().equals("toString")) {
		//			System.out.println("   Aie... votre methode "+mToString.getName()+" semble correspondre a la methode");
		//			System.out.println("   estPresent que doit comporter Cinema mais ne porte pas le nom estPresent.");
		//			noteDeclaration=noteDeclaration/2;
		//		}
		if (!mCreerParadiso.getReturnType().equals(cCinema)) {
			System.out.println("   Aie... votre methode "+mCreerParadiso.getName()+" retourne une valeur de type "+mCreerParadiso.getReturnType().getName()+" au lieu de retourner un Cinema");
			noteDeclaration=noteDeclaration/2;
			return noteDeclaration;
		}
		if (mCreerParadiso.getParameters().length!=0) {//1 || !mToString.getParameters()[0].getType().equals(cSpectateur)) {
			System.out.println("   Aie... votre methode "+mCreerParadiso.getName()+" ne doit prendre aucun parametre");//doit prendre un unique parametre, de type Spectateur");
			noteDeclaration=noteDeclaration/2;
			return noteDeclaration;
		}
		mCreerParadiso.setAccessible(true);
		Object reso=null;
		Object[] argvide= {};
		try {
			if (mCreerParadiso!=null) {
				reso=mCreerParadiso.invoke(null, argvide); //argssn);
			}
			if (reso==null) {
				System.out.println("   Aie... votre methode creerParadiso retourne null");
				return noteDeclaration;
			}
			if (fieldCinemaNom==null) {
				noteNom=0;
			} else {
				String n = (String)(fieldCinemaNom.get(reso));
				if (!n.equals("paradiso")) {
					System.out.println("   Aie... le Cinema retourne par la methode creerParadiso a pour nom \""+n+"\" au lieu de \"paradiso\"");
					noteNom=0;
				}
			}
			if (fieldCinemaSalles==null) {
				return noteDeclaration+noteNom;
			} else {
				List salles = (List)(fieldCinemaSalles.get(reso));
				if (salles==null) {
					System.out.println("   Aie... le Cinema retourne par la methode creerParadiso a null pour liste de salles");
					return noteDeclaration+noteNom;
				} else {
					if (salles.size()!=2) {
						System.out.println("   Aie... le Cinema retourne par la methode creerParadiso a une liste de "+salles.size()+" salles au lieu de 2");
					}
					if (fieldSalleNom==null) {
						return noteDeclaration+noteNom;
					}
					Object salleTornatore = null;
					Object salleNoiret = null;
					if (salles.size()>0) { 
						if ( salles.get(0)!=null && fieldSalleNom.get(salles.get(0))!=null 
								&&((String)(fieldSalleNom.get(salles.get(0)))).toLowerCase().equals("tornatore") ) {
							salleTornatore= salles.get(0);
							if (salles.size()>1) {
								salleNoiret=salles.get(1);
							}
						} else if (salles.get(0)!=null&&fieldSalleNom.get(salles.get(0))!=null 
								&&((String)(fieldSalleNom.get(salles.get(0)))).toLowerCase().equals("noiret") ) {
							salleNoiret= salles.get(0);
							if (salles.size()>1) {
								salleTornatore=salles.get(1);
							}

						}
					}
					if (salleTornatore==null) {
						System.out.println("   Aie... je ne trouve pas la salle tornatore");
						noteTornatore=0;
						noteSalvatore=0;
						noteElena=0;
					}else {
						if (fieldSalleCapacite!=null) {
							int si = (int)(fieldSalleCapacite.get(salleTornatore));
							if (si!=80) {
								System.out.println("   Aie... la salle tornatore a une capacite de "+si+" au lieu de 80");
								noteTornatore=noteTornatore/2;
							}
						} else {
							noteTornatore=noteTornatore/2;
						}
						if (fieldSalleSpectateurs==null) {
							System.out.println("   Aie... La classe salle n'a pas la variable d'instance permettant de memoriser la liste des spectateur --> aucun point pour les 3 spectateurs que doit comporter le cinema paradiso");
							noteSalvatore=0;
							noteElena=0;
							noteAlfredo=0;
						} else {
							List spec = (List)(fieldSalleSpectateurs.get(salleTornatore));
							if (spec==null) {
								System.out.println("   Aie... la salle tornatore a une liste de spectateur qui vaut null");
								noteTornatore=noteTornatore/2;
								noteSalvatore=0;
								noteElena=0;
							} else {
								Object salvatore=null;
								Object elena=null;
								if (fieldSpectateurNom==null) {
									System.out.println("   Aie... la classe spectateur ne possede pas la variable permettant de memoriser le nom du spectateur. Cette variable est indispensable pour verifier la presence des 3 spectateurs du cinema paradiso");
									noteSalvatore=0;
									noteElena=0;
									noteAlfredo=0;
								} else {
									if (spec.size()<=0 || spec.size()>2) {
										System.out.println("   Aie... la salle \"tornatore\" contient "+spec.size()+" spectateurs au lieu de 2");
										noteTornatore=noteTornatore/2;
										noteSalvatore=0;
										noteElena=0;
									} 
									if (spec.size()>0) {
										if (((String)(fieldSpectateurNom.get(spec.get(0)))).equals("salvatore")) {
											salvatore=spec.get(0);
											if (spec.size()>1) {
												elena=spec.get(1);
											} else {
												System.out.println("   Aie... la salle \"tornatore\" ne contient qu'un seul spectateur");
												noteTornatore=noteTornatore/2;
											}
										} else {
											elena=spec.get(0);
											if (spec.size()>1) {
												salvatore=spec.get(1);
											} else {
												System.out.println("   Aie... la salle \"tornatore\" ne contient qu'un seul spectateur");
												noteTornatore=noteTornatore/2;
											}
										}
									}
								}
								if (salvatore!=null) {
									if (fieldSpectateurNom==null || !((String)(fieldSpectateurNom.get(salvatore))).equals("salvatore")) {
										System.out.println("   Aie... je ne trouve pas le spectateur portant le nom \"salvatore\" dans la salle \"tornatore\"");
										noteSalvatore=noteSalvatore/2;
									}
									if (fieldSpectateurAge==null || ((int)(fieldSpectateurAge.getInt(salvatore)))!=17) {
										if (fieldSpectateurAge!=null) System.out.println("   Aie... salvatore doit avoir 17 ans");
										noteSalvatore=noteSalvatore/2;
									}
								} else {
									noteSalvatore=0;
									System.out.println("   Aie... je ne trouve pas salvatore dans la salle tornatore");
								}
								if (elena!=null) {
									if (fieldSpectateurNom==null || !((String)(fieldSpectateurNom.get(elena))).equals("elena")) {
										System.out.println("   Aie... je ne trouve pas le spectateur portant le nom \"elena\" dans la salle \"tornatore\"");
										noteElena=noteElena/2;
									}
									if (fieldSpectateurAge==null || ((int)(fieldSpectateurAge.getInt(elena)))!=19) {
										if (fieldSpectateurAge!=null) System.out.println("   Aie... elena doit avoir 19 ans");
										noteElena=noteElena/2;
									}
								} else {
									noteElena=0;
									System.out.println("   Aie... je ne trouve pas elena dans la salle tornatore");
								}
							}
						} 
					}
					if (salleNoiret==null) {
						System.out.println("   Aie... je ne trouve pas la salle nommee noiret");
						noteNoiret=0;
						noteAlfredo=0;
					} else {
						if (fieldSalleCapacite!=null) {
							int si = (int)(fieldSalleCapacite.get(salleNoiret));
							if (si!=120) {
								System.out.println("   Aie... la salle noiret a une capacite de "+si+" au lieu de 120");
								noteNoiret=noteNoiret/2;
							}
						} else {
							noteNoiret=noteNoiret/2;
						}
						if (fieldSalleSpectateurs==null) {
							System.out.println("   Aie... la classe Salle ne dispose pas de la variable d'instance memorisant la liste des spectateurs.");
							noteSalvatore=0;
							noteElena=0;
							noteAlfredo=0;
						} else {
							List spec = (List)(fieldSalleSpectateurs.get(salleNoiret));
							if (spec==null) {
								System.out.println("   Aie... la salle noiret a une liste de spectateurs qui vaut null");
								noteNoiret=noteNoiret/2;
								noteAlfredo=0;
							} else {
								Object alfredo=null;
								if (fieldSpectateurNom==null) {
									System.out.println("   Aie... la classe Spectateur ne dispose pas de la variable d'instance permettant de memoriser le nom. Cette variable est indispensable pour les verifications des 3 spectateurs du cinema paradiso");
									noteSalvatore=0;
									noteElena=0;
									noteAlfredo=0;
								} else {
									if (spec.size()<=0 ) {
										System.out.println("   Aie... il n'y a aucun spectateur (au lieu de 1) dans la salle \"noiret\"");
										noteAlfredo=0;
										noteNoiret=noteNoiret/2;
									} else {
										alfredo=spec.get(0);
										if (spec.size()>1) {
											System.out.println("   Aie... il ne devrait y avoir qu'un seul spectateur dans la salle \"noiret\"");
											noteNoiret=noteNoiret/2;
										}
									}
								}
								if (alfredo!=null) {
									if (fieldSpectateurNom==null || !((String)(fieldSpectateurNom.get(alfredo))).equals("alfredo")) {
										System.out.println("   Aie... je ne trouve pas le spectateur portant le nom \"alfredo\" dans la salle \"noiret\"");
										noteAlfredo=noteAlfredo/2;
									}
									if (fieldSpectateurAge==null || ((int)(fieldSpectateurAge.getInt(alfredo)))!=39) {
										if (fieldSpectateurAge!=null) System.out.println("   Aie... alfredo doit avoir 39 ans");
										noteAlfredo=noteAlfredo/2;
									}
								} else {
									noteAlfredo=0;
								}
							}
						} 
					}

				}
			}

		} catch (Exception e) {
			if (e instanceof InvocationTargetException) {
				e.getCause().printStackTrace();
			} else {
				e.printStackTrace();
			}
			return 0;
		}


		if (noteDeclaration+noteAlfredo+noteElena+noteNom+noteTornatore+noteNoiret+noteSalvatore==100) {
			System.out.println(" OK, votre implementation passe le test.");
			return 100;
		} else {
			return noteDeclaration+noteAlfredo+noteElena+noteNom+noteTornatore+noteNoiret+noteSalvatore;
		}

	}



}

