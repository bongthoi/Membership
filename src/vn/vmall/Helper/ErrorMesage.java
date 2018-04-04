package vn.vmall.Helper;


import vn.vmall.DAL.Errordal;

public class ErrorMesage {

	public static String getMesageError(int error,String lang)
	{
		if(error==-1){
			return "EXCUTED Fail [-1]";
		}
		if(error==0){
			return "EXCUTED Success [0]";
		}
		return Errordal.getMesageError(error,lang);
	}
	public static ErrorMessageModel get_json_mes_error (int error,String lang){
		ErrorMessageModel e=new ErrorMessageModel();
		e.setF(error);
		if(error==-1){
			e.setMessage("EXCUTED Fail [-1]");
			return e;
		}
		if(error==0){
			e.setMessage("EXCUTED Success [0]");
			return e;
		}
		e.setMessage(Errordal.getMesageError(error,lang));
		return e;
		
	}
}
