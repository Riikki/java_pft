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
	private String address;

	public int getId() {
		return id;
	}

	public ContactData withId(int id) {
		this.id = id;
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
