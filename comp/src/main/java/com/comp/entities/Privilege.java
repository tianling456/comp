package com.comp.entities;

public class Privilege implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String privilegeId;

    private String parentPrivilegeId;

    private String privilegeName;

    private String createTime;

    private String available;

    private String description;

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId == null ? null : privilegeId.trim();
    }

    public String getParentPrivilegeId() {
        return parentPrivilegeId;
    }

    public void setParentPrivilegeId(String parentPrivilegeId) {
        this.parentPrivilegeId = parentPrivilegeId == null ? null : parentPrivilegeId.trim();
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName == null ? null : privilegeName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}