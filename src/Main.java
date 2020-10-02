import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 经验：
 * * 对文件和文件夹命名不能使用以下9个字符：
 *   / \ : * " < >  ？
 *   否则会导致 File.mkdir() 失败
 */

public class Main {
	public static void main(String[] args) throws Exception {
		GetFile getFile = new GetFile(new File("C:\\Users\\86188\\Desktop\\learnSpringFromItheima\\copy"));
		for (File file:getFile.getFiles(getFile.selectNameContains(""))) {
			if (file.getName().substring(file.getName().length()-5).equals(".java")){
				System.out.println(file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"//",""));
			}
			if (file.getName().substring(file.getName().length()-4).equals(".xml")){
				System.out.println(file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"<!--","-->"));
			}
			if (file.getName().substring(file.getName().length()-4).equals(".jsp")){
				System.out.println(file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"<%--","--%>"));
			}
		}
	}

	public static final String comment(String filePath,String head,String end) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader bufferedreader = new BufferedReader(fr);
		String instring="";
		StringBuffer sb = new StringBuffer("");
		while (true) {
			instring = bufferedreader.readLine();
			if (instring==null){
				break;
			}
			if (0 != instring.length()) {
				sb.append(head+instring+end+"\n");
			}
		}
		fr.close();
		return sb.toString();
	}
}
