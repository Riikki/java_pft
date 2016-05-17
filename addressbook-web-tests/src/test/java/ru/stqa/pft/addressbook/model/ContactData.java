package ru.stqa.pft.addressbook.model;

public class ContactData {
	private int id;

	private String firstname;
	private String middlename;
	private String lastname;
	private String nickname;
	private String home;
	private String mobile;
	private String work;
	private String group;
	private String allPhones;
	private String address;
	private String email1;
	private String email2;
	private String email3;
	private String allMails;

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

	public String getGetEmail1() {
		return email1;
	}

	public String getGetEmail2() {
		return email2;
	}

	public String getGetEmail3() {
		return email3;
	}
	@Override
	public String toString() {
		return "ContactData{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
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
}
