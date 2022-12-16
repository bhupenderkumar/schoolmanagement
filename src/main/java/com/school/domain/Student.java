package com.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Student implements Serializable {

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
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "emergency_contact_number", nullable = false)
    private String emergencyContactNumber;

    @Lob
    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @NotNull
    @Column(name = "photo_content_type", nullable = false)
    private String photoContentType;

    @Lob
    @Column(name = "child_id_proof", nullable = false)
    private byte[] childIdProof;

    @NotNull
    @Column(name = "child_id_proof_content_type", nullable = false)
    private String childIdProofContentType;

    @Lob
    @Column(name = "parent_id_proof", nullable = false)
    private byte[] parentIdProof;

    @NotNull
    @Column(name = "parent_id_proof_content_type", nullable = false)
    private String parentIdProofContentType;

    @Lob
    @Column(name = "any_other_document")
    private byte[] anyOtherDocument;

    @Column(name = "any_other_document_content_type")
    private String anyOtherDocumentContentType;

    @Column(name = "any_disease")
    private String anyDisease;

    @Column(name = "family_doctor_name")
    private String familyDoctorName;

    @Column(name = "family_doctor_number")
    private String familyDoctorNumber;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "tc_date")
    private LocalDate tcDate;

    @JsonIgnoreProperties(value = { "parentId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Parent parentId;

    @JsonIgnoreProperties(value = { "student" }, allowSetters = true)
    @OneToOne(mappedBy = "student")
    private Fees fees;

    @ManyToMany(mappedBy = "students")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "students" }, allowSetters = true)
    private Set<Attendance> attendances = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Student id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Student firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Student lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return this.address;
    }

    public Student address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Student phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyContactNumber() {
        return this.emergencyContactNumber;
    }

    public Student emergencyContactNumber(String emergencyContactNumber) {
        this.setEmergencyContactNumber(emergencyContactNumber);
        return this;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public Student photo(byte[] photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return this.photoContentType;
    }

    public Student photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getChildIdProof() {
        return this.childIdProof;
    }

    public Student childIdProof(byte[] childIdProof) {
        this.setChildIdProof(childIdProof);
        return this;
    }

    public void setChildIdProof(byte[] childIdProof) {
        this.childIdProof = childIdProof;
    }

    public String getChildIdProofContentType() {
        return this.childIdProofContentType;
    }

    public Student childIdProofContentType(String childIdProofContentType) {
        this.childIdProofContentType = childIdProofContentType;
        return this;
    }

    public void setChildIdProofContentType(String childIdProofContentType) {
        this.childIdProofContentType = childIdProofContentType;
    }

    public byte[] getParentIdProof() {
        return this.parentIdProof;
    }

    public Student parentIdProof(byte[] parentIdProof) {
        this.setParentIdProof(parentIdProof);
        return this;
    }

    public void setParentIdProof(byte[] parentIdProof) {
        this.parentIdProof = parentIdProof;
    }

    public String getParentIdProofContentType() {
        return this.parentIdProofContentType;
    }

    public Student parentIdProofContentType(String parentIdProofContentType) {
        this.parentIdProofContentType = parentIdProofContentType;
        return this;
    }

    public void setParentIdProofContentType(String parentIdProofContentType) {
        this.parentIdProofContentType = parentIdProofContentType;
    }

    public byte[] getAnyOtherDocument() {
        return this.anyOtherDocument;
    }

    public Student anyOtherDocument(byte[] anyOtherDocument) {
        this.setAnyOtherDocument(anyOtherDocument);
        return this;
    }

    public void setAnyOtherDocument(byte[] anyOtherDocument) {
        this.anyOtherDocument = anyOtherDocument;
    }

    public String getAnyOtherDocumentContentType() {
        return this.anyOtherDocumentContentType;
    }

    public Student anyOtherDocumentContentType(String anyOtherDocumentContentType) {
        this.anyOtherDocumentContentType = anyOtherDocumentContentType;
        return this;
    }

    public void setAnyOtherDocumentContentType(String anyOtherDocumentContentType) {
        this.anyOtherDocumentContentType = anyOtherDocumentContentType;
    }

    public String getAnyDisease() {
        return this.anyDisease;
    }

    public Student anyDisease(String anyDisease) {
        this.setAnyDisease(anyDisease);
        return this;
    }

    public void setAnyDisease(String anyDisease) {
        this.anyDisease = anyDisease;
    }

    public String getFamilyDoctorName() {
        return this.familyDoctorName;
    }

    public Student familyDoctorName(String familyDoctorName) {
        this.setFamilyDoctorName(familyDoctorName);
        return this;
    }

    public void setFamilyDoctorName(String familyDoctorName) {
        this.familyDoctorName = familyDoctorName;
    }

    public String getFamilyDoctorNumber() {
        return this.familyDoctorNumber;
    }

    public Student familyDoctorNumber(String familyDoctorNumber) {
        this.setFamilyDoctorNumber(familyDoctorNumber);
        return this;
    }

    public void setFamilyDoctorNumber(String familyDoctorNumber) {
        this.familyDoctorNumber = familyDoctorNumber;
    }

    public LocalDate getAdmissionDate() {
        return this.admissionDate;
    }

    public Student admissionDate(LocalDate admissionDate) {
        this.setAdmissionDate(admissionDate);
        return this;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getTcDate() {
        return this.tcDate;
    }

    public Student tcDate(LocalDate tcDate) {
        this.setTcDate(tcDate);
        return this;
    }

    public void setTcDate(LocalDate tcDate) {
        this.tcDate = tcDate;
    }

    public Parent getParentId() {
        return this.parentId;
    }

    public void setParentId(Parent parent) {
        this.parentId = parent;
    }

    public Student parentId(Parent parent) {
        this.setParentId(parent);
        return this;
    }

    public Fees getFees() {
        return this.fees;
    }

    public void setFees(Fees fees) {
        if (this.fees != null) {
            this.fees.setStudent(null);
        }
        if (fees != null) {
            fees.setStudent(this);
        }
        this.fees = fees;
    }

    public Student fees(Fees fees) {
        this.setFees(fees);
        return this;
    }

    public Set<Attendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        if (this.attendances != null) {
            this.attendances.forEach(i -> i.removeStudent(this));
        }
        if (attendances != null) {
            attendances.forEach(i -> i.addStudent(this));
        }
        this.attendances = attendances;
    }

    public Student attendances(Set<Attendance> attendances) {
        this.setAttendances(attendances);
        return this;
    }

    public Student addAttendance(Attendance attendance) {
        this.attendances.add(attendance);
        attendance.getStudents().add(this);
        return this;
    }

    public Student removeAttendance(Attendance attendance) {
        this.attendances.remove(attendance);
        attendance.getStudents().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", emergencyContactNumber='" + getEmergencyContactNumber() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", childIdProof='" + getChildIdProof() + "'" +
            ", childIdProofContentType='" + getChildIdProofContentType() + "'" +
            ", parentIdProof='" + getParentIdProof() + "'" +
            ", parentIdProofContentType='" + getParentIdProofContentType() + "'" +
            ", anyOtherDocument='" + getAnyOtherDocument() + "'" +
            ", anyOtherDocumentContentType='" + getAnyOtherDocumentContentType() + "'" +
            ", anyDisease='" + getAnyDisease() + "'" +
            ", familyDoctorName='" + getFamilyDoctorName() + "'" +
            ", familyDoctorNumber='" + getFamilyDoctorNumber() + "'" +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", tcDate='" + getTcDate() + "'" +
            "}";
    }
}
