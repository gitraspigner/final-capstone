package org.yearup.controllers;

import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    // Registration (create new profile)
    @PostMapping
    public Profile register(@RequestBody Profile newProfile) {
        return profileDao.create(newProfile);
    }

    // Fetch profile by userId
    @GetMapping("/{userId}")
    public Profile getProfile(@PathVariable int userId) {
        return profileDao.getByUserId(userId);
    }

    // Update existing profile
    @PutMapping("/{userId}")
    public Profile updateProfile(@PathVariable int userId, @RequestBody Profile updatedProfile) {
        updatedProfile.setUserId(userId);
        return profileDao.update(updatedProfile);
    }
}