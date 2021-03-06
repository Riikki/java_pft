package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try{
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                            "user=root&password=1" +
                            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("Select group_id, group_name, group_header, group_footer  from group_list");
            Groups groups = new Groups();
            while(resultSet.next()){
                groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                        .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer"))
                );
            }
            resultSet.close();
            st.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
