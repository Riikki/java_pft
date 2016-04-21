package ru.stqa.pft.addressbook.model;

public class ContactData {
	private final String firstname;
	private final String middlename;
	private final String lastname;
	private final String nickname;
	private final String mobile;
	private final String mail;

	public ContactData(String firstname, String middlename, String lastname, String nickname, String mobile, String mail) {
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nickname = nickname;
		this.mobile = mobile;
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}
}