package com.example.takwa.project.entities;

/**
 * Created by takwa on 26/11/2017.
 */

public class Document {
    int id ;
    String path ;
    int size ;
    String  name ;
    long  date ;
    String etadoc ;
    User user ;

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", etadoc='" + etadoc + '\'' +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getEtadoc() {
        return etadoc;
    }

    public void setEtadoc(String etadoc) {
        this.etadoc = etadoc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
