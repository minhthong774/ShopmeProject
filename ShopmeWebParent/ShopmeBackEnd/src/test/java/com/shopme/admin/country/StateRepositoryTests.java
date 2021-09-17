package com.shopme.admin.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.shopme.admin.Country.StateRepository;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StateRepositoryTests {
    
    @Autowired
    private StateRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateState(){
        Integer countryId = 1;
        Country country = entityManager.find(Country.class, countryId);
        State state = repo.save(new State("Ohio", country));

        assertThat(state).isNotNull();
        assertThat(state.getId()).isGreaterThan(0);

    }

    @Test
    public void testListStatesByCountry(){
        Integer countryId = 1;
        Country country = entityManager.find(Country.class, countryId);
        List<State> listStates = repo.findByCountryOrderByNameAsc(country);

        listStates.forEach(System.out::println);

        assertThat(listStates.size()).isGreaterThan(0);
    }

    @Test
    public void testUpdateState(){
        Integer stateId = 1;
        String stateName = "New York";
        State state = repo.findById(stateId).get();

        state.setName(stateName);
        State updatedState = repo.save(state);

        assertThat(updatedState.getName()).isEqualTo(stateName);
    }

    @Test
    public void testDeleteState(){
        Integer stateId = 2;
        repo.deleteById(stateId);

        Optional<State> findById = repo.findById(stateId);
        assertThat(findById.isEmpty());
    }
}
