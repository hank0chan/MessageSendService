package alidayu_demo.util;

import java.util.Date;

import org.junit.Test;

public class TestTimeUtils {

	@Test
	public void test() {
		String iso8901_format = TimeUtils.ISO8601.format(new Date());
		System.out.println(iso8901_format);
		String format = TimeUtils.YYYYMMDDHHMM.format(new Date());
		System.out.println(format);
		String yMdHHmmss = TimeUtils.YYYYMMDDHHMMSS.format(new Date());
		System.out.println(yMdHHmmss);
		String yMdHHmmssfff = TimeUtils.YYYYMMDDHHMMSSFFF.format(new Date()); // 不可用
		System.out.println(yMdHHmmssfff); // 不可用
	}
}
