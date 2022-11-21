package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable{
    public String username;
    public String address;
    public String phoneNumber;
    public Renter(int id) {
        super(id);
    }
}
