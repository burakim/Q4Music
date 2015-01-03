package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;


public class UserModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int ObjectId;
    private String UserName;
    private String Password;
    private String EMailAddress;
    private String FullName;
    private Date BirthDate;
    private Boolean Gender;
    private String Country;
    private String City;
    private String UserType;
    private Boolean IsApproved;
    private Boolean IsActive;

    public int GetObjectId() {
        return ObjectId;
    }

    public void SetObjectId(int ObjectId) {
        this.ObjectId = ObjectId;
    }

    public String GetUserName() {
        return UserName;
    }

    public void SetUserName(String UserName) {
        this.UserName = UserName;
    }

    public String GetPassword() {
        return Password;
    }

    public void SetPassword(String Password) {
        try {

            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(Password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            this.Password = new String(sb);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

    public String GetEMailAddress() {
        return EMailAddress;
    }

    public void SetEMailAddress(String EMailAddress) {
        this.EMailAddress = EMailAddress;
    }

    public String GetFullName() {
        return FullName;
    }

    public void SetFullName(String FullName) {
        this.FullName = FullName;
    }

    public Date GetBirthdate() {
        return BirthDate;
    }

    public void SetBirthdate(Date Birthdate) {
        this.BirthDate = Birthdate;
    }

    public Boolean GetGender() {
        return Gender;
    }

    public void SetGender(Boolean Gender) {
        this.Gender = Gender;
    }

    public String GetCountry() {
        return Country;
    }

    public void SetCountry(String Country) {
        this.Country = Country;
    }

    public String GetCity() {
        return City;
    }

    public void SetCity(String City) {
        this.City = City;
    }

    public String GetUserType() {
        return UserType;
    }

    public void SetUserType(String UserType) {
        this.UserType = UserType;
    }

    public Boolean GetIsAproved() {
        return IsApproved;
    }

    public void SetIsApproved(Boolean IsApproved) {
        this.IsApproved = IsApproved;
    }

    public Boolean GetIsActive() {
        return IsActive;
    }

    public void SetIsActive(Boolean IsActive) {
        this.IsActive = IsActive;
    }

    public UserModel(String UserName, String Password, String EMail,
            String FullName) {
        SetUserName(UserName);
        SetPassword(Password);
        SetEMailAddress(EMail);
        SetFullName(FullName);
    }

    public UserModel(String UserName, String Password) {
        SetUserName(UserName);
        SetPassword(Password);
    }

    public UserModel(int ObjectId, String UserName, String FullName,
            String EMail, String UserType) {
        SetObjectId(ObjectId);
        SetUserName(UserName);
        SetFullName(FullName);
        SetEMailAddress(EMail);
        SetUserType(UserType);
    }

    public UserModel(String UserName, String FullName, String EMail,
            String UserType, String Password, Boolean Gender, Boolean isactive,
            Boolean isapproved) {
        SetUserName(UserName);
        SetFullName(FullName);
        SetEMailAddress(EMail);
        SetUserType(UserType);
        SetPassword(Password);

        SetGender(Gender);
        SetIsActive(isactive);
        SetIsApproved(isapproved);
    }

    public UserModel() {

    }
}
