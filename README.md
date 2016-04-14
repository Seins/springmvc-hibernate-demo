# springmvc-hibernate-demo
demo for springmvc +  hibernate project ,an empty project to teach

project tree
root --<br>
     |--service / web service module
       |--src /
       |--java /  java class files path
       |--resources / configuration files path
       |--webapp / webroot path,include static resources and jsp or other view template files
     |
     |
     |--parent / parent module ,mult-modules version control key module,packaging by pom,other pom.xml set parent:artifactId=this.artifactId




1.clone project into your workspace ,use idea 14 or later version open project;
2.use maven install dependence jars,run with jetty;
3.request http://localhost:8090/service/service/json in POST method ,then your will see that:
{
  "msg": "request success!framework running success",
  "result": "0"
}
