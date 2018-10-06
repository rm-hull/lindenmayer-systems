FROM frolvlad/alpine-oraclejdk8:latest
MAINTAINER Richard Hull <rm_hull@yahoo.co.uk>

ENV LEIN_ROOT 1

RUN apk add --update wget ca-certificates bash && \
    wget -q "https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein" \
         -O /usr/local/bin/lein && \
    chmod 0755 /usr/local/bin/lein && \
    lein && \
    apk del wget ca-certificates && \
    rm -rf /tmp/* /var/cache/apk/*

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN \
  lein deps && \
  lein ring uberjar && \
  rm -rf target/classes ~/.m2
  
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "target/lindenmayer-systems-0.2.1-standalone.jar"]