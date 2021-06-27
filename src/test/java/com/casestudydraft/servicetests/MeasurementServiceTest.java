package com.casestudydraft.servicetests;
import static org.junit.jupiter.api.Assertions.*;
import com.casestudydraft.model.Measurement;
import com.casestudydraft.repository.MeasurementRepository;
import com.casestudydraft.service.MeasurementService;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyLong;
import java.util.Optional;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback
public class MeasurementServiceTest {

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private MeasurementRepository measurementRepository;

    static Long testId;
    static final String name = String.valueOf(Math.random()*Integer.MAX_VALUE);
    @Test
    @Order(1)
    @Rollback
    void testSave() {
        Measurement measurement = new Measurement(name);
        measurementService.save(measurement);
        testId = measurement.getId();
        assertNotNull(measurement.getId());
    }

    @Test
    @Order(2)
    @Transactional
    void testGet() {
        Measurement expected = new Measurement(name);
        expected.setId(testId);

        //I'm getting a org.hibernate.LazyInitializationException: could not initialize proxy [com.casestudydraft.model.Measurement#20] - no Session error
        // not sure how to proceed
        //edit: added transactional to method and it seems to work

        Measurement proxy = measurementService.get(testId);

        // Don't ask me how this works but I've spent all day trying to figure out how to fix this method
        // And apparently I have to use it throughout all the methods where I call the get service
        Object actual1 = Hibernate.unproxy(proxy);
        Measurement actual = (Measurement) actual1;
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    @Transactional
    void testUpdate(){
        // get the measurement with the testId
        Measurement proxy = measurementService.get(testId);
        Object actual1 = Hibernate.unproxy(proxy);
        Measurement actual = (Measurement) actual1;
        testId = actual.getId();

        //change it and save it
        actual.setName(name+ "-updated");
        measurementService.save(actual);

        //make sure the ids are still the same
        assertEquals(actual.getId(), testId);
    }
    @Test
    @Order(4)
    @Transactional
    void testDelete(){
        //get measurement
        Measurement proxy = measurementService.get(testId);
        Object actual1 = Hibernate.unproxy(proxy);
        Measurement measurement = (Measurement) actual1;

        measurementService.delete(measurement);

        //the id shouldn't be there anymore
        assertEquals(measurementRepository.findById(measurement.getId()), Optional.empty());


    }

}
