package com.comp.entities;

public class Parameter implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String parameterId;

    private String parameterCode;

    private String parameterValue;

    private String parameterName;

    private String parameteraVailable;

    private String describe;

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId == null ? null : parameterId.trim();
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode == null ? null : parameterCode.trim();
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue == null ? null : parameterValue.trim();
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName == null ? null : parameterName.trim();
    }

    public String getParameteraVailable() {
        return parameteraVailable;
    }

    public void setParameteraVailable(String parameteraVailable) {
        this.parameteraVailable = parameteraVailable == null ? null : parameteraVailable.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}