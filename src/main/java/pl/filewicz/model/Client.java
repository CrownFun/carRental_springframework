package pl.filewicz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private long phoneNumber;
    private String addres;
    @ManyToMany(mappedBy = "clients",fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    public Client(String name, String lastName, long phoneNumber, String addres) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addres = addres;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCars(Car car){
        cars.add(car);
    }
}
