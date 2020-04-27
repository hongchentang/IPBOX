package test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SystemUtils;


public class StringTest {

	public static void main(String[] args) {
		/*String str="18418158889";
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^(13[0-9]|15[0-9]|18[0-9])\\d{4,8}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        System.out.println(b);*/
		System.out.println(FilenameUtils.getExtension("asdf.xml"));
		
		int fileCount = 1273;
		String diskName = "MyDisk";
		Object[] testArgs = {new Long(fileCount), diskName};
		MessageFormat form = new MessageFormat( "The disk \"{1}\" contains {0} file(s).");
		System.out.println(form.format(testArgs));
		System.out.println(ClassPathUtils.toFullyQualifiedName(StringTest.class,""));
		System.out.println(ClassPathUtils.toFullyQualifiedPath(StringTest.class,""));
		System.out.println(ClassPathUtils.toFullyQualifiedPath(Class.class, ""));

		System.out.println(SystemUtils.getUserDir());
		System.out.println(SystemUtils.getUserHome());

	}
	
}
