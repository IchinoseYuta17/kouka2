package bean;

//Serializableインターフェースを実装してBeanを作成する
public class Subject implements java.io.Serializable {

	private String cd;
	private String name;
	private School school;

	// ゲッターメソッド
	public String getCd() {
		return cd;
	}
	public String getName() {
		return name;
	}
	public School getSchool() {
		return school;
	}


	// セッターメソッド
	public void setCd(String cd) {
		this.cd=cd;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setSchool(School school) {
		this.school = school;
	}
}
