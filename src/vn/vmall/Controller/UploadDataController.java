package vn.vmall.Controller;

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
@RequestMapping(value = "UploadDataController")
public class UploadDataController {

	@RequestMapping(value = "upload_picture_company", method = RequestMethod.POST)
	@ResponseBody
	public String upload_image_category(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsontext = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl = request.getServletContext();
		String folder_save = servl.getInitParameter("save_image_company");
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
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy_mm_dd_hh_mm_ss");
						Calendar cal = Calendar.getInstance();
						String name = dateFormat.format(cal.getTime()) + "_"
								+ new File(item.getName()).getName();
						filename += name;

						String file_path = savePath + File.separator + name;
						item.write(new File(file_path));
						UploadFPTP.uploadFTP(file_path, name,
								"company");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		jsontext = filename;
		return jsontext;
	}

}
