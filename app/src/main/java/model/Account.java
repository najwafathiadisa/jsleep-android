package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable{
    public String name;
    public String password;
    public Renter renter;
    public String email;
    public double balance;
}
