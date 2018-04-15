# 打包、构建镜像、推送阿里云
# 这个步骤，在生产环境中是由专门的CI服务器去拉取代码，进行构建的。开发人员不会知道内网的镜像信息
# mvn clean package docker:build -DpushImage

FROM java
# 将当前目录下的*.jar添加到镜像中去，并且命名为app.jar
ADD *.jar app.jar
# 镜像启动的时候执行这个命令
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar