package evaljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Discotheque {

	private static String[] menu= {"menu artistes => saisir 1", "menu disques => saisir 2", "afficher types => saisir 3", "afficher styles => saisir 4", "quitter => saisir 5"};
	private static String[] subMenu= {"tout afficher => saisir 1", "ajouter => saisir 2", "modifier => saisir 3", "supprimer => saisir 4", "revenir au menu principal => saisir 5"};

	// HashMaps for storing list of types and styles - not really used as expected following the "Great Simplification"...
	private static HashMap<Long, String> listTypes= new HashMap<Long, String>();
	private static HashMap<Long, String> listStyles= new HashMap<Long, String>();

	public static void main(String[] args) {

		// connection string for local DB (PostgreSQL)
		// TODO store login/password in properties file + prepared statements + some exceptions...

		String[] connectStringLocalDb= {"jdbc:postgresql://localhost:5432/discotheque", "postgres", "admin"};

		//System.out.println("test connexion base locale : " + connectStringLocalDb[0]);

		try(Connection connLocal= DriverManager.getConnection(connectStringLocalDb[0], connectStringLocalDb[1], connectStringLocalDb[2])) {
			//System.out.println("connexion ouverte : " + connLocal);

			try (Statement stmt= connLocal.createStatement()) {
				
				// print menu
				Scanner input= new Scanner(System.in);
				String menuChoice= "";
				String subMenuChoice= "";
				getAllTypes(stmt);
				getAllStyles(stmt);

				do {
					printMenu();
					menuChoice = input.nextLine();

					switch(menuChoice) {
					case "1":
					case "2":						
						do {
							printSubMenu();
							subMenuChoice= input.nextLine();
							String choice= menuChoice + "." + subMenuChoice;
							callSubMenu(choice, stmt, input);
						} while (!subMenuChoice.equals("5"));
						break;

					case "3":
						System.out.println(listTypes);
						break;

					case "4":
						System.out.println(listStyles);
						break;

					case "5":
						System.out.println("bye bye!");
						break;

					default:
						System.out.println("Erreur de saisie, essaye encore!");
						break;
					}
				} while (!menuChoice.equals("5"));

				input.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.out.println("connexion à la base KO");
			e.printStackTrace();
		}

	}

	// print menu and submenu
	public static void printMenu() {
		System.out.println();
		for (int i= 0; i < menu.length; ++i) {
			System.out.println(menu[i]);
		}
	}

	public static void printSubMenu() {
		System.out.println();
		for (int i= 0; i < subMenu.length; ++i) {
			System.out.println(subMenu[i]);
		}	
	}

	// call submenu method
	public static void callSubMenu(String pChoice, Statement pStmt, Scanner pInput) {
		switch (pChoice) {
		// artist menu
		case "1.1": // list artists
			printAllArtists(pStmt);
			break;

		case "1.2": // create artist
			Artist newArtist= new Artist();
			System.out.println("Saisir nom de l'artiste à créer :");
			newArtist.setName(pInput.nextLine());
			insertArtist(newArtist, pStmt);
			break;

		case "1.3": // update artist
			Artist upArtist= new Artist();

			System.out.println("Aide à la saisie :");
			System.out.println("Liste des artistes : ");
			printAllArtists(pStmt);

			System.out.println("Saisir l'id de l'artiste à modifier :");
			upArtist.setId(pInput.nextLong());
			pInput.nextLine();
			
			System.out.println("Saisir nom de l'artiste :");
			upArtist.setName(pInput.nextLine());
			
			updateArtist(upArtist, pStmt);
			
			break;

		case "1.4": // delete artist
			System.out.println("Aide à la saisie :");
			printAllArtists(pStmt);
			System.out.println("Saisir id de l'artiste à supprimer :");
			Long idArDel= new Long(pInput.nextLong());
			pInput.nextLine();
			deleteArtist(idArDel, pStmt);
			break;
			
		// disc menu
		case "2.1": // list discs
			printAllDiscs(pStmt);
			break;

		case "2.2": // create disc
			insertDisc(setDisc("creation", pInput, pStmt), pStmt);
			break;

		case "2.3": // update disc
			updateDisc(setDisc("update", pInput, pStmt), pStmt);
			break;

		case "2.4": // delete disc
			System.out.println("Aide à la saisie :");
			printAllDiscs(pStmt);
			System.out.println("Saisir id du disque à supprimer :");
			Long idDel= new Long(pInput.nextLong());
			pInput.nextLine();
			deleteDisc(idDel, pStmt);
			break;

		case "1.5":
		case "2.5":
			System.out.println("\nRetour au menu principal\n");
			break;

		default:
			System.out.println("Erreur de saisie, essaye encore!");
		}

	}

	// method for getting all types
	public static void getAllTypes(Statement pStmt) {
		String sql= "SELECT pk_id, name FROM public.\"Type\";";
		//System.out.println(sql);

		try (ResultSet result= pStmt.executeQuery(sql)) {
			while (result.next()) {
				listTypes.put(new Long(result.getLong(1)), result.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for getting all styles
	public static void getAllStyles(Statement pStmt) {
		String sql= "SELECT pk_id, name FROM public.\"Style\";";

		try (ResultSet result= pStmt.executeQuery(sql)) {
			while (result.next()) {
				listStyles.put(new Long(result.getLong(1)), result.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for printing all artists
	public static void printAllArtists(Statement pStmt) {
		ArrayList<Artist> listArtists= new ArrayList<Artist>();

		// select and print all discs

		try (ResultSet result= pStmt.executeQuery("select pk_id, name from public.\"Artist\" order by name;")) {
			while (result.next()) {
				// add a Disc to the list
				listArtists.add(new Artist(result.getLong(1), result.getString(2)));
			}
			// print artists
			for (Artist ar : listArtists) {
				System.out.println(ar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for printing all discs
	public static void printAllDiscs(Statement pStmt) {
		// list of discs to store result of select
		ArrayList<Disc> listDiscs= new ArrayList<Disc>();

		// query = select a.pk_id, a.title, a.year, a.rating, b.name, c.name, d.name from public."Disc" a, public."Artist" b, public."Type" c, public."Style" d where a.fk_artist_id=b.pk_id and a.fk_type_id=c.pk_id and a.fk_style_id=d.pk_id;
		String sql= "select a.pk_id, a.title, b.pk_id, b.name, c.pk_id, c.name, d.pk_id, d.name, a.year, a.rating from public.\"Disc\" a, public.\"Artist\" b, public.\"Type\" c, public.\"Style\" d where a.fk_artist_id=b.pk_id and a.fk_type_id=c.pk_id and a.fk_style_id=d.pk_id;";
		//System.out.println(sql);

		try (ResultSet result= pStmt.executeQuery(sql)) {
			while (result.next()) {
				// add a Disc to the list
				listDiscs.add(new Disc(result.getLong(1), result.getString(2), result.getLong(3), result.getString(4), result.getLong(5), result.getString(6), result.getLong(7), result.getString(8), result.getInt(9), result.getDouble(10)));
			}
			// print discs
			for (Disc ds : listDiscs) {
				System.out.println(ds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// set disc for creation or update
	public static Disc setDisc(String mode, Scanner pInput, Statement pStmt) {
		Disc res= new Disc();

		System.out.println("Aide à la saisie :");
		System.out.println("Liste des artistes : ");
		printAllArtists(pStmt);
		System.out.println("Liste des types : " + listTypes);
		System.out.println("Liste des styles : " + listStyles);

		if (mode.equals("update")) {
			printAllDiscs(pStmt);		
			System.out.println("Saisir id du disque à modifier :");
			res.setId(new Long(pInput.nextLong()));
			pInput.nextLine();
		}

		System.out.println("Saisir titre :");
		res.setTitle(pInput.nextLine());

		System.out.println("Saisir année de sortie :");
		res.setYear(pInput.nextInt());
		pInput.nextLine();

		System.out.println("Saisir note :");
		res.setRating(pInput.nextDouble());
		pInput.nextLine();

		System.out.println("Saisir id artiste :");
		res.setArtistId(new Long(pInput.nextLong()));
		pInput.nextLine();

		System.out.println("Saisir id type :");
		res.setTypeId(new Long(pInput.nextLong()));
		pInput.nextLine();

		System.out.println("Saisir id style :");
		res.setStyleId(new Long(pInput.nextLong()));
		pInput.nextLine();

		return res;
	}


	// insert disc
	public static Long insertDisc(Disc pDisc, Statement pStmt) {
		long res= 0;

		String sql= "INSERT INTO public.\"Disc\" (title, year, rating, fk_artist_id, fk_type_id, fk_style_id) VALUES ('" + pDisc.getTitle() + "'," + pDisc.getYear() + "," + pDisc.getRating() + "," + pDisc.getArtistId() + "," + pDisc.getTypeId() + "," + pDisc.getStyleId() + ")";
		//System.out.println(sql);

		try {
			pStmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs= pStmt.getGeneratedKeys();
			rs.next();
			res= rs.getLong("pk_id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (new Long(res));

	}

	// update disc
	public static void updateDisc(Disc pDisc, Statement pStmt) {

		String sql= "UPDATE public.\"Disc\" SET title= '" + pDisc.getTitle() + "', year= " + pDisc.getYear() + ", rating= " + pDisc.getRating() + ", fk_artist_id= " + pDisc.getArtistId() + ", fk_type_id= " + pDisc.getTypeId() + ", fk_style_id= " + pDisc.getStyleId() + " where pk_id= " + pDisc.getId();
		System.out.println(sql);

		try {
			pStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// delete disc
	public static void deleteDisc(Long id, Statement pStmt) {
		String sql= "DELETE FROM public.\"Disc\" WHERE pk_id=" + id.longValue();
		//System.out.println(sql);

		try {
			System.out.println("Nombre de disques supprimés = " + pStmt.executeUpdate(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// insert artist
	public static Long insertArtist(Artist pArtist, Statement pStmt) {
		long res= 0;

		String sql= "INSERT INTO public.\"Artist\" (name) VALUES ('" + pArtist.getName() + "')";
		System.out.println(sql);

		try {
			pStmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs= pStmt.getGeneratedKeys();
			rs.next();
			res= rs.getLong("pk_id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (new Long(res));
	}
	
	// update artist
	public static void updateArtist(Artist pArtist, Statement pStmt) {

		String sql= "UPDATE public.\"Artist\" SET name= '" + pArtist.getName() + "' where pk_id= " + pArtist.getId();
		System.out.println(sql);

		try {
			pStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// delete artist
	public static void deleteArtist(Long id, Statement pStmt) {
		String sql= "DELETE FROM public.\"Artist\" WHERE pk_id=" + id.longValue();
		System.out.println(sql);

		try {
			System.out.println("Nombre d'artistes supprimés = " + pStmt.executeUpdate(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
