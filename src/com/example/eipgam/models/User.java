package com.example.eipgam.models;

import com.example.eipgam.helpers.data.EntityBase;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Users")
public class User extends EntityBase  {

     @DatabaseField
     private String userName;
     @DatabaseField
     private String email;
     @DatabaseField
     private double salary;
     @DatabaseField
     private int salaryDay;
     @DatabaseField
     private String psw;
     @DatabaseField
     private String pswRepite;
     
     
     @DatabaseField
     private String country;
     @DatabaseField
     private String state;
     @DatabaseField
     private String cityName;
     @DatabaseField
     private String cityGps;
     @DatabaseField
     private String address;
     
     @DatabaseField
     private boolean isSync;
     
     public String getEmail() {
     	return email;
     }
     public void setEmail(String email) {
     	this.email = email;
     }
     public String getUserName() {
     	return userName;
     }
     public void setUserName(String userName) {
     	this.userName = userName;
     }
     public boolean isSync() {
     	return isSync;
     }
     public void setSync(boolean isSync) {
     	this.isSync = isSync;
     }
     public String getAddress() {
     	return address;
     }
     public void setAddress(String address) {
     	this.address = address;
     }
     
     public String getCityGps() {
     	return cityGps;
     }
     
     public void setCityGps(String cityGps) {
     	this.cityGps = cityGps;
     }
     public String getCityName() {
     	return cityName;
     }
     public void setCityName(String cityName) {
     	this.cityName = cityName;
     }
     public String getState() {
     	return state;
     }
     public void setState(String state) {
     	this.state = state;
     }
     public String getCountry() {
     	return country;
     }
     public void setCountry(String country) {
     	this.country = country;
     }
     public String getPsw() {
     	return psw;
     }
     public void setPsw(String psw) {
     	this.psw = psw;
     }
     public int getSalaryDay() {
     	return salaryDay;
     }
     public void setSalaryDay(int salaryDay) {
     	this.salaryDay = salaryDay;
     }
     public double getSalary() {
     	return salary;
     }
     public void setSalary(double salary) {
     	this.salary = salary;
     }
     public String getPswRepite() {
     	return pswRepite;
     }
     public void setPswRepite(String pswRepite) {
     	this.pswRepite = pswRepite;
     }
     

}
