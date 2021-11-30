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

/** This is an auto generated class representing the Dashbord type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Dashbords")
public final class Dashbord implements Model {
  public static final QueryField ID = field("Dashbord", "id");
  public static final QueryField IMAGE_URL = field("Dashbord", "imageUrl");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String imageUrl;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getImageUrl() {
      return imageUrl;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Dashbord(String id, String imageUrl) {
    this.id = id;
    this.imageUrl = imageUrl;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Dashbord dashbord = (Dashbord) obj;
      return ObjectsCompat.equals(getId(), dashbord.getId()) &&
              ObjectsCompat.equals(getImageUrl(), dashbord.getImageUrl()) &&
              ObjectsCompat.equals(getCreatedAt(), dashbord.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), dashbord.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getImageUrl())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Dashbord {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("imageUrl=" + String.valueOf(getImageUrl()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static ImageUrlStep builder() {
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
  public static Dashbord justId(String id) {
    return new Dashbord(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      imageUrl);
  }
  public interface ImageUrlStep {
    BuildStep imageUrl(String imageUrl);
  }
  

  public interface BuildStep {
    Dashbord build();
    BuildStep id(String id);
  }
  

  public static class Builder implements ImageUrlStep, BuildStep {
    private String id;
    private String imageUrl;
    @Override
     public Dashbord build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Dashbord(
          id,
          imageUrl);
    }
    
    @Override
     public BuildStep imageUrl(String imageUrl) {
        Objects.requireNonNull(imageUrl);
        this.imageUrl = imageUrl;
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
    private CopyOfBuilder(String id, String imageUrl) {
      super.id(id);
      super.imageUrl(imageUrl);
    }
    
    @Override
     public CopyOfBuilder imageUrl(String imageUrl) {
      return (CopyOfBuilder) super.imageUrl(imageUrl);
    }
  }
  
}
