package ru.rakhimova.instagramclient.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_hits")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    @SerializedName("webformatURL")
    private String webformatURL;

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url= '" + webformatURL + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }
}
