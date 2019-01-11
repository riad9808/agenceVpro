package traitement;

import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {
	public static java.sql.Connection con() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
				
		} catch (Exception e) {
						e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/agencelocationvehicule","root","riad");
	}
}
