# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

hosptitals = Hospital.create([{name: 'Sahlgrenska', hospitalType: 'Emergency', latitude: 57.7072024, longitude: 11.9392741, rating: 5, comments: 'hey!', openingHours: '8:00-17:00', address: 'Horselgangen 1, 12345 Gothenburg', phoneNumber: '031-123456'},
{name: 'WeFixU', hospitalType: 'Health care center', latitude: 57.7039308, longitude: 11.9395899, rating: 2, comments: 'I like this hospital. I got my dog back!', openingHours: '0:00-24:00', address: 'Horselgangen 2, 12345 Gothenburg', phoneNumber: '031-7890123'}])
