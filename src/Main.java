import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws Exception {
		GetFile getFile = new GetFile(new File("D:\\core\\Java\\framework\\learnSpringFromItheima\\copy\\03_itheima_spring_aop\\itheima_spring_aop\\src"));
		for (File file:getFile.getFiles(getFile.selectNameContains(""))) {
			if (file.getParentFile().getName().equals(".idea")){
				continue;
			}
			if (file.getName().substring(file.getName().length()-6).equals(".class")){
				continue;
			}

			if (file.getName().substring(file.getName().length()-5).equals(".java")){
				System.out.println("注释了："+file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"//",""));
			}
			else if (file.getName().substring(file.getName().length()-4).equals(".xml")){
				System.out.println("注释了："+file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"<!--","-->"));
			}
			else if (file.getName().substring(file.getName().length()-4).equals(".jsp")){
				System.out.println("注释了："+file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"<%--","--%>"));
			}
			else if (file.getName().substring(file.getName().length()-11).equals(".properties")){
				System.out.println("注释了："+file.getName());
				SaveAndRead.save(file.getAbsolutePath(),comment(file.getAbsolutePath(),"#",""));
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
