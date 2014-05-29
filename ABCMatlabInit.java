import matlabcontrol.*;
import matlabcontrol.extensions.*;

//java -cp matlabcontrol-4.1.0.jar:. ABCMatlabInit;javac -cp matlabcontrol-4.1.0.jar:. ABCMatlabInit.java
//javac -cp matlabcontrol-4.1.0.jar:. MatlabInit.java


public class ABCMatlabInit {
  public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException
  {
    MatlabProxyFactory factory = new MatlabProxyFactory();
    MatlabProxy proxy = factory.getProxy();
    proxy.disconnect();
  }
}
