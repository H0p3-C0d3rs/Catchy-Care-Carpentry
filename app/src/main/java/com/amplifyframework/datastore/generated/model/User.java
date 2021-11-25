package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USERNAME = field("User", "username");
  public static final QueryField IMAGE = field("User", "image");
  public static final QueryField FIRSTNAME = field("User", "firstname");
  public static final QueryField LASTNAME = field("User", "lastname");
  public static final QueryField PHONE = field("User", "phone");
  public static final QueryField EMAIL = field("User", "email");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String") String image;
  private final @ModelField(targetType="String", isRequired = true) String firstname;
  private final @ModelField(targetType="String", isRequired = true) String lastname;
  private final @ModelField(targetType="String", isRequired = true) String phone;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="Request") @HasMany(associatedWith = "user", type = Request.class) List<Request> request = null;
  private final @ModelField(targetType="Furnuture") @HasMany(associatedWith = "user", type = Furnuture.class) List<Furnuture> furnuture = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getImage() {
      return image;
  }
  
  public String getFirstname() {
      return firstname;
  }
  
  public String getLastname() {
      return lastname;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getEmail() {
      return email;
  }
  
  public List<Request> getRequest() {
      return request;
  }
  
  public List<Furnuture> getFurnuture() {
      return furnuture;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String username, String image, String firstname, String lastname, String phone, String email) {
    this.id = id;
    this.username = username;
    this.image = image;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.email = email;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getImage(), user.getImage()) &&
              ObjectsCompat.equals(getFirstname(), user.getFirstname()) &&
              ObjectsCompat.equals(getLastname(), user.getLastname()) &&
              ObjectsCompat.equals(getPhone(), user.getPhone()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getImage())
      .append(getFirstname())
      .append(getLastname())
      .append(getPhone())
      .append(getEmail())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("image=" + String.valueOf(getImage()) + ", ")
      .append("firstname=" + String.valueOf(getFirstname()) + ", ")
      .append("lastname=" + String.valueOf(getLastname()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
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
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      image,
      firstname,
      lastname,
      phone,
      email);
  }
  public interface UsernameStep {
    FirstnameStep username(String username);
  }
  

  public interface FirstnameStep {
    LastnameStep firstname(String firstname);
  }
  

  public interface LastnameStep {
    PhoneStep lastname(String lastname);
  }
  

  public interface PhoneStep {
    EmailStep phone(String phone);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep image(String image);
  }
  

  public static class Builder implements UsernameStep, FirstnameStep, LastnameStep, PhoneStep, EmailStep, BuildStep {
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String image;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          username,
          image,
          firstname,
          lastname,
          phone,
          email);
    }
    
    @Override
     public FirstnameStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public LastnameStep firstname(String firstname) {
        Objects.requireNonNull(firstname);
        this.firstname = firstname;
        return this;
    }
    
    @Override
     public PhoneStep lastname(String lastname) {
        Objects.requireNonNull(lastname);
        this.lastname = lastname;
        return this;
    }
    
    @Override
     public EmailStep phone(String phone) {
        Objects.requireNonNull(phone);
        this.phone = phone;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep image(String image) {
        this.image = image;
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
    private CopyOfBuilder(String id, String username, String image, String firstname, String lastname, String phone, String email) {
      super.id(id);
      super.username(username)
        .firstname(firstname)
        .lastname(lastname)
        .phone(phone)
        .email(email)
        .image(image);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder firstname(String firstname) {
      return (CopyOfBuilder) super.firstname(firstname);
    }
    
    @Override
     public CopyOfBuilder lastname(String lastname) {
      return (CopyOfBuilder) super.lastname(lastname);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder image(String image) {
      return (CopyOfBuilder) super.image(image);
    }
  }
  
}
