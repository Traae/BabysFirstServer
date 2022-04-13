FROM Alpine
LABEL maintainer="bloxtraa@isu.edu"
RUN apk add -update nodejs nodejs -npm
COPY library/src
WORKDIR app/src
RUN npm install
EXPOSE 8080
ENTRYPOINT ["node", "./app.js"]