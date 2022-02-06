package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestsController extends BaseRestController {

    private final GuestsService guestService;

    //Add Guest
    @PostMapping
    public ResponseEntity<Void> addGuest(@RequestBody GuestsDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    //Find Guest by id
    @GetMapping("/{guestId}")
    public ResponseEntity<GuestsDTO> findGuestById(@PathVariable("guestId") Long guestId) {
        return ResponseEntity.ok(guestService.findGuestById(guestId));
    }

    // Find guest by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<GuestsDTO>> findGuestByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(guestService.findGuestByName(name));
    }

    // Update Guest details
    @PutMapping("/{guestId}/name/{name}")
    public ResponseEntity<GuestsDTO> updateGuest(@PathVariable("guestId") Long guestId, @PathVariable("name") String name) {
        return ResponseEntity.ok(guestService.updateGuest(guestId, name));
    }

    // Delete guest
    @DeleteMapping("/{guestId}")
    public void deleteGuestById(@PathVariable("guestId") Long guestId) {
        guestService.deleteGuestById(guestId);
    }
}
