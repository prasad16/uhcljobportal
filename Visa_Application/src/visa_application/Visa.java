/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visa_application;

/**
 *
 * @author Prejan
 */
public class Visa {
    
    String passportid;
    String month;
    String status;

    public Visa(String passportid, String month, String status) {
        this.passportid = passportid;
        this.month = month;
        this.status = status;
    }

    public String getPassportid() {
        return passportid;
    }

    public void setPassportid(String passportid) {
        this.passportid = passportid;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
