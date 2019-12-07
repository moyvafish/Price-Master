package com.example.mc;

public class UnderCatalog {
    private String name;
    private int icon;
    private Integer idUnderCatalog;
    public UnderCatalog(String name, int icon, int idUnderCatalog){

        this.name=name;
        this.icon = icon;
        this.idUnderCatalog = idUnderCatalog;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getIdUnderCatalog() {
        return this.idUnderCatalog;
    }

    public void setIdUnderCatalog(int idUnderCatalog) {
        this.idUnderCatalog = idUnderCatalog;
    }
}

