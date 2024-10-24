package tn.esprit.tpfoyer17.services.impementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.FoyerService;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FoyerServiceTest {

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private FoyerService foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        // Arrange
        long universiteId = 1L;
        Foyer foyer = new Foyer();
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();


        Universite universite = new Universite();
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(universite));
        when(foyerRepository.save(foyer)).thenReturn(foyer);
        when(blocRepository.save(bloc1)).thenReturn(bloc1);
        when(blocRepository.save(bloc2)).thenReturn(bloc2);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Foyer savedFoyer = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, universiteId);

        // Assert
        assertEquals(foyer, savedFoyer);
        verify(foyerRepository, times(1)).save(foyer);
        verify(blocRepository, times(1)).save(bloc1);
        verify(blocRepository, times(1)).save(bloc2);
        verify(universiteRepository, times(1)).save(universite);
    }
}
