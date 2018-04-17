FROM salientcrgt/odos2-res-app-ngrunner:latest

MAINTAINER Pramod Malhotra <pramod.malhotra@salientcrgt.com>

ARG env

COPY . .
RUN npm install && ng build --environment=$env

# work around perms on OpenShift
RUN chmod -R a+rwx /opt/app
CMD ["ng", "serve", "--host", "0.0.0.0", "--disableHostCheck", "--env", "$env"]


EXPOSE 4200
