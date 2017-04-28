package com.comp.entities;

public class User implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String available;

    private String userName;

    private String loginType;

    private String name;

    private String organizationId;

    private String password;

    private String salt;

	private String userId;

	private String userUuid;

	
	
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String available, String userName, String loginType,
			String name, String organizationId, String password, String salt,
			String userId, String userUuid) {
		super();
		this.available = available;
		this.userName = userName;
		this.loginType = loginType;
		this.name = name;
		this.organizationId = organizationId;
		this.password = password;
		this.salt = salt;
		this.userId = userId;
		this.userUuid = userUuid;
	}


	public String getAvailable() {
		return available;
	}


	public String getLoginType() {
		return loginType;
	}

	public String getName() {
        return name;
    }

	public String getOrganizationId() {
        return organizationId;
    }

	public String getUserName() {
		return userName;
	}


	public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserUuid() {
        return userUuid;
    }


    public void setAvailable(String available) {
		this.available = available == null ? null : available.trim();
	}

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public void setLoginType(String loginType) {
		this.loginType = loginType == null ? null : loginType.trim();
	}

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }
}