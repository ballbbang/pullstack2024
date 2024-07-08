package frontproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import frontproject.vo.SampleTBVO;

public class SampleTBDAO {
	private final String url ="jdbc:mysql://localhost:3306/javaspringclass";
	private final String user="javaspringclass";
	private final String password="ezen";
	
	
	public List<SampleTBVO> selectList(){
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			//연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user,password);
			//연결정보에 쿼리실행
			String query="select * from sampletb";
			
			psmt=conn.prepareStatement(query);
			//결과 담기
			rs=psmt.executeQuery();
			
			List<SampleTBVO>slist=new ArrayList<SampleTBVO>();
			
			while(rs.next()) {//while 안에서 next찍지 않도록 주의 두번 이동됨 
				SampleTBVO svo=new SampleTBVO();//SampleTBVO객체 생성 vo객체 하나가 row한줄 
				svo.setSno(rs.getInt("sno"));//rs.getInt"sno"는 ResultSet의 현재 행에서 "sno"라는 이름의 컬럼(column) 값을 정수형(int)으로 가져옵니다.
				//svo는 SampleTBVO 클래스의 객체입니다. 이 객체는 데이터베이스의 한 행(row)의 데이터를 담기 위한 객체입니다.
				svo.setTitle(rs.getString("title"));
				svo.setWriter(rs.getString("writer"));
				svo.setRdate(rs.getString("rdate"));
				svo.setBody(rs.getString("body"));
			
				slist.add(svo);//화면으로 보낼 list에 add추가함 
			}
			return slist;
			//dao는 오로지 데이터를 가공하는 역할 포워드안함
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {//컴파일러 예외처리 때문에 추가로 try-catch작성
				if(rs !=null)rs.close();
				if(psmt !=null)psmt.close();
				if(conn !=null)conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return null;
		
	}

	public SampleTBVO selectOne(int sno) {
				
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			//연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user,password);
			//연결정보에 쿼리실행
			String query="select * from sampletb where sno=?";
			
			psmt=conn.prepareStatement(query);
			psmt.setInt(1, sno);
			//결과 담기
			rs=psmt.executeQuery();//지금 한개라 굳이 while문과 list담을 필요가 없음 
			
			SampleTBVO svo=null;
			if(rs.next()) {
				svo=new SampleTBVO();
				svo.setSno(rs.getInt("sno"));
				svo.setTitle(rs.getString("title"));
				svo.setWriter(rs.getString("writer"));
				svo.setRdate(rs.getString("rdate"));
				svo.setBody(rs.getString("body"));
			}
			
			return svo;
			
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {//컴파일러 예외처리 때문에 추가로 try-catch작성
				if(rs !=null)rs.close();
				if(psmt !=null)psmt.close();
				if(conn !=null)conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return null;
		
	}
	public int update(SampleTBVO svo){
		
		Connection conn=null;
		PreparedStatement psmt=null;
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user,password);
			//연결정보에 쿼리실행
			String query="update sampletb set title =?, writer=?, body=? where sno=?";
			
			psmt=conn.prepareStatement(query);
			psmt.setString(1, svo.getTitle());
			psmt.setString(2, svo.getWriter());
			psmt.setString(3, svo.getBody());
			psmt.setInt(4,svo.getSno());
			
			int result=psmt.executeUpdate();
			
			return result;
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {//컴파일러 예외처리 때문에 추가로 try-catch작성
				
				if(psmt !=null)psmt.close();
				if(conn !=null)conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}

	}
		
		return 0;
		
	}
	public int enroll(SampleTBVO svo) {
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user,password);
			//연결정보에 쿼리실행
			String query="insert into sampletb(title, writer, body,rdate)"
					+ "values(?,?,?,now())";
					
			
			psmt=conn.prepareStatement(query);
			psmt.setString(1, svo.getTitle());
			psmt.setString(2, svo.getWriter());
			psmt.setString(3, svo.getBody());
			
			
			int result=psmt.executeUpdate();
			return result;	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {//컴파일러 예외처리 때문에 추가로 try-catch작성
				
				if(psmt !=null)psmt.close();
				if(conn !=null)conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}

	}
		
		return 0;	
			
	}
	
	
}

