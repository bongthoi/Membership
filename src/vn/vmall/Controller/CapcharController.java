package vn.vmall.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="CapcharController")
public class CapcharController {
	final String _font="Arial";
	@RequestMapping(value="createcapchar")
	public void createcapchar(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		int width = 150;
		int height =40;	

		char[]data = new char[5];
		char[]arr_date = {
							'0','1','2','3','4','5','6','7','8','9'//,
							/*'q','w','e','r','t','y','u','i','o','p',
							'a','s','d','f','g','h','j','k','l',
							'z','x','c','v','b','n','m'*/
						  };
		Random ran =new Random();
		for(int i=0;i<5;i++)
		{
			int j = ran.nextInt(arr_date.length);
			data[i]= arr_date[j];
		}

		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font(_font, Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		GradientPaint gp = new GradientPaint(0, 0, Color.darkGray, 0, height / 2,
				Color.darkGray, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(0,255, 0));
		
		Random r = new Random();	

		String captcha = String.copyValueOf(data);
		request.getSession().setAttribute("captcha", captcha);

		int x = 0;
		int y = 0;

		for (int i = 0; i < 5; i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(data, i, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}
	@RequestMapping(value="check_capchar")
	@ResponseBody
	public String check_capchar(@RequestParam("strcapchar") String strcapchar,HttpServletRequest request) throws IOException{
		
		String jsontext="";
		try{
			if(request.getSession().getAttribute("captcha")!=null){
				String ss_capchar = request.getSession().getAttribute("captcha").toString();
				
				if(ss_capchar == null || ss_capchar =="")
					jsontext = "-1";
				else if(!ss_capchar.equals(strcapchar))
					jsontext="-1";
				else if(ss_capchar.equals(strcapchar))
					jsontext="0";	
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
			
		return jsontext;
	}
	@RequestMapping(value="createcapchar_register")
	public void createcapchar_register(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		int width = 150;
		int height =40;	

		char[]data = new char[5];
		char[]arr_date = {
							'0','1','2','3','4','5','6','7','8','9'//,
						/*	'q','w','e','r','t','y','u','i','o','p',
							'a','s','d','f','g','h','j','k','l',
							'z','x','c','v','b','n','m'*/
						  };
		Random ran =new Random();
		for(int i=0;i<5;i++)
		{
			int j = ran.nextInt(arr_date.length);
			data[i]= arr_date[j];
		}

		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font(_font, Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		GradientPaint gp = new GradientPaint(0, 0, Color.darkGray, 0, height / 2,
				Color.darkGray, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(0,255, 0));
		
		Random r = new Random();	

		String captcha = String.copyValueOf(data);
		request.getSession().setAttribute("captcharegister", captcha);
	//	System.out.println("resgsiter:"+captcha);
		int x = 0;
		int y = 0;

		for (int i = 0; i < 5; i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(data, i, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}
	@RequestMapping(value="check_capchar_register")
	@ResponseBody
	public String check_capchar_register(@RequestParam("strcapchar") String strcapchar,HttpServletRequest request) throws IOException{
		
		String jsontext="";
		try{
			if(request.getSession().getAttribute("captcharegister")!=null){
				String ss_capchar = request.getSession().getAttribute("captcharegister").toString();
				//System.out.println("strcapchar_rgis:"+strcapchar);
				//System.out.println("captcharegister:"+ss_capchar);
				if(ss_capchar == null || ss_capchar =="")
					jsontext = "-1";
				else if(!ss_capchar.equals(strcapchar))
					jsontext="-1";
				else if(ss_capchar.equals(strcapchar))
					jsontext="0";	
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
			
		return jsontext;
	}
}
