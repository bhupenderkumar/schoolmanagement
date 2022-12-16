package com.school.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.school.IntegrationTest;
import com.school.domain.Student;
import com.school.repository.StudentRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CHILD_ID_PROOF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CHILD_ID_PROOF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CHILD_ID_PROOF_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PARENT_ID_PROOF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PARENT_ID_PROOF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PARENT_ID_PROOF_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ANY_OTHER_DOCUMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ANY_OTHER_DOCUMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ANY_OTHER_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ANY_DISEASE = "AAAAAAAAAA";
    private static final String UPDATED_ANY_DISEASE = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_DOCTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_DOCTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_DOCTOR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_DOCTOR_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ADMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/students";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .address(DEFAULT_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .emergencyContactNumber(DEFAULT_EMERGENCY_CONTACT_NUMBER)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .childIdProof(DEFAULT_CHILD_ID_PROOF)
            .childIdProofContentType(DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE)
            .parentIdProof(DEFAULT_PARENT_ID_PROOF)
            .parentIdProofContentType(DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE)
            .anyOtherDocument(DEFAULT_ANY_OTHER_DOCUMENT)
            .anyOtherDocumentContentType(DEFAULT_ANY_OTHER_DOCUMENT_CONTENT_TYPE)
            .anyDisease(DEFAULT_ANY_DISEASE)
            .familyDoctorName(DEFAULT_FAMILY_DOCTOR_NAME)
            .familyDoctorNumber(DEFAULT_FAMILY_DOCTOR_NUMBER)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .tcDate(DEFAULT_TC_DATE);
        return student;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity(EntityManager em) {
        Student student = new Student()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .childIdProof(UPDATED_CHILD_ID_PROOF)
            .childIdProofContentType(UPDATED_CHILD_ID_PROOF_CONTENT_TYPE)
            .parentIdProof(UPDATED_PARENT_ID_PROOF)
            .parentIdProofContentType(UPDATED_PARENT_ID_PROOF_CONTENT_TYPE)
            .anyOtherDocument(UPDATED_ANY_OTHER_DOCUMENT)
            .anyOtherDocumentContentType(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE)
            .anyDisease(UPDATED_ANY_DISEASE)
            .familyDoctorName(UPDATED_FAMILY_DOCTOR_NAME)
            .familyDoctorNumber(UPDATED_FAMILY_DOCTOR_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .tcDate(UPDATED_TC_DATE);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        // Create the Student
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testStudent.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testStudent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testStudent.getEmergencyContactNumber()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NUMBER);
        assertThat(testStudent.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStudent.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStudent.getChildIdProof()).isEqualTo(DEFAULT_CHILD_ID_PROOF);
        assertThat(testStudent.getChildIdProofContentType()).isEqualTo(DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getParentIdProof()).isEqualTo(DEFAULT_PARENT_ID_PROOF);
        assertThat(testStudent.getParentIdProofContentType()).isEqualTo(DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getAnyOtherDocument()).isEqualTo(DEFAULT_ANY_OTHER_DOCUMENT);
        assertThat(testStudent.getAnyOtherDocumentContentType()).isEqualTo(DEFAULT_ANY_OTHER_DOCUMENT_CONTENT_TYPE);
        assertThat(testStudent.getAnyDisease()).isEqualTo(DEFAULT_ANY_DISEASE);
        assertThat(testStudent.getFamilyDoctorName()).isEqualTo(DEFAULT_FAMILY_DOCTOR_NAME);
        assertThat(testStudent.getFamilyDoctorNumber()).isEqualTo(DEFAULT_FAMILY_DOCTOR_NUMBER);
        assertThat(testStudent.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testStudent.getTcDate()).isEqualTo(DEFAULT_TC_DATE);
    }

    @Test
    @Transactional
    void createStudentWithExistingId() throws Exception {
        // Create the Student with an existing ID
        student.setId(1L);

        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setFirstname(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setLastname(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAddress(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setPhoneNumber(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmergencyContactNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmergencyContactNumber(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].emergencyContactNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].childIdProofContentType").value(hasItem(DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].childIdProof").value(hasItem(Base64Utils.encodeToString(DEFAULT_CHILD_ID_PROOF))))
            .andExpect(jsonPath("$.[*].parentIdProofContentType").value(hasItem(DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].parentIdProof").value(hasItem(Base64Utils.encodeToString(DEFAULT_PARENT_ID_PROOF))))
            .andExpect(jsonPath("$.[*].anyOtherDocumentContentType").value(hasItem(DEFAULT_ANY_OTHER_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].anyOtherDocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_ANY_OTHER_DOCUMENT))))
            .andExpect(jsonPath("$.[*].anyDisease").value(hasItem(DEFAULT_ANY_DISEASE)))
            .andExpect(jsonPath("$.[*].familyDoctorName").value(hasItem(DEFAULT_FAMILY_DOCTOR_NAME)))
            .andExpect(jsonPath("$.[*].familyDoctorNumber").value(hasItem(DEFAULT_FAMILY_DOCTOR_NUMBER)))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].tcDate").value(hasItem(DEFAULT_TC_DATE.toString())));
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_ID, student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.emergencyContactNumber").value(DEFAULT_EMERGENCY_CONTACT_NUMBER))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.childIdProofContentType").value(DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE))
            .andExpect(jsonPath("$.childIdProof").value(Base64Utils.encodeToString(DEFAULT_CHILD_ID_PROOF)))
            .andExpect(jsonPath("$.parentIdProofContentType").value(DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE))
            .andExpect(jsonPath("$.parentIdProof").value(Base64Utils.encodeToString(DEFAULT_PARENT_ID_PROOF)))
            .andExpect(jsonPath("$.anyOtherDocumentContentType").value(DEFAULT_ANY_OTHER_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.anyOtherDocument").value(Base64Utils.encodeToString(DEFAULT_ANY_OTHER_DOCUMENT)))
            .andExpect(jsonPath("$.anyDisease").value(DEFAULT_ANY_DISEASE))
            .andExpect(jsonPath("$.familyDoctorName").value(DEFAULT_FAMILY_DOCTOR_NAME))
            .andExpect(jsonPath("$.familyDoctorNumber").value(DEFAULT_FAMILY_DOCTOR_NUMBER))
            .andExpect(jsonPath("$.admissionDate").value(DEFAULT_ADMISSION_DATE.toString()))
            .andExpect(jsonPath("$.tcDate").value(DEFAULT_TC_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .childIdProof(UPDATED_CHILD_ID_PROOF)
            .childIdProofContentType(UPDATED_CHILD_ID_PROOF_CONTENT_TYPE)
            .parentIdProof(UPDATED_PARENT_ID_PROOF)
            .parentIdProofContentType(UPDATED_PARENT_ID_PROOF_CONTENT_TYPE)
            .anyOtherDocument(UPDATED_ANY_OTHER_DOCUMENT)
            .anyOtherDocumentContentType(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE)
            .anyDisease(UPDATED_ANY_DISEASE)
            .familyDoctorName(UPDATED_FAMILY_DOCTOR_NAME)
            .familyDoctorNumber(UPDATED_FAMILY_DOCTOR_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .tcDate(UPDATED_TC_DATE);

        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStudent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testStudent.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getEmergencyContactNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NUMBER);
        assertThat(testStudent.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStudent.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStudent.getChildIdProof()).isEqualTo(UPDATED_CHILD_ID_PROOF);
        assertThat(testStudent.getChildIdProofContentType()).isEqualTo(UPDATED_CHILD_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getParentIdProof()).isEqualTo(UPDATED_PARENT_ID_PROOF);
        assertThat(testStudent.getParentIdProofContentType()).isEqualTo(UPDATED_PARENT_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getAnyOtherDocument()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT);
        assertThat(testStudent.getAnyOtherDocumentContentType()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE);
        assertThat(testStudent.getAnyDisease()).isEqualTo(UPDATED_ANY_DISEASE);
        assertThat(testStudent.getFamilyDoctorName()).isEqualTo(UPDATED_FAMILY_DOCTOR_NAME);
        assertThat(testStudent.getFamilyDoctorNumber()).isEqualTo(UPDATED_FAMILY_DOCTOR_NUMBER);
        assertThat(testStudent.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testStudent.getTcDate()).isEqualTo(UPDATED_TC_DATE);
    }

    @Test
    @Transactional
    void putNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, student.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .anyOtherDocument(UPDATED_ANY_OTHER_DOCUMENT)
            .anyOtherDocumentContentType(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE)
            .familyDoctorNumber(UPDATED_FAMILY_DOCTOR_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testStudent.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getEmergencyContactNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NUMBER);
        assertThat(testStudent.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStudent.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStudent.getChildIdProof()).isEqualTo(DEFAULT_CHILD_ID_PROOF);
        assertThat(testStudent.getChildIdProofContentType()).isEqualTo(DEFAULT_CHILD_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getParentIdProof()).isEqualTo(DEFAULT_PARENT_ID_PROOF);
        assertThat(testStudent.getParentIdProofContentType()).isEqualTo(DEFAULT_PARENT_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getAnyOtherDocument()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT);
        assertThat(testStudent.getAnyOtherDocumentContentType()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE);
        assertThat(testStudent.getAnyDisease()).isEqualTo(DEFAULT_ANY_DISEASE);
        assertThat(testStudent.getFamilyDoctorName()).isEqualTo(DEFAULT_FAMILY_DOCTOR_NAME);
        assertThat(testStudent.getFamilyDoctorNumber()).isEqualTo(UPDATED_FAMILY_DOCTOR_NUMBER);
        assertThat(testStudent.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testStudent.getTcDate()).isEqualTo(DEFAULT_TC_DATE);
    }

    @Test
    @Transactional
    void fullUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .childIdProof(UPDATED_CHILD_ID_PROOF)
            .childIdProofContentType(UPDATED_CHILD_ID_PROOF_CONTENT_TYPE)
            .parentIdProof(UPDATED_PARENT_ID_PROOF)
            .parentIdProofContentType(UPDATED_PARENT_ID_PROOF_CONTENT_TYPE)
            .anyOtherDocument(UPDATED_ANY_OTHER_DOCUMENT)
            .anyOtherDocumentContentType(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE)
            .anyDisease(UPDATED_ANY_DISEASE)
            .familyDoctorName(UPDATED_FAMILY_DOCTOR_NAME)
            .familyDoctorNumber(UPDATED_FAMILY_DOCTOR_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .tcDate(UPDATED_TC_DATE);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testStudent.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getEmergencyContactNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NUMBER);
        assertThat(testStudent.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStudent.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStudent.getChildIdProof()).isEqualTo(UPDATED_CHILD_ID_PROOF);
        assertThat(testStudent.getChildIdProofContentType()).isEqualTo(UPDATED_CHILD_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getParentIdProof()).isEqualTo(UPDATED_PARENT_ID_PROOF);
        assertThat(testStudent.getParentIdProofContentType()).isEqualTo(UPDATED_PARENT_ID_PROOF_CONTENT_TYPE);
        assertThat(testStudent.getAnyOtherDocument()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT);
        assertThat(testStudent.getAnyOtherDocumentContentType()).isEqualTo(UPDATED_ANY_OTHER_DOCUMENT_CONTENT_TYPE);
        assertThat(testStudent.getAnyDisease()).isEqualTo(UPDATED_ANY_DISEASE);
        assertThat(testStudent.getFamilyDoctorName()).isEqualTo(UPDATED_FAMILY_DOCTOR_NAME);
        assertThat(testStudent.getFamilyDoctorNumber()).isEqualTo(UPDATED_FAMILY_DOCTOR_NUMBER);
        assertThat(testStudent.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testStudent.getTcDate()).isEqualTo(UPDATED_TC_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, student.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc
            .perform(delete(ENTITY_API_URL_ID, student.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
