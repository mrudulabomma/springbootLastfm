package com.stackroute.muzixapp.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

	@Autowired
	TrackService trackService;

	public TrackController(TrackService trackService) {
		this.trackService = trackService;
	}

	//update all the methods with code
	@PostMapping("track")
	public ResponseEntity<?> addTrack(@RequestBody Track track) throws TrackAlreadyExistsException, IOException {
		ResponseEntity responseEntity;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode jsonNode = objectMapper.readTree(new URL("http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=418152b967af12b837b3d08d45f45514&format=json")).get("tracks").get("track");
		for(JsonNode node: jsonNode) {
			System.out.println(node);
			trackService.saveTrack(objectMapper.convertValue(node, Track.class));
		}
		responseEntity = new ResponseEntity<String>("Succesfully Created", HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("track")
	public ResponseEntity<?> indexPage(ModelMap model) {
		ResponseEntity responseEntity;
		List<Track> trackList = trackService.getAllTracks();
		model.addAttribute("trackList", trackList);
		responseEntity = new ResponseEntity<List<Track>>(trackList, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track/{name}")
	public ResponseEntity<?> findByName(@PathVariable(value = "name") String name, ModelMap model) {
//		ResponseEntity responseEntity;
//		//List<Track> trackList = trackService.findByName(name);
//		model.addAttribute("trackList", trackList);
//		responseEntity = new ResponseEntity<List<Track>>(trackList, HttpStatus.OK);
//		return responseEntity;
		return null;
	}

	@DeleteMapping("track/{id}")
	public ResponseEntity<?> deleteTrack(@PathVariable(name = "id") int id) throws TrackNotFoundException {
		ResponseEntity responseEntity;
		trackService.deleteTrack(id);
		responseEntity = new ResponseEntity<String>("Succesfully Deleted", HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("track/{id}")
	public ResponseEntity<?> updateTrack(@PathVariable(name = "id") int id, @RequestBody Track track) throws TrackNotFoundException {
		ResponseEntity responseEntity;
		trackService.updateTrack(track);
		responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}

}