import org.springframework.stereotype.Service;

import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.service.bean.Application;

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
            
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Application app = Application.class.newInstance(); 
        Class<?> clazz = app.getClass();
        
        String[] ss = new String[]{"a","b"};
        
        ListHelper.listOf(ss).forEach(s->{
            System.out.println(s);
        });
        
       /* System.out.println(app);
        try {
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            Method method = clazz.getDeclaredMethod("setName",String.class);
           method.invoke(app, "test");
            System.out.println(app);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
	}
    
    public static <T> T newObject(Class<T> clazz){
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
  