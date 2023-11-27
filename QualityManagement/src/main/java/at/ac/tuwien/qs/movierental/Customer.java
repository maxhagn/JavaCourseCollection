package at.ac.tuwien.qs.movierental;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Customer {

    private ObjectProperty<Long> id = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> firstName = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> lastName = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> email = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> phone = new SimpleObjectProperty<>(null);
    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> address = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> zipCode = new SimpleObjectProperty<>(null);
    private ObjectProperty<String> city = new SimpleObjectProperty<>(null);
    private ObjectProperty<Boolean> patron = new SimpleObjectProperty<>(false);
    private ObjectProperty<Integer> videopoints = new SimpleObjectProperty<>(0);
    private ObjectProperty<File> photo = new SimpleObjectProperty<>(null);

    public Long getId() {
        return id.get();
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public ObjectProperty<String> firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public ObjectProperty<String> lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public ObjectProperty<String> emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public ObjectProperty<String> phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getAddress() {
        return address.get();
    }

    public ObjectProperty<String> addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public ObjectProperty<String> zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getCity() {
        return city.get();
    }

    public ObjectProperty<String> cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public Boolean getPatron() {
        return patron.get();
    }

    public ObjectProperty<Boolean> patronProperty() {
        return patron;
    }

    public void setPatron(Boolean patron) {
        this.patron.set(patron);
    }

    public Integer getVideopoints() {
        return videopoints.get();
    }

    public ObjectProperty<Integer> videopointsProperty() {
        return videopoints;
    }

    public void setVideopoints(Integer videopoints) {
        this.videopoints.set(videopoints);
    }

    public File getPhoto() {
        return photo.get();
    }

    public ObjectProperty<File> photoProperty() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo.set(photo);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", videopoints=" + videopoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return !(id != null ? !id.equals(customer.id) : customer.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static int calculateVideopointsGain(long priceInCent, boolean patron) {
        int videopoints = 0;
        if (priceInCent < 0) {
            throw new IllegalArgumentException("The price in Cent must not be negativ.");
        } else if (priceInCent >= 200) {
            long euro = priceInCent / 100;
            videopoints += euro / 2;
        }
        if (patron) {
            videopoints++;
        }
        return videopoints;
    }

    public float calculateDiscount() {
        float discount = 0;
        if (Boolean.TRUE.equals(this.getPatron())) {
            discount += 1.5;
        }
        if (this.getVideopoints() < 0) {
           throw new IllegalArgumentException("The Videopoints must not be negative.");
        } else if (this.getVideopoints() >= 20) {
            discount += 6;
        } else if (this.getVideopoints() >= 10) {
            discount += 2;
        }
        return discount;
    }
}

