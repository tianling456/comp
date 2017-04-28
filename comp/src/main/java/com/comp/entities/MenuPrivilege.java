package com.comp.entities;

public class MenuPrivilege implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String menuPrivilegeId;

    private String privilegeId;

    private String code;

    private String privilegesType;

    public String getMenuPrivilegeId() {
        return menuPrivilegeId;
    }

    public void setMenuPrivilegeId(String menuPrivilegeId) {
        this.menuPrivilegeId = menuPrivilegeId == null ? null : menuPrivilegeId.trim();
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId == null ? null : privilegeId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPrivilegesType() {
        return privilegesType;
    }

    public void setPrivilegesType(String privilegesType) {
        this.privilegesType = privilegesType == null ? null : privilegesType.trim();
    }
}