package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "tb_m_role")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "level", nullable = false)
    private Integer Level;

    public void setId(Integer id){
        Id = id;
    }

    public Integer getId(){
        return Id;
    }

    public void setName(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setLevel(Integer level){
        Level = level;
    }

    public Integer getLevel(){
        return Level;
    }
}
