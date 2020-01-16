package source_code_java.java_.nio.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


	private static String encoding = "UTF-8";
	
	/**
	 * 
	 * @title: writeTxtFile
	 * @description: 写txt文件
	 * @param filePath
	 *            文件路径（包含文件名） 例如：E：//test.txt
	 * @param content
	 *            文件内容
	 * @param encode
	 *            生成文件编码
	 * @return success
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String filePath, String content,
			String encode, boolean append) throws Exception {
		if (encode == null || "".equals(encode)) {
			encode = encoding;
		}
		boolean success = false;
		File file = new File(filePath);
		FileOutputStream fos = null;
		try {
			File dir = file.getParentFile();
			if ((!(dir.exists())) && (!(dir.mkdirs()))) {
				throw new Exception("创建目录[" + dir.getCanonicalPath() + "]失败");
			}
			file.createNewFile();
			fos = new FileOutputStream(file, append);
			fos.write(content.getBytes(encode));
			fos.flush();
			success = true;
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if (null != fos) {
				fos.close();
			}
		}
		return success;
	}
	
	  // 文件总行数
    public static int getTotalLines(String fileName) throws Exception {

        BufferedReader br = null;
        int n = 0;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    fileName)));
            while ((br.readLine()) != null) {
                n++;
            }
            return n;
        } catch (IOException e) {
            throw new Exception("读取文件失败,文件名称:" + fileName, e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
    
	public static List<String> readerFiletoList(String fileName, int beginLine, int endLine, int size)
            throws Exception {

        BufferedReader br = null;
		List<String> list = new ArrayList<String>(size);

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileName), "UTF-8"));
            String line = null;
            int n = 1;
            while ((line = br.readLine()) != null) {
                if (n > beginLine && n <= endLine) {
                    list.add(line);
                    line = null;
                }
                n++;
            }
//          list.parallelStream().forEach(System.out::println);
//          System.out.println(list.get(list.size() - 1));
            return list;
        } catch (IOException e) {
            throw new Exception("读取文件失败,文件名称:" + fileName, e);
        } finally {
            if (br != null) {
                br.close();
            }
        }

    }
    
    public static List<String> listDirectory(String filePath) throws Exception{
    	 
    	  List<String> fileNames = null;
    	  File file = new File(filePath);
    	 
		  File flist[] = file.listFiles();
		  if (flist == null || flist.length == 0) {
            System.out.println(filePath + "目录下没找到文件");
			  return fileNames;
		  }
		  fileNames = new ArrayList<String>();
		  for (File f : flist) {
		      if (f.isDirectory()) {
                System.out.println("文件夹不处理");
                continue;
		      } else {
		        fileNames.add(f.getName());		  
		      }
		}
		  return fileNames;
    }	  
      
}
