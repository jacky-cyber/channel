import com.zjht.channel.service.Service;

/** 
 * Project Name:channel-server 
 * File Name:Test.java 
 * Package Name: 
 * Date:2015年8月27日下午2:47:04 
 * 
 */

/** 
 * ClassName: Test <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年8月27日 下午2:47:04 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.7
 */
public class Test {
	public static void main(String[] args) {
//		System.out.println(Symbol.COMMA.equals(","));
//		System.out.println(Symbol.COMMA);
//		System.out.println(Arrays.toString(StringHelper.split("/Zjht/get.tokenId/1.0", Symbol.SLASH.getCode())));
/*	    long l1  = 1440391003000L;
	    long l2 = 1441793599000L;
	    long c = System.currentTimeMillis();
	    System.out.println(c);
	    System.out.println(c-l1);
	    System.out.println(c-l2);
	    
	    SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    //2015-08-17
	    System.out.println(sdf.format(new Date(l1)));*/
	    
	   /* String[] strs = new String[]{"1","2","3"};
	    Stream<String> stream = Stream.of(strs);
	    stream.forEach(s->System.out.println(s));
	    
	    Queue<String> q  = Lists.newLinkedList();
	    Stream.of(strs).forEach(s->q.offer(s));*/
	    
	    System.out.println(Service.class.getPackage().getImplementationVersion());
	}
}
  