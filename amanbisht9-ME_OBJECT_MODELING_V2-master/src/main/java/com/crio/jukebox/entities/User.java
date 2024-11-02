package com.crio.jukebox.entities;


public class User  {
    private int userId;

    private String userName;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public User(int userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public User(String name) {
        this.userName = name;
    }

    @Override
    public String toString() {
        return userId + " " + userName;
    }
    
}
