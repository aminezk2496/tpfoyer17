package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends CrudRepository<Chambre, Long> {

    List<Chambre> findByNumeroChambreIn(List<Long> numeroChambreList);

    List<Chambre> findByBlocIdBloc(Long idBloc);

    List<Chambre> findByTypeChambreAndReservationsEstValide(TypeChambre typeChambre, boolean estvalide);

    List<Chambre> findByBlocFoyerUniversiteNomUniversiteLike(String nomUni);
    Chambre findByReservationsIdReservation(String reservationsIdReservation);


    List<Chambre> findByBlocIdBlocAndTypeChambre(Long idBloc, TypeChambre typeChambre); // Added this method

    @Query("SELECT chambre FROM Chambre chambre WHERE chambre.bloc.idBloc = :idbloc AND chambre.typeChambre = :typechambre")
    List<Chambre> recupererParBlocEtTypeChambre(@Param("idbloc") long idbloc, @Param("typechambre") TypeChambre typechambre);

    @Query("SELECT chambre FROM Chambre chambre WHERE (SELECT COUNT(r) FROM chambre.reservations r) = 0")
    List<Chambre> getChambresNonReserve();

    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
}
