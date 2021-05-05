package com.animal.api.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="animal_inform")
public class Inform {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="gender")
    private int gender;

    @Column(name="informer_id")
    private String informer_id;

    @Column(name="org_status")
    private Integer org_status;

    @Column(name = "captured_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp captured_time;

    @Column(name = "progress_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp progress_time;

    @Column(name="now_status")
    private Integer now_status;

    @Column(name="handler_id")
    private String handler_id;

    @Column(name="address")
    private String address;

    @Column(name="img_url")
    private String img_url;

    @Column(name="latlong")
    private String latlong;

    //針對img_url,必須做一個處理multiple images名稱的getter

    //default
    Inform(){

    }

    //setting
    Inform(Integer id,String name,String type,int gender,String informer_id,Integer org_status,Timestamp captured_time,Timestamp progress_time,Integer now_status,String handler_id,String address,String img_url,String latlong){
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.informer_id = informer_id;
        this.org_status = org_status;
        this.captured_time = captured_time;
        this.progress_time = progress_time;
        this.now_status = now_status;
        this.handler_id = handler_id;
        this.address = address;
        this.img_url = img_url;
        this.latlong = latlong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getInformer_id() {
        return informer_id;
    }

    public void setInformer_id(String informer_id) {
        this.informer_id = informer_id;
    }

    public int getOrg_status() {
        return org_status;
    }

    public void setOrg_status(int org_status) {
        this.org_status = org_status;
    }

    public Timestamp getCaptured_time() {
        return captured_time;
    }

    public void setCaptured_time(Timestamp captured_time) {
        this.captured_time = captured_time;
    }

    public Timestamp getProgress_time() {
        return progress_time;
    }

    public void setProgress_time(Timestamp progress_time) {
        this.progress_time = progress_time;
    }

    public int getNow_status() {
        return now_status;
    }

    public void setNow_status(int now_status) {
        this.now_status = now_status;
    }

    public String getHandler_id() {
        return handler_id;
    }

    public void setHandler_id(String handler_id) {
        this.handler_id = handler_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg_url(){
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLatlong(){ return latlong; }

    public void setLatlong(String latlong){ this.latlong = latlong; }

    @Override
    public String toString() {
        return "Inform{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", gender=" + gender +
                ", informer_id='" + informer_id + '\'' +
                ", org_status=" + org_status +
                ", captured_time=" + captured_time +
                ", progress_time=" + progress_time +
                ", now_status=" + now_status +
                ", handler_id='" + handler_id + '\'' +
                ", address='" + address + '\'' +
                ", lan='" + latlong + '\'' +
                '}';
    }
}
