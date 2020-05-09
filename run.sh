#!/bin/bash
mongod --bind_ip_all --port 27020 &

# CODE in case of debugging need.
mongorestore --port 27020 -d movies movies  2> /dev/null
while true; do sleep 100; done
# kill %1
# sleep 3 # need to wait for the process to die
# mongod
# 
# EXPOSE 27020
# CMD [ "mongod", "--bind_ip_all", "--port", "27020" ]