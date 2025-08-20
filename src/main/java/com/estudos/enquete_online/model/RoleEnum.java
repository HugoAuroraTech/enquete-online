package com.estudos.enquete_online.model;

public enum RoleEnum {
    USER("usuario"),
    ADMIN("admin");

    private String role;

    RoleEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
