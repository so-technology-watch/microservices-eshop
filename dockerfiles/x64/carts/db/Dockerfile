FROM lechelong/eshop-basex64
RUN apk --update add ca-certificates redis
RUN mkdir /data && chown redis:redis /data
VOLUME /data
WORKDIR /data
EXPOSE 6379
USER redis
ADD redis.conf /usr/local/etc/redis/redis.conf
CMD /usr/bin/redis-server
