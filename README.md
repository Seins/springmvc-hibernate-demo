# springmvc-hibernate-demo<br>
demo for springmvc +  hibernate project ,an empty project to teach<br>
<br>
project tree<br>
root --<br>
     |--service / web service module<br>
       |--src /<br>
       |--java /  java class files path<br>
       |--resources / configuration files path<br>
       |--webapp / webroot path,include static resources and jsp or other view template files<br>
     |<br>
     |<br>
     |--parent / parent module ,mult-modules version control key module,packaging by pom,other pom.xml set<br> parent:artifactId=this.artifactId<br>
<br>
<br>
<br>
<br>
1.clone project into your workspace ,use idea 14 or later version open project;<br>
2.use maven install dependence jars,run with jetty;<br>
3.request http://localhost:8090/service/service/json in POST method ,then your will see that:<br>
{<br>
  "msg": "request success!framework running success",<br>
  "result": "0"<br>
}<br>
