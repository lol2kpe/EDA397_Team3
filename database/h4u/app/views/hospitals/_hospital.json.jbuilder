json.extract! hospital, :id, :name, :hospitalType, :latitude, :longitude, :rating, :comments, :openingHours, :address, :phoneNumber, :created_at, :updated_at
json.url hospital_url(hospital, format: :json)
