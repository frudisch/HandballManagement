FROM mongo:latest

# Modify child mongo to use /data/pl_details as dbpath (because /data/db wont persist the build)
RUN mkdir -p /data/pl_details \
    && echo "dbpath = /data/pl_details" > /etc/mongodb.conf \
    && chown -R mongodb:mongodb /data/pl_details

# Make the new dir a VOLUME to persists it
VOLUME /data/pl_details

CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]