package vn.vmall.AdminController;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.vmall.Helper.UploadFPTP;

@Controller
@RequestMapping(value = "ad/UploadController")
public class UploadController {

	@RequestMapping(value = "upload_image_category", method = RequestMethod.POST)
	@ResponseBody
	public String upload_image_category(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.print("AAA");
		String jsontext = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl = request.getServletContext();
		// String savePath
		// =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("save_image_cate");// "category"
		String savePath = servl.getRealPath(folder_save);

		// String savePath =
		// servl.getContextPath()+File.separator+"Upload"+File.separator+"Feature_Image";
		// String savePath2 =
		// request.getServletContext().getInitParameter("savepath2");
		// //System.out.println(savePath);
		String filename = "";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload
						.parseRequest(request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {

						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy_mm_dd_hh_mm_ss");
						Calendar cal = Calendar.getInstance();
						String name = dateFormat.format(cal.getTime()) + "_"
								+ new File(item.getName()).getName();
						filename += name;

						String file_path = savePath + File.separator + name;
						item.write(new File(file_path));
						UploadFPTP.uploadFTP(file_path, name, "category");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// //System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}

	@RequestMapping(value = "upload_image_normal_withparam", method = RequestMethod.POST)
	@ResponseBody
	public String upload_image_normal_withparam(
			@RequestParam(value = "folder", required = true) String foldersett,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(foldersett);
		String jsontext = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl = request.getServletContext();

		String folder_save = servl.getInitParameter(foldersett);

		System.out.println(folder_save);
		String savePath = servl.getRealPath(folder_save);

		System.out.println(savePath);
		String filename = "";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload
						.parseRequest(request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {
						
						//
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy_mm_dd_hh_mm_ss");
						Calendar cal = Calendar.getInstance();
						String name = dateFormat.format(cal.getTime()) + "_"
								+ new File(item.getName()).getName();
						filename += name;

						String file_path = savePath + File.separator + name;
						item.write(new File(file_path));
						String[]arr_fld = folder_save.split("/");
						if(arr_fld.length>0){
							UploadFPTP.uploadFTP(file_path, name, arr_fld[arr_fld.length-1]);	
						}						

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// //System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}

}
