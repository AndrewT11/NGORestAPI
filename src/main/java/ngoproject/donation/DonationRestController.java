package ngoproject.donation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/donation")
public class DonationRestController {

	@Autowired
	private DonationService donationService;
	
	@GetMapping("/donations")
	List<Donation> getAllDonations() {
		return donationService.getAllDonaltions();
	}

	@PostMapping("/register")
	void registerDonation(@RequestBody Donation donation) {
		donationService.registerDonation(donation);
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteDonation(@PathVariable Integer id) {
		String updateMessage = donationService.deleteDonation(id);
		return ResponseEntity.ok(updateMessage);
	}
}
