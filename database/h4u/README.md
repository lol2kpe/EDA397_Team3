# README

## Server info
Server can be accessed on
*lol2kpe.asuscomm.com:3001*

Accessing tables by adding 
    /name
after the host adress.
*example: lol2kpe.asuscomm.com:3001/name*

Accessing json-objects by adding
    .json
after the name.
*example: lol2kpe.asuscomm.com/name.json*

Added tables:
* hospitals

## Table creation code ##

* hospitals
*rails generate scaffold Hospital name:string hospitalType:string latitude:float longitude:float rating:integer comments:string openingHours:string address:string phoneNumber:string*

## TODO in readme: 

* Ruby version

* System dependencies

* Configuration

* Database creation

* Database initialization

* How to run the test suite

* Services (job queues, cache servers, search engines, etc.)

* Deployment instructions

* ...
