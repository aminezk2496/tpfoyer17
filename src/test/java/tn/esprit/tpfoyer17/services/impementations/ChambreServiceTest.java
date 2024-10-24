package tn.esprit.tpfoyer17.services.impementations;

import org.aspectj.lang.annotation.Before;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChambreServiceTest {


    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private ChambreService chambreService;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveAllChambres_ShouldReturnListOfChambres() {
        // Arrange
        when(chambreRepository.findAll()).thenReturn(Collections.singletonList(new Chambre(/* provide necessary arguments */)));

        // Act
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Assert
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void addChambre_ShouldReturnSavedChambre() {
        // Arrange
        Chambre chambre = new Chambre(/* provide necessary arguments */);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.addChambre(chambre);

        // Assert
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void updateChambre_ShouldReturnUpdatedChambre() {
        // Arrange
        Chambre chambre = new Chambre(/* provide necessary arguments */);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.updateChambre(chambre);

        // Assert
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void retrieveChambre_ShouldReturnChambre() {
        // Arrange
        Chambre chambre = new Chambre(/* provide necessary arguments */);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Act
        Chambre result = chambreService.retrieveChambre(1L);

        // Assert
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void affecterChambresABloc_ShouldAffectChambres() {
        // Arrange
        Bloc bloc = new Bloc(/* provide necessary arguments */);
        List<Long> numChambre = List.of(1L, 2L);
        List<Chambre> chambreList = List.of(new Chambre(), new Chambre());
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));
        when(chambreRepository.findByNumeroChambreIn(numChambre)).thenReturn(chambreList);

        // Act
        Bloc result = chambreService.affecterChambresABloc(numChambre, 1L);

        // Assert
        assertEquals(bloc, result);
        verify(chambreRepository, times(2)).save(any(Chambre.class));
    }

    @Test
    public void findByTypeChambre_ShouldReturnChambresOfSpecificType() {
        // Arrange
        when(chambreRepository.findByTypeChambreAndReservationsEstValide(TypeChambre.DOUBLE, true))
                .thenReturn(Collections.singletonList(new Chambre()));

        // Act
        List<Chambre> result = chambreService.findByTypeChambre();

        // Assert
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findByTypeChambreAndReservationsEstValide(TypeChambre.DOUBLE, true);
    }

    @Test
    public void getChambresParNomUniversite_ShouldReturnChambresForUniversite() {
        // Arrange
        when(chambreRepository.findByBlocFoyerUniversiteNomUniversiteLike("Esprit"))
                .thenReturn(Collections.singletonList(new Chambre()));

        // Act
        List<Chambre> result = chambreService.getChambresParNomUniversite("Esprit");

        // Assert
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findByBlocFoyerUniversiteNomUniversiteLike("Esprit");
    }
}
