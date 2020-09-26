package edu.bigdata.training.testservice.utils;

import edu.bigdata.training.testservice.dao.TestServiceRepository;
import edu.bigdata.training.testservice.model.PersonEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ignite.Ignite;

import java.util.UUID;

public class EntityUtils {
    private TestServiceRepository testServiceRepository;
    private Ignite ignite;

    public EntityUtils(TestServiceRepository testServiceRepository, Ignite ignite) {
        this.testServiceRepository = testServiceRepository;
        this.ignite = ignite;
    }

    public PersonEntity createAndSavePersonEntity() {
        PersonEntity personEntity = createPersonEntity();
        testServiceRepository.save(personEntity);

        return personEntity;
    }

    public PersonEntity getPersonEntity(String id) {
        return testServiceRepository.get(UUID.fromString(id));
    }

    public void clearPersonEntitiesCache() {
        ignite.getOrCreateCache("person").clear();
    }

    public static PersonEntity createPersonEntity() {
        return new PersonEntity(rdmStr(15));
    }

    public static String rdmStr(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
