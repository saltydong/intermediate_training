package helloworld;



import org.junit.Assert;
import org.junit.Test;

public class testhw {

	@Test
	public void helloWorldNormal() {
        String result = Helloworld.helloWorld();
        Assert.assertEquals("Hello World!", result);
    }


}


 