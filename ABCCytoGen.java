//package matlabcontrol;
//import matlabcontrol.MatlabConnectionException;
//import matlabcontrol.MatlabInvocationException;
//import matlabcontrol.MatlabProxy;
//import matlabcontrol.MatlabProxyFactory;
//import matlabcontrol.MatlabProxyFactoryOptions;
import matlabcontrol.*;
import matlabcontrol.extensions.*;

//java -cp matlabcontrol-4.1.0.jar:. ABCCytoGen;javac -cp matlabcontrol-4.1.0.jar:. ABCCytoGen.java
//javac -cp matlabcontrol-4.1.0.jar:. ABCCytoGen.java


public class ABCCytoGen {

  public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException
  {
    runExample();
  }

  public static void runExample() throws MatlabConnectionException, MatlabInvocationException
  {
    double[][] array = new double[][] { { 110, 110, 30 } };

    //MatlabTypeConverter processor = new MatlabTypeConverter (proxy );
    //processor.setNumericArray("array", new MatlabNumericArray(array, null));

    double score = noisyCompareAndScore( new String(), array, 0.001 );
    System.out.println("Score " + score );


  }
  public static MatlabProxy getMatlabProxy() throws MatlabConnectionException, MatlabInvocationException
  {
    MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
                                              .setUsePreviouslyControlledSession(true)
                                              .setHidden(true)
                                              .setMatlabLocation(null).build();

    //FIXME - looks like you have to call it without options the first time you run it. boohoo.
    //hence the script MatlabInit
    MatlabProxyFactory factory = new MatlabProxyFactory( options );
    MatlabProxy proxy = factory.getProxy();

    return proxy;
  }

  public static double noisyCompareAndScore( String _targetCanvasFilename/*UNUSED FOR NOW*/, double[][] cellInfoArray, double noiseVariance ) throws MatlabConnectionException, MatlabInvocationException
  {
    MatlabProxy proxy = getMatlabProxy();
    Object[] score = proxy.returningFeval( "abcNoisyCompareAndScore", 1, "", cellInfoArray , noiseVariance );
    //Disconnect the proxy from MATLAB
    proxy.disconnect();

    return ( ( double[] )score[ 0 ] )[ 0 ];



  }

}
