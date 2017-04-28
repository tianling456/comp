package com.comp.entities;

public class GroupPrivilege implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String groupPrivilegeId;

    private String groupId;

    private String privilegeId;

    private String privilegeType;

    public String getGroupPrivilegeId() {
        return groupPrivilegeId;
    }

    public void setGroupPrivilegeId(String groupPrivilegeId) {
        this.groupPrivilegeId = groupPrivilegeId == null ? null : groupPrivilegeId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
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