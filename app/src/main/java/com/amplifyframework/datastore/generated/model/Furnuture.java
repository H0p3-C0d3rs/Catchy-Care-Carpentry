package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
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

/** This is an auto generated class representing the Furnuture type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Furnutures")
public final class Furnuture implements Model {
  public static final QueryField ID = field("Furnuture", "id");
  public static final QueryField TYPE = field("Furnuture", "type");
  public static final QueryField MODEL = field("Furnuture", "model");
  public static final QueryField WOOD_TYPE = field("Furnuture", "woodType");
  public static final QueryField USER = field("Furnuture", "furnutureUserId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String type;
  private final @ModelField(targetType="String") String model;
  private final @ModelField(targetType="String") String woodType;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "furnutureUserId", type = User.class) User user;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getType() {
      return type;
  }
  
  public String getModel() {
      return model;
  }
  
  public String getWoodType() {
      return woodType;
  }
  
  public User getUser() {
      return user;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Furnuture(String id, String type, String model, String woodType, User user) {
    this.id = id;
    this.type = type;
    this.model = model;
    this.woodType = woodType;
    this.user = user;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Furnuture furnuture = (Furnuture) obj;
      return ObjectsCompat.equals(getId(), furnuture.getId()) &&
              ObjectsCompat.equals(getType(), furnuture.getType()) &&
              ObjectsCompat.equals(getModel(), furnuture.getModel()) &&
              ObjectsCompat.equals(getWoodType(), furnuture.getWoodType()) &&
              ObjectsCompat.equals(getUser(), furnuture.getUser()) &&
              ObjectsCompat.equals(getCreatedAt(), furnuture.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), furnuture.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getType())
      .append(getModel())
      .append(getWoodType())
      .append(getUser())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Furnuture {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("model=" + String.valueOf(getModel()) + ", ")
      .append("woodType=" + String.valueOf(getWoodType()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TypeStep builder() {
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
  public static Furnuture justId(String id) {
    return new Furnuture(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      type,
      model,
      woodType,
      user);
  }
  public interface TypeStep {
    BuildStep type(String type);
  }
  

  public interface BuildStep {
    Furnuture build();
    BuildStep id(String id);
    BuildStep model(String model);
    BuildStep woodType(String woodType);
    BuildStep user(User user);
  }
  

  public static class Builder implements TypeStep, BuildStep {
    private String id;
    private String type;
    private String model;
    private String woodType;
    private User user;
    @Override
     public Furnuture build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Furnuture(
          id,
          type,
          model,
          woodType,
          user);
    }
    
    @Override
     public BuildStep type(String type) {
        Objects.requireNonNull(type);
        this.type = type;
        return this;
    }
    
    @Override
     public BuildStep model(String model) {
        this.model = model;
        return this;
    }
    
    @Override
     public BuildStep woodType(String woodType) {
        this.woodType = woodType;
        return this;
    }
    
    @Override
     public BuildStep user(User user) {
        this.user = user;
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
    private CopyOfBuilder(String id, String type, String model, String woodType, User user) {
      super.id(id);
      super.type(type)
        .model(model)
        .woodType(woodType)
        .user(user);
    }
    
    @Override
     public CopyOfBuilder type(String type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder model(String model) {
      return (CopyOfBuilder) super.model(model);
    }
    
    @Override
     public CopyOfBuilder woodType(String woodType) {
      return (CopyOfBuilder) super.woodType(woodType);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
  }
  
}
