//Run this script in the command line with the Mongo Shell: mongo < DB_setup.js
use candidatesdb
db.dropDatabase()
db.createCollection("candidates")