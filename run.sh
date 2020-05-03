#!/bin/bash
mongod --bind_ip_all --port 27020

# CODE in case of debugging need.
# mongorestore -d movies movies
# kill %1
# sleep 3 # need to wait for the process to die
# mongod
# 
# EXPOSE 27020
# CMD [ "mongod", "--bind_ip_all", "--port", "27020" ]