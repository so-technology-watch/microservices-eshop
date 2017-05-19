FROM lechelong/eshop-basex64
RUN apk --update add ca-certificates redis
RUN mkdir /data && chown redis:redis /data
VOLUME /data
WORKDIR /data
EXPOSE 6379
USER redis
CMD /usr/bin/redis-server --protected-mode no
