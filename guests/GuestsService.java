package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestsService {

    private final GuestsRepository guestRepository;

    private final GuestsMapper guestMapper;

    public GuestsDTO addGuest(GuestsDTO createGuestRequest) {
        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(createGuestRequest)));
    }

    public GuestsDTO findGuestById(Long guestId) {
        return guestRepository.findById(guestId).map(guestMapper::map).orElseThrow(() -> {
            return new EntityNotFoundException("Guest not found.");
        });
    }

    public List<GuestsDTO> findGuestByName(String name) {
        List<Guest> guests = guestRepository.findByName(name);
        if(guests.isEmpty()) {
            throw new EntityNotFoundException("Guest not found.");
        }
        return guestMapper.map(guests);
    }

    public GuestsDTO updateGuest(Long guestId, String name) {
        return guestRepository.findById(guestId).map(guest -> {
            guest.setName(name);
            guest.setDateUpdate(LocalDateTime.now());
            return guestMapper.map(guestRepository.save(guest));
        }).orElseThrow(() -> {
            return new EntityNotFoundException("Guest not found.");
        });
    }

    public void deleteGuestById(Long guestId) {
        guestRepository.findById(guestId).map(guest -> {
            guestRepository.deleteById(guestId);
            return null;
        }).orElseThrow(() -> {
            return new EntityNotFoundException("Guest not found.");
        });
    }
}
