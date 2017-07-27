import java.io.Serializable;

public class Student implements Serializable{

private static final long serialVersionUID = -2321662391465592071L;
private Integer studentID;
private String studentName;
private String loginPsw;
public Integer getStudentID() {
	return studentID;
}
public void setStudentID(Integer studentID) {
	this.studentID = studentID;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getLoginPsw() {
	return loginPsw;
}
public void setLoginPsw(String loginPsw) {
	this.loginPsw = loginPsw;
}
@Override
public String toString() {
	return "info [studentID=" + studentID + ", studentName=" + studentName
			+ ", loginPsw=" + loginPsw + "]";
}

}
