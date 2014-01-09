package hello;

import java.io.Serializable;
import java.util.Comparator;
//import org.joda.time.DateTime;

/**
 * NOTES: Ideally, Domain Objects should (properly) implement the <code>Serializable</code> interface. Action Item: Make
 * sure that implementing the <code>Serializable</code> interface is Ok with Resin/JAMP.
 */
public class Employee implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        id = value;
    }

    // Hack converted to string.  How do we custom serialization w/ Resin?
    // Solution with Jackson:
    // @JsonSerialize(using = CustomDateSerializer.class)
    private String createdDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String value) {
        createdDate = value;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        lastName = value;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String value) {
        userId = value;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        address = value;
    }

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        city = value;
    }

    private String zipcode;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String value) {
        zipcode = value;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String value) {
        state = value;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        email = value;
    }

    ///////////////////////////////////////////

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        phone = value;
    }

    //////////////////////////////////////////


    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String value) {
        birthday = value;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        password = value;
    }

    public Employee() {

    }

    /**
     * Constructor arguments are positional/ordinal: 1. firstName 2. lastName 3. userId 4. address 5. city 6. zipcode 7.
     * state 8. email 9. phone 10. birthday 11. password
     *
     * @param paramFirstName , of type<code>String</code>
     * @param paramLastName, of type<code>String</code>
     * @param paramUserId, of type<code>String</code>
     * @param paramAddress, of type<code>String</code>
     * @param paramCity, of type<code>String</code>
     * @param paramZipcode, of type<code>String</code>
     * @param paramState, of type<code>String</code>
     * @param paramEmail, of type<code>String</code>
     * @param paramPhone, of type<code>String</code>
     * @param paramBirthday, of type<code>String</code>
     * @param paramPassword, of type<code>String</code>
     */
    public Employee(
            /// wrong? long paramID,  // because: this attribute is lazily initialized
            /// wrong? long paramID,  // because: this attribute is lazily initialized
            String paramFirstName,
            String paramLastName,
            String paramUserId,
            String paramAddress,
            String paramCity,
            String paramZipcode,
            String paramState,
            String paramEmail,
            String paramPhone,
            String paramBirthday,
            String paramPassword) {

        // wrong? id= paramID; // because: this attribute is lazily initialized
        firstName = paramFirstName;
        lastName = paramLastName;
        userId = paramUserId;
        address = paramAddress;
        city = paramCity;
        zipcode = paramZipcode;
        state = paramState;
        email = paramEmail;
        phone = paramPhone;
        birthday = paramBirthday;
        password = paramPassword;
    }

    /**
     * Constructor arguments are positional/ordinal: 1. firstName 2. lastName 3. userId 4. address 5. city 6. zipcode 7.
     * state 8. email 9. phone 10. birthday 11. password 12. nation
     *
     * @param paramFirstName , of type<code>String</code>
     * @param paramLastName, of type<code>String</code>
     * @param paramUserId, of type<code>String</code>
     * @param paramAddress, of type<code>String</code>
     * @param paramCity, of type<code>String</code>
     * @param paramZipcode, of type<code>String</code>
     * @param paramState, of type<code>String</code>
     * @param paramEmail, of type<code>String</code>
     * @param paramPhone, of type<code>String</code>
     * @param paramBirthday, of type<code>String</code>
     * @param paramPassword, of type<code>String</code>
     * @param paramNation, of type<code>String</code>
     */
    public Employee(
            /// wrong? long paramID,  // because: this attribute is lazily initialized
            String paramFirstName,
            String paramLastName,
            String paramUserId,
            String paramAddress,
            String paramCity,
            String paramZipcode,
            String paramState,
            String paramEmail,
            String paramPhone,
            String paramBirthday,
            String paramPassword,
            String paramNation) {

        // wrong? id= paramID; // because: this attribute is lazily initialized
        firstName = paramFirstName;
        lastName = paramLastName;
        userId = paramUserId;
        address = paramAddress;
        city = paramCity;
        zipcode = paramZipcode;
        state = paramState;
        email = paramEmail;
        phone = paramPhone;
        birthday = paramBirthday;
        password = paramPassword;
    }

    @Override
    public int hashCode() {
        return 31 * 1 + (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Employee [");

        sb.append("firstName=");
        sb.append(firstName);
        sb.append(", lastName=");
        sb.append(lastName);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", address=");
        sb.append(address);
        sb.append(", city=");
        sb.append(city);
        sb.append(", zipcode=");
        sb.append(zipcode);
        sb.append(", state=");
        sb.append(state);
        sb.append(", email=");
        sb.append(email);
        sb.append(", phone=");
        sb.append(phone);
        sb.append(", birthday=");
        sb.append(birthday);
        sb.append(", password=");
        sb.append(password);
        sb.append(", id=");
        sb.append(id);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append("]");

        return sb.toString();
    }

    /**
     * Be careful about/when including the 'id' attribute in this algorithm.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Employee other = (Employee) obj;

        if (id != other.id) {
            return false;
        }

        if (firstName == null) {

            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }

        if (lastName == null) {

            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }

        if (userId == null) {

            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }

        if (address == null) {

            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }

        if (birthday == null) {

            if (other.birthday != null) {
                return false;
            }
        } else if (!birthday.equals(other.birthday)) {
            return false;
        }

        if (city == null) {

            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }

        if (email == null) {

            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
///////////////////////
        if (phone == null) {

            if (other.phone != null) {
                return false;
            }
        } else if (!phone.equals(other.phone)) {
            return false;
        }
///////////////////
        if (password == null) {

            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }

        if (state == null) {

            if (other.state != null) {
                return false;
            }
        } else if (!state.equals(other.state)) {
            return false;
        }

        if (zipcode == null) {

            if (other.zipcode != null) {
                return false;
            }
        } else if (!zipcode.equals(other.zipcode)) {
            return false;
        }

        return true;
    }
}
