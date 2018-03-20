package evaljdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Discotheque {

	private static String[] menu= {"menu artistes => saisir 1", "menu disques => saisir 2", "menu types => saisir 3", "menu styles => saisir 4", "quitter => saisir 5"};
	private static String[] subMenu= {"tout afficher => saisir 1", "ajouter => saisir 2", "modifier => saisir 3", "supprimer => saisir 4", "revenir au menu principal => saisir 5"};
	
	private static HashMap<Long, String> listTypes= new  HashMap<Long, String>();
	private static HashMap<Long, String> listStyles= new  HashMap<Long, String>();


	public static void main(String[] args) {

		// print menu
		Scanner input= new Scanner(System.in);
		String menuChoice= "";
		String subMenuChoice= "";

		do {
			printMenu();
			menuChoice = input.nextLine();

			switch(menuChoice) {
			case "1":
				do {
					printSubMenu();
					subMenuChoice= input.nextLine();
					switch (subMenuChoice) {
					case "1":
						
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					default:
						System.out.println("Erreur de saisie, essaye encore!");
						break;
					}
				} while (!subMenuChoice.equals("5"));
				break;

			case "2":
				break;
				
			case "3":
				break;
				
			case "4":
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

		// connection string for local DB (PostgreSQL)
		// TODO store login/password in properties file
		/*
		String[] connectStringLocalDb= {"jdbc:postgresql://localhost:5432/discotheque", "postgres", "admin"};

		System.out.println("test connexion base locale : " + connectStringLocalDb[0]);

		try(Connection connLocal= DriverManager.getConnection(connectStringLocalDb[0], connectStringLocalDb[1], connectStringLocalDb[2])) {
			System.out.println("connexion ouverte : " + connLocal);

			try (Statement stmt= connLocal.createStatement()) {

				// get all styles and types
				getAllTypes(stmt);
				getAllStyles(stmt);

				//printAllDiscs(stmt);
				//printAllArtists(stmt);
				
				//	public Disc(Long id, String title, Long artistId, String artistName, Long typeId, String typeName, Long styleId,
				Disc test= new Disc(null, "Hunky Dory", new Long(4), "", new Long(1), "", new Long(4), "", 1971, 5.0);
				insertDisc(test, stmt);
				printAllDiscs(stmt);

				//deleteDisc(new Long(6), stmt);
				//printAllDiscs(stmt);


			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.out.println("connexion à la base KO");
			e.printStackTrace();
		}
		*/

	}

	// print menu and submenu
	public static void printMenu() {
		for (int i= 0; i < menu.length; ++i) {
			System.out.println(menu[i]);
		}
	}
	
	public static void printSubMenu() {
		for (int i= 0; i < subMenu.length; ++i) {
			System.out.println(subMenu[i]);
		}	
	}
	
	// method for getting all types
	public static void getAllTypes(Statement pStmt) {
		// query = SELECT pk_id, name FROM public."Type";
		String sql= "SELECT pk_id, name FROM public.\"Type\";";
		//System.out.println(sql);

		try (ResultSet result= pStmt.executeQuery(sql)) {
			while (result.next()) {
				// add a Disc to the list
				listTypes.put(result.getLong(1), result.getString(2));
			}

			//System.out.println(listTypes.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for getting all styles
	public static void getAllStyles(Statement pStmt) {

		// query = SELECT pk_id, name FROM public."Style";
		String sql= "SELECT pk_id, name FROM public.\"Style\";";
		//System.out.println(sql);

		try (ResultSet result= pStmt.executeQuery(sql)) {
			while (result.next()) {
				// add a Disc to the list
				listStyles.put(result.getLong(1), result.getString(2));
			}

			//System.out.println(listStyles.toString());

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

	// method for printing all artists
	public static void printAllArtists(Statement pStmt) {
		// list of artists to store result of select
		ArrayList<Artist> listArtists= new ArrayList<Artist>();

		// query = select pk_id, name from public."Artist" order by name;
		String sql= "select pk_id, name from public.\"Artist\" order by name;";
		//System.out.println(sql);

		try (ResultSet result= pStmt.executeQuery(sql)) {
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

	// insert disc
	public static Long insertDisc(Disc pDisc, Statement pStmt) {
		long res= 0;

		//query example : INSERT INTO public."Disc" (title, year, rating, fk_artist_id, fk_type_id, fk_style_id) VALUES ('Rubber Soul', 1965, 5.0, 1, 1, 4);
		String sql= "INSERT INTO public.\"Disc\" (title, year, rating, fk_artist_id, fk_type_id, fk_style_id) VALUES ('" + pDisc.getTitle() + "'," + pDisc.getYear() + "," + pDisc.getRating() + "," + pDisc.getArtistId() + "," + pDisc.getTypeId() + "," + pDisc.getStyleId() + ")";
		System.out.println(sql);
		
		try {
			pStmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs= pStmt.getGeneratedKeys();
			rs.next();
			res= rs.getLong("pk_id");
			System.out.println("Generated key = " + res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (new Long(res));

	}

	// delete disc
	public static void deleteDisc(Long id, Statement pStmt) {
		String sql= "DELETE FROM public.\"Disc\" WHERE pk_id=" + id.longValue();
		System.out.println(sql);
		
		try {
			System.out.println("nb rows deleted = " + pStmt.executeUpdate(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
