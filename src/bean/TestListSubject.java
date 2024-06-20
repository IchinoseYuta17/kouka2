package bean;

import java.util.HashMap;
import java.util.Map;

//Serializableインターフェースを実装してBeanを作成する
public class TestListSubject implements java.io.Serializable {

	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private String Num;
	private Map<Integer, Integer> points;

    // コンストラクタ
    public TestListSubject() {
        this.points = new HashMap<>(); // 初期化（必要に応じて）
    }

	// ゲッターメソッド
	public int getEntYear() {
		return entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return points;
	}
	public Integer getPoint(int key){
		return points.get(key);
	}
	public String getNum() {
		return Num;
	}


	// セッターメソッド
	public void setEntYear(int entYear) {
		this.entYear=entYear;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo=studentNo;
	}
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public void setPoints(Map<Integer,Integer> points){
		this.points = points;

	}
	public void putPoint(int key, int value){
		this.points.put(key, value);
	}
	public void setNum(String Num) {
		this.Num = Num;
	}
}
