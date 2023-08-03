Automation Framework Setup
 1.  Pre-requisites
Following are the tools and their versions should be installed before setting up this automation framework:
Be sure to match the versions below exactly!
ToolsVersion/ReleaseOSWINDOWS 64-bitJAVAJDK 11 or later. 64-bitEclipse2021-12 (4.22.0), Win-64-bitApache Maven3.8.4TestNG6.11 (included out of box from Maven2+)1.1 Java Setup
1. Open JDK can be downloaded and installed from the below mentioned link:
https://adoptopenjdk.net/upstream.html?variant=openjdk11&ga=ga
2. Extract zip file under C:\Program Files\Java\ folder
3. Environment variables to be setup as follows:
Environment VariableValueJAVA_HOME(Create New System Variable)C:\Program Files\Java\jdk-11.0.XXPath(Update Path)%JAVA_HOME%\binJAVA_TOOL_OPTIONS(User Variable)-Dfile.encoding=UTF84. To check Java has been installed correctly, open new command prompt and type:
“java –version”. Correct version of java should be displayed as follows:

1.2 Eclipse Setup
Eclipse is an integrated development environment (IDE) and is written mostly in JAVA. Its primary use is for developing Java applications. Eclipse can be downloaded from below mentioned link:
1. https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2021-12/R/eclipse-java-2021-12-R-win32-x86_64.zip (Eclipse IDE for Java Developers, windows 64-bit) 
1. Extract eclipse at any folder you want in your system.
Note: There is no need to install eclipse, you just need to run eclipse.exe whenever you want to open and use eclipse.
1.3 Maven Setup
Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
1. Download “apache-maven-3.8.4-bin.zip” from the below mentioned link: https://maven.apache.org/download.cgi
2. Unzip apache-maven-3.8.4-bin.zip under “C:\Program Files\Apache Software Foundation” folder.
3. Set environment variables as follows:
Environment VariableValueM2_HOME (Create New System Variable)C:\Program Files\Apache Software Foundation\apache-maven-3.8.4M2 (Create New System Variable)%M2_HOME%\binPath (Update Path)%M2_HOME%\bin4. To check maven has been installed correctly, restart your computer, open command prompt and type:
            “mvn –version”. Correct version of maven should be displayed as follows:
              
2.  Selenium Automation Framework Setup
1. Download automation framework. 
2. Go to the folder where you have extracted eclipse and double click eclipse.exe


3. Click on File ? Import


4.  Click on General ? Existing Projects into Workspace


5. Browse the directory where you have copied your “selenium-assignment” and click select folder.



6. On importing the project, your project structure should look like as follows:


7. Config ? app.properties has setup configurations.
8. Install testNg plug-in from Eclipse marketplace (Help ? Eclipse Marketplace...)

9. testngSmoke.xml or testngCritical.xml modifications 
1. If you want to execute specific set of test cases on your local bench. 
1. Add a temporary group name on the intended test case(s) which you want to execute and mention it in the method selector.  
2. The <method-selectors> will ensure only the tests decorated with the mentioned group are executed. Also, ensure that it is part of an included package.
Example:
Test Case: @Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = {"myTest"})
testNgHierarchical > Method-Selector: <script>: <script language="beanshell"><![CDATA[(groups.containsKey("myTest"))]]></script>
testNgHierarchical > packages: Make the target test case part of a package and include the package name for your run.
          <classes>
			<class name="com.nagarro.nagp.VerifyLoginFunctionality" />
</classes>
10. Right click on pom.xml file and click Run As ? 1 Maven build
   
11. In “Goals” section write “clean install”, click “Apply” and then “Run”.
      
12. After above step Maven will download all its dependencies this may take some time. In the end “Build Success” should come in the console window of Eclipse.


Build and Test execution from Command prompt:
1. For Build:
a. mvn eclipse:eclipse
b. mvn clean install -DskipTest (build without executing test cases)
Referenced Libraries will be automatically updated. If not updated, then try to import the project into eclipse again.

2. For executing test cases:
a. mvn test (execute test cases only).




