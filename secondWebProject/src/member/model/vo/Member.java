package member.model.vo;

import java.sql.Date;

//vo(Value Object) == do(Domain Object) == entity
//== dto(Data Transfer Object) == bean
//값 저장용 객체 : 테이블의 한 행의 정보저장용 객체임

//vo 클래스가 되기 위한 조건
//1. 직렬화 처리할 것
//2. 모든 필드는 반드시 private
//3. 기본 생성자와 매개변수 있는 생성자 작성
//4. 모든 필드에 대한 getter와 setter 작성

public class Member implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String hobby;
	private String etc;
	private Date enrollDate;
	private Date lastModifiedDate;
	
	public Member(){}

	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			String hobby, String etc) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.etc = etc;
	}

	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			String hobby, String etc, Date enrollDate, Date lastModifiedDate) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.etc = etc;
		this.enrollDate = enrollDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public String toString(){
		return this.userId + ", " + this.userPwd
			+ ", " + this.userName
			+ ", " + this.gender
			+ ", " + this.age
			+ ", " + this.email
			+ ", " + this.phone
			+ ", " + this.hobby
			+ ", " + this.etc
			+ ", " + this.enrollDate
			+ ", " + this.lastModifiedDate;
	}
}










