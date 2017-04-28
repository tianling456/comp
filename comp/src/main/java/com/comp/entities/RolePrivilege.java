package com.comp.entities;

public class RolePrivilege implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String rolePrivilegeId;

    private String roleId;

    private String privilegeId;

    private String privilegeType;

    public String getRolePrivilegeId() {
        return rolePrivilegeId;
    }

    public void setRolePrivilegeId(String rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId == null ? null : rolePrivilegeId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId == null ? null : privilegeId.trim();
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType == null ? null : privilegeType.trim();
    }
}