package board_proj.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public class BoardFileDownAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			download1(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			download2(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void download1(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		String downFile = request.getParameter("downFile");
		ServletContext context = request.getServletContext();
		String sFilePath = context.getRealPath("/boardUpload/")+downFile;
		
		
		FileInputStream in = new FileInputStream(sFilePath);
		
		encoding(request, response, downFile, context, sFilePath);
		
		
		ServletOutputStream out = response.getOutputStream();
		
		int data = 0;
		byte b[] = new byte[4096];
	
		while((data=in.read(b))!= -1) {
//			System.out.println("data : "+data);
			out.write(b,0,data);
		}
		out.flush();
		out.close();
		in.close();
	}

	private void encoding(HttpServletRequest request, HttpServletResponse response, String downFile,
			ServletContext context, String sFilePath) throws UnsupportedEncodingException {
			String sMimeType = context.getMimeType(sFilePath);
			if (sMimeType == null) {
				sMimeType = "application/octet-stream";
			}
	
			response.setContentType(sMimeType);
			String agent = request.getHeader("User-Agent");
			boolean isBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
	
			if (isBrowser) {
				downFile = URLEncoder.encode(downFile, "UTF-8").replaceAll("\\+", "%20");
			} else {
				downFile = new String(downFile.getBytes("UTF-8"), "iso-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment; filename= " + downFile);
	}
	
	private void download2(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		String fileName = request.getParameter("downFile");

		String savePath = "boardUpload";
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		byte b[] = new byte[4096];
		FileInputStream in = new FileInputStream(sFilePath);
		
		encoding(request, response, fileName, context, sFilePath);

		ServletOutputStream out2 = response.getOutputStream();
		int numRead;

		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out2.write(b, 0, numRead);
		}
		out2.flush();
		out2.close();
		in.close();
	}
	
}
