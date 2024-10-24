package tn.esprit.tpfoyer17.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;

import java.util.Arrays;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveAllChambres_ShouldReturnListOfChambres() {
        // Arrange
        List<Chambre> chambreList = Collections.singletonList(new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambreList);

        // Act
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Assert
        assertEquals(chambreList.size(), result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void addChambre_ShouldReturnSavedChambre() {
        // Arrange
        Chambre chambre = new Chambre();
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
        Chambre chambre = new Chambre();
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
        Chambre chambre = new Chambre();
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
        Bloc bloc = new Bloc();
        List<Long> numChambres = Arrays.asList(1L, 2L);
        List<Chambre> chambreList = Arrays.asList(new Chambre(), new Chambre());

        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));
        when(chambreRepository.findByNumeroChambreIn(numChambres)).thenReturn(chambreList);

        // Act
        Bloc result = chambreService.affecterChambresABloc(numChambres, 1L);

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
