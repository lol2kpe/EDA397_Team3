class CreateHospitals < ActiveRecord::Migration[5.0]
  def change
    create_table :hospitals do |t|
      t.string :name
      t.string :hospitalType
      t.float :latitude
      t.float :longitude
      t.integer :rating
      t.string :comments
      t.string :openingHours
      t.string :address
      t.string :phoneNumber

      t.timestamps
    end
  end
end
