package test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.hcis.ipanther.common.user.utils.UserConstants;

/**
 * @author Chaos
 * @date 2013-3-15
 * @time 下午2:56:04
 *
 */
public class StringTest {

	private final static Log log=LogFactory.getLog(StringTest.class);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			log.info(DigestUtils.md5Hex("admin".getBytes("GBK")));
			log.info(Integer.MAX_VALUE);
			DecimalFormat dformat=new DecimalFormat("000000");
			BigDecimal num=new BigDecimal("90");
			log.info(dformat.format(num));
			log.info(DigestUtils.md5Hex("11111".getBytes()));
			String aa="{regionsName}1111";
			log.info(aa.replaceAll("\\{regionsName\\}","aaa"));
			log.info("431111".substring(0,2)+"0000");
			log.info(StringUtils.defaultString(null,"111"));
			log.info(StringUtils.defaultString("","112"));
			log.info(StringUtils.defaultIfEmpty(null,"121"));
			log.info(StringUtils.defaultIfEmpty("","122"));
			String[] bb={"11111","22222","33333","123123"};
			log.info(ArrayUtils.indexOf(bb,"123123"));
			log.info(StringUtils.contains("11111", "11"));
			log.info("11".length()>3?"11".substring(0,3):"11");
			log.info(1800/2);
			log.info(JSONArray.fromObject(bb).toString());
			for(int i=0;i<10000;i++){
				log.info(UUID.randomUUID().toString());
			}
			for(int i=0;i<100;i++){
				UUID.randomUUID();
			}
			for(int i=0;i<100;i++){
				UUID.randomUUID();
			}
			
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
