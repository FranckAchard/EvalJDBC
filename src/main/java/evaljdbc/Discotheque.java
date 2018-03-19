package evaljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Discotheque {

	public static void main(String[] args) {

		/* TODO menu :
		 * 1. artistes :
		 *   1.1 lister tous les artistes
		 *   1.2 ajouter un artiste
		 *   1.3 modifier un artiste
		 *   1.4 supprimer un artiste
		 * 2. disques :
		 *   2.1 lister tous les disques
		 *   2.2 ajouter un disque
		 *   2.3 modifier un disque
		 *   2.4 supprimer un disque
		 * 3. types :
		 *   3.1 lister tous les types
		 *   3.2 ajouter un type
		 *   3.3 modifier un type
		 *   3.4 supprimer un type
		 * 4. styles :
		 *   4.1 lister tous les styles
		 *   4.2 ajouter un style
		 *   4.3 modifier un style
		 *   4.4 supprimer un style
		 */
		
		// connection string for local DB (PostgreSQL)
		// TODO store login/password in properties file
		String[] connectStringLocalDb= {"jdbc:postgresql://localhost:5432/discotheque", "postgres", "admin"};

		System.out.println("test connexion base locale : " + connectStringLocalDb[0]);

		try(Connection connLocal= DriverManager.getConnection(connectStringLocalDb[0], connectStringLocalDb[1], connectStringLocalDb[2])) {
			System.out.println("connexion ouverte : " + connLocal);
			
			try (Statement stmt= connLocal.createStatement()) {
				
				//printAllDiscs(connLocal);
				printAllArtists(connLocal);
				//insertDisc();

				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.out.println("connexion à la base KO");
			e.printStackTrace();
		}

	}
	
	// method for printing all discs
	public static void printAllDiscs(Connection conn) {
		// list of discs to store result of select
		ArrayList<Disc> listDiscs= new ArrayList<Disc>();

		try (Statement stmt= conn.createStatement()) {

			// select and print all discs
			// query = select a.pk_id, a.title, a.year, a.rating, b.name, c.name, d.name from public."Disc" a, public."Artist" b, public."Type" c, public."Style" d where a.fk_artist_id=b.pk_id and a.fk_type_id=c.pk_id and a.fk_style_id=d.pk_id;

			try (ResultSet result= stmt.executeQuery("select a.pk_id, a.title, b.pk_id, b.name, c.pk_id, c.name, d.pk_id, d.name, a.year, a.rating from public.\"Disc\" a, public.\"Artist\" b, public.\"Type\" c, public.\"Style\" d where a.fk_artist_id=b.pk_id and a.fk_type_id=c.pk_id and a.fk_style_id=d.pk_id;")) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// method for printing all artists
	public static void printAllArtists(Connection conn) {
		// list of artists to store result of select
		ArrayList<Artist> listArtists= new ArrayList<Artist>();

		try (Statement stmt= conn.createStatement()) {

			// select and print all discs
			// query = select pk_id, name from public."Artist" order by name;

			try (ResultSet result= stmt.executeQuery("select pk_id, name from public.\"Artist\" order by name;")) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public static Long insertDisc(Disc pDisc, Statement pStmt) {
		long res= 0;
		//query example : INSERT INTO public."Disc" (title, year, rating, fk_artist_id, fk_type_id, fk_style_id) VALUES ('Rubber Soul', 1965, 5.0, 1, 1, 4);
		String sql= "INSERT INTO public.\"Disc\" (title, year, rating, fk_artist_id, fk_type_id, fk_style_id) VALUES (" + pDisc.getTitle() + "," + pDisc.getYear() + "," + pDisc.getRating() + "," + ...)";
		System.out.println(sql);
		try {
			pStmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs= pStmt.getGeneratedKeys();
			rs.next();
			res= rs.getLong("id");
			System.out.println("Generated key = " + res);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return (new Long(res));

	}
	*/
}
