package main.notes.exceptions;

import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;

import javax.management.AttributeNotFoundException;

public class DifferentiatingExceptionType {
	private static void putBasicExceptionMessage(Map apiMp, String functionName, Exception ex) {
		String RTN_CODE = "0";
		String ERR_CODE = "";
		String RTN_MSG = "";

		try {
			if (ex != null) {
				throw ex;
			}
		} catch (MalformedInputException eie) {
			RTN_CODE = "-1";
			ERR_CODE = "-102";
			RTN_MSG = functionName + "：無傳入參數";
		} catch (AttributeNotFoundException dnfe) {
			RTN_CODE = "1";
			ERR_CODE = "-101";
			RTN_MSG = functionName + "：無符合資料";
		} catch (Exception e) {
			RTN_CODE = "-1";
			ERR_CODE = "-999";
			RTN_MSG = functionName + "：查詢異常";
		}

		apiMp.put("RTN_CODE", RTN_CODE);
		apiMp.put("RTN_MSG", RTN_MSG);
		apiMp.put("ERR_CODE", ERR_CODE);
	}

	public static void main(String[] args) {
		Map RTN_MAP = new HashMap();
		putBasicExceptionMessage(RTN_MAP, "functionName", null);
		System.out.println(RTN_MAP);
		putBasicExceptionMessage(RTN_MAP, "functionName", new MalformedInputException(0));
		System.out.println(RTN_MAP);
		putBasicExceptionMessage(RTN_MAP, "functionName", new AttributeNotFoundException());
		System.out.println(RTN_MAP);
		putBasicExceptionMessage(RTN_MAP, "functionName", new Exception());
		System.out.println(RTN_MAP);
	}

}
