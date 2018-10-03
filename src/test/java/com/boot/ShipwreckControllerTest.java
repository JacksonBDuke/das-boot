package com.boot;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {

    @InjectMocks
    private ShipwreckController sc;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGet() {
        Shipwreck sw =  new Shipwreck();
        sw.setId(1L);

        /*
            "when(shipwreckRepository.findById(1L).get()).thenReturn(sw);"
            Was never going to be 'called', because that's now how the Interface works.
            Instead of getting a Shipwreck from the shipwreckRepository we get an Optional of
            type Shipwreck, so we need to then return an Optional of type Shipwreck like below.
         */
        when(shipwreckRepository.findById(1L)).thenReturn(Optional.of(sw));

        Shipwreck wreck = sc.get(1L);

        verify(shipwreckRepository).findById(1L);

//        assertEquals(1L, wreck.getId().longValue());
        assertThat(wreck.getId(), is(1L));
    }
}
