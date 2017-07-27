/**
 * 
 */
package com.neuedu.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * DESC: 文件上传示例 
 * 2017年7月27日
 * @author BKF
 */
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		FileOutputStream fos = null;
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> its = items.iterator();
			// 迭代表单项
			while( its.hasNext() ) {
				
				FileItem it = its.next();
				// 处理文本域内容
				if(it.isFormField()) {
					String name = it.getFieldName();
					String value = it.getString();
					System.out.println( name + ":" + new String(value.getBytes("ISO-8859-1"), "UTF-8") );
				} else {	// 处理文件域：将上传的文件保存到服务器的磁盘上
					String fileName = it.getName();

					InputStream is = it.getInputStream();
					fos = new FileOutputStream(this.getServletContext().getRealPath("/upload/" + fileName));
					IOUtils.copy(is, fos);
					fos.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
}
