package com.amrmustafa.AAD.model;

import com.amrmustafa.AAD.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chike on 12/10/2016.
 */

public class Post {

    @SerializedName(Constants.Email_Address)
    @Expose
    private String email;

    @SerializedName(Constants.Name)
    @Expose
    private String name;

    @SerializedName(Constants.Last_Name)
    @Expose
    private String lName;


    @SerializedName(Constants.url)
    @Expose
    private String url;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getlName() {
        return lName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Post{" +
                Constants.Email_Address+"='" + email + '\'' +
                ","+ Constants.Name+"='" + name + '\'' +
                ", "+Constants.Last_Name+"=" + lName +
                ", "+Constants.Link_to_project+"=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (email != null ? !email.equals(post.email) : post.email != null) return false;
        if (name != null ? !name.equals(post.name) : post.name != null) return false;
        if (lName != null ? !lName.equals(post.lName) : post.lName != null) return false;

        if (url != null ? !url.equals(post.url) : post.url != null) return false;
        return url != null ? url.equals(post.url) : post.url == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lName != null ? lName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
