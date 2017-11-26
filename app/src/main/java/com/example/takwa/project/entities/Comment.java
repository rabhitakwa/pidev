package com.example.takwa.project.entities;

/**
 * Created by takwa on 23/11/2017.
 */

public class Comment {
    String description;
    int id;
    int idUsers ;
    String userName ;
    int idDocument ;
    String date ;
    Document document ;
    User utilisateur ;

    @Override
    public String toString() {
        return "Comment{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", idUsers=" + idUsers +
                ", userName='" + userName + '\'' +
                ", idDocument=" + idDocument +
                ", date='" + date + '\'' +
                ", document=" + document +
                ", utilisateur=" + utilisateur +
                '}';
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Comment(String description, String date) {
        this.description = description;
        this.date = date;
    }
    public Comment() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
