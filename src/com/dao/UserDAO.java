/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.helper.Constants;
import java.util.List;


import com.helper.GetSetProperties;
import com.helper.NetworkHelper;
import com.helper.SimpleCrypto;
import com.helper.StringHelper;
import com.helper.UserSession;
import com.model.UserModel;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import swinghelper.OptionHelper;

/**
 *
 * @author admin
 */
public class UserDAO {

    //add user details in userinfo : RegistrationForm.java
    public boolean AddUser(String loginID, String password, int userID, String firstName, String lastName, long cellNo, String emailId, String city, String pincode) {
//            String ipAddrStr = LoginForm.userInfo.getIpAddrStr();
        String ipAddrStr = "";
    
            ipAddrStr = NetworkHelper.getMACAddress();
       
        String query = "insert into userinfo (`LoginId`, `UserPassword`,  `FName`, `LName`, `IPAddress`, `CellNumber`, `EmailAddress`, `ActiveFlag`, `LogStatus`, `LogOnTime`, `LogOffTime`,`city`,`pincode` )" + " values ('" + loginID + "','" + password + "','" + firstName + "','" + lastName + "','" + ipAddrStr + "','" + cellNo + "','" + emailId + "',1,'Online'," + GetSetProperties.getProperty("CURR_TIMESTAMP") + ",null,'" + city + "','" + pincode + "')";
        System.out.println(" query " + query);
//            new UserDAOHelper().executeInsert(query);
        int i = ConnectionManager.executeUpdate(query, null);
        boolean success = false;
        if (i > 0) {
            success = true;
        }

        return success;
    }

    public boolean addTrackDownload(String fileId) {
//            String ipAddrStr = LoginForm.userInfo.getIpAddrStr();
        String ipAddrStr = "";
        try {
            ipAddrStr = NetworkHelper.getMACAddress();
        } catch (Exception ex) {
            ex.printStackTrace();
            ;
        }
        if (UserSession.loggedInUser == null) {
            OptionHelper.showError("Please login.", "Error");
            return false;
        }
        String query = "insert into trackdownloads (`userid`, `fileid`,  `ipaddress`)" + "values (" + UserSession.loggedInUser.getUserId() + "," + fileId + ",'" + ipAddrStr + "')";
        System.out.println(" query " + query);
//            new UserDAOHelper().executeInsert(query);
        int i = ConnectionManager.executeUpdate(query, null);
        boolean success = false;
        if (i > 0) {
            success = true;
        }

        return success;
    }
    public boolean addAgentRequest(String fileDesc,String distributorId) {
//            String ipAddrStr = LoginForm.userInfo.getIpAddrStr();
        String ipAddrStr = "";
    
            ipAddrStr = NetworkHelper.getMACAddress();
       
        if (UserSession.loggedInUser == null) {
            OptionHelper.showError("Please login.", "Error");
            return false;
        }
        String query = "insert into agentrequest (description, ditributorid,  sentFromIp,agentId) values ('" +
                fileDesc + "'," + distributorId + ",'" + ipAddrStr + "',"+UserSession.loggedInUser.getUserId() +")";
        System.out.println(" query " + query);
//            new UserDAOHelper().executeInsert(query);
        int i = ConnectionManager.executeUpdate(query, null);
        boolean success = false;
        if (i > 0) {
            success = true;
        }

        return success;
    }

    public boolean addRequest(String sender, String folderPath, String totalSize, String receiver, String encryptionKey, String originalPath, String desc) {
//            String ipAddrStr = LoginForm.userInfo.getIpAddrStr();
        String ipAddrStr = "";
        try {
            ipAddrStr = NetworkHelper.getMACAddress();
            encryptionKey = SimpleCrypto.encrypt("DATALEAK", encryptionKey);
        } catch (Exception ex) {
            ex.printStackTrace();
            ;
        }
        String query = "insert into datadb.backupinfo (Sender, FolderPath, TotalSize, Receiver,encryptionKey,originalPath,SaveTimestamp,description )" + " values (" + sender + ",'" + folderPath + "','" + totalSize + "'," + receiver + ",'" + encryptionKey + "','" + originalPath + "',NOW(),'" + desc + "')";
        System.out.println(" query " + query);
//            new UserDAOHelper().executeInsert(query);
        int i = ConnectionManager.executeUpdate(query, null);
        boolean success = false;
        if (i > 0) {
            success = true;
        }

        return success;
    }

    public UserModel CheckIDandPassword(UserModel um) {
        String query = "select activeflag,UserId,FName,LName,CellNumber,EmailAddress,rollId,DATEDIFF( current_timestamp ,`changepassword`) as days from userinfo where LoginId = '" + um.getLoginId() + "'  and UserPassword = '" + um.getUserPassword() + "'";
        //"' and LogStatus = 'Offline'";
        List list = new ConnectionManager().getBeanList(UserModel.class, query);
        if (list.size() > 0) {
            um = (UserModel) list.get(0);
        } else {
            um = null;
        }

        return um;
    }

