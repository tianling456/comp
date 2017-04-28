package com.comp.entities;

public class Menu implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String menuId;

    private String code;

    private String url;

    private String menuName;

    private String imageUrl;

    private String shortCut;

    private String sort;

    private String grade;

    private String deleteMark;

    private String menuType;

    private String allowEdit;

    private String superMenu;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut == null ? null : shortCut.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(String deleteMark) {
        this.deleteMark = deleteMark == null ? null : deleteMark.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(String allowEdit) {
        this.allowEdit = allowEdit == null ? null : allowEdit.trim();
    }

    public String getSuperMenu() {
        return superMenu;
    }

    public void setSuperMenu(String superMenu) {
        this.superMenu = superMenu == null ? null : superMenu.trim();
    }
}