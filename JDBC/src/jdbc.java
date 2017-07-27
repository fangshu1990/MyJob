import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class jdbc {
	public static void main(String[] args) {
/*		List<Student> students = getStudents();
        for (Student s : students) {
			System.out.println(s);
		}*/
		
/*Student s=new Student();
s.setStudentID(30013);
s.setStudentName("张小三");
s.setLoginPsw("321asd");
		if (addStudent(s)) {
			System.out.println("插入成功");
		} else {
			System.out.println("插入失败");

		}*/
		String name="张秋丽";
		String password="123";
		if (login(name, password)) {
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
	}

	// 1.查询方法
	public static List<Student> getStudents() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jt20", "root", "");
			st = con.createStatement();
			rs = st.executeQuery("select * from student");
			while (rs.next()) {
				Student student = new Student();
				student.setStudentID(rs.getInt("studentNo"));
				student.setStudentName(rs.getString("studentName"));
				student.setLoginPsw(rs.getString("loginPwd"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return students;
	}

	//2.插入方法
	public static boolean addStudent(Student s){
		Connection con = null;
		Statement st = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/jt20?useUnicode=true&characterEncoding=utf-8", "root", "");
				st = con.createStatement();
				//准备sql语句
			String sql = "insert into student(studentNo,studentName,loginPwd) values("
					+ s.getStudentID()
					+ ",'"
					+ s.getStudentName()
					+ "','"
					+ s.getLoginPsw() + "')";
			//执行sql语句
			int num=st.executeUpdate(sql);//更新条目
			if(num>0){
				return true;
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//释放资源
				if(st!=null){
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return false;
	}

    //3.模拟登陆
	public static boolean login(String name, String password) {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/jt20?useUnicode=true&characterEncoding=utf-8",
							"root", "");
			st = con.createStatement();
			// 准备sql语句
			String sql = "select * from student where studentName='" + name
					+ "'and loginPwd='" + password + "'";
			// 执行sql语句
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
