package com.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Parent.
 */
@Entity
@Table(name = "parent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "account_active")
    private Boolean accountActive;

    @Column(name = "address")
    private String address;

    @Column(name = "emaild_id")
    private String emaildId;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "account_starting_date")
    private LocalDate accountStartingDate;

    @JsonIgnoreProperties(value = { "parentId", "fees", "attendances" }, allowSetters = true)
    @OneToOne(mappedBy = "parentId")
    private Student parentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Parent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Parent firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Parent lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Parent phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getAccountActive() {
        return this.accountActive;
    }

    public Parent accountActive(Boolean accountActive) {
        this.setAccountActive(accountActive);
        return this;
    }

    public void setAccountActive(Boolean accountActive) {
        this.accountActive = accountActive;
    }

    public String getAddress() {
        return this.address;
    }

    public Parent address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmaildId() {
        return this.emaildId;
    }

    public Parent emaildId(String emaildId) {
        this.setEmaildId(emaildId);
        return this;
    }

    public void setEmaildId(String emaildId) {
        this.emaildId = emaildId;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public Parent photo(byte[] photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return this.photoContentType;
    }

    public Parent photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public LocalDate getAccountStartingDate() {
        return this.accountStartingDate;
    }

    public Parent accountStartingDate(LocalDate accountStartingDate) {
        this.setAccountStartingDate(accountStartingDate);
        return this;
    }

    public void setAccountStartingDate(LocalDate accountStartingDate) {
        this.accountStartingDate = accountStartingDate;
    }

    public Student getParentId() {
        return this.parentId;
    }

    public void setParentId(Student student) {
        if (this.parentId != null) {
            this.parentId.setParentId(null);
        }
        if (student != null) {
            student.setParentId(this);
        }
        this.parentId = student;
    }

    public Parent parentId(Student student) {
        this.setParentId(student);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parent)) {
            return false;
        }
        return id != null && id.equals(((Parent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parent{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", accountActive='" + getAccountActive() + "'" +
            ", address='" + getAddress() + "'" +
            ", emaildId='" + getEmaildId() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", accountStartingDate='" + getAccountStartingDate() + "'" +
            "}";
    }
}
