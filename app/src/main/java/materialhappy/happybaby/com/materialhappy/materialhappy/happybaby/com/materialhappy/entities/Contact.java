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
    String imagePath;

    @DatabaseField
    String address;

    @DatabaseField
    String district;

    @DatabaseField
    String phone1;

    @DatabaseField
    String phone2;

    @DatabaseField
    String phone3;

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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImageName() {
        return imagePath;
    }

    public String getDistrict() {
        return district;
    }

    public void setImageName(String imageName) {
        this.imagePath = imageName;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
