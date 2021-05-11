# bitochok_bot

## Local launch

 build an image locally:
 
 ```bash
 ./gradlew docker
 ```
 
 run the image with environment variable `BITOCHOK_BOT_TOKEN` - Telegram Bot API token

```bash
docker run -e BITOCHOK_BOT_TOKEN=${token} ghcr.io/cactuscrew/bitochok_bot
```
