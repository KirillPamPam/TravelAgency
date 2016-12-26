package ru.kir.travel.model;

import javax.persistence.*;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Entity
@Table(name = "resorts")
public class Resort {
    @Id
    @Column(name = "resort_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resort_name")
    private String resortName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResortName() {
        return resortName;
    }

    public void setResortName(String resortName) {
        this.resortName = resortName;
    }

    @Override
    public String toString() {
        return "Resort{" +
                "id=" + id +
                ", resortName='" + resortName + '\'' +
                '}';
    }
}
