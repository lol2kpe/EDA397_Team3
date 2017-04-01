require 'test_helper'

class HospitalsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @hospital = hospitals(:one)
  end

  test "should get index" do
    get hospitals_url
    assert_response :success
  end

  test "should get new" do
    get new_hospital_url
    assert_response :success
  end

  test "should create hospital" do
    assert_difference('Hospital.count') do
      post hospitals_url, params: { hospital: { address: @hospital.address, comments: @hospital.comments, hospitalType: @hospital.hospitalType, latitude: @hospital.latitude, longitude: @hospital.longitude, name: @hospital.name, openingHours: @hospital.openingHours, phoneNumber: @hospital.phoneNumber, rating: @hospital.rating } }
    end

    assert_redirected_to hospital_url(Hospital.last)
  end

  test "should show hospital" do
    get hospital_url(@hospital)
    assert_response :success
  end

  test "should get edit" do
    get edit_hospital_url(@hospital)
    assert_response :success
  end

  test "should update hospital" do
    patch hospital_url(@hospital), params: { hospital: { address: @hospital.address, comments: @hospital.comments, hospitalType: @hospital.hospitalType, latitude: @hospital.latitude, longitude: @hospital.longitude, name: @hospital.name, openingHours: @hospital.openingHours, phoneNumber: @hospital.phoneNumber, rating: @hospital.rating } }
    assert_redirected_to hospital_url(@hospital)
  end

  test "should destroy hospital" do
    assert_difference('Hospital.count', -1) do
      delete hospital_url(@hospital)
    end

    assert_redirected_to hospitals_url
  end
end
