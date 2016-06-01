package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
	@XStreamOmitField
	@Id
	@Column(name = "id")
	private int id;
	@Expose
	@Column(name = "firstname")
	private String firstname;
	@Expose
	@Column(name = "middlename")
	private String middlename;
	@Expose
	@Column(name = "lastname")
	private String lastname;
	@Expose
	@Column(name = "nickname")
	private String nickname;
	@Expose
	@Type(type = "text")
	@Column(name = "home")
	private String home;
	@Expose
	@Type(type = "text")
	@Column(name = "mobile")
	private String mobile;
	@Expose
	@Type(type = "text")
	@Column(name = "work")
	private String work;
	@Expose
	@Transient
	private String group;
	@Expose
	@Transient
	private String allPhones;
	@Expose
	@Column(name = "address")
	@Type(type = "text")
	private String address;
	@Expose
	@Type(type = "text")
	@Column(name = "email")
	private String email1;
	@Expose
	@Type(type = "text")
	@Column(name = "email2")
	private String email2;
	@Expose
	@Type(type = "text")
	@Column(name = "email3")
	private String email3;
	@Expose
	@Transient
	private String allMails;
	@Column(name = "photo")
	@Type(type = "text")
	private String photo;

	public File getPhoto() {
		return new File(photo);
	}

	@Override
	public String toString() {
		return "ContactData{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}

	public ContactData withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
	}

	public int getId() {
		return id;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withMiddleName(String middlename) {
		this.middlename = middlename;
		return this;
	}

	public ContactData withLastName(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactData withNickName(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public ContactData withHomePhone(String home) {
		this.home = home;
		return this;
	}

	public ContactData withMobilePhone(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withWorkPhone(String work) {
		this.work = work;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public ContactData withFirstName(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactData withEmail1(String email1) {
		this.email1 = email1;
		return this;
	}

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}

	public ContactData withAllMails(String allMails) {
		this.allMails = allMails;
		return this;
	}

	public ContactData withDataParams(ContactData data){
		this.id = data.getId();
		this.firstname = data.getFirstName();
		this.middlename = data.getMiddleName();
		this.lastname = data.getLastName();
		this.nickname = data.getNickName();
		this.group = data.getGroup();
		this.address = data.getAddress();
		this.mobile = data.getMobilePhone();
		this.home = data.getHomePhone();
		this.work = data.getWorkPhone();
		this.email1 = data.getEmail1();
		this.email2 = data.getEmail2();
		this.email3 = data.getEmail3();
		return this;
	}

	public String getFirstName() {
		return firstname;
	}

	public String getMiddleName() {
		return middlename;
	}

	public String getLastName() {
		return lastname;
	}

	public String getNickName() {
		return nickname;
	}

	public String getHomePhone() {
		return home;
	}

	public String getAddress() {
		return address;
	}

	public String getMobilePhone() {
		return mobile;
	}

	public String getWorkPhone() {
		return work;
	}

	public String getGroup() {
		return group;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getAllMails() {
		return allMails;
	}

	public String getEmail1() {
		return email1;
	}

	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (id != that.id) return false;
		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}

	public boolean hasSameData(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

	}
}