    public List getUsers() {
        List list = null;
        if (UserSession.loggedInUser == null) {
            OptionHelper.showError("Please login.", "Error");
            return null;
        } else {
            String query = "select * from userinfo where ActiveFlag=1 and rollId=" + Constants.AGENT_ROLL_ID + " and UserId != " + UserSession.loggedInUser.getUserId();
            list = new ConnectionManager().getBeanList(UserModel.class, query);
        }

        return list;
    }

    public List getUsers(String rollId) {
        List list = null;
        if (UserSession.loggedInUser == null) {
            OptionHelper.showError("Please login.", "Error");
            return null;
        } else {
            String query = "select * from userinfo where ActiveFlag=1 and rollId=" + rollId ;
            list = new ConnectionManager().getBeanList(UserModel.class, query);
        }
        return list;
    }

    public List populateFiles(JTable jtable) throws Exception {
        String query = "SELECT b.FolderPath, b.TotalSize, b.Receiver,date_format( b.SaveTimestamp, '%d-%b-%y %a') as st ,u.FName , u.LName  FROM datadb.backupinfo b,datadb.userinfo u where u.userid=b.Receiver and b.sender =" + UserSession.loggedInUser.getUserId()+" order by  b.SaveTimestamp desc";
        System.out.println("query " + query);
        List list = new ConnectionManager().getMapList(query);
        ;

        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) jtable.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (int i = 0; i < list.size(); i++) {
            HashMap param = (HashMap) list.get(i);
            String folderPath = StringHelper.nullObjectToStringEmpty(param.get("FolderPath"));
            String totalSize = StringHelper.nullObjectToStringEmpty(param.get("TotalSize"));
            String receiver = StringHelper.nullObjectToStringEmpty(param.get("Receiver"));
            String saveTimestamp = StringHelper.nullObjectToStringEmpty(param.get("st"));
            String fname = StringHelper.nullObjectToStringEmpty(param.get("FName"));
            String lname = StringHelper.nullObjectToStringEmpty(param.get("LName"));
            model.addRow(new Object[]{i + 1, fname + " " + lname, folderPath, totalSize, saveTimestamp});
        }
        return list;
    }
    public List populateFileReviews(JTable jtable) throws Exception {
        String query = "SELECT b.FolderPath, b.TotalSize, b.Receiver,date_format( b.SaveTimestamp, '%d-%b-%y %a') as SaveTimestamp ,u.FName , u.LName  FROM datadb.backupinfo b,datadb.userinfo u where u.userid=b.Receiver and b.sender =" + UserSession.loggedInUser.getUserId();
        System.out.println("query " + query);
        List list = new ConnectionManager().getMapList(query);
        ;

        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) jtable.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (int i = 0; i < list.size(); i++) {
            HashMap param = (HashMap) list.get(i);
            String folderPath = StringHelper.nullObjectToStringEmpty(param.get("FolderPath"));
            String totalSize = StringHelper.nullObjectToStringEmpty(param.get("TotalSize"));
            String receiver = StringHelper.nullObjectToStringEmpty(param.get("Receiver"));
            String saveTimestamp = StringHelper.nullObjectToStringEmpty(param.get("SaveTimestamp"));
            String fname = StringHelper.nullObjectToStringEmpty(param.get("FName"));
            String lname = StringHelper.nullObjectToStringEmpty(param.get("LName"));
            model.addRow(new Object[]{i + 1, fname + " " + lname, folderPath, totalSize, saveTimestamp});
        }
        return list;
    }

    public List populateFiles2(JTable jtable) throws Exception {
        String query = "SELECT b.FolderPath, b.TotalSize, b.Receiver,date_format( b.SaveTimestamp, '%d-%b-%y %a') as SaveTimestamp " +
                ",u.FName , u.LName,originalPath ,u.`EmailAddress`, u.`CellNumber`,b.`description`,b.`ID` FROM datadb.backupinfo b,datadb.userinfo u where u.userid=b.sender and b.Receiver =" + UserSession.loggedInUser.getUserId();
        System.out.println("query " + query);
        List list = new ConnectionManager().getMapList(query);
        ;

        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) jtable.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (int i = 0; i < list.size(); i++) {
            HashMap param = (HashMap) list.get(i);
            String originalPath = StringHelper.nullObjectToStringEmpty(param.get("description"));
            String totalSize = StringHelper.nullObjectToStringEmpty(param.get("TotalSize"));
            String receiver = StringHelper.nullObjectToStringEmpty(param.get("Receiver"));
            String saveTimestamp = StringHelper.nullObjectToStringEmpty(param.get("SaveTimestamp"));
            String fname = StringHelper.nullObjectToStringEmpty(param.get("FName"));
            String lname = StringHelper.nullObjectToStringEmpty(param.get("LName"));
            String EmailAddress = StringHelper.nullObjectToStringEmpty(param.get("EmailAddress"));
            String CellNumber = StringHelper.nullObjectToStringEmpty(param.get("CellNumber"));

            model.addRow(new Object[]{i + 1, fname + " " + lname, EmailAddress, CellNumber, originalPath, totalSize, saveTimestamp});
        }
        return list;
    }
}
