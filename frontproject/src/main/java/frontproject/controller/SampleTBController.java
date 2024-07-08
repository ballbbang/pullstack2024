package frontproject.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontproject.dao.SampleTBDAO;
import frontproject.vo.SampleTBVO;

public class SampleTBController {
	
	public void getAction(HttpServletRequest request, HttpServletResponse response, String []uris) throws ServletException, IOException {
		//frontcontroller 에서 sampleTB관련 모든 요청을 받아서 각 목적에 맞는 메소드를 분기하는 영역
		
		if(uris[1].equals("list.do")) {
			list(request,response);
			//throws
		}else if(uris[1].equals("view.do")) {
			view(request,response);
		}else if(uris[1].equals("modify.do")) {
			modify(request,response);
		}else if(uris[1].equals("enroll.do")) {
			enroll(request,response);
		}
	}
	
	public void postAction(HttpServletRequest request, HttpServletResponse response, String []uris) throws ServletException, IOException {
	//post요청에 대한 처리
		
		if(uris[1].equals("modify.do")) {
			modifyOk(request,response);
		}
		if(uris[1].equals("enroll.do")) {
			enrollOk(request,response);
		}
		
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		SampleTBDAO sampleTBDao=new SampleTBDAO();
		List<SampleTBVO> slist=sampleTBDao.selectList();
		request.setAttribute("slist", slist);
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/sampleTB/list.jsp");
		rd.forward(request, response);
		//throws
		
		
	}




private void view(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
	String snoParam=request.getParameter("sno");//4)
	int sno=0;
	if(snoParam !=null && !snoParam.equals("")) {
		sno=Integer.parseInt(snoParam);
	}else {
		response.sendRedirect("list.do");
	}
	
	SampleTBDAO sampleTBDao =new SampleTBDAO();//2)
	SampleTBVO svo= sampleTBDao.selectOne(sno);//3)sno는 reqest안에 있어서 꺼낸다
	
	request.setAttribute("svo", svo);//5)
	
	
	RequestDispatcher rd=request.getRequestDispatcher("/sampleTB/view.jsp");//1)
	rd.forward(request, response);
	//throws
	
	
}

private void modify(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
	String snoParam=request.getParameter("sno");//4)
	int sno=0;
	if(snoParam !=null && !snoParam.equals("")) {
		sno=Integer.parseInt(snoParam);
	}else {
		response.sendRedirect("list.do");
	}
	
	SampleTBDAO sampleTBDao =new SampleTBDAO();//2)
	SampleTBVO svo= sampleTBDao.selectOne(sno);//3)sno는 reqest안에 있어서 꺼낸다
	
	request.setAttribute("svo", svo);//5)
	
	
	RequestDispatcher rd=request.getRequestDispatcher("/sampleTB/modify.jsp");//1)
	rd.forward(request, response);
	//throws
}

private void modifyOk(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	String snoParam=request.getParameter("sno");//4)
	int sno=0;
	if(snoParam !=null && !snoParam.equals("")) {
		sno=Integer.parseInt(snoParam);
	}
	
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String body=request.getParameter("body");
	
	SampleTBVO svo=new SampleTBVO();
	svo.setSno(sno);
	svo.setTitle(title);
	svo.setWriter(writer);
	svo.setBody(body);
	
	SampleTBDAO sampleTBDao= new SampleTBDAO();
	int result=sampleTBDao.update(svo);
	
	if(result>0) {
		//수정성공
		response.sendRedirect("view.do?sno="+sno);
	}else {
		response.sendRedirect("modify.do?sno="+sno+"&msg=fail");
	}

}

private void enroll(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
	
	RequestDispatcher rd= request.getRequestDispatcher("/sampleTB/enroll.jsp");
	rd.forward(request, response);

}
private void enrollOk(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	


	
	SampleTBVO svo=new SampleTBVO(); //1)
	svo.setTitle(request.getParameter("title"));
	svo.setBody(request.getParameter("body"));
	svo.setWriter(request.getParameter("writer"));
	
	SampleTBDAO sampleTBDao =new SampleTBDAO();//2)//객체생성 메소드 호출하려면 객체가 먼저 필요함
	
	int result=sampleTBDao.enroll(svo);
			
		  
		if(result>0) {
			//등록 성공
			response.sendRedirect("list.do");
					 //sendredirect 할때엔 프로젝트 path부터싹 해야함 redirect 가상경로 포워드는 jsp
		}else {
			//등록 실패
			response.sendRedirect("enroll.do?msg=fail");
		}

	
	

}

}
