package com.comp.entities;

public class LogSetting implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String logId;

    private String tableName;

    private String bussinessName;

    private String primaryKey;

    private String urlTemplate;

    private String userId;

    private String operationTime;

    private String deleteScriptTemplate;

    private String scriptTemplate;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName == null ? null : bussinessName.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate == null ? null : urlTemplate.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime == null ? null : operationTime.trim();
    }

    public String getDeleteScriptTemplate() {
        return deleteScriptTemplate;
    }

    public void setDeleteScriptTemplate(String deleteScriptTemplate) {
        this.deleteScriptTemplate = deleteScriptTemplate == null ? null : deleteScriptTemplate.trim();
    }

    public String getScriptTemplate() {
        return scriptTemplate;
    }

    public void setScriptTemplate(String scriptTemplate) {
        this.scriptTemplate = scriptTemplate == null ? null : scriptTemplate.trim();
    }
}