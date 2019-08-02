package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	@Override
	public boolean saveTrack(Track track) {
		boolean result = false;
		Track savedTrack = trackRepository.save(track);
		if(savedTrack != null) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteTrack(int id) {
		boolean result = false;
		if(!trackRepository.findById(id).isPresent()) {
			result = false;
		}
		else {
			trackRepository.delete(getTrackById(id));
		}
		return result;
	}

	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public Track getTrackById(int id) {
		Track savedTrack = trackRepository.getOne(id);
		return savedTrack;
	}

	@Override
	public boolean updateTrack(Track track) {
		return false;
	}

}
