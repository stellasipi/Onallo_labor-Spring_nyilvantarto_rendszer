package hu.bme.vik.tbs.szakdolgozat.CsotthonApp.repository;

import hu.bme.vik.tbs.szakdolgozat.CsotthonApp.model.RoomCleaningItemPairing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomCleaningItemPairingRepository extends JpaRepository<RoomCleaningItemPairing, Integer> {
    RoomCleaningItemPairing findByRoomNameAndCleaningItemName(String roomName, String CleaningItemName);
    List<RoomCleaningItemPairing> findByRoomName(String roomName);
}
