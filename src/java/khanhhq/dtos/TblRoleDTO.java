/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.dtos;

 import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class TblRoleDTO implements Serializable{
    private String roleID;
    private String rolename;

    public TblRoleDTO(String roleID, String rolename) {
        this.roleID = roleID;
        this.rolename = rolename;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
}
