FROM openjdk:11-jre-slim
LABEL org.opencontainers.image.source=https://github.com/CactusCrew/bitochok_bot

ARG VERSION

WORKDIR /opt/cactuscrew/

COPY "bitochok_bot-fat-${VERSION}.jar" /opt/cactuscrew/bitochok_bot-fat.jar

CMD ["java","-jar","/opt/cactuscrew/bitochok_bot-fat.jar"]
