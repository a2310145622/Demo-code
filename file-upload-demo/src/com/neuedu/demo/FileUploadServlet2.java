/**
 * 
 */
package com.neuedu.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

/**
 * DESC: 
 * 2017年7月27日
 * @author BKF
 */
@WebServlet("/upload2")
@MultipartConfig
public class FileUploadServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String uname = req.getParameter("uname");
		System.out.println( "uname = " + uname );

		String realPath = this.getServletContext().getRealPath("/upload/");

		Part part1 = req.getPart("file1");
		Part part2 = req.getPart("file2");
		
		saveFile(part1, realPath);
		saveFile(part2, realPath);
	}

	private void saveFile(Part part, String realPath) {
		try {
			String fileName1 = getFileName(part);
			FileOutputStream fos = new FileOutputStream(realPath + fileName1);
			IOUtils.copy(part.getInputStream(), fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return fileName;
    }
}
