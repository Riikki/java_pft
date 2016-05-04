package ru.stqa.pft.addressbook.model;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class ContactData {
	private int id;
	private final String firstname;
	private final String middlename;
	private final String lastname;
	private final String nickname;
	private final String mobile;
	private final String group;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContactData(String firstname, String middlename, String lastname, String nickname, String mobile, String group) {
		this.id = Integer.MAX_VALUE;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nickname = nickname;
		this.mobile = mobile;
		this.group = group;
	}

	public ContactData(int id, String firstname, String middlename, String lastname, String nickname, String mobile, String group) {
		this.id = id;
		this.id = Integer.MAX_VALUE;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nickname = nickname;
		this.mobile = mobile;
		this.group = group;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getGroup() {
		return group;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = firstname != null ? firstname.hashCode() : 0;
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"firstname='" + firstname + '\'' +
						", lastname='" + lastname + '\'' +
						'}';
	}
}
