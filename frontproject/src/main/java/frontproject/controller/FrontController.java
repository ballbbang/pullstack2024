package frontproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*frontcontroller 모든 가상경로를 제일 먼저 받아 역할에 맞는 컨트롤러로 요청을 분기하는 처리를 한다.
 * Servlet implementation class FrontController
 */
//가상경로 맵핑 *모든 전체.do로 끝나는 모든 경로를 받는다 컨트롤러가 무한증식이 되지 않고 관심사를 하나씩 만들어 메소드 
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FrontController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURL=request.getRequestURI();//project path부터 필요하기에 I로 가져옴 L은 도메인까지 포함 http:// 전체url(도메인제외) projectpath 포함
		String contextPath=request.getContextPath();//프로젝트 path잘라내려고 얻어옴
		
		String command = requestURL.substring(contextPath.length()+1) ;//프로젝트 path를 제외한 url substring으로 제거 index때문에 +1하여 그 뒤에 슬러시 까지 잘라냄  뒷부분 url 가져오기
		String []uris=command.split("/");// /구분자로 잘라냄 
		if(uris[0].equals("sampleTB")) {//0번째 인덱스가 sampleTB
			
			SampleTBController sampleTBController =new SampleTBController();
			 
			sampleTBController.getAction(request,response,uris);//우리가 만든 메소드 
			
	
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURL=request.getRequestURI();//project path부터 필요하기에 I로 가져옴 L은 도메인까지 포함 http:// 전체url(도메인제외) projectpath 포함
		String contextPath=request.getContextPath();//프로젝트 path잘라내려고 얻어옴
		
		String command = requestURL.substring(contextPath.length()+1) ;//프로젝트 path를 제외한 url substring으로 제거 index때문에 +1하여 그 뒤에 슬러시 까지 잘라냄  뒷부분 url 가져오기
		String []uris=command.split("/");// /구분자로 잘라냄 
		if(uris[0].equals("sampleTB")) {//0번째 인덱스가 sampleTB
			
			SampleTBController sampleTBController =new SampleTBController();
			 
			sampleTBController.postAction(request,response,uris);//우리가 만든 메소드 
		
	}
	
	}
}
