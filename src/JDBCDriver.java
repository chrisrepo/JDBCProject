//STEP 1. Import required packages
import java.sql.*;
import java.util.*;

public class JDBCDriver {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:derby:testdb5";

   //  Database credentials
   static final String USER = "username";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL);
      System.out.println("Connected database successfully...");
      
      boolean exit = true;
      String mainMenu = "Enter the number corresponding to your choice:\n1. List All Album Titles\n2. List All Data For An Album" +
      "\n3. Insert A New Album\n4. Insert New Studio and Update Albums\n5. Remove An Album\n6. Enter Custom Sql";
      Scanner scan = new Scanner(System.in);
      while (exit) {
    	  System.out.println(mainMenu);
    	  if (scan.hasNextInt()) {
    		  int choice = scan.nextInt();
    		  scan.nextLine();
    		  switch (choice) {
    		  case 1: {
    			 // LIST ALL ALBUM TITLES
    		      System.out.println("Creating statement...\n");
    		      stmt = conn.createStatement();

    		      String sql = "SELECT albumTitle FROM albums";
    		      ResultSet rs = stmt.executeQuery(sql);
    		      //STEP 5: Extract data from result set
    		      System.out.println("Album Titles:");
    		      while(rs.next()){
    		         //Retrieve by column name
    		         String first = rs.getString("albumTitle");

    		         //Display values
    		         System.out.println(first);
    		      }
    		      System.out.println();
    		      rs.close();
    		  }//end case 1
    			  break;
    		  case 2: {
    			  //LIST DATA FOR ALBUM
    			  System.out.println("Enter the name of the album to find");
    			  String albumChoice = scan.nextLine();
    			  System.out.println("Creating statement...");
    		      stmt = conn.createStatement();
    			  String sql = "SELECT albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName FROM albums WHERE albumTitle='"+albumChoice+"'";
    			  ResultSet rs = stmt.executeQuery(sql);
    			  while(rs.next()) {
    				  String title = rs.getString("albumTitle");
    				  String date = rs.getString("dateRecorded");
    				  String length = rs.getString("length");
    				  String songs = rs.getString("numberOfSongs");
    				  String groupName = rs.getString("groupName");
    				  String studioName = rs.getString("studioName");
    				  
    				  System.out.println("\nTitle: "+title+"\nRecording Date: "+date+"\nLength: "+length+"\nSongs: "+songs+"\nGroup Name: "+groupName+"\nStudio Name:"+studioName+"\n");
    			  }
    			  
    		  }//end case 2
    			  break;
    		  case 3: {
    			  stmt = conn.createStatement();
    			  System.out.println("Enter the title of the album to add(limit 20 characters)");
    			  String title = scan.nextLine();
    			  System.out.println("Enter the date recorded (Month YYYY)");
    			  String date = scan.nextLine();
    			  System.out.println("Enter the lenght of the record (minutes:seconds)");
    			  String length = scan.nextLine();
    			  System.out.println("Enter the number of songs");
    			  String songs = scan.nextLine();
    			  System.out.println("Enter group name");
    			  String groupName = scan.nextLine();
    			  System.out.println("Enter studio name");
    			  String studioName = scan.nextLine();
    			  System.out.println("Creating statement...");
    			  String sql = "INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName) VALUES " +
    			  		"('"+title+"', '"+date+"', '"+length+"', "+songs+", '"+groupName+"', '"+studioName+"')";
    			  stmt.executeUpdate(sql);
    		  }//end case 3
    			  break;
    		  case 4: {
    			  //INSERT NEW STUDIO/ UPDATE
    			  stmt = conn.createStatement();
    			  System.out.println("Enter name of new studio");
    			  String name = scan.nextLine();
    			  System.out.println("Enter address of studio");
    			  String address = scan.nextLine();
    			  System.out.println("Enter owner name");
    			  String owner = scan.nextLine();
    			  System.out.println("Enter studio phone");
    			  String phone = scan.nextLine();
    			  System.out.println("Enter name of studio to replace");
    			  String replace = scan.nextLine();
    			  String sql = "INSERT INTO studios (studioName, studioAddress, studioOwner, studioPhone) " +
    			  		"VALUES ('"+name+"', '"+address+"', '"+owner+"', '"+phone+"')";
    			  stmt.executeUpdate(sql);
    			  String sql2 = "UPDATE albums SET studioName='"+name+"' WHERE studioName='"+replace +"'";
    			  stmt.executeUpdate(sql2);
    		  }//end case 4
    			  break;
    		  case 5: {
    			  stmt = conn.createStatement();
    			  //REMOVE
    			  System.out.println("Enter the name of the album to be deleted");
    			  String title = scan.nextLine();
    			  System.out.println("Enter the name of the group");
    			  String group = scan.nextLine();
    			  
    			  String sql = "DELETE FROM albums WHERE albumTitle='"+title +"' AND groupName='"+group+"'";
    			  stmt.executeUpdate(sql);
    		  }//end case 5
    			  break;
    		  case 6: {
    			  stmt = conn.createStatement();
    			  System.out.println("Enter custom SQL Query");
    			  String sql = scan.nextLine();
    			  if (sql.contains("SELECT")) {
    				  ResultSet rs = stmt.executeQuery(sql);
    				  ResultSetMetaData rsmd = rs.getMetaData();
    				  int columnsNumber = rsmd.getColumnCount();
    				  while (rs.next()) {
    				        for (int i = 1; i <= columnsNumber; i++) {
    				            if (i > 1) System.out.print(",  ");
    				            String columnValue = rs.getString(i);
    				            System.out.print(columnValue);
    				        }
    				        System.out.println("");
    				    }
    			  } else {
    				  stmt.executeUpdate(sql);
    			  }
    			  System.out.println("");
    		  }//end case 6 
    		  	break; }}
    	 else {
    		  System.out.println("Invalid Input");
    	  }//end else
    	 
      }//end while
       
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}