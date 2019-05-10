package rakhimova.ru.instagramclient.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_hits")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url= '" + webformatURL + '\'' +
                '}';
    }

}
