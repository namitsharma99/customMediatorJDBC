package newsrc.code;

import java.sql.Connection;

import org.json.JSONObject;
import org.json.XML;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import src.jdbc.Connect2JDBC;

public class PersistConnector extends AbstractMediator {

	public boolean mediate(MessageContext context) {
		
/*		String id = (String) context.getProperty("id");
		String fname = (String) context.getProperty("fname");
		String lname = (String) context.getProperty("lname");
		
		// String text = context.getEnvelope().getText();
		System.out.println("ID ================================== "+ id);
		System.out.println("FNAME =============================== "+ fname);
		System.out.println("LNAME =============================== "+ lname);*/
		
		System.out.println("$. from context ++++++++++++++++++++++++++++++ "+context.getProperty("$body"));
		System.out.println("=============================================================================");
		// System.out.println("context.getEnvelope().getBody().getFirstElementLocalName() --> "+context.getEnvelope().getBody().getFirstElementLocalName());
		// System.out.println("context.getEnvelope().getBody().getText() --> "+context.getEnvelope().getBody().getText());
		String xmlText = context.getEnvelope().getBody().toString();
		System.out.println("Json format ------>>>> "+XML.toJSONObject(xmlText).toString());
		JSONObject jsonObj = XML.toJSONObject(xmlText);
		JSONObject innerObj = (JSONObject) jsonObj.get("soapenv:Body");
		JSONObject innermost = (JSONObject) innerObj.get("jsonObject");
		System.out.println("Inner most >>>>>>>>>>>>>>>>> "+ innermost);
		
		System.out.println("ID ================================== "+ innermost.getInt("id"));
		System.out.println("FNAME =============================== "+ innermost.getString("fname"));
		System.out.println("LNAME =============================== "+ innermost.getString("lname"));
		
		// System.out.println("XML Text == "+ xmlText);
		/*System.out.println("Checking iterator.....");
		Iterator itr = context.getEnvelope().getBody().getAllAttributes();
		while (itr.hasNext()) {
			System.out.println(" ITR value --- "+ itr.next());			
		}*/
		int id = innermost.getInt("id");
		String fname = innermost.getString("fname");
		String lname = innermost.getString("lname"); 

		Connection connection = Connect2JDBC.getConnection();
		String sql = "Insert into myEmployees values ("+ id +",\"" + fname + "\",\"" + lname	+ "\");";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			int result = stmt.executeUpdate(sql);
			System.out.println("Result of insert query - " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

/*	public static void main(String[] args) {

		PersistConnector connector = new PersistConnector();
		connector.mediate(null);
	}*/

}
