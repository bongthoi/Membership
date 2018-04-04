package vn.vmall.Service;

import java.rmi.RemoteException;


import javax.servlet.http.HttpServletRequest;


import org.tempuri.WebServiceSoapProxy;

public class ReadServiceMail {
	public static int  SendingFromgmail(String tomail,String title,String message,HttpServletRequest request){		
		WebServiceSoapProxy call = new WebServiceSoapProxy();
		try {
			Boolean data = call.sendMailToCustomer(tomail,message, title);
			if(data==true){
				return 0;
			}
			else{
				return 1;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	public static void main(String[] args) throws RemoteException {
		System.out.print("1");
		//WebServiceSoapProxy call = new WebServiceSoapProxy();
		System.out.print("2");
		//call.sendMailToCustomer("bongthoi1111@gmail.com","123","123");
		System.out.print("3");
	}
}
