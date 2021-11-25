package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the OurLocation type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "OurLocations")
public final class OurLocation implements Model {
  public static final QueryField ID = field("OurLocation", "id");
  public static final QueryField COUNTRY_NAME = field("OurLocation", "countryName");
  public static final QueryField CITY_NAME = field("OurLocation", "cityName");
  public static final QueryField LONGITUDE = field("OurLocation", "longitude");
  public static final QueryField LATITUDE = field("OurLocation", "latitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String countryName;
  private final @ModelField(targetType="String", isRequired = true) String cityName;
  private final @ModelField(targetType="Float", isRequired = true) Double longitude;
  private final @ModelField(targetType="Float", isRequired = true) Double latitude;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getCountryName() {
      return countryName;
  }
  
  public String getCityName() {
      return cityName;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public Double getLatitude() {
      return latitude;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private OurLocation(String id, String countryName, String cityName, Double longitude, Double latitude) {
    this.id = id;
    this.countryName = countryName;
    this.cityName = cityName;
    this.longitude = longitude;
    this.latitude = latitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      OurLocation ourLocation = (OurLocation) obj;
      return ObjectsCompat.equals(getId(), ourLocation.getId()) &&
              ObjectsCompat.equals(getCountryName(), ourLocation.getCountryName()) &&
              ObjectsCompat.equals(getCityName(), ourLocation.getCityName()) &&
              ObjectsCompat.equals(getLongitude(), ourLocation.getLongitude()) &&
              ObjectsCompat.equals(getLatitude(), ourLocation.getLatitude()) &&
              ObjectsCompat.equals(getCreatedAt(), ourLocation.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), ourLocation.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCountryName())
      .append(getCityName())
      .append(getLongitude())
      .append(getLatitude())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("OurLocation {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("countryName=" + String.valueOf(getCountryName()) + ", ")
      .append("cityName=" + String.valueOf(getCityName()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static CountryNameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static OurLocation justId(String id) {
    return new OurLocation(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      countryName,
      cityName,
      longitude,
      latitude);
  }
  public interface CountryNameStep {
    CityNameStep countryName(String countryName);
  }
  

  public interface CityNameStep {
    LongitudeStep cityName(String cityName);
  }
  

  public interface LongitudeStep {
    LatitudeStep longitude(Double longitude);
  }
  

  public interface LatitudeStep {
    BuildStep latitude(Double latitude);
  }
  

  public interface BuildStep {
    OurLocation build();
    BuildStep id(String id);
  }
  

  public static class Builder implements CountryNameStep, CityNameStep, LongitudeStep, LatitudeStep, BuildStep {
    private String id;
    private String countryName;
    private String cityName;
    private Double longitude;
    private Double latitude;
    @Override
     public OurLocation build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new OurLocation(
          id,
          countryName,
          cityName,
          longitude,
          latitude);
    }
    
    @Override
     public CityNameStep countryName(String countryName) {
        Objects.requireNonNull(countryName);
        this.countryName = countryName;
        return this;
    }
    
    @Override
     public LongitudeStep cityName(String cityName) {
        Objects.requireNonNull(cityName);
        this.cityName = cityName;
        return this;
    }
    
    @Override
     public LatitudeStep longitude(Double longitude) {
        Objects.requireNonNull(longitude);
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public BuildStep latitude(Double latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String countryName, String cityName, Double longitude, Double latitude) {
      super.id(id);
      super.countryName(countryName)
        .cityName(cityName)
        .longitude(longitude)
        .latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder countryName(String countryName) {
      return (CopyOfBuilder) super.countryName(countryName);
    }
    
    @Override
     public CopyOfBuilder cityName(String cityName) {
      return (CopyOfBuilder) super.cityName(cityName);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder latitude(Double latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
  }
  
}
