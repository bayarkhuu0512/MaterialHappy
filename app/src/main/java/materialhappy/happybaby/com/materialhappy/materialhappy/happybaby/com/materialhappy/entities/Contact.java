package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by BayarkhuuWork on 2/25/2015.
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = -3002412614298570971L;

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String type;

    @DatabaseField
    String name;

    @DatabaseField
    String logo;

    @DatabaseField
    String picture;

    @DatabaseField
    String address;

    @DatabaseField
    String districtId;

    @DatabaseField
    String horoo;

    @DatabaseField
    String phone1;

    @DatabaseField
    String phone2;

    @DatabaseField
    String phone3;

    @DatabaseField
    String connect;

    @DatabaseField
    String workWeekStart;

    @DatabaseField
    String workWeekOver;

    @DatabaseField
    String workSatStart;

    @DatabaseField
    String workSatOver;

    @DatabaseField
    String workSunStart;

    @DatabaseField
    String workSunOver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getHoroo() {
        return horoo;
    }

    public void setHoroo(String horoo) {
        this.horoo = horoo;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getWorkWeekStart() {
        return workWeekStart;
    }

    public void setWorkWeekStart(String workWeekStart) {
        this.workWeekStart = workWeekStart;
    }

    public String getWorkWeekOver() {
        return workWeekOver;
    }

    public void setWorkWeekOver(String workWeekOver) {
        this.workWeekOver = workWeekOver;
    }

    public String getWorkSatStart() {
        return workSatStart;
    }

    public void setWorkSatStart(String workSatStart) {
        this.workSatStart = workSatStart;
    }

    public String getWorkSatOver() {
        return workSatOver;
    }

    public void setWorkSatOver(String workSatOver) {
        this.workSatOver = workSatOver;
    }

    public String getWorkSunStart() {
        return workSunStart;
    }

    public void setWorkSunStart(String workSunStart) {
        this.workSunStart = workSunStart;
    }

    public String getWorkSunOver() {
        return workSunOver;
    }

    public void setWorkSunOver(String workSunOver) {
        this.workSunOver = workSunOver;
    }
}
