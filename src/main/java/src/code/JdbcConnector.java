package src.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import src.jdbc.Connect2JDBC;

public class JdbcConnector extends AbstractMediator {

	public boolean mediate(MessageContext context) {

		System.out.println("Inside JdbcConnector........................");

		Connection connection = Connect2JDBC.getConnection();
		String sql = "select * from myEmployees;";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count++;
				int id = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				System.out.println("Record# " + count + " = " + id + " : "
						+ " : " + first + " : " + last);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

/*	public static void main(String[] args) {
		JdbcConnector connector = new JdbcConnector();
		connector.mediate(null);
	}*/
}
