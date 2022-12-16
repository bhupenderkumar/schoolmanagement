package com.school.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.school.IntegrationTest;
import com.school.domain.Fees;
import com.school.repository.FeesRepository;
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

/**
 * Integration tests for the {@link FeesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FeesResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/fees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FeesRepository feesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFeesMockMvc;

    private Fees fees;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fees createEntity(EntityManager em) {
        Fees fees = new Fees().date(DEFAULT_DATE);
        return fees;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fees createUpdatedEntity(EntityManager em) {
        Fees fees = new Fees().date(UPDATED_DATE);
        return fees;
    }

    @BeforeEach
    public void initTest() {
        fees = createEntity(em);
    }

    @Test
    @Transactional
    void createFees() throws Exception {
        int databaseSizeBeforeCreate = feesRepository.findAll().size();
        // Create the Fees
        restFeesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fees)))
            .andExpect(status().isCreated());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeCreate + 1);
        Fees testFees = feesList.get(feesList.size() - 1);
        assertThat(testFees.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void createFeesWithExistingId() throws Exception {
        // Create the Fees with an existing ID
        fees.setId(1L);

        int databaseSizeBeforeCreate = feesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fees)))
            .andExpect(status().isBadRequest());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = feesRepository.findAll().size();
        // set the field null
        fees.setDate(null);

        // Create the Fees, which fails.

        restFeesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fees)))
            .andExpect(status().isBadRequest());

        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFees() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        // Get all the feesList
        restFeesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fees.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getFees() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        // Get the fees
        restFeesMockMvc
            .perform(get(ENTITY_API_URL_ID, fees.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fees.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFees() throws Exception {
        // Get the fees
        restFeesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFees() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        int databaseSizeBeforeUpdate = feesRepository.findAll().size();

        // Update the fees
        Fees updatedFees = feesRepository.findById(fees.getId()).get();
        // Disconnect from session so that the updates on updatedFees are not directly saved in db
        em.detach(updatedFees);
        updatedFees.date(UPDATED_DATE);

        restFeesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFees.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFees))
            )
            .andExpect(status().isOk());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
        Fees testFees = feesList.get(feesList.size() - 1);
        assertThat(testFees.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fees.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fees))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fees))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fees)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFeesWithPatch() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        int databaseSizeBeforeUpdate = feesRepository.findAll().size();

        // Update the fees using partial update
        Fees partialUpdatedFees = new Fees();
        partialUpdatedFees.setId(fees.getId());

        restFeesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFees.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFees))
            )
            .andExpect(status().isOk());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
        Fees testFees = feesList.get(feesList.size() - 1);
        assertThat(testFees.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void fullUpdateFeesWithPatch() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        int databaseSizeBeforeUpdate = feesRepository.findAll().size();

        // Update the fees using partial update
        Fees partialUpdatedFees = new Fees();
        partialUpdatedFees.setId(fees.getId());

        partialUpdatedFees.date(UPDATED_DATE);

        restFeesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFees.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFees))
            )
            .andExpect(status().isOk());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
        Fees testFees = feesList.get(feesList.size() - 1);
        assertThat(testFees.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fees.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fees))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fees))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFees() throws Exception {
        int databaseSizeBeforeUpdate = feesRepository.findAll().size();
        fees.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(fees)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fees in the database
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFees() throws Exception {
        // Initialize the database
        feesRepository.saveAndFlush(fees);

        int databaseSizeBeforeDelete = feesRepository.findAll().size();

        // Delete the fees
        restFeesMockMvc
            .perform(delete(ENTITY_API_URL_ID, fees.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fees> feesList = feesRepository.findAll();
        assertThat(feesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
