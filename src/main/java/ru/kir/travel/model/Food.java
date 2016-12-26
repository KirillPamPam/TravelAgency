package ru.kir.travel.model;

import javax.persistence.*;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Entity
@Table(name = "type_of_food")
public class Food {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_of_food_value")
    private String food;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", food='" + food + '\'' +
                '}';
    }
}
