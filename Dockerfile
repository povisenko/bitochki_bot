FROM openjdk:11-jre-slim
WORKDIR /opt/cactuscrew/

COPY bitochok_bot-fat.jar /opt/cactuscrew/bitochok_bot-fat.jar

CMD ["java","-jar","/opt/cactuscrew/bitochok_bot-fat.jar"]
