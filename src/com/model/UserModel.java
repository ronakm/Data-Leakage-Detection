package com.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.helper.GetSetProperties;
import org.openide.util.Exceptions;
import com.helper.SimpleCrypto;



/**
 *
 * @author Admin
 */
public class UserModel {
    private String LoginId 	="";
    private String UserPassword 	="";
    private String UserId	="";
    private String FName 	="";
    private String LName 	="";
    private String IPAddress 	="";
    private String CellNumber 	="";
    private String EmailAddress 	="";
    private String ActiveFlag 	="";
    private String LogStatus 	="";
    private String LogOnTime 	="";
    private String LogOffTime 	="";
    private String rollId="";
    private String days="";
private String city="";
private String pincode="";
    

    /**
     * @return the LoginId
     */
    public String getLoginId() {
        return LoginId;
    }

    /**
     * @param LoginId the LoginId to set
     */
    public void setLoginId(String LoginId) {
        
        this.LoginId = LoginId;
    }

    /**
     * @return the UserPassword
     */
    public String getUserPassword() {
        return UserPassword;
    }

    /**
     * @param UserPassword the UserPassword to set
     */
    public void setUserPassword(String UserPassword) {
        String upassword="";
        try {

            upassword =             SimpleCrypto.encrypt(GetSetProperties.getProperty("SEED_KEY"), UserPassword);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        this.UserPassword = upassword;
    }

    /**
     * @return the UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    /**
     * @return the FName
     */
    public String getFName() {
        return FName;
    }

    /**
     * @param FName the FName to set
     */
    public void setFName(String FName) {
        this.FName = FName;
    }

    /**
     * @return the LName
     */
    public String getLName() {
        return LName;
    }

    /**
     * @param LName the LName to set
     */
    public void setLName(String LName) {
        this.LName = LName;
    }

    /**
     * @return the IPAddress
     */
    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * @param IPAddress the IPAddress to set
     */
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    /**
     * @return the CellNumber
     */
    public String getCellNumber() {
        return CellNumber;
    }

    /**
     * @param CellNumber the CellNumber to set
     */
    public void setCellNumber(String CellNumber) {
        this.CellNumber = CellNumber;
    }

    /**
     * @return the EmailAddress
     */
    public String getEmailAddress() {
        return EmailAddress;
    }

    /**
     * @param EmailAddress the EmailAddress to set
     */
    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    /**
     * @return the ActiveFlag
     */
    public String getActiveFlag() {
        return ActiveFlag;
    }

    /**
     * @param ActiveFlag the ActiveFlag to set
     */
    public void setActiveFlag(String ActiveFlag) {
        this.ActiveFlag = ActiveFlag;
    }

    /**
     * @return the LogStatus
     */
    public String getLogStatus() {
        return LogStatus;
    }

    /**
     * @param LogStatus the LogStatus to set
     */
    public void setLogStatus(String LogStatus) {
        this.LogStatus = LogStatus;
    }

    /**
     * @return the LogOnTime
     */
    public String getLogOnTime() {
        return LogOnTime;
    }

    /**
     * @param LogOnTime the LogOnTime to set
     */
    public void setLogOnTime(String LogOnTime) {
        this.LogOnTime = LogOnTime;
    }

    /**
     * @return the LogOffTime
     */
    public String getLogOffTime() {
        return LogOffTime;
    }

    /**
     * @param LogOffTime the LogOffTime to set
     */
    public void setLogOffTime(String LogOffTime) {
        this.LogOffTime = LogOffTime;
    }

    /**
     * @return the rollId
     */
    public String getRollId() {
        return rollId;
    }

    /**
     * @param rollId the rollId to set
     */
    public void setRollId(String rollId) {
        this.rollId = rollId;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(String days) {
        this.days = days;
    }


}
