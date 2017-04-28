package com.comp.entities;

public class UserPrivilege implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String userPrivilegeId;

    private String userId;

    private String privilegeId;

    private String privilegeType;

    public String getUserPrivilegeId() {
        return userPrivilegeId;
    }

    public void setUserPrivilegeId(String userPrivilegeId) {
        this.userPrivilegeId = userPrivilegeId == null ? null : userPrivilegeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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