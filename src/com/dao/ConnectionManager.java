/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import com.helper.GetSetProperties;


/**
 *
 * @author Admin
 */
public class ConnectionManager {

    private static Connection getDBConnection() {
        DbUtils.loadDriver(GetSetProperties.p("db.driver"));
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(GetSetProperties.p("db.url"), GetSetProperties.p("db.user"), GetSetProperties.p("db.pwd"));
            System.out.println("Got Connection");
        } catch (SQLException ex) {
       ex.printStackTrace();
        }

        return conn;
    }




    private static void closeConnection(Connection conn) {
        try {
            DbUtils.closeQuietly(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String args[]) throws Exception {
        Connection conn = getDBConnection();
      
//        int i=executeUpdate(GetSetProperties.p("USER_INSERT"),
//                "rajesh","raj@gmail.com","9860923474",1,2011,"Div 1",201,"rajesh","rajesh","agrawal");
//    List list=getBeanList(UserModel.class, "Select * from usermaster");
//
//
//        System.out.println("l "+list.get(0));
//
//
//        System.out.println(list.size());

//        Connection conn=getDBConnection();
//        Statement stmt=conn.createStatement();
//        ResultSet rs=stmt.executeQuery("SELECT b.`branchId`, b.`branchName` FROM branch b");
//ArrayList arr=new ArrayList();
//        while(rs.next()){
//            String branchId=rs.getString("branchId");
//            String branchDesc=rs.getString("branchName");
//                System.out.println(" "+branchId+" "+branchDesc);
//
//                HashMap hmp=new HashMap();
//                hmp.put("branchId",branchId);
//                hmp.put("branchName", branchDesc);
//                arr.add(hmp);
//
//        }


//  closeConnection(conn);



         List arr1= getMapList("SELECT * FROM userinfo ");
        System.out.println(" arr "+arr1.size());
        System.out.println("arr "+arr1);

    }

//    public static int saveUser(UserModel um){
//         int i=executeUpdate(GetSetProperties.p("USER_INSERT"),
//                um.getFirstname(),um.getEmailId(),um.getPhoneno(),um.getBranchId(),um.getYearId(),um.getDivision(),um.getRollNo(),um.getLoginId(),um.getCpassword(),um.getLastname());
//         return i;
//    }

    public static List getBeanList(Class cls, String query) {
        Connection conn = null;
        List beans = null;
        try {
            conn = getDBConnection();

            QueryRunner qRunner = new QueryRunner();
            beans = (List) qRunner.query(conn, query, new BeanListHandler(cls));

        } catch (SQLException e) {
            // handle the exception
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return beans;
    }

    public static List getMapList(String query) {
        Connection conn = null;
        List beans = null;
        try {
            System.out.println("query "+query);
            conn = getDBConnection();

            QueryRunner qRunner = new QueryRunner();
            beans = (List) qRunner.query(conn, query, new MapListHandler());

        } catch (SQLException e) {
            // handle the exception
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return beans;
    }

     public static List getParameterizedList(String query,Object... param) {
        Connection conn = null;
        List beans = null;
        try {
            conn = getDBConnection();

            QueryRunner qRunner = new QueryRunner();
            beans = (List) qRunner.query(conn, query, new MapListHandler(),param);

        } catch (SQLException e) {
            // handle the exception
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return beans;
    }

    public static int executeUpdate(String query, Object... param) {
        Connection conn = null;
        int beans = 0;
        try {
            conn = getDBConnection();
            QueryRunner qRunner = new QueryRunner();
            beans = qRunner.update(conn, query, param);
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return beans;
    }
  
}
