# contrastAgent
install steps (build on Windows 10)

0) install Sakai/Tomcat per instructions at https://github.com/sakaiproject/sakai

1) build barryAgent.jar 
	mvn package
2) add an "agents" folder to the toplevel of the tomcat folder
3) place the barryAgent-1.0-SNAPSHOT.jar in the agents folder	
4) modify tomcat/bin catalina.bat file, add AS LINE 224
	SET JAVA_OPTS=%JAVA_OPTS% -Djavaagent:"C:\opt\tomcat\apache-tomcat-8.0.45\agents\BarryAgent-1.0-SNAPSHOT.jar"
5) start tomcat, check logs
6) sent a get to the server at rest/agent and check for output

