abcAnglicanCytoGen
==================

Call this bad boy once to initialize a matlab session

javac -cp matlabcontrol-4.1.0.jar:. ABCMatlabInit.java;java -cp matlabcontrol-4.1.0.jar:. ABCMatlabInit;


From then on, keep that matlab session open, and just call this - the code should run in the same matlab session

javac -cp matlabcontrol-4.1.0.jar:. ABCCytoGen.java;java -cp matlabcontrol-4.1.0.jar:. ABCCytoGen
