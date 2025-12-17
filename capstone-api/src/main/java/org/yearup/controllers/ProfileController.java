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

    @PostMapping
    public Profile register(@RequestBody Profile newProfile) {
        return profileDao.create(newProfile);
    }

    @GetMapping("/{userId}")
    public Profile getProfile(@PathVariable int userId) {
        return profileDao.getByUserId(userId);
    }

    @PutMapping("/{userId}")
    public Profile updateProfile(@PathVariable int userId, @RequestBody Profile updatedProfile) {
        updatedProfile.setUserId(userId);
        return profileDao.update(updatedProfile);
    }
}