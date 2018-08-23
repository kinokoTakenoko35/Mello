package model;

public class FileLogic {
	public String getSuffix(String fileNameWithExt) {
	    if (fileNameWithExt == null)
	        return null;
	    int point = fileNameWithExt.lastIndexOf(".");
	    if (point != -1) {
	        return fileNameWithExt.substring(point + 1);
	    }
	    return fileNameWithExt;
	}
}
